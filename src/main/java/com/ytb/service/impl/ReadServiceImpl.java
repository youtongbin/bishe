package com.ytb.service.impl;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.dao.PaperDao;
import com.ytb.dao.ReadDao;
import com.ytb.pojo.Paper;
import com.ytb.pojo.Read;
import com.ytb.service.IReadService;
import com.ytb.vo.ReadVO;
import com.ytb.vo.tvo.TransVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadServiceImpl implements IReadService {

    @Resource
    private ReadDao readDao;

    @Resource
    private PaperDao paperDao;

    @Override
    @Transactional
    public ServerResponse insert(Integer userId, Read read) {

        if (read == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.INPUT_NULL.getMsg());
        }

        read.setUserId(userId);

        int result = readDao.insert(read);

        if (result > 0){

            Paper paper = new Paper();
            paper.setPaperId(read.getPaperId());
            if (read.getReadStatus()==Const.ReadStatusEnum.SUCCESS.getCode()){
                paper.setPaperStatus(Const.PaperStatusEnum.SUCCESS.getCode());
            }
            if (read.getReadStatus()==Const.ReadStatusEnum.FAIL.getCode()){
                paper.setPaperStatus(Const.PaperStatusEnum.FAIL.getCode());
            }

            int result1 = paperDao.update(paper);
            if (result1 > 0){
                //ReadVO
                ReadVO readVO = TransVO.transReadVO(read);
                return ServerResponse.serverResponseBySuccess(readVO);
            }else {
                return ServerResponse.serverResponseByFail(Const.PaperStatusEnum.codeOf(paper.getPaperStatus()).getMsg());
            }
        }

        return ServerResponse.serverResponseByFail("审核失败");
    }

    @Override
    public ServerResponse see(Integer readId) {

        Read read = readDao.selectByKey(readId);
        if (read == null){
            return ServerResponse.serverResponseByFail("评审信息不存在");
        }
        ReadVO readVO = TransVO.transReadVO(read);

        return ServerResponse.serverResponseBySuccess(readVO);
    }

    @Override
    @Transactional
    public ServerResponse update(Read read) {

        if (read == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.INPUT_NULL.getMsg());
        }

        int result = readDao.update(read);
        if (result > 0){
            Paper paper = new Paper();
            paper.setPaperId(read.getPaperId());
            if (read.getReadStatus() == Const.ReadStatusEnum.SUCCESS.getCode()){
                paper.setPaperStatus(Const.PaperStatusEnum.SUCCESS.getCode());
            }
            if (read.getReadStatus() == Const.ReadStatusEnum.FAIL.getCode()){
                paper.setPaperStatus(Const.PaperStatusEnum.FAIL.getCode());
            }

            int result1 = paperDao.update(paper);
            if (result1 > 0){
                ReadVO readVO = TransVO.transReadVO(read);
                return ServerResponse.serverResponseBySuccess(readVO);
            }else {
                return ServerResponse.serverResponseByFail("修改失败");
            }

        }

        return ServerResponse.serverResponseByFail("修改失败");
    }

    @Override
    public ServerResponse list(Integer paperId) {

        if (paperId == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.INPUT_NULL.getMsg());
        }

        List<Read> readList = readDao.selectAllByPaperId(paperId);

        if (readList == null || readList.size()==0){
            return ServerResponse.serverResponseByFail("该论文没有评审信息");
        }

        List<ReadVO> readVOList = new ArrayList<>();
        for (Read read:readList
             ) {
            readVOList.add(TransVO.transReadVO(read));
        }

        return ServerResponse.serverResponseBySuccess(readVOList);
    }
}

package com.ytb.service.impl;

import com.ytb.common.ServerResponse;
import com.ytb.dao.PaperDao;
import com.ytb.dao.UserDao;
import com.ytb.pojo.Paper;
import com.ytb.service.IPaperManageService;
import com.ytb.vo.PaperVO;
import com.ytb.vo.tvo.TransVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaperManageServiceImpl implements IPaperManageService {

    @Resource
    private PaperDao paperDao;

    @Resource
    private UserDao userDao;

    @Override
    public ServerResponse selectAll() {

        List<Paper> paperList = paperDao.selectAll();

        if (paperList == null || paperList.size() == 0){
            return ServerResponse.serverResponseByFail("没有找到论文");
        }

        List<PaperVO> paperVOList = new ArrayList<>();
        for (Paper paper:paperList
             ) {
            paperVOList.add(TransVO.transPaperVO(paper,userDao.selectByKey(paper.getUserId())));
        }

        return ServerResponse.serverResponseBySuccess(paperVOList);
    }

    @Override
    public ServerResponse delete(Integer paperId) {

        int result = paperDao.delete(paperId);

        if (result > 0){
            return ServerResponse.serverResponseBySuccess("删除成功");
        }

        return ServerResponse.serverResponseByFail("删除失败");
    }
}

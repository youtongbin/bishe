package com.ytb.service.impl;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.dao.PaperDao;
import com.ytb.pojo.Paper;
import com.ytb.service.IPaperService;
import com.ytb.utils.FTPUtils;
import com.ytb.utils.PropertiesUtils;
import com.ytb.vo.PaperVO;
import com.ytb.vo.tvo.TransVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class PaperServiceImpl implements IPaperService {

    @Resource
    private PaperDao paperDao;


    @Override
    public ServerResponse upload(MultipartFile file,Integer userId,String path,String desc) {

        if (file == null){
            return ServerResponse.serverResponseByFail("文件不能为空");
        }

        //1、获取文件名字
        String fileName = file.getOriginalFilename();
        //2、获取后缀扩展名
        String exName = fileName.substring(fileName.lastIndexOf("."));
        if (!exName.equals(".pdf") && !exName.equals(".doc") && !exName.equals(".docx")){
            return ServerResponse.serverResponseByFail("文件格式不正确");
        }

        //3、生成标识名--唯一
        String newFileName = UUID.randomUUID().toString() + exName;

        //4、本地转储
        File filePath = new File(path);
        if (!filePath.exists()){
            filePath.setWritable(true);
            filePath.mkdirs();
        }
        File file1 = new File(path,newFileName);

        try {
            file.transferTo(file1);
            //上传到服务器
            List<File> fileList = new ArrayList<>();
            fileList.add(file1);

            if (FTPUtils.uploadFile(fileList)){

                String fileUrl = PropertiesUtils.readByKey("fileHost") + "/" + newFileName;

                //数据库添加
                Paper paper = new Paper();
                paper.setUserId(userId);
                paper.setIdentifyingName(newFileName);
                paper.setPaperName(fileName);
                paper.setPaperDesc(desc);
                paper.setPaperUrl(fileUrl);
                paper.setPaperStatus(Const.PaperStatusEnum.UN_HANDLE.getCode());

                int result = paperDao.insert(paper);
                if (result > 0){
                    Map<String,String> map = new HashMap<>();
                    map.put("uri",newFileName);
                    map.put("url", fileUrl);
                    map.put("fileName",fileName);

                    file1.delete();

                    return ServerResponse.serverResponseBySuccess(map,"上传成功");
                }
                file1.delete();
                return ServerResponse.serverResponseByFail("数据库保存失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.serverResponseByFail("文件上传失败");
    }

    @Override
    public ServerResponse selectByUserId(Integer userId) {

        List<Paper> paperList = paperDao.selectByUserId(userId);

        if (paperList == null || paperList.size() == 0){
            return ServerResponse.serverResponseByFail("没有论文");
        }

        List<PaperVO> paperVOList = new ArrayList<>();
        for (Paper paper:paperList
             ) {
            paperVOList.add(TransVO.transPaperVO(paper));
        }

        return ServerResponse.serverResponseBySuccess(paperVOList);
    }

    @Override
    public ServerResponse update(MultipartFile file, Integer paperId, String path, String desc) {

        if (file == null){
            return ServerResponse.serverResponseByFail("文件不能为空");
        }

        //1、获取文件名字
        String fileName = file.getOriginalFilename();
        //2、获取后缀扩展名
        String exName = fileName.substring(fileName.lastIndexOf("."));
        if (!exName.equals(".pdf") && !exName.equals(".doc") && !exName.equals(".docx")){
            return ServerResponse.serverResponseByFail("文件格式不正确");
        }

        //3、生成标识名--唯一
        String newFileName = UUID.randomUUID().toString() + exName;

        //4、本地转储
        File filePath = new File(path);
        if (!filePath.exists()){
            filePath.setWritable(true);
            filePath.mkdirs();
        }
        File file1 = new File(path,newFileName);

        try {
            file.transferTo(file1);
            //上传到服务器
            List<File> fileList = new ArrayList<>();
            fileList.add(file1);

            if (FTPUtils.uploadFile(fileList)){

                String fileUrl = PropertiesUtils.readByKey("fileHost") + "/" + newFileName;


                //查找数据库
                Paper paper = paperDao.selectByPaperId(paperId);
                if (paper == null){
                    return ServerResponse.serverResponseByFail("论文不存在，请直接上传");
                }

                if (paper.getPaperStatus() != Const.PaperStatusEnum.UN_HANDLE.getCode()){
                    return ServerResponse.serverResponseByFail("该论文已经审核，不可修改");
                }

                //数据库添加--修改数据
                paper.setIdentifyingName(newFileName);
                paper.setPaperName(fileName);
                paper.setPaperUrl(fileUrl);
                paper.setPaperDesc(desc);
                paper.setPaperStatus(Const.PaperStatusEnum.UN_HANDLE.getCode());

                int result = paperDao.update(paper);

                if (result > 0){
                    Map<String,String> map = new HashMap<>();
                    map.put("url", fileUrl);
                    map.put("uri",newFileName);
                    map.put("fileName",fileName);

                    file1.delete();

                    return ServerResponse.serverResponseBySuccess(map,"修改成功");
                }
                file1.delete();
                return ServerResponse.serverResponseByFail("数据库保存失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ServerResponse.serverResponseByFail("数据库保存失败");
    }

    @Override
    public ServerResponse delete(Integer paperId) {

        int result = paperDao.delete(paperId);
        if (result > 0){
            return ServerResponse.serverResponseBySuccess(null,"删除成功");
        }

        return ServerResponse.serverResponseByFail(1,"删除失败");
    }

    @Override
    public ServerResponse deleteByKeyAndUserId(Integer paperId, Integer userId) {

        int result = paperDao.deleteByKeyAndUserId(paperId,userId);
        if (result > 0){
            return ServerResponse.serverResponseBySuccess("删除修改");
        }

        return ServerResponse.serverResponseByFail("删除失败");
    }

}

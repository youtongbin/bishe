package com.ytb.service;

import com.ytb.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IPaperService {

    /**
     * 论文上传
     * @param file
     * @return
     */
    ServerResponse upload(MultipartFile file,Integer userId,String path,String desc);

    /**
     * 前台用户--论文列表
     * @param userId
     * @return
     */
    ServerResponse selectByUserId(Integer userId);

    /**
     * 修改上传文件
     * @param file
     * @param paperId
     * @param path
     * @param desc
     * @return
     */
    ServerResponse update(MultipartFile file,Integer paperId,String path,String desc);


    /**
     * 管理员删除
     * @param paperId
     * @return
     */
    ServerResponse delete(Integer paperId);

    /**
     * 用户删除文件
     * @param paperId
     * @param userId
     * @return
     */
    ServerResponse deleteByKeyAndUserId(Integer paperId,Integer userId);

}

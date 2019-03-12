package com.ytb.service;

import com.ytb.common.ServerResponse;

public interface IApplyService {

    /**
     * 添加、提交申请
     * @param userId
     * @return
     */
    ServerResponse insert(Integer userId);

    /**
     * 查看当前用户授权申请列表
     * @param userId
     * @return
     */
    ServerResponse selectAllByUserId(Integer userId);

    /**
     * 管理员查看所有列表
     * @return
     */
    ServerResponse selectAll();

}

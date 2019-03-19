package com.ytb.service;

import com.ytb.common.ServerResponse;
import com.ytb.pojo.Read;

public interface IReadService {

    /**
     * 添加审批信息
     * @param userId
     * @param read
     * @return
     */
    ServerResponse insert(Integer userId, Read read);

    /**
     * 管理员查看审批信息
     * @param readId
     * @return
     */
    ServerResponse see(Integer readId);

    /**
     * 修改审批信息
     * @param read
     * @return
     */
    ServerResponse update(Read read);

    /**
     * 查看列表
     * @param paperId
     * @return
     */
    ServerResponse list(Integer paperId);
}

package com.ytb.service;

import com.ytb.common.ServerResponse;

public interface IPaperManageService {

    /**
     * 管理员查看论文列表
     * @return
     */
    ServerResponse selectAll();


    ServerResponse delete(Integer paperId);

}

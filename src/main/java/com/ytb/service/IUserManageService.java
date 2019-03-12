package com.ytb.service;

import com.ytb.common.ServerResponse;
import com.ytb.pojo.User;

public interface IUserManageService {

    /**
     * 添加用户
     * @param user
     * @return
     */
    ServerResponse addUser(User user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    ServerResponse deleteUser(Integer userId);

    /**
     * 授权
     * @param applyId
     * @param role
     * @return
     */
    ServerResponse makePower(Integer applyId,Integer role);

}

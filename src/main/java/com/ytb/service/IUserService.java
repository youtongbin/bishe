package com.ytb.service;

import com.ytb.common.ServerResponse;
import com.ytb.pojo.User;

/**
 * 前台-普通用户
 */
public interface IUserService {

    /**
     * 注册
     * @param user
     * @return
     */
    ServerResponse register(User user);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    ServerResponse login(String username,String password);

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    ServerResponse update(User user);


}

package com.ytb.service;

import com.ytb.pojo.User;

public interface IUserService {

    int insert(User user);

    User selectByUsername(String username);

    int checkUsername(String username);

}

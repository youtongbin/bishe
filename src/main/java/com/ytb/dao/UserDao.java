package com.ytb.dao;

import com.ytb.pojo.User;

public interface UserDao {

    int insert(User user);

    User selectByUsername(String username);

    int checkUsername(String username);

}

package com.ytb.service.impl;

import com.ytb.dao.UserDao;
import com.ytb.pojo.User;
import com.ytb.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public int checkUsername(String username) {
        return userDao.checkUsername(username);
    }
}

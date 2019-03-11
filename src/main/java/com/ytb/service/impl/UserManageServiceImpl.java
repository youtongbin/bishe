package com.ytb.service.impl;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.dao.UserDao;
import com.ytb.pojo.User;
import com.ytb.service.IUserManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserManageServiceImpl implements IUserManageService {

    @Resource
    private UserDao userDao;

    @Override
    public ServerResponse addUser(User user) {

        if (user.getUsername() == null || user.getUsername().matches("[ ]*")){
            return ServerResponse.serverResponseByFail("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().matches("[ ]*")){
            return ServerResponse.serverResponseByFail("密码不能为空");
        }
        if (user.getPhone() == null || user.getPhone().matches("[ ]*")){
            return ServerResponse.serverResponseByFail("手机号不能为空");
        }
        if (user.getEmail() == null || user.getEmail().matches("[ ]*")){
            return ServerResponse.serverResponseByFail("邮箱不能为空");
        }

        if (userDao.checkUsername(user.getUsername()) > 0){
            return ServerResponse.serverResponseByFail("该用户名已存在");
        }

        int result = userDao.insert(user);

        if (result > 0){
            return ServerResponse.serverResponseBySuccess(user,"添加成功");
        }

        return ServerResponse.serverResponseByFail(1,"添加失败");
    }

    @Override
    public ServerResponse deleteUser(Integer userId) {

        if (userId == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.INPUT_NULL.getMsg());
        }

        int result = userDao.delete(userId);

        if (result > 0){
            return ServerResponse.serverResponseBySuccess(null,"删除成功");
        }

        return ServerResponse.serverResponseByFail("删除失败");
    }

    @Override
    public ServerResponse makePower(Integer userId, Integer role) {

        int result = userDao.makePower(userId,role);
        if (result > 0){
            return ServerResponse.serverResponseBySuccess(Const.PowerEnum.codeOf(role).getMsg(),"授权成功");
        }

        return ServerResponse.serverResponseByFail(1,"授权失败");
    }
}

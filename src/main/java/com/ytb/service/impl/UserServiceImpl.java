package com.ytb.service.impl;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.dao.UserDao;
import com.ytb.pojo.User;
import com.ytb.service.IUserService;
import com.ytb.vo.UserVO;
import com.ytb.vo.tvo.TransVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public ServerResponse register(User user) {
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
            return ServerResponse.serverResponseByFail(1,"用户名已存在");
        }

        user.setRole(Const.PowerEnum.COMMON.getCode());

        int result = userDao.insert(user);
        if (result > 0){
            return ServerResponse.serverResponseBySuccess(null,"注册成功");
        }
        return ServerResponse.serverResponseByFail("注册失败");
    }

    @Override
    public ServerResponse login(String username, String password) {

        if (username == null || username.matches("[ ]*")){
            return ServerResponse.serverResponseByFail("用户名不能为空");
        }
        if (password == null || password.matches("[ ]*")){
            return ServerResponse.serverResponseByFail("密码不能为空");
        }

        if (userDao.checkUsername(username) == 0){
            return ServerResponse.serverResponseByFail(1,"用户名不存在");
        }

        User user = userDao.selectByUsernameAndPassword(username,password);
        if (user == null){
            return ServerResponse.serverResponseByFail(1,"密码错误");
        }

        return ServerResponse.serverResponseBySuccess(user,"登录成功");
    }

    @Override
    public ServerResponse update(User user) {

        User user1 = userDao.selectByUsername(user.getUsername());
        if (user1 != null){
            if (user1.getUserId() != user.getUserId()){
                return ServerResponse.serverResponseByFail("用户名已存在");
            }
        }

        int result = userDao.update(user);

        if (result > 0){
            return ServerResponse.serverResponseBySuccess(null,"修改成功");
        }

        return ServerResponse.serverResponseByFail(1,"修改失败");
    }

    @Override
    public ServerResponse getUserInfo(Integer userId) {

        if (userId == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.INPUT_NULL.getMsg());
        }

        User user = userDao.selectByKey(userId);
        if (user == null){
            return ServerResponse.serverResponseByFail("用户不存在");
        }

        UserVO userVO = TransVO.transUserVO(user);

        return ServerResponse.serverResponseBySuccess(userVO);
    }


}

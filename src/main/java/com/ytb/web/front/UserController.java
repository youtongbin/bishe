package com.ytb.web.front;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.pojo.User;
import com.ytb.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/register.do")
    public ServerResponse register(User user){

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

        if (userService.checkUsername(user.getUsername()) > 0){
            return ServerResponse.serverResponseByFail(1,"用户名已存在");
        }

        int result = userService.insert(user);
        if (result > 0){
            return ServerResponse.serverResponseBySuccess(null,"注册成功");
        }

        return ServerResponse.serverResponseByFail("注册失败");

    }

}

package com.ytb.web.front;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.pojo.User;
import com.ytb.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("/register.do")
    public ServerResponse register(@RequestBody User user){
        return userService.register(user);
    }

    /**
     * 登录
     * @param session
     * @return
     */
    @RequestMapping("/login.do")
    public ServerResponse login(HttpSession session,
                                @RequestBody User user){

        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.INPUT_NULL.getMsg());
        }

        ServerResponse serverResponse = userService.login(user.getUsername(),user.getPassword());
        if (serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,(User)serverResponse.getData());
        }
        return serverResponse;
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/logout.do")
    public ServerResponse logout(HttpSession session){
        session.invalidate();
        return ServerResponse.serverResponseBySuccess(null,"退出成功");
    }

    /**
     * 修改个人信息
     * @param session
     * @param user
     * @return
     */
    @RequestMapping("/update.do")
    public ServerResponse update(HttpSession session,User user){

        User user1 =  (User)session.getAttribute(Const.CURRENT_USER);
        if (user1 == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        user.setUserId(user1.getUserId());
        return userService.update(user);
    }

}

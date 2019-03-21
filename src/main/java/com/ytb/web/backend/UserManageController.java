package com.ytb.web.backend;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.pojo.User;
import com.ytb.service.IUserManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/user_manage")
public class UserManageController {

    @Resource
    private IUserManageService userManageService;

    /**
     * 添加用户
     * @return
     */
    @RequestMapping("/add_user.do")
    public ServerResponse addUser(HttpSession session,@RequestBody User user){
        User user1 = (User)session.getAttribute(Const.CURRENT_USER);
        if (user1 == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        if (user1.getRole() != Const.PowerEnum.MANAGER.getCode()){
            return ServerResponse.serverResponseByFail(1,"权限不足");
        }

        return userManageService.addUser(user);
    }

    /**
     * 删除用户
     * @param session
     * @param userId
     * @return
     */
    @RequestMapping("/delete_user.do")
    public ServerResponse deleteUser(HttpSession session, Integer userId){
        User user1 = (User)session.getAttribute(Const.CURRENT_USER);
        if (user1 == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        if (user1.getRole() != Const.PowerEnum.MANAGER.getCode()){
            return ServerResponse.serverResponseByFail(1,"权限不足");
        }

        return userManageService.deleteUser(userId);
    }


    /**
     * 授权
     * @param session
     * @return
     */
    @RequestMapping("/make_power.do")
    public ServerResponse makePower(HttpSession session, Integer applyId){
        User user1 = (User)session.getAttribute(Const.CURRENT_USER);
        if (user1 == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        if (user1.getRole() != Const.PowerEnum.MANAGER.getCode()){
            return ServerResponse.serverResponseByFail(1,"权限不足");
        }

        return userManageService.makePower(applyId,Const.PowerEnum.SPECIALIST.getCode());
    }

    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session){
        User user1 = (User)session.getAttribute(Const.CURRENT_USER);
        if (user1 == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        if (user1.getRole() != Const.PowerEnum.MANAGER.getCode()){
            return ServerResponse.serverResponseByFail(1,"权限不足");
        }

        return userManageService.selectAll();
    }

}

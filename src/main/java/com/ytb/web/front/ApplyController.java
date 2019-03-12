package com.ytb.web.front;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.pojo.User;
import com.ytb.service.IApplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/power")
public class ApplyController {

    @Resource
    private IApplyService applyService;


    @RequestMapping("/power_up.do")
    public ServerResponse powerUp(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(1,"请先登录");
        }

        if (user.getRole() != Const.PowerEnum.COMMON.getCode()){
            return ServerResponse.serverResponseByFail("不是普通用户，无需申请");
        }

        return applyService.insert(user.getUserId());

    }

    @RequestMapping("/power_up_list.do")
    public ServerResponse powerUpList(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(1,"请先登录");
        }

        return applyService.selectAllByUserId(user.getUserId());

    }

}

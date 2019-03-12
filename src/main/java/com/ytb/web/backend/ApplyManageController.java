package com.ytb.web.backend;

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
@RequestMapping("/power_manager")
public class ApplyManageController {

    @Resource
    private IApplyService applyService;

    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        if (user.getRole() != Const.PowerEnum.MANAGER.getCode()){
            return ServerResponse.serverResponseByFail(1,"权限不足");
        }

        return applyService.selectAll();

    }
}

package com.ytb.web.judge;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.pojo.User;
import com.ytb.service.IPaperManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/paper_judge")
public class PaperJudgeController {

    @Resource
    private IPaperManageService paperManageService;

    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }
        if (user.getRole() != Const.PowerEnum.SPECIALIST.getCode()){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NO_POWER.getMsg());
        }

        return paperManageService.selectAll();
    }
}

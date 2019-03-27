package com.ytb.web.front;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.pojo.User;
import com.ytb.service.IReadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/read")
public class ReadController {

    @Resource
    private IReadService readService;

    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session,Integer paperId){

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        return readService.list(paperId,user.getUserId(),user.getRole());
    }

    @RequestMapping("/see.do")
    public ServerResponse see(HttpSession session,Integer readId){

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        return readService.see(readId);
    }

}

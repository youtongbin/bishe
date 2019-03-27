package com.ytb.web.judge;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.pojo.Read;
import com.ytb.pojo.User;
import com.ytb.service.IReadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/read_judge")
public class ReadJudgeController {

    @Resource
    private IReadService readService;


    @RequestMapping("/insert.do")
    public ServerResponse insert(HttpSession session,@RequestBody Read read){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(1,Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        if (user.getRole() == Const.PowerEnum.COMMON.getCode()){
            return ServerResponse.serverResponseByFail(1,Const.CommonEnum.NO_POWER.getMsg());
        }

        return readService.insert(user.getUserId(),read,user.getRole());
    }

    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session,Integer paperId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(1,Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        if (user.getRole() == Const.PowerEnum.COMMON.getCode()){
            return ServerResponse.serverResponseByFail(1,Const.CommonEnum.NO_POWER.getMsg());
        }

        return readService.list(paperId,user.getUserId(),user.getRole());
    }


    @RequestMapping("/see.do")
    public ServerResponse see(HttpSession session,Integer readId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(1,Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        if (user.getRole() == Const.PowerEnum.COMMON.getCode()){
            return ServerResponse.serverResponseByFail(1,Const.CommonEnum.NO_POWER.getMsg());
        }

        return readService.see(readId);
    }

    @RequestMapping("/update.do")
    public ServerResponse update(HttpSession session,Read read){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(1,Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        if (user.getRole() == Const.PowerEnum.COMMON.getCode()){
            return ServerResponse.serverResponseByFail(1,Const.CommonEnum.NO_POWER.getMsg());
        }

        if (read.getUserId() != user.getUserId()){
            return ServerResponse.serverResponseByFail("非当前用户评审，不可修改");
        }

        return readService.update(read,user.getUserId(),user.getRole());
    }

}

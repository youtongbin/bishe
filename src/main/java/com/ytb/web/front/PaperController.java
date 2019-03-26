package com.ytb.web.front;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.pojo.User;
import com.ytb.service.IPaperService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/paper")
public class PaperController {

    @Resource
    private IPaperService paperService;

    @RequestMapping("/upload.do")
    public ServerResponse upload(HttpSession session,
                                 @RequestParam(value = "file_upload",required = false) MultipartFile file,
                                 @RequestParam(value = "paper_desc") String desc){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        return paperService.upload(file,user.getUserId(),Const.TEMP_KEEP,desc);
    }

    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        return paperService.selectByUserId(user.getUserId());
    }

    @RequestMapping("/update.do")
    public ServerResponse update(HttpSession session,
                                 @RequestParam(value = "file_upload",required = false)MultipartFile file,
                                 @RequestParam(value = "paperId") Integer paperId,
                                 @RequestParam(value = "paper_desc") String desc){

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        return paperService.update(file,paperId,Const.TEMP_KEEP,desc,user.getUserId());

    }


    @RequestMapping("/delete.do")
    public ServerResponse delete(HttpSession session,Integer paperId){

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        return paperService.deleteByKeyAndUserId(paperId,user.getUserId());

    }

    @RequestMapping("/one.do")
    public ServerResponse one(HttpSession session,Integer paperId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.NEED_LOGIN.getMsg());
        }

        return paperService.selectByPaperId(paperId);
    }

}

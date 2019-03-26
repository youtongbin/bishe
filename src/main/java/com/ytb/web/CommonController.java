package com.ytb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    @RequestMapping("/login.do")
    public String login(){
        return "login";
    }

    @RequestMapping("/register.do")
    public String register(){
        return "register";
    }

    @RequestMapping("/welcome.do")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/paper_upload.do")
    public String upload(){
        return "paper_upload";
    }

    @RequestMapping("/home.do")
    public String home(){
        return "home";
    }

    @RequestMapping("/user_update.do")
    public String update_user(){
        return "user_update";
    }

    @RequestMapping("/user_add.do")
    public String add_user(){
        return "user_add";
    }

    @RequestMapping("/user_list.do")
    public String user_list(){
        return "user_list";
    }

    @RequestMapping("/apply_list.do")
    public String apply_list(){
        return "apply_list";
    }

    @RequestMapping("/apply_manage_list.do")
    public String apply_manage_list(){
        return "apply_manage_list";
    }

    @RequestMapping("/paper_list")
    public String paper_list(){
        return "paper_list";
    }

    @RequestMapping("/paper_manage_list.do")
    public String paper_manage_list(){
        return "paper_manage_list";
    }

    @RequestMapping("/paper_judge_list.do")
    public String paper_judge_list(){
        return "paper_judge_list";
    }
}

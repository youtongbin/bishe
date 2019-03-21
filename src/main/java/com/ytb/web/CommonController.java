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

    @RequestMapping("/upload.do")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/home.do")
    public String home(){
        return "home";
    }

    @RequestMapping("/update_user.do")
    public String update_user(){
        return "update_user";
    }

    @RequestMapping("/add_user.do")
    public String add_user(){
        return "add_user";
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
}

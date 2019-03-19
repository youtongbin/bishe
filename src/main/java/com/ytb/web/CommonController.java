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

    @RequestMapping("/upload.do")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/home.do")
    public String home(){
        return "home";
    }
}

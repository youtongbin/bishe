package com.ytb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebTest {

    @RequestMapping("/test.do")
    public String test(){
        return "test";
    }

}

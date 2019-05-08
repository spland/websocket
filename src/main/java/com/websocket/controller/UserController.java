package com.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * create by xxie
 * on 2019/5/7
 */
@Controller
public class UserController {
    @GetMapping("/")
    public String ss(){
        return "index";
    }
}

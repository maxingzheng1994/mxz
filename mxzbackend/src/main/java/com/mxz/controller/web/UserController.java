package com.mxz.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mxz.common.advice.aspect.HumenService;
import com.mxz.service.UserService;

/**
*@Description 
*@author mxz
*2018-07-26
**/
@RequestMapping("user")
@RestController
public class UserController {
   
    @Autowired
    private UserService userService;
    
    @GetMapping("/hello")
    public String hello() {
        HumenService h =  (HumenService)userService;
        h.playGame();
        return "hello";
    }
    
    @GetMapping("/error")
    public int error() {
        return 1/0;
    }
}

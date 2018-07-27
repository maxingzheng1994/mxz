package com.mxz.controller.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mxz.model.User;

/**
*@Description 
*@author mxz
*2018-07-26
**/
@RequestMapping("user")
@RestController
public class UserController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}

package com.mxz.service.chekin.controller;

import com.mxz.service.chekin.service.CheckInService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 签到
 *
 * @author mxz on 2020/09/17 11:52
 */
@RestController
@RequestMapping("checkin")
public class CheckInController {

    @Resource
    private CheckInService checkInService;

    @RequestMapping
    public void check(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        checkInService.check(uid);
    }
}

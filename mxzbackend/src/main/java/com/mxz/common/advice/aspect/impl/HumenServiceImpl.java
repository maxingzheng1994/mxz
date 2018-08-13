package com.mxz.common.advice.aspect.impl;

import com.mxz.common.advice.aspect.HumenService;

/**
*@Description  
*@author mxz
*2018年8月9日
**/
public class HumenServiceImpl implements HumenService{

    @Override
    public void playGame() {
        System.out.println("com.mxz.service.impl.UserServiceImpl");
    }

}

package com.mxz.common.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

import com.mxz.common.advice.aspect.HumenService;
import com.mxz.common.advice.aspect.impl.HumenServiceImpl;

/**
*@Description  
*@author mxz
*2018-8-9
**/
@Aspect
@Component
public class UserServiceAspect {
    
    @DeclareParents(value = "com.mxz.service.impl.UserServiceImpl", defaultImpl = HumenServiceImpl.class)
    private HumenService humenService;
}

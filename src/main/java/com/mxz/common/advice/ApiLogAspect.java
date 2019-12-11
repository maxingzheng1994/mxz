package com.mxz.common.advice;

import static com.mxz.common.utils.PrintUtil.print;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
*@Description  
*@author mxz
*2018-8-7
**/
@Aspect
@Component
public class ApiLogAspect {
    /**切面点*/
    private final String POINT_CUT = "execution(public * com.mxz.controller.web.*.*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)";
    @Pointcut(POINT_CUT)  
    public void webApiLog(){}
    
    
    @Before("webApiLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
        HttpServletRequest request = attributes.getRequest(); 
        print("URL : " + request.getRequestURL().toString());  
        print("HTTP_METHOD : " + request.getMethod());  
        print("IP : " + request.getRemoteAddr());  
        print("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());  
        print("ARGS : " + Arrays.toString(joinPoint.getArgs()));  
    }
    
    @AfterThrowing("webApiLog()") 
    public void doThrow(JoinPoint joinPoint) {
        print("have exception");
    }
    
    @Around("webApiLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        print("+++++++++++++++++++++++++++++++++++++++++");
        Object result = pjp.proceed();
        print(result.toString());
        return result;
    }
}

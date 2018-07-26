package com.mxz.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.mxz.common.config.BeanConfig;


/**
*@Description 
*@author mxz
*2018-07-27
**/
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {BeanConfig.class})
public @interface EnableBean {

}

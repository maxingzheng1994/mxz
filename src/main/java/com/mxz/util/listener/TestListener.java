package com.mxz.util.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
//可写任何实现ApplicationEvent 的类
public class TestListener implements ApplicationListener<ApplicationContextEvent>{

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        System.out.println("============================="+event.getSource());
    }

}

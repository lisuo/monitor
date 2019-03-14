package com.monitor.auth.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.servlet.annotation.WebListener;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/10/12:14:34
 */
//@WebListener
public class ProjectStartListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("我的父容器为：" + contextRefreshedEvent.getApplicationContext().getParent());
        System.out.println("初始化时我被调用了。");
    }
}

package com.monitor.auth.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/14:12:25
 */
@WebListener
public class ServletContextInitListener implements ServletContextListener {

    public static String WEB_CONTEXT_FILE_PATH="";

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * 项目
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("-------------------------------6666666666666666666666666666666666666666666666666666----------------------------------");
        // 初始化项目路径
        WEB_CONTEXT_FILE_PATH = arg0.getServletContext().getRealPath("/");
    }

}

package com.monitor.common.utils;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/10/8:19:29
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
}

package com.example.monitor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/15:11:13
 */
public class TestLock {
    public static void main(String[] args) {

        MyService myService = new MyService();
        for (int i = 0; i < 10; i ++){
            Thread thread = new ThreaLock(i + "hello", myService);
            thread.start();
        }
    }
}

class ThreaLock extends Thread{
    private String name;
    private MyService myService;

    public ThreaLock(String name, MyService myService) {
        this.name = name;
        this.myService = myService;
        myService.setName(name);
    }

    @Override
    public void run() {
        Integer num = 100;
        myService.service(num);
    }
}

class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyService(String name) {
        this.name = name;
    }

    public MyService() {
    }

    public void service(Integer num){
        //int num = 100;
        lock.lock();
        for (int i = 0; i < 10; i ++){

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println(Thread.currentThread().getName() + "::" + i);
            System.out.println(Thread.currentThread().getName() + "::" + "----" + "num:" + num --);
        }
        lock.unlock();
    }
}

package com.example.monitor;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/15:10:50
 */
public class TestThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<LocalDateTime> date = new ThreadLocal<>();
        date.set(LocalDateTime.now());
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i < 10; i ++){
            ThreadA a = new ThreadA(date, i);
            a.start();
        }
        System.out.println("主线程：" + date.get());
    }
}

class ThreadA extends Thread{
    private ThreadLocal<LocalDateTime> date;
    private int i;

    public ThreadA(ThreadLocal<LocalDateTime> date, int i) {

        this.date = date;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(20);
            date.set(LocalDateTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程：" + date.get());
    }
}
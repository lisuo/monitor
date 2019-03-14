package com.example.monitor;


import java.util.Arrays;
import java.util.Optional;

/**
 * @Description:
 * @author lisuo
 * @date 2018/9/11 0011上午 12:20
 */
public class LearnTest {
    public static Boolean logicalAnd(Integer a, Integer b){
        return Boolean.logicalAnd(Boolean.valueOf(a.toString()), Boolean.valueOf(b.toString()));
    }

    public static void main(String[] args) {

        System.out.println(logicalAnd(1, 1));
        System.out.println(Arrays.asList("jacj"));
        System.out.println("cd8e6b94113f46b4aabdeb5acce19e15;15a1094836bc4353b8fca173d8310e9b".split(";"));
        Optional.ofNullable("jack").ifPresent(s ->{
            System.out.println(s);
        });
    }
}

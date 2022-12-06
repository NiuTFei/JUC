package com.ntf.juc.thread;

public class TestFrames {
    public static void main(String[] args) {
        new Thread( () -> method1(20),"t1").start();
        method1(10);
        int[] arr = new int[5];
    }

    public static void method1(int x){
        int y = x + 1;
        Object m = method2();
        System.out.println(m);
    }

    public static Object method2(){
        Object o = new Object();
        return o;
    }
}

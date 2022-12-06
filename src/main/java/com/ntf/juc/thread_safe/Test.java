package com.ntf.juc.thread_safe;

public class Test {

    static int a = 0;
    static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    a++;
                }
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5001; i++) {
                synchronized (lock){
                    a--;
                }
            }
        },"t2");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("a = " + a);
    }
}

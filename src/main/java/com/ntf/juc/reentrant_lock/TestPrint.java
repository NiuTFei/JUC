package com.ntf.juc.reentrant_lock;

import java.util.concurrent.locks.Condition;

public class TestPrint {
    public static void main(String[] args) {
        AwaitSignal signal = new AwaitSignal(5);
        Condition a = signal.newCondition();
        Condition b = signal.newCondition();
        Condition c = signal.newCondition();

        new Thread(() -> {
            signal.print("a",a,b);
        }).start();

        new Thread(() -> {
            signal.print("b",b,c);
        }).start();

        new Thread(() -> {
            signal.print("c",c,a);
        }).start();

        try {
            Thread.sleep(1000);
            signal.lock();
            System.out.println("开始打印");
            a.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            signal.unlock();
        }

    }
}

package com.ntf.juc.reentrant_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSignal extends ReentrantLock {
    private int loopNumber;

    public AwaitSignal (int loopNumber){
        this.loopNumber = loopNumber;
    }

    public void print(String str, Condition current, Condition next){
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                System.out.print(str);
                next.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                unlock();
            }
        }
    }
}

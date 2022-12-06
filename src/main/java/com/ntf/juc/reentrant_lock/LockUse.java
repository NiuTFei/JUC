package com.ntf.juc.reentrant_lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockUse {
    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        try {
            reentrantLock.lock();

        } finally {
            reentrantLock.unlock();
        }
    }

}

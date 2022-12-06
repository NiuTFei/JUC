package com.ntf.juc.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MakeTea {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("洗水壶");
            try {
                Thread.sleep(1000);
                log.debug("烧开水");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"老王");

        Thread t2 = new Thread(() -> {
            log.debug("洗茶壶");
            try {
                Thread.sleep(1000);
                log.debug("洗茶杯");
                Thread.sleep(2000);
                log.debug("拿茶叶");
                Thread.sleep(1000);
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("泡茶");
        },"小王");

        t1.start();
        t2.start();
    }
}

package com.ntf.juc.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStatus {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("running");
        },"t1");//new

        Thread t2 = new Thread(() -> {
            while (true){}
        },"t2");
        t2.start();//runnable

        Thread t3 = new Thread(() -> {
            log.info("running");
        },"t3");
        t3.start();//terminated


        Thread t4 = new Thread(() -> {
            synchronized (ThreadStatus.class){
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t4");
        t4.start();//timed_waiting

        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t5");
        t5.start();//waiting

        Thread t6 = new Thread(() -> {
            synchronized (ThreadStatus.class){//拿不到锁
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t6");
        t6.start();//blocked

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("t1 state:{}",t1.getState());
        log.debug("t2 state:{}",t2.getState());
        log.debug("t3 state:{}",t3.getState());
        log.debug("t4 state:{}",t4.getState());
        log.debug("t5 state:{}",t5.getState());
        log.debug("t6 state:{}",t6.getState());

/**
 * 15:50:41.270 [t3] INFO com.ntf.juc.thread.ThreadStatus - running
 * 15:50:41.775 [main] DEBUG com.ntf.juc.thread.ThreadStatus - t1 state:NEW
 * 15:50:41.776 [main] DEBUG com.ntf.juc.thread.ThreadStatus - t2 state:RUNNABLE
 * 15:50:41.776 [main] DEBUG com.ntf.juc.thread.ThreadStatus - t3 state:TERMINATED
 * 15:50:41.776 [main] DEBUG com.ntf.juc.thread.ThreadStatus - t4 state:TIMED_WAITING
 * 15:50:41.776 [main] DEBUG com.ntf.juc.thread.ThreadStatus - t5 state:WAITING
 * 15:50:41.776 [main] DEBUG com.ntf.juc.thread.ThreadStatus - t6 state:BLOCKED
 */
    }
}

package com.ntf.juc.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateThreadWithRunnable {
    public static void main(String[] args) {

        Runnable runnable = () -> log.debug("Thread-0 is running");


        Thread t = new Thread(runnable,"Thread-0");
        t.start();

    }

}

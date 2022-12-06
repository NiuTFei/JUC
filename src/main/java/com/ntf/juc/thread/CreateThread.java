package com.ntf.juc.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateThread {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true){
                log.debug("running");
                System.out.println();
            }
        },"T1").start();
        new Thread(() -> {
            while (true){
                log.debug("running");
            }
        },"T2").start();

    }

}

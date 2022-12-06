package com.ntf.juc.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class CreateThreadWithFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("running");
            Thread.sleep(1000);
            return 1;
        });
        new Thread(task,"thread-1").start();
        log.debug("{}",task.get());
    }
}

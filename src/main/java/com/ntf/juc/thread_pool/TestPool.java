package com.ntf.juc.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
@Slf4j
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1000,
                TimeUnit.MILLISECONDS, 10,
                BlockingQueue::put);
        for (int i = 0; i < 5; i++) {
            int num = i;
            threadPool.execute(() -> {
                log.debug("这是第{}个任务", num);
            });
        }
    }
}

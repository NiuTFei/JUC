package com.ntf.juc.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
@Slf4j
public class ThreadPool {
    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //核心线程数
    private int coreSize;

    //获取任务的超时时间
    private long timeout;

    //时间单位
    private TimeUnit timeUnit;

    //拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int capacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(capacity);
        this.rejectPolicy = rejectPolicy;
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //当task不为空，执行任务
            //当task执行完毕，从任务队列里获取并执行任务
            while (task != null || ((task = taskQueue.poll(timeout, timeUnit)) != null)) {
                try {
                    log.debug("正在执行------>{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.debug("worker被移除{}", this);
                workers.remove(this);
            }
        }
    }

    public void execute(Runnable task) {
        //当任务数没有超过coreSize时，直接交给worker对象执行
        //当任务数超过coreSize时，加入任务队列暂存
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("创建worker{}", worker);
                workers.add(worker);
                worker.start();
            } else {
                log.debug("加入任务队列{}", task);
//                taskQueue.put(task);
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }
}

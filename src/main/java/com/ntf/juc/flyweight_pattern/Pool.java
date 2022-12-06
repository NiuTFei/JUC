package com.ntf.juc.flyweight_pattern;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Pool {
    //连接池大小
    private final int poolSize;

    //连接对象数组
    private Connection[] connections;

    //连接状态数组 0 表示空闲， 1 表示繁忙
    private AtomicIntegerArray status;

    //构造方法初始化
    public Pool(int poolSize) {
        this.poolSize = poolSize;
        connections = new Connection[poolSize];
        status = new AtomicIntegerArray(new int[poolSize]);
        for (int i = 0; i < poolSize; i++) {
            connections[i] = new MockConnection();
        }
    }

    //借连接
    public Connection borrow(){
        while (true){
            //获取空闲连接
            for (int i = 0; i < poolSize; i++) {
                if (status.get(i) == 0){
                    if (status.compareAndSet(i,0,1)){
                        return connections[i];
                    }
                }
            }
            //如果没有线程连接，当前线程进入等待
            synchronized (this){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    //归还连接
    public void free(Connection connection){
        for (int i = 0; i < poolSize; i++) {
            if (connections[i] == connection){
                //一个连接只能被一个线程持有，因此不用CAS
                status.set(i,0);
                synchronized (this){
                    this.notifyAll();
                }
                break;
            }
        }
    }
}

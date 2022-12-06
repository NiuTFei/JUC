package com.ntf.juc.single;

/**
 * 优化后的懒汉式
 */
public final class GoodSingleton {
    private GoodSingleton(){};

    //添加volatile，synchronized块内代码可能重排序，防止重排序，防止构造方法排序到赋值操作之后
    private static volatile GoodSingleton INSTANCE = null;

    //改善性能较低，每次调用都需要加锁的问题
    public static GoodSingleton getInstance(){

        if (INSTANCE != null){
            return INSTANCE;
        }

        synchronized (GoodSingleton.class){
            //需要再次判断INSTANCE是否为空，因为首次创建对象时，多个线程并发都会到此
            if (INSTANCE != null){
                return INSTANCE;
            }
            INSTANCE = new GoodSingleton();
            return INSTANCE;
        }
    }
}

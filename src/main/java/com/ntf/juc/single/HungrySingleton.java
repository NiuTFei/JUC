package com.ntf.juc.single;

public final class HungrySingleton {
    private HungrySingleton(){};

    private static HungrySingleton INSTANCE = null;

    //性能较低，每次调用都需要加锁
    public static synchronized HungrySingleton getInstance(){
        if (INSTANCE != null){
            return INSTANCE;
        }
        INSTANCE = new HungrySingleton();
        return INSTANCE;
    }
}

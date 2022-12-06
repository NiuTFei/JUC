package com.ntf.juc.single;

import java.io.Serializable;

/**
 * 饿汉式
 */
//类设为final防止其他类继承破坏单例的实现
public final class Singleton implements Serializable {

    private Singleton(){};

    //静态成员变量初始化是在类加载阶段完成的
    private static final Singleton INSTANCE = new Singleton();

    public static Singleton getInstance(){
        return INSTANCE;
    }

    //添加此方法可以防止反序列化破坏单例
    public Object readResolve(){
        return INSTANCE;
    }
}

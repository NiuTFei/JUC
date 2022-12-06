package com.ntf.juc.NoneLock;

import sun.misc.Unsafe;

public class MyAtomicInteger {

    private volatile int value;

    private static final long valueOffSet;
    static final Unsafe UNSAFE;

    static {
        UNSAFE = Unsafe.getUnsafe();
        try {
            valueOffSet = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public int getValue(){
        return value;
    }

    public void decrement(int amount){
        while (true){
            int prev = this.value;
            int next = value - amount;
            if (UNSAFE.compareAndSwapInt(this,valueOffSet,prev,next)){
                break;
            }
        }
    }

}

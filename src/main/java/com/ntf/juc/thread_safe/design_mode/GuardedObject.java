package com.ntf.juc.thread_safe.design_mode;

/**
 * 保护性暂停
 */
public class GuardedObject {

    //标识GuardedObject
    private int id;

    private Object response;

    public GuardedObject() {
    }

    public GuardedObject(int id) {
        this.id = id;
    }

    public Object getResponse() {
        synchronized (this){
            while (response == null){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return response;
    }

    //带有等待时间
    public Object getResponse(long timeout) {
        synchronized (this){
            long begin = System.currentTimeMillis();
            long passedTime = 0;
            while (response == null){
                long waitTime = timeout - passedTime;
                if (waitTime <= 0){
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                passedTime = System.currentTimeMillis() - begin;
            }
        }
        return response;
    }

    public int getId() {
        return id;
    }

    public void setResponse(Object response) {
        synchronized (this){
            this.response = response;
            this.notifyAll();
        }

    }
}

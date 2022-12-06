package com.ntf.juc.thread_safe;

public class TicketWindow {
    private int count;

    public TicketWindow(int count){
        this.count = count;
    }

    public int getCount(){
        return count;
    }

    public synchronized int sell(int amount){
        if (this.count >= amount){
            this.count -= amount;
            return amount;
        }else {
            return 0;
        }
    }
}

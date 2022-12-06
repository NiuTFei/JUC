package com.ntf.juc.thread_safe;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

@Slf4j
public class TicketSell {
    public static void main(String[] args) throws InterruptedException {
        TicketWindow window = new TicketWindow(1000);
        List<Thread> threadList = new ArrayList<>();
        List<Integer> list = new Vector<>();

        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                int sell = window.sell(getRandomAmount());//共享变量
                list.add(sell);//共享变量
            });
            threadList.add(thread);
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }

        int count = window.getCount();
        log.debug("余票：{}",count);
        log.debug("卖出的票数：{}",list.stream().mapToInt(i -> i).sum());

    }

    static Random random = new Random();

    public static int getRandomAmount(){
        return random.nextInt(5) + 1;
    }

}

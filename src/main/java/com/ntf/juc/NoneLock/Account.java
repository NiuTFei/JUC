package com.ntf.juc.NoneLock;

import java.util.ArrayList;
import java.util.List;

public interface Account {

    Integer getBalance();

    void withdraw(Integer amount);

    static void demo(Account account){
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        list.forEach(Thread::start);
        list.forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("余额为：" + account.getBalance());
    }
}

package com.ntf.juc.NoneLock;

public class AccountUnsafe implements Account{

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        synchronized (this){
            return balance;
        }
    }

    @Override
    public void withdraw(Integer amount) {
        synchronized (this){    //加锁可以保证安全性
            balance -= amount;
        }
    }
}

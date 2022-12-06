package com.ntf.juc.NoneLock;

public class AccountTest {

    public static void main(String[] args) {
        Account account = new AccountCas(10000);
        Account.demo(account);
    }
}

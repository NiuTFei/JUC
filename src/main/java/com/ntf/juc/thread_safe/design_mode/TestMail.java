package com.ntf.juc.thread_safe.design_mode;

public class TestMail {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Integer id : MailBoxes.getIds()) {
            new Postman(id, "内容" + id).start();
        }

    }
}

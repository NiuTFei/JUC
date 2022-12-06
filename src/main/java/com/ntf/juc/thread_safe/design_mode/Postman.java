package com.ntf.juc.thread_safe.design_mode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Postman extends Thread{

    private int id;
    private String mail;

    public Postman(int id, String mail){
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject guardedObject = MailBoxes.getGuardedObject(id);
        log.debug("收到信 id:{}，内容{}",guardedObject.getId(),mail);
        guardedObject.setResponse(mail);
    }
}

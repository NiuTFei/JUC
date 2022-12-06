package com.ntf.juc.thread_safe.design_mode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class People extends Thread{

    @Override
    public void run() {
        //收信
        GuardedObject guardedObject = MailBoxes.createGuardedObject();
        log.debug("开始收信 id:{}",guardedObject.getId());
        Object mail = guardedObject.getResponse();
        log.debug("收到信 id:{}，内容{}",guardedObject.getId(),mail);
    }
}

package com.ntf.juc.thread_safe.design_mode;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GuardTest {
    public static void main(String[] args) {
        GuardedObject object = new GuardedObject();
        new Thread(() -> {
            log.debug("waiting------");
            List<String> result = (List<String>) object.getResponse();
            System.out.println(result);
        },"t1").start();

        new Thread(() -> {
            log.debug("downloading------");
            List<String> list = new ArrayList<>();
            try {
                 list = Downloader.download();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            object.setResponse(list);
        },"t2").start();
    }
}

package com.ntf.juc.thread_safe.message_queue;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 消息队列类，实现线程间通信
 */
@Slf4j(topic = "test")
public class MessageQueue {

    //消息的队列集合
    private  LinkedList<Message> list = new LinkedList<>();

    //队列容量
    private  int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    //获取消息
    public Message take(){
        synchronized (list){
            while (list.isEmpty()){
                try {
                    log.debug("队列为空，消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Message message = list.removeFirst();
            log.debug("消费者消费消息：{}",message);
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message){
        synchronized (list){
            while (list.size() == capacity){
                try {
                    log.debug("队列已满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            list.addLast(message);
            log.debug("生产者生产消息：{}",message);
            list.notifyAll();
        }
    }

}

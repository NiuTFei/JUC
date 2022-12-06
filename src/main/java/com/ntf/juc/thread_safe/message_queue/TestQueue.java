package com.ntf.juc.thread_safe.message_queue;


public class TestQueue {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id,"message:" + id));
            },"生产者：" + i).start();
        }

        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Message message = queue.take();

            }
        },"消费者").start();
    }
}

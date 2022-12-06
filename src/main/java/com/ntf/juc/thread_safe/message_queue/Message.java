package com.ntf.juc.thread_safe.message_queue;

public class Message {
    private int id;

    private Object message;

    public Message(int id, Object message) {
        this.id = id;
        this.message = message;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public Object getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message=" + message +
                '}';
    }
}

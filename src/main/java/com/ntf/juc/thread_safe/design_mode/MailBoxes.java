package com.ntf.juc.thread_safe.design_mode;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class MailBoxes {
    private static final Map<Integer,GuardedObject> boxes = new Hashtable<>();

    private static int id = 1;

    //产生唯一ID
    private static synchronized int generateId(){
        return id++;
    }

    public static GuardedObject createGuardedObject(){
        GuardedObject object = new GuardedObject(generateId());
        boxes.put(object.getId(),object);
        return object;
    }

    public static Set<Integer> getIds(){
        return boxes.keySet();
    }

    public static GuardedObject getGuardedObject(int id){
        return boxes.remove(id);
    }
}

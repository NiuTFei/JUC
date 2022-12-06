package com.ntf.juc.NoneLock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AtomicArrayTest {

    public static void main(String[] args) {
        demo(
                () -> new int[10],
                (array) -> array.length,
                (array,index) -> array[index]++,
                (array) -> System.out.println(Arrays.toString(array))
        );

        demo(
                () -> new AtomicIntegerArray(10),
                AtomicIntegerArray::length,
                AtomicIntegerArray::getAndIncrement,
                System.out::println
        );
//        LongAdder
//        System.out.println(Runtime.getRuntime().availableProcessors());
    }



    private static <T> void demo(
            Supplier<T> arraySupplier,
            Function<T,Integer> lengthFunction,
            BiConsumer<T,Integer> putConsumer,
            Consumer<T> printConsumer
    ){
        List<Thread> threadList = new ArrayList<>();

        T array = arraySupplier.get();
        Integer length = lengthFunction.apply(array);

        for (int i = 0; i < length; i++) {
            threadList.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j % length);
                }
            }));
        }
        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        printConsumer.accept(array);
    }
}

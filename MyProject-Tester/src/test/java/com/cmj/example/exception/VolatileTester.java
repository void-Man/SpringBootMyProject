package com.cmj.example.exception;

import java.io.IOException;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/13
 */
public class VolatileTester {

    private static volatile int i = 0;

    public static void incr(int count) {
        for (int j = 0; j < 100; j++) {
            i += count;
            System.out.println("Thread is :" + Thread.currentThread().getName() + "\t" + "i = " + i);
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> incr(1), "Thread 1").start();
        new Thread(() -> incr(1), "Thread 2").start();
        new Thread(() -> incr(1), "Thread 3").start();
        new Thread(() -> incr(1), "Thread 4").start();
        new Thread(() -> incr(1), "Thread 5").start();

        System.in.read();
    }

}

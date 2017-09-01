package com.zh.framework.thread;

import java.util.Scanner;

/**
 * created by lihuibo on 17-9-1 上午8:57
 */
public class Tester extends Thread {
    private Handler handler;

    public Tester() {
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.start();
        System.out.println("main thread:" + Thread.currentThread().getId());
    }

    @Override
    public void run() {
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                System.out.println("test thread:" + Thread.currentThread().getId());
            }
        };
        new Mythread().start();
        Looper.loop();
    }

    private class Mythread extends Thread {
        @Override
        public void run() {
            handler.sendMessage(new Message());
            System.out.println("my thread:" + Thread.currentThread().getId());
        }
    }
}

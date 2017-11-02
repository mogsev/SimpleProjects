package com.mogsev.simpleprojects.sync;

import com.mogsev.simpleprojects.urils.AuthHeader;
import com.mogsev.simpleprojects.urils.ResponseWrapper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class SyncExampleTest {

    @Test
    public void syncExampleTest_Successful() {
        final ResponseWrapper rw = new ResponseWrapper();
        rw.setAuthHeader(new AuthHeader(Thread.currentThread().getName(), "start", "uid"));

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 50; i++) {
                synchronized (rw) {
                    System.out.println("Before thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                    rw.setAuthHeader(new AuthHeader(Thread.currentThread().getName(), String.valueOf(i), "uid"));
                    System.out.println("After thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                }
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 100; i <= 150; i++) {
                synchronized (rw) {
                    System.out.println("Before thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                    rw.setAuthHeader(new AuthHeader(Thread.currentThread().getName(), String.valueOf(i), "uid"));
                    System.out.println("After thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                }
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 200; i <= 250; i++) {
                synchronized (rw) {
                    System.out.println("Before thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                    rw.setAuthHeader(new AuthHeader(Thread.currentThread().getName(), String.valueOf(i), "uid"));
                    System.out.println("After thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                }
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 300; i <= 350; i++) {
                synchronized (rw) {
                    System.out.println("Before thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                    rw.setAuthHeader(new AuthHeader(Thread.currentThread().getName(), String.valueOf(i), "uid"));
                    System.out.println("After thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                }
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread5 = new Thread(() -> {
            for (int i = 400; i <= 450; i++) {
                synchronized (rw) {
                    System.out.println("Before thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                    rw.setAuthHeader(new AuthHeader(Thread.currentThread().getName(), String.valueOf(i), "uid"));
                    System.out.println("After thread: " + Thread.currentThread().getName() + " / " + rw.getAuthHeader());
                }
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

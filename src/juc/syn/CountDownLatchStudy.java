package juc.syn;

import myenum.Company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchStudy {

    /**
     * 班长最后一次锁门离开（火箭发射倒计时）
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 1; i <= 3; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+ "\t 离开教室");
                countDownLatch.countDown();
            }, Company.getName(i).getName()).start();
        }

        countDownLatch.await(10, TimeUnit.SECONDS);
        System.out.println("班长离开教室");
    }
}

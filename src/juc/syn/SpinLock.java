package juc.syn;


import java.util.concurrent.atomic.AtomicReference;

/***
 * 利用CAS实现自旋锁
 */
public class SpinLock {

    AtomicReference<Thread> myLock = new AtomicReference<>();

    private void myLock(){
        Thread thread = Thread.currentThread();
        do{
           // System.out.println(System.currentTimeMillis() + "thread name \t" + thread.getName() + " 循环获取锁");
        }while (!myLock.compareAndSet(null,thread));
        System.out.println(System.currentTimeMillis() + "thread name \t" + thread.getName() + " invoke myLock()");
    }

    private void myUnLock(){
        Thread thread = Thread.currentThread();
        myLock.compareAndSet(thread,null);
        System.out.println(System.currentTimeMillis() + "thread name \t" + thread.getName() + " invoke myUnLock()");
    }

    public static void main(String[] args) throws InterruptedException {
/*
        SpinLock lock =new SpinLock();
        new Thread(()->{

            try {
                lock.myLock();
                Thread.sleep(2000);
                lock.myUnLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();


        Thread.sleep(1000);

        new Thread(()->{
            lock.myLock();
            lock.myUnLock();
        },"t2").start();

        */

        String java = new StringBuilder("java").toString();
        System.out.println(java);
        System.out.println(java.intern());
        System.out.println(java == java.intern());
    }


}

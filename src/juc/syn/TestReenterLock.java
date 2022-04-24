package juc.syn;

class Person{

    public synchronized void sendMsg() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + "\t sendMsg");
        sendWX();
    }

    public synchronized void sendWX() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "\t sendWX");
        //Thread.sleep(10000);
    }
}

public class TestReenterLock {
    /**
     * 测试出来
     * 1、synchronized 是可重入锁
     * 2、无论某个线程先进入sendMsg或sendWX ，另外一个线程都必须等待
     *      比如：线程t2先执行sendWX，还没完成时，线程t1不会进入sendMsg
     * @param args
     */
    public static void main(String[] args) {
        Person person = new Person();

        new Thread(()->{
            try {
                person.sendMsg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                person.sendWX();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

    }
}

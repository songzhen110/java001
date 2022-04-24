package thread;

import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintABC{
    private volatile int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA(){
        
        lock.lock();
        try {
            while (number!=1){
                condition1.await();
            }
            System.out.println("currentThreadName =" +Thread.currentThread().getName() +",  A");
            number=2;
            condition2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB(){
        lock.lock();
        try {
            while (number!=2){
                condition2.await();
            }
            System.out.println("currentThreadName =" +Thread.currentThread().getName() +",  B");
            number=3;
            condition3.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printC(){
        lock.lock();
        try {
            while (number!=3){
                condition3.await();
            }
            System.out.println("currentThreadName =" +Thread.currentThread().getName() +",  C");
            number=1;
            condition1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class PrintABCByCondition {

    public static void main(String[] args) {
        PrintABC printABC = new PrintABC();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                printABC.printA();
            }
        },"A").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                printABC.printB();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                printABC.printC();
            }
        },"C").start();

    }

}

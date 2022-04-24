package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TaskAddAndJian{
    private int i =0;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    public void add(){
        lock.lock();

        try {

            while (0!=i){
                condition1.await();
            }

            System.out.println("当前 i 是"+i +" 进行加");
            i++;
            condition2.signal();

        } catch (Exception e){

        } finally {
            lock.unlock();
        }
    }

    public void jian(){
        lock.lock();

        try {

            while (1!=i){
                condition2.await();
            }
            System.out.println("当前 i 是"+i +" 进行减");
            i--;
            condition1.signal();

        } catch (Exception e){

        } finally {
            lock.unlock();
        }
    }
}

public class ThreadEx {
    public static void main(String[] args) {
        TaskAddAndJian jiajian = new TaskAddAndJian();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                jiajian.add();
            }

        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                jiajian.jian();
            }

        }).start();
    }


}

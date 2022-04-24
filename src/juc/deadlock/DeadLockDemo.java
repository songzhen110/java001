package juc.deadlock;

class ThreadResource implements Runnable {
    private String lockA;
    private String lockB;

    public ThreadResource(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + " \t 获取到锁 \t"+ lockA + " \t 尝试获取锁 \t"+ lockB);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + " \t 获取到锁 \t"+ lockB + " \t 尝试获取锁 \t"+ lockA);
            }
        }
    }
}

/**
 * 死锁的产生，排查
 * 命令 jps -l jstack {进程ID}
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new ThreadResource(lockA,lockB),"t1").start();
        new Thread(new ThreadResource(lockB,lockA),"t2").start();
    }
}

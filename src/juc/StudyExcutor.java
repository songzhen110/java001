package juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StudyExcutor {

    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "work-pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()){
                t.setDaemon(false);}
            if (t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);}
            return t;
        }
    }

    static class MyRejected implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                final Thread t = new Thread(r, "Temporary task executor");
                t.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {


        new Thread(()->{
            ExecutorService service = new ThreadPoolExecutor(
                    3,
                    5,
                    30,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(3),
                    new MyThreadFactory(),
                    new MyRejected());

            for (int i = 1; i < 12; i++) {
                int finalI = i;
                service.submit(()->{
                    System.out.println("currentThreadName = " + Thread.currentThread().getName() + " , taskId = "+ finalI);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            service.shutdown();

        },"T1").start();

    }
}

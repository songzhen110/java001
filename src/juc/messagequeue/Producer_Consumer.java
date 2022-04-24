package juc.messagequeue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyMachine {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingDeque;

    public MyMachine(BlockingQueue<String> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    public void producer() throws InterruptedException{
        String data;
        boolean result;

        while (flag){
            data = String.valueOf(atomicInteger.incrementAndGet());
            result = blockingDeque.offer(data, 2, TimeUnit.SECONDS);

            if(result){
                System.out.println(data + " \t 生产完成");
            }else {
                System.out.println(data + "\t 生产失败");
            }
            Thread.sleep(1000);
        }

        System.out.println("结束生产任务");

    }

    public void consumer() throws InterruptedException{

        String data;
        while (flag){
            data = blockingDeque.poll(2,TimeUnit.SECONDS);
            if(null == data || data.equalsIgnoreCase("")){
                System.out.println("2秒钟没有消费到数据");
                continue;
            }
            System.out.println(data+ " \t 消费完成");
        }
        System.out.println("结束消费任务");

    }

    public void stop(){
        this.flag = false;
    }
}

public class Producer_Consumer {

    public static void main(String[] args) throws InterruptedException {

        MyMachine myMachine = new MyMachine(new ArrayBlockingQueue<String>(3));

        new Thread(()->{
            try {
                myMachine.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(()->{
            try {
                myMachine.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();

        Thread.sleep(3000);

        myMachine.stop();
    }
}

package thread;

import java.util.concurrent.SynchronousQueue;

public class Thread_003 {

    public static void main(String[] args) {

        char[] one = "123456".toCharArray();
        char[] abc = "ABCDEF".toCharArray();

        SynchronousQueue queue = new SynchronousQueue();

        new Thread(()->{
            for (char c : one) {
                try {
                    queue.put(c);
                    System.out.print(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(()->{
            for (char c : abc) {
                try {
                    System.out.print(queue.take());
                    queue.put(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}

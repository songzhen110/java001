package thread;

public class Test {
    private static Object obj = new Object();

    public static void main(String[] args) throws Exception {

        Object o = new Object();


        new Thread(()->{
            synchronized (o){
                for (int i=0;i<10;i++) {
                    System.out.print("A");
                    try {
                        o.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        },"AAA").start();

        new Thread(()->{
            synchronized (o){
                for (int i=0;i<10;i++) {
                    System.out.print("B");
                    try {
                        o.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"BBB").start();

    }

    private static void newThread(String name) throws Exception {
        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " get");
            }
        }, name).start();
        Thread.sleep(100L);
    }
}

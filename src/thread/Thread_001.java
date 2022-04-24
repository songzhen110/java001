package thread;

/**
 * 类的静态成员变量 可以 类名.属性名 获取 （或者 实例化后 实例对象.属性名）
 * 类的静态方法  可以 类名.方法名 调用 (或者 只声明类成NULL，然后 实例对象.方法名)
 *
 *         System.out.println(MyThread.a);
 *         MyThread.add();
 *
 *         MyThread myThread = null;
 *         myThread.add();
 */
public class Thread_001 {

    private static class MyThread extends Thread {

        public static String a = "1";
        public String b = "1";
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "\t MyThread invoke run() ...");
        }

        public static void add(){
            System.out.println("MyThread invoke add() ...");
        }

        public void add2(){
            System.out.println("MyThread invoke add2() ...");
        }
    }

    public static void main(String[] args) {
        new MyThread().run();
        new MyThread().start();
    }
}

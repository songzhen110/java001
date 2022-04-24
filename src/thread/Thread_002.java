package thread;

/***
 * 筷子
 */
class Chopstick {
}

/***
 * 哲学家
 */
class Philosopher extends Thread {

    private Chopstick left,right;
    private int index;

    public Philosopher(Chopstick left, Chopstick right, int index) {
        this.left = left;
        this.right = right;
        this.index = index;
    }

    @Override
    public void run() {
        if(index%2==0){
            synchronized (left){
                try {
                    Thread.sleep(1 + index);
                } catch (InterruptedException e) {

                }

                synchronized (right){
                    System.out.println("科学家 \t" + index + "吃到饭");
                }
            }
        } else {
            synchronized (right){
                try {
                    Thread.sleep(1 + index);
                } catch (InterruptedException e) {

                }

                synchronized (left){
                    System.out.println("科学家 \t" + index + "吃到饭");
                }
            }
        }

    }
}

public class Thread_002 {

    public static void main(String[] args) {

        Chopstick chopstick0 = new Chopstick();
        Chopstick chopstick1 = new Chopstick();
        Chopstick chopstick2 = new Chopstick();
        Chopstick chopstick3 = new Chopstick();
        Chopstick chopstick4 = new Chopstick();


        new Philosopher(chopstick1,chopstick0,0).start();
        new Philosopher(chopstick2,chopstick1,1).start();
        new Philosopher(chopstick3,chopstick2,2).start();
        new Philosopher(chopstick4,chopstick3,3).start();
        new Philosopher(chopstick0,chopstick4,4).start();


    }

}

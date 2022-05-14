package thread;

/***
 * ThreadLocal线程本地变量
 *
 * Thread ThreadLocal TheadLocalMap 对应关系
 * 一个线程实例会有一个 TheadLocalMap实例,一个TheadLocalMap实例可以存储多个ThreadLocal
 *
 * 当使用ThreadLocal存储数据时，会被放入当前线程对应的ThreadLocalMap内的Entry[]，Entry结构(ThreadLocal<?> k, Object v)
 *
 * 当使用线程池的时候会出现内存溢出
 * 原因：线程池内的线程不消失的话，该线程的ThreadLocalMap也会存在，
 * 但代码已经执行过ThreadLocal所在类的方法，而且ThreadLocal所在类实例可能已经被释放，
 * ThreadLocal一回收，导致Entry(extends WeakReference<ThreadLocal<?>>)的key为null,但value却还在，
 * 而无法回收掉（因为没有了ThreadLocal实例）
 * 随着该线程不断的执行，会造成越来越多这样的value，最终内存溢出
 */
public class Thread_004 {

    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("AAA");
        System.out.println(threadLocal.get());
        threadLocal.remove();
    }
}

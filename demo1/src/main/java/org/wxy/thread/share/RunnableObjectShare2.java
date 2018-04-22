package org.wxy.thread.share;

/**
 * 多个线程访问共享对象和数据的方式:
 * 每个线程执行的代码不同（方案2）
 * 将这些Runnable对象作为某一个类中的内部类，共享数据作为这个外部类中的成员变量，
 * 每个线程对共享数据的操作方法也分配给外部类，以便实现对共享数据进行的各个操作的互斥和通信，
 * 作为内部类的各个Runnable对象调用外部类的这些方法。
 */
public class RunnableObjectShare2 { //多线程卖票，2个加，2个减

    private int j;

    public static void main(String args[]) {
        RunnableObjectShare2 tt = new RunnableObjectShare2();
        Inc inc = tt.new Inc();
        Dec dec = tt.new Dec();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(inc);
            t.start();
            t = new Thread(dec);
            t.start();
        }
    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }

    class Inc implements Runnable {      //线程1
        public void run() {
            for (int i = 0; i < 5; i++) {
                inc();
            }
        }
    }

    class Dec implements Runnable {      //线程2
        public void run() {
            for (int i = 0; i < 5; i++) {
                dec();
            }
        }
    }
}

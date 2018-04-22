package org.wxy.thread.share;

/**
 * 多个线程访问共享对象和数据的方式:
 *  如果每个线程执行的代码相同，可以使用同一个Runnable对象，这个Runnable对象属性可以用于共享数据。
 */
public class RunnableShare {

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        SellTicket mt = new SellTicket();  //实例化Runnable子类对象
        Thread t1 = new Thread(mt,"t1"); // 实例化Thread类对象
        Thread t2 = new Thread(mt, "t2"); // 实例化Thread类对象
        Thread t3 = new Thread(mt, "t3"); // 实例化Thread类对象
        t1.start(); // 启动线程
        t2.start(); // 启动线程
        t3.start(); // 启动线程
    }

}

class SellTicket implements Runnable {
    private int ticket = 100;

    public void run() {                             //覆写Thread类中的run()方法
        // TODO 自动生成的方法存根
        for (int i = 0; i < 10; i++) {
            synchronized (this) {                   //设置需要同步的操作
                System.out.println("当前线程：" + Thread.currentThread().getName());
                if (ticket > 0) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("卖票：ticket=" + --ticket);
                }
            }
//          this.sale();                                                //调用同步方法
        }
    }

}
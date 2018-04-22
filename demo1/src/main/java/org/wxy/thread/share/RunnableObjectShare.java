package org.wxy.thread.share;

/**
 * 多个线程访问共享对象和数据的方式:
 *  每个线程执行的代码不同（方案1）
 *  将共享数据封装在另外一个对象中，然后将这个对象逐一传递给各个Runnable对象。
 *   每个线程对共享数据的操作方法也分配到那个对象身上去完成，这样容易实现针对该数据进行的各个操作的互斥和通信。
 */
public class RunnableObjectShare { //多线程卖票，一个加，一个减

    public static void main(String[] args) {
        ShareData data2 = new ShareData();
        new Thread(new MyRunnable1(data2), "线程1").start();
        new Thread(new MyRunnable2(data2), "线程2").start();

//        final ShareData1 data1 = new ShareData1();
//        new Thread(new Runnable() {
//            public void run() {
//                data1.decrement();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                data1.increment();
//            }
//        }).start();

    }

}

class MyRunnable1 implements Runnable {      //线程1
    private ShareData data1;

    public MyRunnable1(ShareData data1) {
        this.data1 = data1;
    }

    public void run() {
        System.out.println("MyRunnable1:" + data1.toString());
        System.out.println("MyRunnable1线程启动...");
        while (data1.getJ() !=  0) {
            data1.decrement();
        }
        System.out.println("MyRunnable1线程结束。");
    }
}

class MyRunnable2 implements Runnable {      //线程2
    private ShareData data1;

    public MyRunnable2(ShareData data1) {
        this.data1 = data1;
    }

    public void run() {
        System.out.println("MyRunnable2:" + data1.toString());
        System.out.println("MyRunnable2线程启动...");
        while (data1.getJ() !=  200) {
            data1.increment();
        }
        System.out.println("MyRunnable2线程结束。");
    }
}

class ShareData {  //共享对象
    private int j = 100;

    public int getJ() {
        return this.j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    /**
     * +
     */
    public synchronized void increment() {
        j++;
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("加1：" + j);
        Thread.yield();  // 出让当前的线程的CPU时间片
    }

    /**
     * -
     */
    public synchronized void decrement() {
        j--;
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("减1：" + j);
        Thread.yield();  // 出让当前的线程的CPU时间片
    }
}

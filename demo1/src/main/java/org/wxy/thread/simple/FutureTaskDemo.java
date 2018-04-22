package org.wxy.thread.simple;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 一个有返回结果的线程,主线程在取结果的时候会阻塞<br>
 *     其它线程超时
 *
 * @author Administrator
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        System.out.println("主线程 "+ Thread.currentThread() + "开始");
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                return operation();
            }
        });
        System.out.println("futureTask开始执行计算:" + System.currentTimeMillis());
        Thread td = new Thread(futureTask);
        td.start();

        System.out.println("主线程可以做些其他事情:" + System.currentTimeMillis());
        System.out.println("主线程 happy中...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // futureTask的get方法会阻塞，直到可以取得结果为止
            String result = futureTask.get(2000, TimeUnit.MILLISECONDS); // 线程超时时间
            System.out.println("主线程得到计算的结果是:" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException timeout) {
            System.out.println("线程超时!");
        }
        System.out.println("主线程 "+ Thread.currentThread() + "结束");
    }

    public static String operation() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "数据转换完成！";
    }
}


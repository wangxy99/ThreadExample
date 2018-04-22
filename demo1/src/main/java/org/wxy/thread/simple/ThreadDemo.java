package org.wxy.thread.simple;

/**
 * 一个简单的Thread线程
 */
public class ThreadDemo {
	private String prtNo;

	public ThreadDemo(String prtNo) {
		this.prtNo = prtNo;
	}

	public void send() {
		// 新线程处理发送任务
		new Thread() {
			public void run() {
				System.out.println("上传中...");
				try {
					Thread.sleep(5000);
					System.out.println("prtNo=" + prtNo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("上传完成!");
			}
		}.start();
	}

	public static void main(String[] args) {
		String prtNo = "1001200912310155555";
		System.out.println("发送前操作....");
		// 调用异步发送短信
		ThreadDemo sendAsync = new ThreadDemo(prtNo);
		sendAsync.send();

		System.out.println("发送后操作....");

	}

}
//		发送前操作....
//		发送后操作....
//		上传中...
//		prtNo=1001200912310155555
//		上传完成!
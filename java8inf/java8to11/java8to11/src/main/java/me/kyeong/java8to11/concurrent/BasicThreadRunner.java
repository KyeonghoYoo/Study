package me.kyeong.java8to11.concurrent;

public class BasicThreadRunner {

	public static void main(String[] args) throws InterruptedException {
		Thread helloThread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					System.out.println("interrupted!");
					return; // InterruptedException이 발생해도 쓰레드 자체가 종료되는 것이 아니다.
				}

				System.out.println("HelloThread: " + Thread.currentThread().getName());
			}
		});

		MyThread myThread = new MyThread();

		helloThread.start();
		myThread.start();


		myThread.join(); // 해당 Thread가 종료되는 것을 기다린다.
		System.out.println(myThread + " is finished");
		
		System.out.println("MainThread: " + Thread.currentThread().getName()); // main Thread
		Thread.sleep(3000L);
		helloThread.interrupt(); // interrupt 발생
		
	}

	static class MyThread extends Thread {

		@Override
		public void run() {
			System.out.println("MyThread: " + Thread.currentThread().getName());
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

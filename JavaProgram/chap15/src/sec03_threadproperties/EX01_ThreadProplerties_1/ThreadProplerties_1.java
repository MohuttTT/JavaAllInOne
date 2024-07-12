package sec03_threadproperties.EX01_ThreadProplerties_1;

public class ThreadProplerties_1 {

	public static void main(String[] args) {
	
		// 현재 실행 중인 스레드 객체 참조 및 스레드 개수 가져오기.
		Thread curThread = Thread.currentThread();
		System.out.println("현재 쓰레드의 이름 = " + curThread.getName());
		System.out.println("동작하는 쓰레드의 개수 = " + Thread.activeCount());

		// 쓰레드 이름 자동 지정
		for(int i = 0; i < 3; i++) {
			Thread thread = new Thread();
			System.out.println(thread.getName());
			thread.start();
		}
		
		// 쓰레드 이름 직접 지정 - setName()
		for(int i = 0; i < 3; i++) {
			Thread thread = new Thread();
			thread.setName(i + "번째 스레드");
			System.out.println(thread.getName());
			thread.start();
		}
		
		// 쓰레드의 개수 가져오기
		System.out.println("동작하는 쓰레드의 개수 : " + Thread.activeCount());
		
		
		for(int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					int i = 0;
					while(true) {
						if(i==1)
							break;
						i++;
						System.out.println("현재 실행 중인 스레드 객체 이름 : " + Thread.currentThread().getName());
						System.out.println("실행 중인 스레드 수 : " + Thread.activeCount());
					}
				}
			});
			
			thread.setName("스레드-" + i);
			thread.start();
		}
	}

}

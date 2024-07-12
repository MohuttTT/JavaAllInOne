public class Counter {

    private int count = 0;
    private final Object lock = new Object();

    public void performTask() {
        // 동기화되지 않은 코드
        System.out.println(Thread.currentThread().getName() + ": Starting task");

        // 동기화 블록
        synchronized (lock) {
            count++;
            System.out.println(Thread.currentThread().getName() + ": Incremented count to " + count);
        }

        // 동기화되지 않은 코드
        System.out.println(Thread.currentThread().getName() + ": Ending task");
    }

    public static void main(String[] args) {
        
        Counter counter = new Counter();
        
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                counter.performTask();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                counter.performTask();
            }
        });
        
        t1.start();
        t2.start();
        
        try { // t1과 t2 스레드가 종료될 때까지 main 스레드 대기 - .join
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }
        
        // t1, t2 스레드 종료 후 실행되는 main 스레드 코드
        System.out.println("Final count : " + counter);
    }

}
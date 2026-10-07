public class Deadlock {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) throws Exception {

        System.out.println("DEADLOCK EXAMPLE:");

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 locked Lock 1");

                synchronized (lock2) {
                    System.out.println("Thread 1 locked Lock 2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 locked Lock 2");

                synchronized (lock1) {
                    System.out.println("Thread 2 locked Lock 1");
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("\nFixed Version:");

        Thread thread3 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 3 locked Lock 1");

                synchronized (lock2) {
                    System.out.println("Thread 3 locked Lock 2");
                }
            }
        });

        Thread thread4 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 4 locked Lock 1");

                synchronized (lock2) {
                    System.out.println("Thread 4 locked Lock 2");
                }
            }
        });

        thread3.start();
        thread4.start();

        thread3.join();
        thread4.join();

        System.out.println("Program completed.");
    }
}
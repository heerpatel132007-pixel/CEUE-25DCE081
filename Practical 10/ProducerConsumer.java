import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    Queue<Integer> numbers = new LinkedList<>();
    int size = 3;

    synchronized void produce(int number) throws InterruptedException {
        while (numbers.size() == size) {
            wait();
        }

        numbers.add(number);
        System.out.println("Produced: " + number);

        notify();
    }

    synchronized int consume() throws InterruptedException {
        while (numbers.isEmpty()) {
            wait();
        }

        int number = numbers.remove();
        System.out.println("Consumed: " + number);

        notify();
        return number;
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws Exception {

        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce(i);
                }
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted");
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.consume();
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted");
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items processed.");
    }
}
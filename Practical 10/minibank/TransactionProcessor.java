package minibank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransactionProcessor {

    ExecutorService pool =
            Executors.newFixedThreadPool(4);

    void submit(Runnable task) {
        pool.execute(task);
    }

    void stop() throws InterruptedException {
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
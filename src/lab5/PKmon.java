package lab5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PKmon {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int M = 100;
        int consum = 1;
        int producer = 1;
        Buffer buf = new Buffer(M);
        ExecutorService service = Executors.newFixedThreadPool(consum+producer);

        for (int i =1; i<=consum; i++) service.submit(new Producer(buf));
        for (int i = 1; i<=producer;i++) service.submit(new Consumer(buf));
        long time = System.currentTimeMillis() - start;
        System.out.println("czas: " + time);

        service.shutdown();
    }
}
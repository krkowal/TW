package lab6;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 1000;
        ElementList elementList = new ElementList();
        try{
            PrintWriter results = new PrintWriter("src/lab6/result2.txt", StandardCharsets.UTF_8);
            for(int j = 0; j<numberOfThreads;j++){
                List<Thread> threads = new ArrayList<>();
                for(int i =0;i<j;i++){
                    threads.add(new Thread(new Operations(elementList)));
                }
                long startTime = System.nanoTime();
                for(Thread thread : threads){
                    thread.start();
                    thread.join();
                }
                long stopTime = System.nanoTime();
                results.print(j+" ");
                results.println(TimeUnit.SECONDS.convert(stopTime-startTime,TimeUnit.MILLISECONDS));
            }
            results.close();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

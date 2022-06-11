package lab6;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 1000;
        ElementList elementList = new ElementList();
        try{
            PrintWriter results = new PrintWriter("src/lab6/result.txt","UTF-8");
            for(int j = 0; j<numberOfThreads;j++){
                List<Thread> threads = new ArrayList<>();
                for(int i =0;i<j;i++){
                    threads.add(new Thread(new SingleOperation(elementList)));
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

        } catch (FileNotFoundException | UnsupportedEncodingException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

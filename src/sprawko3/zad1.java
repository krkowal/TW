package sprawko3;

// PKmon.java

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Producer extends Thread {
    private Buffer buf;
    public Producer(Buffer buf){
        this.buf = buf;
    }
    public void run() {
        for (int i = 0; i < 100; ++i) {
            buf.put(i);
            try{
                sleep((int)(Math.random()*100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Consumer extends Thread {
    private Buffer buf;
    public Consumer(Buffer buf){
        this.buf = buf;
    }


    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println(buf.get());
            try{
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Buffer {
    private ArrayList<Integer> arr = new ArrayList<>();
    private int i;

    public Buffer(int i) {
        this.i = i;
    }

    public synchronized void put(int i) {
        while(arr.size()>=i){
            try{
                System.out.println("producer waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("producer puts");
        arr.add(i);
        notify();
    }

    public synchronized int get() {
        while (arr.size()==0){
            try{
                System.out.println("consumer waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int ind = new Random().nextInt(arr.size());
        int item = arr.get(ind);
        arr.remove(item);
        notify();
        System.out.println("consumer is getting"+item);
        return item;
    }
}

class PKmon {
    public static void main(String[] args) {
        Buffer buf = new Buffer(100);
        int n1 = 1;
        int n2 = 1;

        int prod = 100;
        int cons = 100;


        ExecutorService service = Executors.newFixedThreadPool(n1+n2);
        for(int i = 0;i<n1;i++) service.submit(new Producer(buf));
        for(int i=0;i<=n2;i++) service.submit(new Consumer(buf));
    }
}
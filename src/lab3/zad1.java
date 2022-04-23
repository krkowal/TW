package sprawko3;

// PKmon.java

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Producer extends Thread {
    private Buffer buf;
    private int i;
    public Producer(Buffer buf, int i){
        this.buf = buf;
        this.i = i;
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
    private int i;
    public Consumer(Buffer buf, int i ){
        this.buf = buf;
        this.i =i;
    }


    public void run() {
        for (int j = 0; j < i; ++j) {
            buf.get();
            try{
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Buffer {
    private final ArrayList<Integer> arr = new ArrayList<>();
    private final int i;

    public Buffer(int i) {
        this.i = i;
    }

    public synchronized void put(int i) {
        while(arr.size()>=this.i){
            try{
                System.out.println("producer waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("producer puts " + i);
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
        System.out.println("consumer is getting "+item);
        return item;
    }
}

class Main {
    public static void main(String[] args) {
        Buffer buf = new Buffer(20);
        int n1 = 4;
        int n2 =2;

        int prod = 25;
        int cons = 50;


        ExecutorService service = Executors.newFixedThreadPool(n1+n2);
        for(int i = 0;i<n1;i++) service.submit(new Producer(buf,prod));
        for(int i=0;i<=n2;i++) service.submit(new Consumer(buf,cons));
        service.shutdown();
    }
}
class Semaphore{
    private boolean stan=true;
    private int czeka = 0;

    public synchronized void P(){
        this.czeka++;
        while(!stan){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        czeka--;
        stan=false;
    }
    public synchronized void V(){
        if(czeka>0){
            this.notify();
        }
        stan=true;
    }
}
class SemaphoreProducer extends Thread {
    private Buffer buf;
    private int i;
    private Semaphore s;
    public SemaphoreProducer(Buffer buf, int i, Semaphore s){
        this.buf = buf;
        this.i = i;
        this.s= s;
    }
    public void run() {
        for (int i = 0; i < 100; ++i) {
            buf.put(i);
            try{
                s.P();
                sleep((int)(Math.random()*100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally{
                s.V();
            }
        }
    }

}

class SemaphoreConsumer extends Thread {
    private Buffer buf;
    private int i;
    private Semaphore s;
    public SemaphoreConsumer(Buffer buf, int i, Semaphore s ){
        this.buf = buf;
        this.i =i;
        this.s=s;
    }


    public void run() {
        for (int j = 0; j < i; ++j) {
            buf.get();
            try{
                s.P();
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                s.V();
            }
        }
    }
}
class SemaphoreMain {
    public static void main(String[] args) {
        Semaphore s = new Semaphore();
        Buffer buf = new Buffer(20);
        int n1 = 4;
        int n2 =4;

        int prod = 25;
        int cons = 25;


        ExecutorService service = Executors.newFixedThreadPool(n1+n2);
        for(int i = 0;i<n1;i++) service.submit(new SemaphoreProducer(buf,prod,s));
        for(int i=0;i<=n2;i++) service.submit(new SemaphoreConsumer(buf,cons,s));
        service.shutdown();
    }
}
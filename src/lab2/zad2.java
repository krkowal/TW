package lab2;

import java.util.HashMap;

public class zad2 {
    public static void main(String[] args) {
        HashMap<Integer,Integer> map = new HashMap();
        for(int i=0;i<1000;i++) {
            Semaphore semaphore = new Semaphore();
            Counter counter = new Counter(semaphore);
            IncrementalThread iThread = new IncrementalThread(counter, semaphore);
            DecrementalThread dThread = new DecrementalThread(counter, semaphore);

            iThread.start();
            dThread.start();

            try {
                iThread.join();
                dThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            System.out.println("val = " + counter.getCount());
            if(map.containsKey(counter.getCount())){
                map.put(counter.getCount(),map.get(counter.getCount())+1);
            }
            else{
                map.put(counter.getCount(),1);
            }
        }
        System.out.println(map);
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
class Counter{
    private int count=0;
    private Semaphore semaphore;

    public Counter(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
    public void increment (){
        count++;
    }
    public void decrement(){
        count--;
    }

    public int getCount() {
        return count;
    }
}
class IncrementalThread extends Thread{
    private Counter c;
    private Semaphore semaphore;

    public IncrementalThread(Counter c,Semaphore s){
        this.c=c;
        this.semaphore=s;
    }

    public void run(){
        for (int i=0;i<1000000;i++){
            try{
                this.semaphore.P();
                c.increment();
            } finally {
                this.semaphore.V();
            }
        }
    }
}
class DecrementalThread extends Thread{
    private Counter c;
    private Semaphore semaphore;

    public DecrementalThread(Counter c,Semaphore s){
        this.c=c;
        this.semaphore=s;
    }

    public void run(){
        for (int i=0;i<1000000;i++){
            try{
                this.semaphore.P();
                c.decrement();
            } finally {
                this.semaphore.V();
            }
        }
    }
}

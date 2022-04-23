package lab1;

import java.util.HashMap;

public class zad1 {
    public static void main(String[] args) throws InterruptedException {
        HashMap<Integer,Integer> map = new HashMap();
        for(int i =0;i<1000;i++) {
            Counter c = new Counter();
            IncrementalThread t1 = new IncrementalThread(c);
            DecrementalThread t2 = new DecrementalThread(c);
            t1.start();
//            while(c.getCount()!=10000){
//                System.out.println("pierwszy while");
//            }
            t2.start();
//            while(c.getCount()!=0){
//                System.out.println("drugi while");
//            }
            t1.join();
            t2.join();
            if(map.containsKey(c.getCount())){
                map.put(c.getCount(),map.get(c.getCount())+1);
            }
            else{
                map.put(c.getCount(),1);
            }
        }
        System.out.println(map);
    }
}

class Counter{
    private int count=0;
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
    private Counter counter;
    public IncrementalThread(Counter counter){
        this.counter=counter;
    }
    public void run(){
        for(int i=0;i<10000;i++){
            counter.increment();
        }
    }
}

class DecrementalThread extends Thread{
    private Counter counter;
    public DecrementalThread(Counter counter){
        this.counter=counter;
    }
    public void run(){
        for(int i=0;i<10000;i++){
            counter.decrement();
        }
    }
}
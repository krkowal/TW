package sprawko3;

// PKmon.java

import java.util.ArrayList;
import java.util.Random;

class Producer extends Thread {
    private Buffer _buf;

    public void run() {
        for (int i = 0; i < 100; ++i) {
            _buf.put(i);
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
                sleep((int) (Math.random() * 100))
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
        int item = arr.get(ind)
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
        int n2 = 2;

        int prod = 100;
        int cons = 100;
    }
}
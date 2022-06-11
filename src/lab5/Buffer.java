package lab5;

import java.util.ArrayList;
import java.util.Random;

class Buffer {
    private int size;
    private ArrayList<Integer> buf = new ArrayList<>();

    public Buffer(int size){
        this.size = 2*size;
    }
    public synchronized void put(int[] a) {
        while(size - buf.size() < a.length){
            try{
                System.out.println("producer waits");
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        for (int i : a) {
            buf.add(i);
            System.out.println("producer puts " + i);
        }
        notify();
    }
    public synchronized void get(int a) {
        while(buf.size() <a){
            try{
                System.out.println("consumer waits");
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
       	int val = 0;
        for (int i=0;i<a;i++){
            int index  =new Random().nextInt(buf.size());
            val = buf.get(index);
            buf.remove(index);
            notify();
            System.out.println("consumer gets "+val);
        }
    }
}
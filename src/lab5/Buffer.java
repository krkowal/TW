package lab5;

import java.util.ArrayList;

class Buffer {
    private int size;
    private ArrayList<Integer> buf = new ArrayList<>();

    public Buffer(int size){
        this.size = size;
    }
    public synchronized void put(int i) {
        while(size - buf.size() < i){
            try{
                System.out.println("producer waits");
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        for (int j= 0;j<i;j++){
            buf.add()
        }
    }
    public synchronized int get(int a) {
        while(_buf.size() == 0){
            try{
                wait();
            }catch(InterruptedException e){}
        }
       	…
        return value;
    }
}

package lab5;

class Consumer extends Thread {
    private Buffer buf;
    public Consumer(Buffer buffer){
        this.buf = buffer;
    }
    public void run() {
        while(true){
            buf.get((int)(Math.random()*10+1));
            try{
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
package lab5;

class Converter extends Thread{
    private Buffer previous;
    private Buffer next;
       …
    public Converter(Buffer previous, Buffer next, …){
        this.previous = previous;
        this.next = next;
       …
    }
    public void run(){
       …
        int tmp = previous.get();
        try{
            sleep(10);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        next.put(tmp);
    }
}
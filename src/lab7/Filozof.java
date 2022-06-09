package lab7;

public class Filozof extends Thread{
    private int _licznik = 0;
    private int num;
    private Lokaj lokaj;

    public Filozof(int num, Lokaj lokaj){
        this.num= num;
        this.lokaj = lokaj;
    }

    public void run(){
        long startTime = System.currentTimeMillis();
        int lewyWidelec = ((num==0)? 4 : num-1);
        int prawyWidelec = ((num==0)? 4: num+1);

        while (true){
            lokaj.podajWidelce(lewyWidelec,prawyWidelec);
            _licznik++;
            lokaj.zabierzWidelce(lewyWidelec,prawyWidelec);
            if(_licznik%100==0){
                System.out.println("Filozof: "+Thread.currentThread() + "jadłem "+_licznik+" razy czas: "+(System.currentTimeMillis()-startTime));
                break;
            }
        }
    }
}

package lab7;

import java.util.List;

public class Filozof extends Thread{
    private int _licznik = 0;
    private int num;
    private List<Widelec> widelce;
    private final Object lockObject;

    public Filozof(int num, List<Widelec> widelce, Object lock){
        lockObject = lock;
        this.num= num;
        this.widelce = widelce;
    }

    public void run(){
        long startTime = System.currentTimeMillis();
        int lewyWidelec = ((num==0)? 4 : num-1);
        int prawyWidelec = ((num==0)? 4: num+1);

        while (true){

            synchronized (lockObject){
                widelce.get(lewyWidelec).podnies();
                widelce.get(prawyWidelec).podnies();
            }

            _licznik++;
            widelce.get(lewyWidelec).odloz();
            widelce.get(prawyWidelec).odloz();

            if(_licznik%100==0){
                System.out.println("Filozof: "+Thread.currentThread() + "jadłem "+_licznik+" razy czas: "+(System.currentTimeMillis()-startTime));
                break;
            }
        }
    }
}

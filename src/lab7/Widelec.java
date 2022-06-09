package lab7;

public class Widelec {
    private boolean state;

    public Widelec(){
        this.state = true;
    }

    public synchronized void podnies(){
        while(!state){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        state= false;
    }
    public synchronized odloz(){
        state=true;
        notifyAll();
    }

    public boolean zajety(){
        return state;
    }
}

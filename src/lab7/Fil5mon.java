package lab7;

import java.util.ArrayList;

public class Fil5mon {
    public static void main(String[] args) {
        ArrayList<Widelec> widelce = new ArrayList<>();
        Filozof[] filozofs = new Filozof[5];

        Object lockA = new Object();

        for(int i = 0; i<5;i++){
            widelce.add(new Widelec());
            filozofs[i] = new Filozof(i,widelce,lockA);
        }

        for(int i =0; i<5;i++){
            filozofs[i].start();
        }

        for(int i =0;i<5;i++){
            try{
                filozofs[i].join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

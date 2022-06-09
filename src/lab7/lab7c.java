package lab7;

public class lab7c {
    public static void main(String[] args) {
        Filozof[] f = new Filozof[5];
        Lokaj lokaj = new Lokaj();

        for (int i=0;i<5;i++){
            f[i] = new Filozof(i,lokaj);
        }
        for(int i = 0; i<5;i++){
            f[i].start();
        }

        for(int i =0; i<5;i++){
            try{
                f[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

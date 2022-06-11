package lab6;

public class Operations implements Runnable{

    private final ElementList elementList;

    public Operations(ElementList elementList) {
        this.elementList = elementList;
    }

    @Override
    public void run() {
        Object[] objectList = new Object[5];
        for(int i=0;i<5;i++){
            objectList[i] = new Object();
            elementList.add(objectList[i]);
        }
        for(int i=0;i<5;i++){
            elementList.contains(objectList[i]);
        }
        for(int i=0;i<5;i++){
            elementList.remove(objectList[i]);
        }
    }
}

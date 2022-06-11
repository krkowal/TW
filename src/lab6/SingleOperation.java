package lab6;

public class SingleOperation implements Runnable{

    private ElementList elementList;

    public SingleOperation(ElementList elementList) {
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

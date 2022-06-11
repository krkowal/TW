package lab6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Element {
    private Element nextElement;
    private final Object object;
    private final int value;
    private final Lock lock = new ReentrantLock();

    public Element(Object object, int value) {
        this.nextElement = null;
        this.object = object;
        this.value = value;
    }

    public void setNextElement(Element nextElement){
        this.nextElement=nextElement;
    }

    public Element getNextElement() {
        return nextElement;
    }

    public Object getObject() {
        return object;
    }

    public void lock(){
        this.lock.lock();
    }

    public void unlock(){
        this.lock.unlock();
    }
}

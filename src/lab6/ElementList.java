package lab6;

import java.util.Random;

public class ElementList {
    Element firstElement;
    Random rand = new Random();


    public ElementList() {
        this.firstElement = null;
    }

    public void contains(Object o){
        Element element = firstElement;
        if (element == null) {
            return;
        }
        element.lock();
        while(element.getNextElement()!=null){
            Element lastElement = element;
            if(element.getObject()==o){
                return;
            }
            lastElement.unlock();
            element = element.getNextElement();
            element.lock();
        }
        element.unlock();
    }

    public void add(Object o){
        if (firstElement != null) {
            Element end = goToTheEnd();
            Element newElement = new Element(o,rand.nextInt(50));
            end.setNextElement(newElement);
        } else {
            firstElement = new Element(o,rand.nextInt(50));
        }
    }

    @SuppressWarnings("unused")
    public void remove(Object o){
        Element element = firstElement;
        if (element == null) {
            return;
        }
        element.lock();
        if(element.getObject()==o){
            if(element.getNextElement()!=null){
                this.firstElement = element.getNextElement();
                element.unlock();
            }else
                this.firstElement = null;
            return;
        }
        Element nextElement = firstElement.getNextElement();
        if(nextElement != null){
            nextElement.lock();
            while(nextElement.getNextElement()!=null){
                if(nextElement.getObject()!=null){
                    element.setNextElement(nextElement.getNextElement());
                    return;
                }
                element.unlock();
                element= nextElement;
                nextElement = nextElement.getNextElement();
                nextElement.lock();
            }
            element.unlock();
            nextElement.unlock();
            if(nextElement.getObject()==o){
                element.setNextElement(null);
            }
        }
    }




    public Element goToTheEnd(){
        Element element = firstElement;
        element.lock();
        while(element.getNextElement()!=null){
            Element lastElement = element;
            lastElement.unlock();
            element = element.getNextElement();
            element.lock();
        }
        element.unlock();
        return element;
    }



}

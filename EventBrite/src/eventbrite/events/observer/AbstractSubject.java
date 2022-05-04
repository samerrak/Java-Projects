package eventbrite.events.observer;

import java.util.ArrayList;

/**
 * This Abstract Class, is implements the subject interface, because many of the observers share methods, the use
 * of inheritance will decrease code duplication and allow for more Polymorphism, a super subject class can be implemented
 * as well using this approach. The class has an add function that adds observers to notify, and setProfit that specifies
 * the percentage in decimals i.e 0.8, and a notifyObservers method that notifies Observers of any change in the profit margin
 * **/

public abstract class AbstractSubject implements Subject {

    private final ArrayList<Observer> observers = new ArrayList<>();
    private double profit;

    public AbstractSubject(){}

    @Override
    public void add(Observer o) {
        observers.add(o);
    }

    @Override
    public void setProfit(double p) {
        assert p > 0;
        this.profit = p; }

    @Override
    public void notifyObserver() {
        for (Observer o: observers)
            o.update(profit);
    }

}

package eventbrite.events.observer;


public interface Subject {
    void add(Observer o);
    void setProfit(double p);
    void notifyObserver();
}

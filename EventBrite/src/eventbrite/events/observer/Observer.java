package eventbrite.events.observer;

/**
 * The Observer Design Pattern is implemented specifically the Push Model. The subject bob will send the observer
 * the information it needs, which will allow the observer (events) to not query bob for information.
 * **/

public interface Observer {
    /**
     * The Observer Classes, are the events, and they are updated by the Subject aka Bob, by utilizing the
     * Observer Design patter, the EventManager can utilize the push model where it sends the observer, the events
     * if the price was updated.
     * **/

    void update(double profit);
}

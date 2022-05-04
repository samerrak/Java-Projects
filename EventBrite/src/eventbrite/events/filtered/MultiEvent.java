package eventbrite.events.filtered;

import eventbrite.events.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 The main motivation behind this class, is that it allows the developer in the future the option to utilize the
 Decorator Pattern by adding specified behaviors or adding a function before getting the prefiltered events,
 That is why this class is here i.e. if a dev wanted to print or visit he could add a Decorator Class
 it also provides FilterResult with the default functions defined below
 **/

public abstract class MultiEvent{

    private final ArrayList<Event> aFilteredEvents = new ArrayList<>();

    MultiEvent(List<Event> events){
        aFilteredEvents.addAll(events);
    }

    public ArrayList<Event> getaFilteredEvents() {
        return aFilteredEvents;
    }
}

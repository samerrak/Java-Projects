package eventbrite.events.observer;

import eventbrite.events.events.Concert;

/**
 * The ConcertSubject Class, inherits all the methods of the superclass but checks if the observer added is an
 * instance of Concert before adding
 **/

public class ConcertSubject extends AbstractSubject{

    @Override
    public void add(Observer o) {
        assert o instanceof Concert;
        super.add(o);
    }

}

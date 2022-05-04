package eventbrite.events.observer;

import eventbrite.events.events.Screenings;

/**
 * The ScreeningSubject Class, inherits all the methods of the superclass but checks if the observer added is an
 * instance of Screening before adding
 **/

public class ScreeningSubject extends AbstractSubject{

    @Override
    public void add(Observer o) {
        assert o instanceof Screenings;
        super.add(o);
    }

}

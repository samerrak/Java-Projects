package eventbrite.events.observer;
import eventbrite.events.events.Workshops;

/**
 * The WorkshopSubject Class, inherits all the methods of the superclass but checks if the observer added is an
 * instance of Workshop before adding
 **/

public class WorkshopSubject extends AbstractSubject{

    @Override
    public void add(Observer o) {
        assert o instanceof Workshops;
        super.add(o);
    }

}

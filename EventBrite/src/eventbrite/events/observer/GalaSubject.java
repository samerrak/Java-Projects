package eventbrite.events.observer;
import eventbrite.events.events.Galas;


/**
 * The GalaSubject Class, inherits all the methods of the superclass but checks if the observer added is an
 * instance of Gala before adding
 **/

public class GalaSubject extends AbstractSubject{

    @Override
    public void add(Observer o) {
        assert o instanceof Galas;
        super.add(o);
    }


}

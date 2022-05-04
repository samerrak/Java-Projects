package eventbrite.events.filtered.predicates;

import eventbrite.events.events.Event;

import java.util.function.Predicate;

public class VipFilter implements Predicate<Event> {

    /**
     The interface predicate allows us to use a combination of lambda expressions and other functions belonging to list
     to filter out the results that do not match our conditions. Here the dev filters out the results based on the range of
     the price that user has specified, The predicate has no argument
     and tests for classes with types concert and galas, by overriding the test() method.
     */

    public VipFilter()
    {
    }

    @Override
    public boolean test(Event event) {
        return !(event.getClass().getSimpleName().equals("Concert") || event.getClass().getSimpleName().equals("Galas"));
    }

}

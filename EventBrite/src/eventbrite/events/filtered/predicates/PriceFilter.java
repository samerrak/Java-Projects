package eventbrite.events.filtered.predicates;

import eventbrite.events.events.Event;

import java.util.function.Predicate;

public class PriceFilter implements Predicate<Event> {
    /**
     The min and max are final as a design choice to improve encapsulation but to update them simply add generate an upDate min or max
     method to update them
     */

    private final double min;
    private final double max;

    /**
     The interface predicate allows us to use a combination of lambda expressions and other functions belonging to list
     to filter out the results that do not match our conditions. Here the dev filters out the results based on the range of
     the price that user has specified, The predicate LocationFilter takes one argument
     and tests for the equality of location, by overriding the test() method.
     */

    public PriceFilter(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean test(Event event) {
        return !(event.getPrice() >= min && event.getPrice() <= max);
    }



}

package eventbrite.events.filtered.predicates;

import eventbrite.Location;
import eventbrite.events.events.Event;

import java.util.Optional;
import java.util.function.Predicate;

public class LocationFilter implements Predicate<Event> {

    /**
     The location is final as a design choice to improve encapsulation but to update it simply add generate an upDateLocation
     method to update it
     */

    private final Optional<Location> location;

    /**
     The interface predicate allows us to use a combination of lambda expressions and other functions belonging to list
     to filter out the results that do not match our conditions. Here the dev filters out the results where the location
     does not match this design allows us to couple conditions very efficiently as well with the smallest amounts of code.
     The predicate LocationFilter takes one argument and tests for the equality of location, by overriding the test() method
     */

    public LocationFilter(Optional<Location> location){
        assert location.isPresent();
        this.location = location;
    }

    @Override
    public boolean test(Event event) {
        return !(event.getLocation().equals(location));
    }

}

package eventbrite.events.filtered;


import eventbrite.events.events.Event;

import java.util.function.Predicate;

/**
 The interface segregation principle here is utilized to decouple the code, it allows the client code to not forced to
 depend on interfaces it does need
 **/

public interface Filterable {

    FilterResult filter(Predicate<Event>... predicates);
}

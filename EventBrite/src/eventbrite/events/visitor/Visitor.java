package eventbrite.events.visitor;

import eventbrite.events.filtered.FilterResult;
import eventbrite.events.events.*;

/**
 * The Visitor Design Pattern is implemented here as it allows the developer to preform the same action of many objects without
 * changing any of the classes constructors or methods and hence increases code reusability.
 **/


public interface Visitor {
    /**
     * The Visitor interface declares all the visit() operations for all the classes that implement Visitable.
     **/
    double visit(AbstractEvent event);
    double visit(FilterResult filterResult);




}

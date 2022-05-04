package eventbrite.events.visitor;

import eventbrite.events.events.AbstractEvent;
import eventbrite.events.events.Event;
import eventbrite.events.filtered.FilterResult;


/**
 * The ProfitVisitor is a Concrete Visitor that calculates the profit based on the percentage set by the Subject,
 * and thus it implements all the visit functions to specify how the operation of profit calculation should be done.
 **/

public class ProfitVisitor implements Visitor{
    private double profit = 0;

    public ProfitVisitor(){ }

    @Override
    public double visit(AbstractEvent event) {
        profit = event.getProfitPercentage() * event.getPrice();
        return profit;
    }

    @Override
    public double visit(FilterResult filterResult) {
        for (Event e: filterResult.getaFilteredEvents())
           profit += visit((AbstractEvent) e);

        return profit;
    }
}

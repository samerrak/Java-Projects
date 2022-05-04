package eventbrite.events.visitor;

import eventbrite.events.events.AbstractEvent;
import eventbrite.events.events.Event;
import eventbrite.events.filtered.FilterResult;

public class VipVisitor implements Visitor{
    private double size;

    @Override
    public double visit(AbstractEvent event) {
        size = event.getVIPList().size();
        return size;
    }

    @Override
    public double visit(FilterResult filterResult) {
        for (Event e: filterResult.getaFilteredEvents())
            size += ((AbstractEvent) e).getVIPList().size();
        return size;
    }
}

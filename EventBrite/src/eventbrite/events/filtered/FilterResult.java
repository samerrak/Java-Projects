package eventbrite.events.filtered;
import eventbrite.Location;
import eventbrite.events.events.*;
import eventbrite.events.filtered.predicates.LocationFilter;
import eventbrite.events.filtered.predicates.PriceFilter;
import eventbrite.events.filtered.predicates.VipFilter;
import eventbrite.events.guests.VIP;
import eventbrite.events.visitor.Visitable;
import eventbrite.events.visitor.Visitor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class FilterResult extends MultiEvent implements Filterable, Visitable {
    private final ArrayList<VIP> vipList = new ArrayList<>();
    private double revenue;


    /**
     * The FilterResult class extends MultiEvent, as it inherits its fields and methods, it also adds additional
     * functionality to the MultiEvent by implementing Filterable it defines the function filter and specifies
     * the predicates or filtering setting the user wants, the user can add whatever predicate or filter he wants
     * and can either combine both or use them separately, this gives the user more options and provides the developer
     * with a more clean and concise solution to understand (consulted with professor before using predicate)
     **/

    public static void main(String[] args){
        Concert c1 =  Concert.getConcert("bla", Optional.of(Location.BellCentre), LocalDate.of(2000, 12, 1), 67.9, 9);
        Concert c2 =  Concert.getConcert("bloo", Optional.of(Location.ParcJeanDrapeau), LocalDate.of(2000, 12, 1), 90, 99);
        Workshops g1 =  Workshops.getWorkshop("boo", Optional.of(Location.BellCentre), LocalDate.of(2002, 12, 1), 90, 120);
        List<Event> events = new ArrayList<>();
        events.add(c1);
        events.add(c2);
        events.add(g1);
        Predicate<Event> p1 = new PriceFilter(30, 130);
        Predicate<Event> p2 = new LocationFilter(Optional.of(Location.BellCentre));
        Predicate<Event> p4 = new VipFilter();
        FilterResult preFiltered = new FilterResult(events);
        System.out.println(preFiltered.getaFilteredEvents());
        FilterResult filterResult = preFiltered.filter(p1,p2);
        System.out.println(preFiltered.getaFilteredEvents());
        System.out.println(filterResult.getaFilteredEvents());
        Predicate<Event> p3 = new LocationFilter(Optional.of(Location.ParcJeanDrapeau));
        FilterResult filterResult2 = preFiltered.filter(p3);
        System.out.println(filterResult2.getaFilteredEvents());

        FilterResult filterResult3 = preFiltered.filter();
        System.out.println(filterResult3.getaFilteredEvents());

        FilterResult filterResult4 = preFiltered.filter(p4);
        System.out.println(filterResult4.getaFilteredEvents());



    }

    /**
     The FilterResult takes a list of events as an input to filter the events, the design of this class is structured in a
     way where multiple systems of filteration can be applied on the object without changing the original eventList as specified
     in the design manual. The composite design pattern would have been a good choice here but the behavior of the getters are
     not in the solution manual and therefore it would not make sense to implement it here.
     **/

    public FilterResult(List<Event> eventList) {
        super(eventList);
    }

    /**
     filter utilizes Varargs by allowing the user to specify the filter conditions they want, this helps with code reusability
     and polymorphism, it makes a deep copy of the list to be filtered and iterates through the filters applying one by one,
     if the uses presses filter without choosing any filters to increase code continuity, the filter function just returns
     a FilterResult that is equal to the previous one. To access filtered results in a new FilteredResult object.
     **/

    @Override
    public FilterResult filter(Predicate<Event>... predicates){
        ArrayList<Event> filtered = new ArrayList<>(getaFilteredEvents());
        for (Predicate<Event> predicate : predicates)
            filtered.removeIf(predicate);
        return new FilterResult(filtered);
    }


    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public List<VIP> getVipList() {
        for (Event e: getaFilteredEvents()) {
            assert e instanceof AbstractEvent;
            vipList.addAll(((AbstractEvent) e).getVIPList());
        }

        return vipList;
    }

    public double revenue(Visitor visitor){
        assert !(getaFilteredEvents().isEmpty());
        for (Event e: getaFilteredEvents()){
            assert ((AbstractEvent)e).getProfitPercentage() != 0;
            revenue += accept(visitor);
        }
        return revenue;
    }

}

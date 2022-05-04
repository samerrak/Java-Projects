package eventbrite.events.events;

import eventbrite.Location;
import eventbrite.events.guests.VIP;
import eventbrite.events.observer.Observer;
import eventbrite.events.visitor.Visitable;
import eventbrite.events.visitor.Visitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 Representation of an abstract type of event that can exist, where multiple fields and methods are inherited by the following events
 Galas, Concert, Location, Screenings, Workshops
 **/

public abstract class AbstractEvent implements Event, Observer, Visitable {
    private final String name; //immutable fields
    private final Optional<Location> location;
    private final LocalDate date;
    private final double price;
    private final int tickets;
    private double profit;
    private final static HashMap<String,AbstractEvent> events = new HashMap<>();

    public AbstractEvent(String name, Optional<Location> location, LocalDate date, double price, int tickets) {
        assert name != null && location.isPresent() && date != null;
        this.name = name;
        this.location = location;
        this.date = date;
        this.price = price;
        this.tickets = tickets;
    }

    /**
     * Due to the shared behavior between AbstractEvents, the AbstractEvent class implements Observer which causes
     * the rest of the subclasses to inherit its behavior, it thus provides the rest of classes a way to update the information
     * which they won't need to do since if a change occurs Bob will be responsible to notify the class. The class also acts
     * as a Concrete visitable by implementing accept() which in course lets the rest of the methods to implement accept as well
     **/

    @Override
    public void update(double profit) {this.profit = profit;}
    @Override
    public double accept(Visitor visitor) { return visitor.visit(this);}
    @Override
    public String getName() {return name;}
    @Override
    public Optional<Location> getLocation(){
        return location;
    }
    @Override
    public LocalDate getDate(){
        return date;
    }
    @Override
    public double getPrice(){return price;}
    @Override
    public int getNumTickets(){
        return tickets;
    }

    public List<VIP> getVIPList(){ return new ArrayList<>();}
    public double getProfitPercentage() { return profit; }


    /**
     It is important to override both HashCode() and equals(Object o) are relied upon by Hash Structures to check whether
     to add another item to list or if it already exists, therefore since the FLyWeight method is utilized in all Event classes
     implementing it in the class makes sense to avoid Code Duplication.
     **/

    @Override
    public int hashCode() {
        return getLocation().hashCode() + getDate().hashCode() + getClass().hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass()))
            return false;

        return ((AbstractEvent) o).getDate().equals(getDate()) && ((AbstractEvent) o).getLocation().equals(getLocation());
    }

}

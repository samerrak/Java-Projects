package eventbrite.events.events;
import eventbrite.Location;
import eventbrite.events.guests.VIP;
import eventbrite.events.guests.VIPs;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Optional;

/**
 The composite pattern is implemented here as it lets the developer manipulate a group of events
 in the same manner as one event
 */

public class Festival extends AbstractEvent{
    private final HashSet<Event> festival = new HashSet<>();
    private final static HashMap<String, Festival> festivals = new HashMap<>();
    private final VIPs vips;


    /**
     To finalize the list of events, and make it immutable, the developer provides the user with only
     one opportunity to initialize the list of events added, the user also has an option of adding a nestedFestival
     or not, as it is allowed to use null in this case
     */
    private Festival(String name, Optional<Location> location, LocalDate date, double price, int tickets,AbstractEvent... events){
        super(name,location,date,price,tickets);
        festival.addAll(List.of(events));
        vips = new VIPs(name);
    }

    /**
     The getFestival method utilizing the FlyWeight pattern follows a quite different approach that the rest of the elements,
     since we utilize the composite design pattern and the location and date are determined by the events,
     of the festival, then to determine the correct location and date, an approach was taken similar to the one
     in getDate and getLocation to find out the correct date and location based on the conditions, therefore some
     code duplication is expected as non-static methods cannot be referenced from a static context
     */

    public static Festival getFestival(String name , AbstractEvent... events){
        assert name != null && events != null;
        Optional<Location> location = Festival.helpGetLocation(events);
        LocalDate date = Festival.helpGetDate(events);
        String key = location + " " + date;

        if (festivals.containsKey(key))
            return festivals.get(key);
        else
        {
            Festival festival = new Festival(name, location, date, Festival.helpGetPrice(events), Festival.helpGetNumTickets(events), events);
            festivals.put(key, festival);
            return festival;
        }
    }



    /**
     * Since the festival's profit percentage is dependent on the other events therefore it is not an observable,
     * to calculate its profit percentage simply divide the projected profits by the total price. Note Festival is
     * a Visitable as it can access the profit, you'll notice that this was probably not needed by was added as
     * an implementation to highlight how it can work in case a different method to calculate the profits was needed
     */

    @Override
    public double getProfitPercentage() {
        double profit = 0;
        for (Event e: festival){
            assert ((AbstractEvent) e).getProfitPercentage() != 0;
            profit += ((AbstractEvent) e).getProfitPercentage() * (0.8* e.getPrice());
        }
        return profit/getPrice();
    }

    @Override
    public List<VIP> getVIPList() {
        for (Event e : festival){
            for (VIP v : (((AbstractEvent) e).getVIPList()))
                vips.addVIP(v);
        }
        return vips.listVIP();
    }

    /**
     The helper function helpGetPrice, helpGetLocation, helpGetNumTickets, helpGetData, are all private static methods,
     to help encapsulate the design, the reason why the dev decided to go with this design is that instead of implementing
     Festival to just implement Event, and redefine the basic functions and equals and hashCode, this way the client uses the
     default methods in the Abstract Class and this also helps with polymorphism. This is a very elegant solution compared to
     just implementing Event, as it also allows to use the default constructor instead of a custom constructor or an overloaded
     one.
     */

    private static double helpGetPrice(AbstractEvent... events) {
        double price = 0;
        for (Event e : new HashSet<>(List.of(events)))
        {
            price += e.getPrice();
        }
        price *= 0.8;
        return price;
    }

    private static Optional<Location> helpGetLocation(AbstractEvent... events) {
        HashSet<Optional<Location>> location = new HashSet<>(List.of(events)).stream().map(Event::getLocation).collect(Collectors.toCollection(HashSet::new));
        if (location.size() == 1) {
            return location.iterator().next();
        } else
            return Optional.of(Location.Multiple);
    }

    /**
     Utilizing anonymous classes and the strategy pattern helps with avoiding the antipattern of
     switch statements, the set is sorted by comparator and thus the first element would be the one
     with the fewest number of tickets.
     */

    private static int helpGetNumTickets(AbstractEvent... events) {
        ArrayList<Event> buffer = new ArrayList<>(new HashSet<>(List.of(events)));
        buffer.sort(new Comparator<>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getNumTickets() - o2.getNumTickets();
            }
        });
        return buffer.get(0).getNumTickets();
    }

    private static LocalDate helpGetDate(AbstractEvent... events) {
        ArrayList<Event> buffer = new ArrayList<>(new HashSet<>(List.of(events)));
        buffer.sort(Comparator.comparing(Event::getDate)); //Strategy Pattern, following suggestions of IntelliJ with format
        return buffer.get(0).getDate();
    }





}

package eventbrite.events.events;

import eventbrite.Location;

import java.time.LocalDate;
import java.util.*;

public class Workshops extends AbstractEvent {
    private final HashSet<Workshops> prequisites = new HashSet<>();
    private final static HashMap<String,Workshops> workshops = new HashMap<>();

    public Workshops(String name, Optional<Location> location, LocalDate date, double price, int tickets) {
        super(name, location, date, price, tickets);
    }

    public static Workshops getWorkshop(String name, Optional<Location>  location, LocalDate date, double price, int tickets){
        assert name != null && location.isPresent() && date != null;
        String key =  location + " " + date;
        if (workshops.containsKey(key)) {
            return workshops.get(key); }
        else
        {
            Workshops workshop = new Workshops(name,location,date,price,tickets);
            workshops.put(key, workshop);
            return workshop;
        }
    }


    public Set<Workshops> getPrequisites() {
        return Collections.unmodifiableSet(prequisites);
    }

    /**
     Protected setter methods, to prevent user from having access to setter methods
     **/

    public final void setPrequisites(Workshops... preqs) {
        assert preqs != null;
        prequisites.addAll(Arrays.asList(preqs));
    }




}

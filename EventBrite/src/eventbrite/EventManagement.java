package eventbrite;

import eventbrite.events.events.*;
import eventbrite.events.guests.Artist;
import eventbrite.events.guests.VIP;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
Controller to manage events hosted on EventBrite.
 */
public class EventManagement {
    private final List<AbstractEvent> aHostedEvents = new ArrayList<>();

    /**
    Method to host a new concert event
     */
    public void addConcertEvent(String name, Location location, LocalDate date, double price, int tickets, Artist artist, VIP... vips){
        assert name != null && date != null && artist != null && vips !=null;
        Concert c1 = Concert.getConcert(name,Optional.of(location),date,price,tickets);
        c1.setArtist(artist); c1.setVIP(vips);
        aHostedEvents.add(c1);
    }

    /**
    Method to host a new Gala event
     */
    public void addGalaEvent(String name, Location location, LocalDate date, double price, int tickets, VIP... vips){
        assert name != null && date != null && vips !=null;
        Galas g1 = Galas.getGalas(name,Optional.of(location) ,date,price,tickets);
        g1.setVIP(vips);
        aHostedEvents.add(g1);
    }

    /**
    Method to host a new Screening event
     */
    public void addScreeningEvent(String name, Location location, LocalDate date, double price, int tickets, Ratings ratings){
        assert name != null && date != null;
        Screenings s1 = Screenings.getScreenings(name,Optional.of(location) ,date,price,tickets);
        s1.setRating(Optional.of(ratings));
        aHostedEvents.add(s1);
    }

    /**
    Method to host a new Workshop event
     */
    public void addWorkshopEvent(String name, Location location, LocalDate date, double price, int tickets, Workshops... workshops){
        assert name != null && date != null && workshops !=null;
        Workshops w1 = Workshops.getWorkshop(name,Optional.of(location) ,date,price,tickets);
        w1.setPrequisites(workshops);
        aHostedEvents.add(w1);
    }

    /**
    Return the list of hosted events on EventBrite.
    This method assumes that Events are immutable.
     */
    public ArrayList<AbstractEvent> getHostedEvents(){
        return new ArrayList<>(aHostedEvents);
    }
}

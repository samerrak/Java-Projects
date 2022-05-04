import eventbrite.EventManagement;
import eventbrite.Location;
import eventbrite.Ratings;
import eventbrite.events.events.*;
import eventbrite.events.guests.Artist;
import eventbrite.events.guests.VIP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventManagementTest {
    Concert c; Galas g; Workshops w, w1; Screenings s;
    String name = "event"; Location location = Location.BellCentre;
    Artist a = new Artist("Mitski"); VIP v = new VIP("Johnny Depp"); Ratings r = Ratings.R;
    LocalDate date = LocalDate.of(2000, 12, 1); double price = 90; int tickets = 100;
    EventManagement bob; ArrayList<Event> events;

    @BeforeEach
    void setUp() {
        c = Concert.getConcert(name, Optional.of(location),date,price,tickets); c.setVIP(v); c.setArtist(a);
        g = Galas.getGalas(name, Optional.of(location),date,price,tickets); g.setVIP(v);
        w = Workshops.getWorkshop(name, Optional.of(location),date,price,tickets);
        s = Screenings.getScreenings(name, Optional.of(location),date,price,tickets); s.setRating(Optional.of(r));
        w1 = Workshops.getWorkshop(name, Optional.of(Location.BellCentre),LocalDate.now(),70,tickets); w.setPrequisites(w1);
        events = new ArrayList<>(Arrays.asList(c, g, w, s));
        bob = new EventManagement();
    }


    /**
     The class of test cases EventManagementTest, tests the EventManagement class, by utilizing all the methods and
     creating events based on the input, using the same fields the events are created and added to a list, the test
     checks whether the list returned by getHostedEvents is equal to the preinitialized list events.
     **/

    @Test
    void TestEventManagement(){
        bob.addConcertEvent(name, location,date,price,tickets, a, v);
        bob.addGalaEvent(name, location,date,price,tickets,v);
        bob.addWorkshopEvent(name, location,date,price,tickets, w1);
        bob.addScreeningEvent(name, location,date,price,tickets, r);

        assertEquals(bob.getHostedEvents(), events);


    }
}

import eventbrite.Location;
import eventbrite.Ratings;
import eventbrite.events.events.*;
import eventbrite.events.guests.Artist;
import eventbrite.events.guests.VIP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DifferentEventsTest {
    ComingSoon cs; Concert c; Galas g; Workshops w, w1; Screenings s; Festival f, f1;
    String name = "event"; Location location = Location.BellCentre; Artist a = new Artist("Mitski"); VIP v = new VIP("Johnny Depp"); Ratings r = Ratings.R;
    LocalDate date = LocalDate.of(2000, 12, 1); double price = 90; int tickets = 100; ArrayList<Event> events;

    @BeforeEach
    void setUp() {
        cs = new ComingSoon(name, date);
        c = Concert.getConcert(name, Optional.of(location),date,price,tickets); c.setVIP(v); c.setArtist(a);
        g = Galas.getGalas(name, Optional.of(location),date,price,tickets); g.setVIP(v);
        w = Workshops.getWorkshop(name, Optional.of(location),date,price,tickets);
        s = Screenings.getScreenings(name, Optional.of(location),date,price,tickets); s.setRating(Optional.of(r));
        f = Festival.getFestival(name, c, g, w, s);
        w1 = Workshops.getWorkshop(name, Optional.of(Location.ParcJeanDrapeau),date,price,tickets); w.setPrequisites(w1);
        f1 = Festival.getFestival(name, c, g, w, s, w1);
        events = new ArrayList<>(Arrays.asList(c, g, w, s, f));
    }

    /**
     The class of test cases DifferentEventTests, tests the basic behaviors of each AbstractEvent and Coming Soon
     checking whether the program will still run smoothly if it is added,by implementing the basic features
     and checking using getter methods whether they're properly initialized or not, it also
     checks whether any duplicate events are created by testing and comparing using equals() and hashcode methods.
     Lastly it tests one of the private helper functions in the Festival class using reflection.
     **/

    @Test
    void TestDefaultEvents(){
        for (Event e: events)
            assertTrue(e.getPrice() >= 90 && e.getDate().equals(date) && e.getLocation().equals(Optional.of(location)) && e.getNumTickets()==100 && e.getName().equals(name));
        assertTrue(c.getArtist().equals(a) && c.getVIPList().equals(g.getVIPList()) && s.getRating().equals(Optional.of(r)) && !(w.getPrequisites().isEmpty())); c.getName().equals(name);
        assertNotNull(cs.getDate());assertNotNull(cs.getLocation()); assertTrue(cs.getDate().equals(date) && cs.getName().equals(name) && cs.getLocation().equals(Optional.empty()) && cs.getPrice() == 0 && cs.getNumTickets() == 0);
        assertEquals(f.getVIPList(), c.getVIPList());
    }

    @Test
    void TestNoDuplicateObjects(){
        Concert c1 = Concert.getConcert("duplicate", Optional.of(location),date,90,tickets); c.setVIP(v); c.setArtist(a);
        assertEquals(c1, c);
        assertEquals(c1.hashCode(), c.hashCode());
    }

    @Test
    void TestComingSoon(){
        assertNotNull(cs.getDate());assertNotNull(cs.getLocation());
        assertTrue(cs.getDate().equals(date) && cs.getName().equals(name) && cs.getLocation().equals(Optional.empty()) && cs.getPrice() == 0 && cs.getNumTickets() == 0);
    }

    @Test
    void TestReflectionPrivateHelpFestival() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class[] parameterTypes = new Class[] { AbstractEvent[].class };
        Method helpGetLocation = f1.getClass().getDeclaredMethod("helpGetLocation", parameterTypes);
        helpGetLocation.setAccessible(true);
        Object[] arguments = new Object[] {new AbstractEvent[] { c, g, w}};
        helpGetLocation.invoke(f1, arguments);
        assertEquals(f1.getLocation(), Optional.of(Location.Multiple));
    }





}

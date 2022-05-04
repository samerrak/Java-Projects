import eventbrite.Location;
import eventbrite.Ratings;
import eventbrite.events.events.*;
import eventbrite.events.filtered.FilterResult;
import eventbrite.events.filtered.predicates.LocationFilter;
import eventbrite.events.filtered.predicates.PriceFilter;
import eventbrite.events.guests.Artist;
import eventbrite.events.guests.VIP;
import eventbrite.events.observer.*;
import eventbrite.events.visitor.ProfitVisitor;
import eventbrite.events.visitor.VipVisitor;
import eventbrite.events.visitor.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class FilterEventTest {

    Concert c; Galas g; Workshops w, w1; Screenings s; Visitor profit = new ProfitVisitor();
    StubEvent stub = new StubEvent(); FilterResult fr, fr1; String name = "event"; Location location = Location.BellCentre;
    Artist a = new Artist("Mitski"); VIP v = new VIP("Johnny Depp"); Ratings r = Ratings.R;
    Subject cs = new ConcertSubject(), ws = new WorkshopSubject(), gs = new GalaSubject(), ss = new ScreeningSubject();
    Predicate<Event> p; LocalDate date = LocalDate.of(2000, 12, 1); double price = 90; int tickets = 100; ArrayList<Event> events; FilterResult test;

    static class StubEvent implements Event {
        @Override
        public String getName() { return null; }
        @Override
        public Optional<Location> getLocation() { return Optional.of(Location.ParcJeanDrapeau);}
        @Override
        public LocalDate getDate() { return null; }
        @Override
        public double getPrice() { return 60; }
        @Override
        public int getNumTickets() { return 0; }
    }

    @BeforeEach
    void setUp() {
        c = Concert.getConcert(name, Optional.of(location),date,price,tickets);
        g = Galas.getGalas(name, Optional.of(location),date,price,tickets);
        w = Workshops.getWorkshop(name, Optional.of(location),date,price,tickets);
        s = Screenings.getScreenings(name, Optional.of(location),date,price,tickets); s.setRating(Optional.of(r));
        w1 = Workshops.getWorkshop(name, Optional.of(Location.BellCentre),LocalDate.now(),70,tickets); w.setPrequisites(w1);
        events = new ArrayList<>(Arrays.asList(c, g, w, s, stub));
        fr = new FilterResult(events);
        events.add(w1);
        fr1 = new FilterResult(events);
        p = new LocationFilter(Optional.of(Location.ParcJeanDrapeau));
        cs.add(c); ss.add(s); ws.add(w); gs.add(g);
        cs.setProfit(0.6); ss.setProfit(0.4); ws.setProfit(0.8); gs.setProfit(0.6);
        cs.notifyObserver(); ss.notifyObserver(); ws.notifyObserver(); gs.notifyObserver();
        test = fr1.filter(new PriceFilter(80,100));
    }

    /**
     The class of test cases FilterEventTest, tests the FilterEvent, by implementing a stub event and other AbstractEvent and checking
     whether the desired output is reached by either Filtering by price, location or both. It also tests whether
     the profit can be calculated correctly by using the Observer classes and then Visitor classes to calculate
     the profit, the profit is manually and automatically checked
     **/

    @Test
    void TestLocationFilterEventWithStub() {
        assertEquals(fr.filter(p).getaFilteredEvents(), new ArrayList<>(List.of(stub)));
    }

    @Test
    void TestPriceFilterEvent() {
        assertEquals(fr1.filter(new PriceFilter(60,80)).getaFilteredEvents(), new ArrayList<>(List.of(stub,w1)));
    }

    @Test
    void TestPriceAndLocationFilterEvent() {
        assertEquals(fr1.filter(new PriceFilter(60,80), p).getaFilteredEvents(), new ArrayList<>(List.of(stub)));
    }

    @Test
    void TestVipListFilterEvent() {
        assertEquals(fr1.filter(new PriceFilter(70,80)).getVipList(), new ArrayList<VIP>());
    }

    @Test
    void TestExpectedProfitFilterEvent() {
        FilterResult test = fr1.filter(new PriceFilter(80,100));
        double x = test.accept(profit);
        assertTrue(c.accept(profit) == 54 && w.accept(profit) == 72 && s.accept(profit) == 36 && g.accept(profit) == 54 );
        assertEquals(x, c.accept(profit) + w.accept(profit) + s.accept(profit) + g.accept(profit));
    }

    @Test
    void TestVipListSize() {
        c.setVIP(v, new VIP("James Franco")); c.setArtist(a);
        g.setVIP(new VIP("Amber Heard"));
        double x = test.accept(new VipVisitor());
        assertEquals(x, 3);
    }







}

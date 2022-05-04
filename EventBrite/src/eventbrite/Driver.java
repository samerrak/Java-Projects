package eventbrite;

import eventbrite.events.events.*;
import eventbrite.events.filtered.FilterResult;
import eventbrite.events.filtered.predicates.LocationFilter;
import eventbrite.events.filtered.predicates.PriceFilter;
import eventbrite.events.guests.Artist;
import eventbrite.events.guests.VIP;
import eventbrite.events.observer.*;
import eventbrite.events.visitor.ProfitVisitor;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Optional;


public class Driver {

    public static void main(String[] args) {
        String name = "event"; Location location = Location.BellCentre; Artist a = new Artist("Mitski");
        VIP v = new VIP("Johnny Depp"); VIP v1 = new VIP("Amber Heard");
        Ratings r = Ratings.R; LocalDate date = LocalDate.of(2000, 12, 1); double price = 90;int tickets = 100;

        EventManagement bob = new EventManagement();
        bob.addConcertEvent(name, location,date,price,tickets, a, v); bob.addGalaEvent(name, location,date,price,tickets,v1);
        bob.addWorkshopEvent(name, Location.ParcJeanDrapeau,date,price,tickets); bob.addScreeningEvent(name, location,date,price,tickets, r);

        Festival f = Festival.getFestival(name, bob.getHostedEvents().get(0), bob.getHostedEvents().get(1),
                bob.getHostedEvents().get(2), bob.getHostedEvents().get(3));

        FilterResult events = new FilterResult(new ArrayList<>(bob.getHostedEvents()));
        FilterResult priceFilteredEvents = events.filter(new PriceFilter(30, 120));
        FilterResult locationFilteredEvents = events.filter(new LocationFilter(Optional.of(Location.ParcJeanDrapeau)));

        Subject cs = new ConcertSubject(), ws = new WorkshopSubject(), gs = new GalaSubject(), ss = new ScreeningSubject();
        cs.add(bob.getHostedEvents().get(0)); ss.add(bob.getHostedEvents().get(3));
        ws.add(bob.getHostedEvents().get(2)); gs.add(bob.getHostedEvents().get(1));
        cs.setProfit(0.6); ss.setProfit(0.4); ws.setProfit(0.8); gs.setProfit(0.2);
        cs.notifyObserver(); ss.notifyObserver(); ws.notifyObserver(); gs.notifyObserver();

        ProfitVisitor profitVisitor = new ProfitVisitor();

        for (int i = 0; i < bob.getHostedEvents().size(); i++) {
            System.out.println(bob.getHostedEvents().get(i).getClass().getSimpleName() + " " +
                    bob.getHostedEvents().get(i).accept(profitVisitor));
        }

        System.out.println( priceFilteredEvents.getClass().getSimpleName()
                + " " + priceFilteredEvents.accept(profitVisitor));

    }
}

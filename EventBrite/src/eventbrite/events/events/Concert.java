package eventbrite.events.events;

import eventbrite.Location;
import eventbrite.events.guests.Artist;
import eventbrite.events.guests.Guest;
import eventbrite.events.guests.VIP;
import eventbrite.events.guests.VIPs;
import eventbrite.events.observer.ConcertSubject;
import eventbrite.events.visitor.ProfitVisitor;
import eventbrite.events.visitor.Visitor;

import java.time.LocalDate;
import java.util.*;


public class Concert extends AbstractEvent  {
    private Guest artist;
    private final VIPs vipList;
    private final static HashMap<String,Concert> concerts = new HashMap<>();

    public static void main(String[] args){
        Concert c1 =  getConcert("bla", Optional.of(Location.BellCentre), LocalDate.of(2000, 12, 1), 67.9, 9);
        Concert c2 =  getConcert("bloo", Optional.of(Location.Multiple), LocalDate.of(2000, 12, 1), 90, 99);
        ConcertSubject subject = new ConcertSubject();
        subject.add(c1);
        subject.add(c2);
        subject.setProfit(0.8);
        subject.notifyObserver();
        System.out.println(c1.getProfitPercentage());
        Visitor v1 = new ProfitVisitor();
        System.out.println(c1.accept(v1));
    }

    /**
     Abstract classes give access to the super method to avoid code duplication,
     therefore initializing the private fields of the super class
     **/

    private Concert(String name, Optional<Location> location, LocalDate date, double price, int tickets){
        super(name,location,date,price,tickets);
        vipList = new VIPs(name);
    }

    /**
     The getConcert method, allows the user to either create or find a concert if it exists,
     by utilizing this pattern, this allows the program to be more efficient as it does not create duplicate
     objects based on the equals and hashcode method
     **/

    public static Concert getConcert(String name, Optional<Location> location, LocalDate date, double price, int tickets){
        assert name != null && location.isPresent() && date != null;
        String key =  location + " " + date;
        if (concerts.containsKey(key)) {
            return concerts.get(key);
        }
        else
        {
            Concert concert = new Concert(name,location,date,price,tickets);
            concerts.put(key, concert);
            return concert;
        }
    }

    @Override
    public List<VIP> getVIPList() {
        return vipList.listVIP();
    }

    /**
     *Setter methods for VIP and Artist
     * **/

    public Guest getArtist() {
        assert artist != null;
        return artist;
    }

    public final void setVIP(VIP... name) {
        for (VIP v: name)
            vipList.addVIP(v);
    }

    public final void setArtist(Artist artist) {
        this.artist = artist;
    }

}

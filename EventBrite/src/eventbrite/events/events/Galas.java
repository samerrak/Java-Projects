package eventbrite.events.events;

import eventbrite.Location;
import eventbrite.events.guests.VIP;
import eventbrite.events.guests.VIPs;

import java.time.LocalDate;
import java.util.*;

public class Galas extends AbstractEvent {
    private final static HashMap<String,Galas> galas = new HashMap<>();
    private final VIPs vipList;

    private Galas(String name, Optional<Location> location, LocalDate date, double price, int tickets) {
        super(name, location, date, price, tickets);
        vipList = new VIPs(name);
    }

    /**
     The getGalas method, follows a very similar and close to identical approach to that of concert,
     by making the constructor private the dev ensures that the user is unable to construct any new elements of gala,
     without using getGalas first, also an essential part of the FLyWeight method is a structure that keeps track of objects.
     created and in all events the way the objects created are being kept track off is by using a Key that for a HashMap that
     compromises of location and date.
     **/

    public static Galas getGalas(String name, Optional<Location> location, LocalDate date, double price, int tickets){
        assert name != null && location.isPresent() && date != null;
        String key =  location + " " + date;
        if (galas.containsKey(key)) {
            return galas.get(key);
        }
        else
        {
            Galas gala = new Galas(name,location,date,price,tickets);
            galas.put(key, gala);
            return gala;
        }
    }


    @Override
    public List<VIP> getVIPList() {
        return vipList.listVIP();
    }
    /**
     Protected setter methods, to prevent user from having access to setter methods
     **/

    public final void setVIP(VIP... name) {
        for (VIP v: name)
            vipList.addVIP(v);
    }




}

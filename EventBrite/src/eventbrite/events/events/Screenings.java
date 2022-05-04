package eventbrite.events.events;

import eventbrite.Location;
import eventbrite.Ratings;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class Screenings extends AbstractEvent {

    /**
     Optional Types, were used here a less elegant solution would've been to just add UNRATED as an element in the
     enum class of Ratings but to provide the user with more functionality here the empty value, represents unrated, but
     if a user wants UNRANKED is also available in enum
     **/

    private Optional<Ratings> rating;
    private final static HashMap<String,Screenings> screenings = new HashMap<>();

    private Screenings(String name, Optional<Location> location, LocalDate date, double price, int tickets) {
        super(name, location, date, price, tickets);
        rating = Optional.empty(); //or rating could be equal to Optional.empty() this is my design choice
    }

    public static Screenings getScreenings(String name, Optional<Location> location, LocalDate date, double price, int tickets){
        assert name != null && location.isPresent() && date != null;
        String key =  location + " " + date;
        if (screenings.containsKey(key)) {
            return screenings.get(key); }
        else
        {
            Screenings screening = new Screenings(name,location,date,price,tickets);
            screenings.put(key, screening);
            return screening;
        }
    }

    /**
     A constraint is set on the setRating method, to not allow the user to input an Optional.empty() option, as that
     would contradict the setRating definition.
     **/

    public void setRating(Optional<Ratings> rating) {
        assert rating.isPresent();
        this.rating = rating;
    }

    public Optional<Ratings> getRating() {
        return rating;
    }

}

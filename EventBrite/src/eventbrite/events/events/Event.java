package eventbrite.events.events;

import eventbrite.Location;
import java.time.LocalDate;
import java.util.Optional;

/**
Representation of a type of Event that can exist
 */

public interface Event {

    String getName();

    /**
     The getLocation() method has been reassigned to accommodate the case of the ComingSoon Event since the Location
     of the event is unknown, similarly to Screenings we use Optional.empty() property to highlight the fact that this
     event has unknown location
     */

    Optional<Location> getLocation();
    LocalDate getDate();
    double getPrice();
    int getNumTickets();



}

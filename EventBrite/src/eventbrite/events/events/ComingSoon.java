package eventbrite.events.events;

import eventbrite.Location;

import java.time.LocalDate;
import java.util.Optional;

public class ComingSoon implements Event {
    private final LocalDate date;
    private final String name;

    public ComingSoon(String name, LocalDate date)
    {
        this.date = date;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public Optional<Location> getLocation() {
        return Optional.empty();
    }
    @Override
    public LocalDate getDate() {
        return date;
    }
    @Override
    public double getPrice() {
        return 0;
    }
    @Override
    public int getNumTickets() {
        return 0;
    }
}

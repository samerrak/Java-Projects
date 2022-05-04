package eventbrite.events.guests;

public class Artist implements Guest {
    private String name;

    public Artist(String name)
    {
        assert name != null;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "The artist attending is " + name;
    }

    @Override
    public void setName(String name) {
        assert name != null;
        this.name = name;

    }

}

package eventbrite.events.guests;

public class VIP implements Guest {
    private String name;

    public VIP(String name)
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
        return "The guest attending is " + name;
    }

    @Override
    public void setName(String name) {
        assert name != null;
        this.name = name;
    }

}

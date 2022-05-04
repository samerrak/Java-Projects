package eventbrite.events.guests;
import java.util.HashSet;
import java.util.List;

/**
 * The Composite Pattern is implemented here as it acts as a single VIP but is actually an aggregation of VIPs
 */
public class VIPs extends VIP{
    private final HashSet<VIP> vipList = new HashSet<> ();

    public VIPs(String name, VIP... vips) {
        super(name);
        vipList.addAll(List.of(vips));
    }


    public void addVIP (VIP vip) {
        vipList.add(vip);
    }

    public List<VIP> listVIP() {
        return List.copyOf(vipList);
    }

    @Override
    public String toString() {
        return "VIP List: " + getName();
    }
}

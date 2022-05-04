import java.util.*;

public class Flight {

    private ArrayList<Tricks> tricks = new ArrayList<>();
    private Set<Movement> move = new HashSet<>();
    private Set<Direction> motion = new HashSet<>();
    private boolean executed=false;
    private Recording type;
    private boolean records=true;
    private static int counter = 0;
    private int nFlight = 0;
    String flight = "Flight";


    public Flight(){
        nFlight = counter;
        counter++;
        for (Tricks x: tricks){
            if (!x.isRecorded())
                records = false;
        }
    }


    public void removeControl(Tricks trick){
        tricks.remove(trick);
    }

    public void addControl(Tricks trick){
        tricks.add(trick);
    }

    public void executeFlight(){
        executed = true;
        for (Tricks trick: tricks){
            trick.execute();
        }
    }

    public void setOfTricks(){
        for (Tricks x: tricks){
            System.out.println(x);
        }
    }
    public int uniqueDirections(){
        for (Tricks trick: tricks){
            motion.addAll(trick.uDirection());
        }
        return motion.size();
    }

    public int uniqueMotion(){
        for (Tricks trick: tricks){
            move.addAll(trick.uMovements());
        }

        return move.size();
    }

    public int numberOfTricks(){
        return Collections.unmodifiableList(tricks).size();
    }

    public List<Tricks> Tricks(){
        return Collections.unmodifiableList(tricks);
    }


    public static Comparator<Flight> createUniqueComparator(){
        return new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.uniqueMotion() - o2.uniqueMotion();
            }
        };
    }

    public static Comparator<Flight> createSizeComparator(){
        return new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.numberOfTricks() - o2.numberOfTricks();
            }
        };
    }

    public void record()
    {
        for (Tricks x: tricks){
            assert x.isRecorded();
        }
        records=true;
    }

    public void save(String name, Recording type){
        assert name != null && type != null && records && executed: "enter a valid value";
        this.type = type;
        System.out.println("Recording flight" + name + "." + type + " successfully saved");
    }

    public boolean isRecords() {
        return records;
    }

    public String toString(){//overriding the toString() method
        return flight +" "+ nFlight;
    }



}

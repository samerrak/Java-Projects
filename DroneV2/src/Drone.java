import java.util.ArrayList;
import java.util.List;

public class Drone {

    final private String name;
    private static int counter = 0;
    private static final int maxDistance = 1500;
    private boolean record = false;
    private boolean executed;

    public Drone(String name) {
        assert name != null;
        this.name = name;
    }

    private static List<Flight> flightList = new ArrayList<>();
    private static List<Movement> moveLists = new ArrayList<>();


    public void uniqueMoveSort(){
        System.out.println("\nBEFORE MOVEMENT SORTING: " + flightList +"\n");
        List<Flight> moveSort = new ArrayList<>(flightList);
        moveSort.sort(Flight.createUniqueComparator());
        System.out.println("\nAFTER MOVEMENT SORTING: " + moveSort +"\n");
    }

    public void uniqueSizeSort(){
        System.out.println("\nBEFORE TRICK SIZE SORTING: "+ flightList +"\n");
        List<Flight> sizeSort = new ArrayList<>(flightList);
        sizeSort.sort(Flight.createUniqueComparator());
        System.out.println("\nAFTER TRICK SIZE SORTING: " + sizeSort +"\n");
    }

    public void addMove(Movement move){
        moveLists.add(move);
    }

    public void removeMove(Movement move){
        moveLists.remove(move);
    }

    public void executeMoves(){
        executed = true;
        for (Movement x : moveLists){
            System.out.println(x);
            x.execute();
        }
    }
    public void addFlight(Flight flight){
        counter++;
        flightList.add(flight);
    }

    public void removeFlight(Flight flight){
        counter--;
        flightList.remove(flight);
    }
    public void executeFlights(){
        executed = true;

        for (Flight x : flightList){
            System.out.println(x);
            x.executeFlight();
            System.out.println("\n");
        }
    }

    public void record()
    {
        for (Flight x: flightList){
            assert x.isRecords();
        }
        record=true;
    }

    public void save(String name, Recording type){
        assert name != null && type != null && record && executed: "enter a valid value";
        System.out.println("Recording all sequences " + name + "." + type + " successfully saved");
    }

    public static int getMaxDistance() {
        return maxDistance;
    }

    public String getName() {
        return this.name;
    }




}

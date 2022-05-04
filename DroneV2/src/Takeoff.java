import java.util.*;

public class Takeoff implements Tricks{

    private boolean record;
    private Recording type;
    private final int distance;
    private boolean executed=false;
    private ArrayList<Movement> moves = new ArrayList<>();
    private HashSet<Direction> directions = new HashSet<>();
    private HashSet<Movement> motions = new HashSet<>();
    private  static int counter = 0;
    private int nTakeoff = 0;
    private String takeoff = "TakeOff";


    Takeoff(boolean recording, int distance){
        assert distance < Drone.getMaxDistance();
        this.distance = distance;
        record = recording;
        if (record){
            moves.add(RecordedMovement.get(Direction.UP, Speed.LOW,distance));
            moves.add(RecordedMovement.get(Direction.UP, Speed.MODERATE, distance));
        }
        else{
            moves.add(UnRecordedMovement.get(Direction.UP,Speed.LOW, distance));
            moves.add(UnRecordedMovement.get(Direction.UP, Speed.MODERATE, distance));
        }

        for(Movement x : moves){
            directions.add(x.getDirection());
        }

        for (Movement x : moves) {
            motions.add(x);
        }
        nTakeoff = counter;
        counter++;
    }

    @Override
    public void execute(){
        executed=true;

        for (Movement x : moves) {
            x.execute();
        }
    }


    @Override
    public void save(String name, Recording type){
        assert name != null && type != null && record && executed: "enter a valid value";
        for (Movement x: moves){
            assert x.isRecorded();
        }
        this.type = type;
        System.out.println("Recording trick " + name + "." + type + " successfully saved");
    }

    @Override
    public List<Movement> movements() {
        return Collections.unmodifiableList(moves);
    }

    @Override
    public Set<Direction> uDirection() {
        return Collections.unmodifiableSet(directions);
    }

    @Override
    public Set<Movement> uMovements() {
        return Collections.unmodifiableSet(motions);
    }


    @Override
    public boolean isRecorded() {
        return record;
    }


    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return takeoff +" "+ nTakeoff;
    }
}

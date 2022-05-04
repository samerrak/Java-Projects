import java.util.*;

public class Sidewinder implements Tricks {
    private boolean record;
    private Recording type;
    private final int distance;
    private boolean executed=false;
    private ArrayList<Movement> moves = new ArrayList<>();
    private HashSet<Direction> directions = new HashSet<>();
    private HashSet<Movement> motions = new HashSet<>();
    private static int counter = 0;
    private int nSide = 0;
    private String side = "Sidewinder";

    Sidewinder(boolean recording, int distance){
        assert distance < Drone.getMaxDistance();
        this.distance = distance;
        record = recording;
        if (record){
            record = true;
            moves.add(RecordedMovement.get(Direction.BACKWARD, Speed.MODERATE, distance));
            moves.add(RecordedMovement.get(Direction.LEFT, Speed.MODERATE, distance));
            moves.add(RecordedMovement.get(Direction.BACKWARD, Speed.MODERATE, distance));
            moves.add(RecordedMovement.get(Direction.RIGHT, Speed.MODERATE, distance));

        }
        else{
            record = false;
            moves.add(UnRecordedMovement.get(Direction.BACKWARD, Speed.MODERATE, distance));
            moves.add(UnRecordedMovement.get(Direction.LEFT, Speed.MODERATE, distance));
            moves.add(UnRecordedMovement.get(Direction.BACKWARD, Speed.MODERATE, distance));
            moves.add(UnRecordedMovement.get(Direction.RIGHT, Speed.MODERATE, distance));
        }

        for(Movement x : moves){
            directions.add(x.getDirection());
        }

        for (Movement x : moves) {
            motions.add(x);
        }
        nSide = counter;
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
        assert name != null && type != null && record && executed;
        for (Movement x: moves){
            assert x.isRecorded();
        }
        this.type = type;
        System.out.println("Recording trick " + name + "." + type + " successfully saved");
        moves.clear();
    }


    @Override
    public Set<Direction> uDirection() {
        return Collections.unmodifiableSet(directions);
    }

    @Override
    public List<Movement> movements() {
        return Collections.unmodifiableList(moves);
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
        return side +" "+ nSide;
    }
}

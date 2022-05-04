import java.util.*;

public class Pucker implements Tricks{

    private boolean record;
    private Recording type;
    private Speed speed;
    private int distance;
    private boolean executed=false;
    private ArrayList<Movement> moves = new  ArrayList<>();
    private HashSet<Direction> directions = new HashSet<>();
    private HashSet<Movement> motions = new HashSet<>();
    private static int counter = 0;
    private int nPucker = 0;
    private String pucker = "Pucker";


    Pucker (boolean recording, Speed speed, int distance){
        assert distance < Drone.getMaxDistance();
        this.distance = distance;
        record = recording;
        this.speed = speed;
        if (record){
            record = true;
            moves.add(RecordedMovement.get(Direction.UP, speed, distance));
            moves.add(RecordedMovement.get(Direction.LEFT, speed, distance));
            moves.add(RecordedMovement.get(Direction.LEFT, speed, distance));
            moves.add(RecordedMovement.get(Direction.LEFT, speed, distance));
            moves.add(RecordedMovement.get(Direction.LEFT, speed, distance));
            moves.add(RecordedMovement.get(Direction.DOWN, speed, distance));
        }
        else{
            moves.add(UnRecordedMovement.get(Direction.UP, speed, distance));
            moves.add(UnRecordedMovement.get(Direction.LEFT, speed, distance));
            moves.add(UnRecordedMovement.get(Direction.LEFT, speed, distance));
            moves.add(UnRecordedMovement.get(Direction.LEFT, speed, distance));
            moves.add(UnRecordedMovement.get(Direction.LEFT, speed, distance));
            moves.add(UnRecordedMovement.get(Direction.DOWN, speed, distance));
        }

        for(Movement x : moves){
            directions.add(x.getDirection());
        }

        for (Movement x : moves) {
            motions.add(x);
        }

        nPucker = counter;
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
        System.out.println("Recording trick" + name + "." + type + " successfully saved");
        moves.clear();

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
    public Set<Direction> uDirection() {
        return Collections.unmodifiableSet(directions);
    }

    @Override
    public int getDistance() {
        return distance ;
    }

    @Override
    public boolean isRecorded() {
        return record;
    }

    public Speed getSpeed() {
        return speed;
    }

    public String toString(){//overriding the toString() method
        return  pucker+" "+ nPucker;
    }





}

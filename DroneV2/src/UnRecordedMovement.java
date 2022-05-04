
public class UnRecordedMovement implements Movement{
    private Direction direction;
    private int speed;
    private int distance;
    private boolean execute = false;

    private static final UnRecordedMovement[][][] UnRecMove = new UnRecordedMovement[Direction.values().length][Speed.values().length][Drone.getMaxDistance()];
    static
    {
        for( Direction direction : Direction.values() )
        {
            for( Speed speeds : Speed.values() )
            {
                for (int i=0; i<Drone.getMaxDistance(); i++){
                    UnRecMove[direction.ordinal()][speeds.ordinal()][i]=new UnRecordedMovement(direction, speeds, i);}}}}

    private UnRecordedMovement(Direction direction, Speed speed, int distance){
        assert direction != null && speed != null;
        this.speed = speed.valueOfEnum;
        this.direction = direction;
        this.distance = distance;
    }

    public static UnRecordedMovement get(Direction direction, Speed speed, int distance){
        assert distance > 0 && speed != null && direction != null && distance < Drone.getMaxDistance();
        return UnRecMove[direction.ordinal()][speed.ordinal()][distance];
    }
    @Override
    public void execute() {
        execute = true;
        if (this.direction == Direction.FORWARD)
            System.out.println("Move forward " + distance + " meters at " + speed + " mph" );
        else if (this.direction == Direction.BACKWARD)
            System.out.println("Move backward " + distance + " meters at " + speed + " mph" );
        else if (this.direction == Direction.UP)
            System.out.println("Move up " + distance + " meters at " + speed + " mph" );
        else if (this.direction == Direction.DOWN)
            System.out.println("Move down " + distance + " meters at " + speed + " mph" );
        else if (this.direction == Direction.LEFT)
            System.out.println("Move left " + distance + " meters at " + speed + " mph" );
        else if (this.direction == Direction.RIGHT)
            System.out.println("Move right " + distance + " meters at " + speed + " mph" );
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean isRecorded() {
        return false;
    }

}

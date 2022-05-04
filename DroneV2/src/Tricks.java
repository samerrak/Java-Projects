import java.util.List;
import java.util.Set;

public interface Tricks {

    void save(String name, Recording type);

    void execute();


    List<Movement> movements();

    int getDistance();

    boolean isRecorded();

    Set<Direction> uDirection();

    Set<Movement> uMovements();


}

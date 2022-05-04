import java.util.LinkedList;
import java.util.List;

/**
 * Represents a default playable to be played
 */

public class PlayableManager {

    private Playable prototype = new PlayList("Name");
    private final List<Playable> aPlayables = new LinkedList<>();

    public Playable createPlayable()
    {
        return prototype.clone();
    }
    public void setPlayable(Playable audio)
    {
        assert audio != null;
        prototype = audio.clone();
    }


}

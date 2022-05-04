import java.util.*;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {

    private final List<Playable> aList = new ArrayList<>();
    private final String aName;



    public PlayList(String pName) {
        assert pName != null;
        aName = pName;

    }


    public void add(Playable... audio) {
        assert audio != null;

        for (Playable p : audio){
            assert !(p instanceof Podcast) && !(p instanceof PlayList):"You cannot add Podcasts or PlayLists to Playlists";
            aList.add(p);
        }

    }


    public void removePlayable(Playable playable){
        assert playable != null && aList.contains(playable);
        aList.remove(playable);
    }

    public String toString(){
        return aName + ": " + aList;
    }

    public boolean isEmpty(){
        return aList.isEmpty();
    }

    public String getName() {
        return aName;
    }

    @Override
    public void play() {
        for (Playable p : aList)
            p.play();
    }

    @Override
    public int hashCode() {
        return aList.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PlayList s) {

            if (s.isEmpty() && aList.isEmpty())
                return false;

            return aList.equals(s.aList);
        }
        return false;
    }

}

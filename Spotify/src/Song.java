import java.util.HashMap;
import java.util.Objects;

/**
 * Represents a single Song, with at least a title, and an artist name.
 */
public class Song implements Playable{

    private final String aTitle;
    private final String aArtist;
    private final String aKey;
    private final static HashMap<String,Song> song = new HashMap<>();

    private Song(String title, String artist){
        assert title != null:"enter a valid song name"; assert artist != null:"enter a valid artist name";
        aTitle = title.toLowerCase();
        aArtist = artist.toLowerCase();
        aKey = title + artist;
    }

    public static Song getSong(String title, String artist){
        assert artist != null:"enter a valid artist name";
        artist = artist.toLowerCase();
        title = Objects.requireNonNull(title).toLowerCase();
        String songKey = title + artist;
        if (song.containsKey(songKey)) {
            return song.get(songKey);
        }
        else
        {
            Song newSong = new Song(title, artist);
            song.put(songKey, newSong);
            return newSong;
        }
    }

    public void play() {
        assert aTitle != null:"enter a valid song name"; assert aArtist != null:"enter a valid artist name";

        System.out.println("Now playing " + aTitle + " by " + aArtist);
    }

    @Override
    public String toString() {
        return  aTitle +
                " by " + aArtist;
    }

    @Override
    public int hashCode() {
        return (aKey + aArtist + aTitle).hashCode();
    }

    @Override
    public boolean equals(Object song) {
        if(song != null & song instanceof Song) {
            Song s = (Song)song;
            return aKey.equals(s.aKey);
        }
        return false;
    }

}




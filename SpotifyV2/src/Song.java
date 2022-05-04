import java.lang.reflect.Field;
import java.util.*;

/**
 * Represents a single Song, with at least a title, and an artist name.
 */
public class Song implements Playable {

    private final String aTitle;
    private final String aArtist;

    /**
     * Creates a new Song.
     *
     * @param pTitle
     *            the title of the song
     * @param pArtist
     *            the artist of the song
     * @pre pTitle!=null && pArtist!=null;
     */
    public Song(String pTitle, String pArtist)
    {
        assert  pTitle!=null && pArtist!=null;
        aTitle=pTitle;
        aArtist=pArtist;
    }

    /**
     * @return The title of the song.
     */
    public String getTitle() {
        return aTitle;
    }

    /**
     * @return The artist of the song.
     */
    public String getArtist() {
        return aArtist;
    }

    public void play() {
        // Just a stub.
        System.out.println("Now playing " + aTitle);
    }

    @Override
    public Song clone() {
        try {
            Song clone = (Song) super.clone();
            Field artist = Song.class.getDeclaredField("aArtist");
            artist.setAccessible(true);
            artist.set(clone, aArtist);
            Field title = Song.class.getDeclaredField("aTitle");
            title.setAccessible(true);
            title.set(clone, aTitle);
            return clone;

        }
        catch (CloneNotSupportedException | NoSuchFieldException | IllegalAccessException e) {
            assert false;
            return null;
        }
    }

    /**
     * Checks is two songs are equal based on song title and artist
     *
     * @param o
     *            The object to compare a song to
     * @return    This method returns true if the song is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return aTitle.equalsIgnoreCase(song.aTitle) && aArtist.equalsIgnoreCase(song.aArtist);
    }

    /**
     * Equal songs have the same hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(aTitle, aArtist);
    }

}
import java.util.HashMap;
import java.util.Objects;

/**
 * Represents a single episode, with at least a title, and an episode number.
 */
public class Episode implements Playable {

    private final Podcast aPodcast;
    private final String aTitle;
    private final int aEpisodeNumber;
    private final String EpisodeKey;
    private final static HashMap<String, Episode> episodes = new HashMap<>();



    private Episode(Podcast pPodcast, String pTitle, int pEpisodeNumber) {
        assert (pPodcast != null) && (pTitle != null);
        aPodcast = pPodcast;
        aTitle = pTitle;
        aEpisodeNumber = pEpisodeNumber;
        EpisodeKey = (pPodcast.getName() + pPodcast.getaHost() + pTitle + pEpisodeNumber);
        aPodcast.addEpisode(this);
    }

    public static Episode getEpisode(Podcast pPodcast, String pTitle, int pEpisodeNumber){
        assert (pPodcast != null);
        pTitle = Objects.requireNonNull(pTitle).toLowerCase();
        String episodeKey =(pPodcast.getName() + pPodcast.getaHost() + pTitle + pEpisodeNumber);
        if (episodes.containsKey(episodeKey)){
            return episodes.get(episodeKey);
        }
        else{
            Episode newEpisode = new Episode(pPodcast, pTitle, pEpisodeNumber);
            episodes.put(episodeKey, newEpisode);
            return newEpisode;
        }
    }

    public Podcast getaPodcast() {
        return aPodcast;
    }

    public String getaTitle() {
        return aTitle;
    }

    public int getaEpisodeNumber() {
        return aEpisodeNumber;
    }

    @Override
    public void play() {
        System.out.println("Now playing " + aPodcast.getName() + " [" + aEpisodeNumber + "]: " + aTitle);
    }

    @Override
    public String toString() {
        return "[" + aPodcast.getName() + ": " + aEpisodeNumber + ". " + aTitle + "]" ;
    }

    @Override
    public int hashCode() {
        return (Integer.toString(aEpisodeNumber) + aPodcast.getName() + aTitle).hashCode();
    }

    @Override
    public boolean equals(Object episode) {
        if(episode != null & episode instanceof Episode) {
            Episode s = (Episode) episode;
            return EpisodeKey.equals(s.EpisodeKey);
        }
        return false;
    }


}

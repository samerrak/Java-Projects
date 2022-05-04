import java.util.*;

/**
 * Represents a single Podcast, with at least a name and a host. Each Podcast aggregates episodes.
 */
public class Podcast implements Playable {

    private final String aName;
    private final String aHost;
    private final List<Episode> aEpisodes = new ArrayList<>();
    private final String aKey;
    private final static HashMap<String, Podcast> podcasts = new HashMap<>();
    private static int playCounter = 0;

   private Podcast(String name, String host)
   {
       assert name != null:"enter a valid podcast name"; assert host != null:"enter a valid podcast name";
       aName = name.toLowerCase();
       aHost = host.toLowerCase();
       aKey = name+host;
   }

    public static Podcast getPodcast(String name, String host){
        name = Objects.requireNonNull(name).toLowerCase();
        host = Objects.requireNonNull(host).toLowerCase();
        String podcastKey =  name+host;
        if (podcasts.containsKey(podcastKey)) {
            return podcasts.get(podcastKey);
        }
        else
        {
            Podcast newPodcast = new Podcast(name, host);
            podcasts.put(podcastKey, newPodcast);
            return newPodcast;
        }


   }

   public void removeEpisode(Episode episode){
       if (aEpisodes.contains(episode))
           aEpisodes.remove(episode);
       else
           throw new RuntimeException("You cannot remove an episode that's not in the Podcast");
   }
    protected void addEpisode(Episode pEpisode) {
        if(!aEpisodes.contains(pEpisode)) {
            aEpisodes.add(pEpisode);
        }
    }

    public Episode getEpisode(int Pnumber) {

       for (Episode e: aEpisodes){
           if (e.getaEpisodeNumber() == Pnumber)
               return e;
       }
        throw new RuntimeException("Can't get Episode, Please try another value or wait until the host adds the Episode");
    }

    public List<Episode> getEpisodes() {
        return Collections.unmodifiableList(aEpisodes);
    }

    @Override
    public void play() {
       Episode ep = Collections.unmodifiableList(aEpisodes).get(playCounter);
       Collections.unmodifiableList(aEpisodes).get(playCounter).play();
       playCounter++;
       System.out.println("Playing " + ep);
    }

    public String getName() {
        return aName + " by " + aHost;
    }

    public String getaHost() {
        return aHost;
    }

    @Override
    public String toString() {
        return  aName +
                " hosted by " + aHost + ":\n"+ aEpisodes ;
    }


    @Override
    public int hashCode() {
        return (aKey).hashCode();
    }

    @Override
    public boolean equals(Object podcast) {
        if(podcast != null & podcast instanceof Podcast) {
            Podcast p = (Podcast) podcast;
            return aEpisodes.equals((p).aEpisodes);
        }
        return false;
    }




}
import java.util.*;


public class Library {
     private static final Library library = new Library();
     private final Set<Playable> allAudio = new HashSet<>();
     private final Set<Song> songList = new HashSet<>();
     private final Set<Episode> episodeList = new HashSet<>();
     private final Set<Podcast> podcastList = new HashSet<>();
     private final Set<PlayList> playList = new HashSet<>();
     private static String name;
     private static String description;
     private static int counter = 0;
     //add description as well

    private Library(){
        if (library != null){
            throw new RuntimeException("Use getLibrary to get access to Library");
        }
     }

     public static Library aLibrary(String names, String descriptions){
        assert names != null;
        if (counter > 0){
            if (!name.equals(names))
                throw new RuntimeException("You can only have one library and you've already defined it's name as " + name);
        }
        else{
            name = names;
            description = descriptions;
        }

        counter++;
        return library;
     }

     public void addToPlaylist(String name, Playable... audio){
         assert name != null && audio != null;
         PlayList pList = null;
         for (PlayList p : playList)
         {
             if (p.getName().equals(name))
                 pList = p;
         }
         assert pList!=null: "Playlist not found in Library";
         for (Playable p: audio) {
             assert !(p instanceof Podcast ) && !(p instanceof PlayList);
             pList.add(p);
         }
         HashSet<Playable> temp = new HashSet<>(playList);
         playList.clear();
         for (Playable p : temp)
         {
             playList.add((PlayList) p);
         }
     }

     public void removeFromLibrary(Playable audio) {
         assert audio != null;
         if (audio instanceof Song)
             songList.remove((Song) audio);
         else if (audio instanceof Episode)
             episodeList.remove((Episode) audio);
         else if (audio instanceof PlayList)
             playList.remove((PlayList) audio);
         else
             podcastList.remove((Podcast) audio);

         allAudio.remove(audio);
         HashSet<Playable> temp = new HashSet<>(playList);
         playList.clear();
         for (Playable p : temp) {
             playList.add((PlayList) p);
         }

     }

    public void addToPodcast(String name, String host, String title, int aEp)
    {
        assert  name !=null & host !=null &title!=null;
        assert podcastList.contains(Podcast.getPodcast(name,host));
        Podcast p = Podcast.getPodcast(name,host);
        p.addEpisode(Episode.getEpisode(p,title,aEp));
        episodeList.add(Episode.getEpisode(p,title,aEp));
    }

     public void removeFromPodcast(String name, String host, String a, int aEp)
     {
         assert  name !=null & host !=null &a!=null;
         assert podcastList.contains(Podcast.getPodcast(name,host));
         Podcast p = Podcast.getPodcast(name,host);
         removeFromLibrary(Episode.getEpisode(p,a,aEp));
         p.removeEpisode(Episode.getEpisode(p,a,aEp));
     }

     public void removeFromPlaylist(String name, Playable audio){

        assert name != null && audio != null;
        PlayList pList = null;
        for (PlayList p : playList)
        {
            if (p.getName().equals(name))
                pList = p;
        }
        assert pList!=null: "Playlist not found in Library";
        pList.removePlayable(audio);
        HashSet<Playable> temp = new HashSet<>(playList);
        playList.clear();
        for (Playable p : temp)
        {
            playList.add((PlayList) p);
        }

    }

    public void createPlaylist(String name, Playable... playables){
        PlayList pList = new PlayList(name);

        for (Playable p: playables)
        { assert p!=null;
            pList.add(p);}

        playList.add(pList);
        allAudio.add(pList);
        HashSet<Playable> temp = new HashSet<>(playList);
        playList.clear();
        for (Playable p : temp)
        {
            playList.add((PlayList) p);
        }
    }


     public void add(Playable audio) {
        assert audio != null;
        if (audio instanceof Song)
            songList.add((Song)audio);
        else if (audio instanceof Episode)
            episodeList.add((Episode) audio);
        else if (audio instanceof PlayList){
            playList.add((PlayList) audio); }
        else {
            if (!podcastList.contains((Podcast) audio)) {
                podcastList.add((Podcast) audio);
                for (Episode ep : ((Podcast) audio).getEpisodes())
                    episodeList.add(ep);
            }
        }


        allAudio.add(audio);
    }

    public void play(Playable audio){
        assert audio != null;
        if (allAudio.contains(audio)) {
            for (Playable sound : allAudio) {
                if (sound.equals(audio)) {
                    sound.play();
                    break;
                }
            }
        }
        else
            System.out.println("To play this audio add it first to the library\n");
    }

     public static String getName() {
         return name;
     }

     public static String getDescription(){
        return description;
     }


     @Override
     public String toString() {
        StringBuilder pLibrary = new StringBuilder();
        pLibrary.append("Library: ").append(getName()).append(", ").append("Description: ").append(getDescription()).append("\n");

        if (!playList.isEmpty()){
            pLibrary.append("Playlists:\n");
            pLibrary.append(playList);
        }

        if (!songList.isEmpty()){
            pLibrary.append("\nSongs:\n");
            pLibrary.append(songList);}

        if (!podcastList.isEmpty()){
            pLibrary.append("\nPodcasts:\n");
            pLibrary.append(podcastList);}

        if (!episodeList.isEmpty()){
            pLibrary.append("\nEpisodes:\n");
            pLibrary.append(episodeList);}

        pLibrary.append("\n");

         return pLibrary.toString();

     }

 }

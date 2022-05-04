

public class Main {

        Library lib = Library.aLibrary("Padre", "Chill Library");
        //Library gor = Library.aLibrary("Madre", "Chillax"); //will cause an error


        lib.createPlaylist("chilling");
        lib.createPlaylist("homie");
        lib.createPlaylist("workout", Song.getSong("Les", "Childish Gambino"));

        lib.addToPlaylist("homie", Song.getSong("Les", "Childish Gambino"), Song.getSong("Englishman in New York", "Sting"));
        lib.addToPlaylist("chilling",Song.getSong("Les", "Childish Gambino"), Song.getSong("Blank Space", "Taylor Swift"), Song.getSong("RoboCop", "Kanye West"));
        lib.addToPlaylist("workout",  Song.getSong("Les", "Childish Gambino"), Song.getSong("Blank Space", "Taylor Swift")); //assertion error
        lib.add(Song.getSong("Waves","Calpurnia"));
        lib.add(Song.getSong("Waves", "Dean Lewis"));

        System.out.println(lib); //only workout will be printed because the Playlists are the same
        lib.removeFromPlaylist("chilling", Song.getSong("RoboCop", "Kanye West") );


        lib.add(Podcast.getPodcast("Smartless","Jason Bateman"));
        lib.addToPodcast("Smartless", "Jason Bateman", "Courtney Cox", 84);
        lib.addToPodcast("Smartless", "Jason Bateman", "Courtney Cox", 84);
        lib.addToPodcast("Smartless", "Jason Bateman", "Micheal Moore", 89);
        lib.addToPodcast("Smartless", "Jason Bateman", "Eddie Vedder", 83);
        lib.removeFromPodcast("Smartless", "Jason Bateman", "Micheal Moore", 89);
        lib.add(Podcast.getPodcast("Smartless", "Jason Bateman"));//nothing happens because implementation allows it.


        lib.add(Episode.getEpisode(Podcast.getPodcast("The Rumor","Blue Wire"), "Something Magic Happens", 2));
        lib.add(Podcast.getPodcast("Smartless","Jason Bateman"));

        lib.removeFromLibrary(Song.getSong("Waves","Calpurnia"));
        lib.removeFromPlaylist("workout", Song.getSong("Blank Space", "Taylor Swift"));
        lib.addToPlaylist("homie", Podcast.getPodcast("Smartless", "Jason Bateman").getEpisode(84));
        lib.removeFromPlaylist("homie", Song.getSong("Englishman in New York", "Sting"));
        lib.play(Song.getSong("Waves", "Dean Lewis"));
        System.out.println(lib);











//
//














//        System.out.println(lib.equals(libs)); //checks if only one instance


    }
}

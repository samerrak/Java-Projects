import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayableManagerTest {
    PlayableManager f;

    @BeforeEach
    void setUp() {
        f = new PlayableManager();

    }

    @Test
    void TestValidPlayableManager() {
        assertNotNull(f);
    }

    @Test
    void TestValidDefaultCreate() {
        Playable prototype = f.createPlayable();
        f.setPlayable(prototype);
        Playable clone = f.createPlayable();
        assertTrue((clone != null) && clone.equals(prototype) && (clone != prototype));
    }

    @Test
    void TestValidSongCreate() {
        Song prototype = new Song("Home", "Catie Turner");
        f.setPlayable(prototype);
        Playable clone = f.createPlayable();
        assertTrue((clone instanceof Song) && clone.equals(prototype) && (clone != prototype));
    }

    @Test
    void TestValidEpisodeCreate() {
        Podcast p = new Podcast("bla", "bla");
        Podcast h = new Podcast("bla", "bla");
        f.setPlayable(h.createAndAddEpisode(p.createAndAddEpisode("world").getTitle()));
        Playable clone = f.createPlayable();
        assertTrue((clone instanceof Podcast.Episode) && p.getEpisode(0) != clone && p.getEpisode(0).equals(clone));
    }

    @Test
    void TestValidPlayListCreate() {
        PlayList prototype = new PlayList("bla");
        f.setPlayable(prototype);
        Playable clone = f.createPlayable();
        assertTrue((clone instanceof PlayList) && prototype.equals(clone) && prototype != clone);
    }

    @Test
    void TestNullCreate() {
        try {
            PlayList prototype = null;
            f.setPlayable(prototype);
            Playable clone = f.createPlayable();
            fail();

        } catch (AssertionError ignored) {

       }
    }
























    @Test
    void setPlayable() {
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayListTests {
    PlayList f;
    Song song1 = new Song("Ribs", "Lorde");
    Song song2 = new Song("Home", "Catie Turner");
    Song song3 = new Song("Donna", "The Lumineers");


    @BeforeEach
    void setUp() {
        f = new PlayList("name");
        f.addPlayable(song1);
        f.addPlayable(song2);
        f.addPlayable(song3);
    }


    @Test
    void TestAddToPlayList() {
        PlayList g = new PlayList("bop");
        g.addPlayable(song1);
        g.addPlayable(song2);
        g.addPlayable(song3);
        assertEquals(g,f);
        try {
            f.addPlayable(null);
            fail();

        } catch (AssertionError ignored) {
        }
    }

    @Test
    void TestRemoveFromPlayList(){
        PlayList g =  f.clone();
        g.removePlayable(0);
        g.removePlayable(1);
        assertNotEquals(f,g);
        try {
            f.removePlayable(3);
            fail();

        } catch (AssertionError ignored) {

        }
    }

    @Test
    void TestShufflePlayList(){
        PlayList g = f.clone();
        g.shuffle();
        try {
            assertNotEquals(g, f);
        }
        catch (AssertionError e){
            g.shuffle();
            assertNotEquals(g, f);
        }
        try {
            PlayList k = new PlayList("hum");
            k.shuffle();
            fail();

        } catch (AssertionError ignored) {}
    }

    @Test
    void TestSetNamePlayList(){
        PlayList g = f.clone();
        g.setName("copo");
        assertNotEquals(g.getName(), f.getName());
        try {
            g.setName(null);
            fail();

        } catch (AssertionError ignored) {}
    }

    @Test
    void TestUndoAddPlayList(){
        PlayList g = f.clone();
        f.undo();
        f.undo();
        assertNotEquals(g, f);
    }

    @Test
    void TestUndoShufflePlayList() {
        PlayList g = f.clone();
        g.shuffle();
        assertNotEquals(g, f);
        g.undo();
        assertEquals(g, f);
    }

    @Test
    void TestUndoSetNamePlayList() {
        PlayList g = f.clone();
        g.setName("kokaddaf");
        assertNotEquals(g.getName(), f.getName());
        g.undo();
        assertEquals(g.getName(), f.getName());

    }

    @Test
    void TestUndoRemovePlayList() {
        PlayList g = f.clone();
        g.removePlayable(0);
        assertNotEquals(g, f);
        g.undo();
        assertEquals(g, f);
    }

    @Test
    void TestUndoMoreThanExecutedPlayList(){
        f.undo();
        f.undo();
        f.undo();
        PlayList g = f.clone();
        f.undo();
        f.undo();
        assertEquals(g, f);
    }

    @Test
    void TestRedoAddPlayList(){
        PlayList g = f.clone();
        assertEquals(g, f);
        f.undo();
        assertNotEquals(g, f);
        g.redo();
        assertEquals(g, f);
    }

    @Test
    void TestRedoShufflePlayList(){
        PlayList g = f.clone();
        g.shuffle();
        assertNotEquals(g, f);
        g.undo();
        assertEquals(g, f);
        g.redo();
        assertNotEquals(g, f);
    }

    @Test
    void TestRedoSetNamePlayList(){
        PlayList g = f.clone();
        assertEquals(g.getName(), f.getName());
        g.setName("karaboochi");
        assertNotEquals(g.getName(), f.getName());
        g.undo();
        assertEquals(g.getName(), f.getName());
        g.redo();
        assertNotEquals(g.getName(), f.getName());
    }

    @Test
    void TestRedoRemovePlayList(){
        PlayList g = f.clone();
        assertEquals(g, f);
        g.removePlayable(1);
        assertNotEquals(g, f);
        g.undo();
        assertEquals(g, f);
        g.redo();
        assertNotEquals(g, f);
    }

    @Test
    void TestRedoPlayList(){
        PlayList g = f.clone();
        f.undo();
        f.undo();
        f.redo();
        f.redo();
        assertEquals(g, f);
    }

    @Test
    void TestRedoMoreThanUndoPlayList(){
        f.undo();
        f.undo();
        f.redo();
        f.redo();
        PlayList g = f.clone();
        f.redo();
        f.redo();
        assertEquals(g, f);
    }

    @Test
    void TestRedoMoreAfterPlayablePlayList(){
        f.undo();
        f.addPlayable(song3);
        PlayList g = f.clone();
        f.redo();
        f.addPlayable(song2);
        f.redo();
        assertNotEquals(g, f);
        f.redo();
        f.redo();
        g.addPlayable(song3);
        g.addPlayable(song2);
        assertEquals(g, f);
    }

















}

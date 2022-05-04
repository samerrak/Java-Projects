
import java.util.*;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {

    private List<Playable> aList = new LinkedList<>();
    private String aName;
    private final CommandProcessor commands = new CommandProcessor();

    public PlayList(String pName) {
        assert pName != null;
        aName = pName;
    }

    public void addPlayable(Playable pPlayable) {
        assert pPlayable != null;
        Command command = new AddShortcut(pPlayable, aList);
        commands.consume(command);
    }


    public Playable removePlayable(int pIndex) {
        assert pIndex >= 0 && pIndex < aList.size();
        RemoveShortcut command = new RemoveShortcut(pIndex, aList);
        commands.consume(command);
        return command.getRemoved();
    }


    public void shuffle() {
        assert !(aList.isEmpty());
        class ShuffleShortcut implements Command{

            private List<Playable> preList;

            @Override
            public void click() {
                preList = new ArrayList<>(aList);
                Collections.shuffle(aList);

                while (preList.equals(aList))
                    Collections.shuffle(aList);

            }

            @Override
            public void redo() {
                click();
            }

            @Override
            public void undo() {
                aList = new ArrayList<>(preList);
            }


        }
        Command command = new ShuffleShortcut();
        commands.consume(command);
    }


    public void setName(String pName) {
        assert pName != null;
        class NameShortcut implements Command {
            private String preName;
            private String postName;
            NameShortcut(String pName)
            {
                postName = pName;
            }
            @Override
            public void click() {
                preName = aName;
                aName = postName;
            }
            @Override
            public void redo() {
                click();
            }

            @Override
            public void undo() {
                aName = preName;
            }
        }
        Command command = new NameShortcut(pName);
        commands.consume(command);
    }

    public void undo()
    {
        class UndoShortcut implements Command {
        @Override
        public void click() {
            commands.undoLast();
        }
        }
        Command command = new UndoShortcut();
        command.click();
        ;
    }

    public void redo()
    {
        class RedoShortcut implements Command {
            @Override
            public void click() {
                commands.redoLast();
            }
        }
        Command command = new RedoShortcut();
        command.click();
    }

    public String getName() {
        return aName;
    }

    @Override
    public void play() {
        for(Playable playable:aList){
            playable.play();
        }
    }

    @Override
    public PlayList clone() {
        try {
            PlayList clone = (PlayList) super.clone();
            clone.aList = new LinkedList<>();
            clone.aName = aName;
            for (Playable pl: aList)
            {
                clone.aList.add(pl.clone());
            }
            return clone;

        }
        catch (CloneNotSupportedException e) {
            assert false;
            return null;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return this.aList.equals(playList.aList);
    }


    @Override
    public int hashCode() {
        return Objects.hash(aList);
    }

    @Override
    public String toString() {
        return " " + aList;
    }


}

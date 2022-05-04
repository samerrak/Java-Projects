import java.util.List;

public class RemoveShortcut implements Command {
    private List<Playable> aList;
    private final int index;
    private Playable removed;



    RemoveShortcut(int index, List<Playable> aList) {
        this.aList = aList;
        this.index = index;
    }

    @Override
    public void click() {
        this.removed = aList.remove(index);
    }

    @Override
    public void redo() {
        click();
    }

    @Override
    public void undo() {
        aList.add(index, removed);
    }

    public Playable getRemoved(){
        return removed;
    }

}

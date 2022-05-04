

import java.util.List;

public class AddShortcut implements Command {

    private List<Playable> aList;
    private final Playable added;

    AddShortcut(Playable added, List<Playable> aList) {
        this.aList = aList;
        this.added = added;
    }

    @Override
    public void click() {
        aList.add(added);
    }

    @Override
    public void redo() {
        click();
    }

    @Override
    public void undo() {
        aList.remove(aList.size() - 1);
    }


}

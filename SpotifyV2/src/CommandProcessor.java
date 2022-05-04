import java.util.ArrayList;
import java.util.List; //Credit Code to Martin Robillard
import java.util.ArrayList;
import java.util.List; //Credit Code to Martin Robillard

public class CommandProcessor
{
    private final List<Command> aExecutedCommands = new ArrayList<>();
    private final List<Command> aUndoneCommands = new ArrayList<>();

    public void consume(Command pCommand)
    {
        pCommand.click();
        aExecutedCommands.add(pCommand);
    }

    public void undoLast()
    {
        if (!aExecutedCommands.isEmpty()) {
            Command command = aExecutedCommands.remove(aExecutedCommands.size() - 1);
            command.undo();
            aUndoneCommands.add(command);
        }

    }

    public void redoLast()
    {
        if (aExecutedCommands.size() == 1 && aUndoneCommands.isEmpty())
        {
            aExecutedCommands.remove(aExecutedCommands.size()-1).click();

        }
        else {
            if (aUndoneCommands.isEmpty()) {
                aExecutedCommands.clear();
            } else {
                Command command = aUndoneCommands.remove(aUndoneCommands.size() - 1);
                consume(command);
                aExecutedCommands.add(command);
            }
        }
    }

}


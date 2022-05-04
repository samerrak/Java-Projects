import java.util.Optional;

public interface Command
{
    void click();
    default void redo(){};
    default void undo(){};

}
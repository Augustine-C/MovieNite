package command;

import java.util.ArrayList;
import java.util.Stack;

public class Switch {
    Stack<ICommand> commands;

    private static Switch s;

    public static Switch getSwitch() {
        if (s == null) {
            s = new Switch();
        }
        return s;
    }

    private Switch() {
        this.commands = new Stack();
    }

    public void switchPanel(ICommand command) {
        commands.add(command);
        command.execute();
    }

    public void undo() {
        try {
            commands.pop().unexecute();
        } catch (Exception e) {

        }
    }

}

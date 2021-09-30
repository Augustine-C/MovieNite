package command;

import javax.swing.JPanel;

import panels.AddEventPanel;
import testtest.MenuSingleton;

public class PanelCommand implements ICommand {

    JPanel panel;
    private JPanel previousPanel;

    public PanelCommand(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        previousPanel = MenuSingleton.getMenuSingleton().currentPanel();
        MenuSingleton.getMenuSingleton().switchPanel(panel);
    }

    @Override
    public void unexecute() {
        // TODO Auto-generated method stub
        MenuSingleton.getMenuSingleton().switchPanel(previousPanel);
    }
}

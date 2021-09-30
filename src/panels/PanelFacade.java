package panels;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import service.AddFriendService;
import service.DatabaseConnectionService;
import testtest.MenuSingleton;

public class PanelFacade {

    private ShowFriendPanel sfPanel;
    private ChangePasswordPanel cpPanel;
    private ShowEventPanel sePanel;
    private ShowAttendEventPanel saPanel;
    private LoginPanel lpPanel;
    private MenuSingleton mSingleton;
    private JFrame frame;

    public void addPaneltoFrame(JFrame frame) {
        this.sfPanel = ShowFriendPanel.getShowFriendPanel();
        this.cpPanel = ChangePasswordPanel.getChangePasswordPanel();
        this.sePanel = ShowEventPanel.getShowEventPanel();
        this.saPanel = ShowAttendEventPanel.getShowAttendEventPanel();
        this.lpPanel = LoginPanel.getLoginPanel();
        this.frame = frame;
        frame.getContentPane().add(cpPanel);
        frame.getContentPane().add(sePanel);
        frame.getContentPane().add(sfPanel);
        frame.getContentPane().add(saPanel);
        frame.getContentPane().add(lpPanel);

        cpPanel.setVisible(false);
        sePanel.setVisible(false);
        sfPanel.setVisible(false);
        saPanel.setVisible(false);
        lpPanel.setVisible(false);
    }

    public void setFrame() {
        mSingleton = MenuSingleton.getMenuSingleton();
        mSingleton.setFrame(frame);
        frame.getContentPane().add(mSingleton.getMenuBar());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                try {
                    DatabaseConnectionService.getdbConnectionService().closeConnection();
                    System.out.println("Connection Closed");
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        });
    }

    public void switchPanel() {
        mSingleton.switchPanel(lpPanel);
    }

}

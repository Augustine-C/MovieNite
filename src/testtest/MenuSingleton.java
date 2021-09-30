package testtest;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import command.ICommand;
import command.PanelCommand;
import command.Switch;
import panels.ChangePasswordPanel;
import panels.LoginPanel;
import panels.MoviePanel;
import panels.NonUserPanelCreator;
import panels.PanelCreator;
import panels.ShowAttendEventPanel;
import panels.ShowEventPanel;
import panels.ShowFriendPanel;
import panels.UserPanelCreator;
import service.UserService;

public class MenuSingleton extends JMenuBar {
    private static MenuSingleton mSingleton;
    private JMenuBar menuBar;
    private JMenuItem mntmChange;
    private JMenuItem mntmHost;
    private JMenuItem mntmAttend;
    private JMenuItem mntmFriend;
    private JMenuItem mntmSearchMovie;
    private JPanel currentPanel;
    private UserService userService;
    private JFrame frame;
    private PanelCreator pCreator;
    private JMenuItem mntmLogin;
    private Switch s;
    private JMenuItem mntmBack;

    private MenuSingleton() {
        userService = UserService.getUserService();

        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 882, 26);

        JMenu mnMenu = new JMenu("menu");
        menuBar.add(mnMenu);

        mntmChange = new JMenuItem("ChangePersonalInfo");
        mntmChange.setHorizontalAlignment(SwingConstants.LEFT);
        mnMenu.add(mntmChange);

        mntmHost = new JMenuItem("ViewHostEvents");

        mntmHost.setHorizontalAlignment(SwingConstants.LEFT);
        mnMenu.add(mntmHost);

        mntmAttend = new JMenuItem("ViewAttendEvents");
        mntmAttend.setHorizontalAlignment(SwingConstants.LEFT);
        mnMenu.add(mntmAttend);

        mntmFriend = new JMenuItem("ViewFriends");
        mntmFriend.setHorizontalAlignment(SwingConstants.LEFT);
        mnMenu.add(mntmFriend);

        mntmSearchMovie = new JMenuItem("Search Movie");
        mntmSearchMovie.setHorizontalAlignment(SwingConstants.LEFT);
        mnMenu.add(mntmSearchMovie);

        mntmLogin = new JMenuItem("Login");
        mntmLogin.setHorizontalAlignment(SwingConstants.LEFT);
        mnMenu.add(mntmLogin);

        mntmBack = new JMenuItem("go back");
        mntmBack.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mntmBack);

        s = Switch.getSwitch();

        mntmBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                s.undo();
            }
        });

        mntmChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (userService.isLogin() == true) {
                    ICommand command = new PanelCommand(ChangePasswordPanel.getChangePasswordPanel());
                    // mSingleton.switchPanel(ChangePasswordPanel.getChangePasswordPanel());
                    s.switchPanel(command);
                }
            }
        });

        mntmAttend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (userService.isLogin() == true) {
                    ICommand command = new PanelCommand(ShowAttendEventPanel.getShowAttendEventPanel());
                    // mSingleton.switchPanel(ShowAttendEventPanel.getShowAttendEventPanel());
                    s.switchPanel(command);
                }
            }
        });

        mntmFriend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (userService.isLogin() == true) {
                    // mSingleton.switchPanel(ShowFriendPanel.getShowFriendPanel());
                    ICommand command = new PanelCommand(ShowFriendPanel.getShowFriendPanel());
                    s.switchPanel(command);
                }

            }
        });

        mntmLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // mSingleton.switchPanel(LoginPanel.getLoginPanel());
                ICommand command = new PanelCommand(LoginPanel.getLoginPanel());
                s.switchPanel(command);

            }
        });

        mntmSearchMovie.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PanelCreator pCreator;
                if (userService.isLogin() == true) {
                    pCreator = new UserPanelCreator();

                } else {
                    pCreator = new NonUserPanelCreator();
                }
                mSingleton.setPanelCreator(pCreator);
                mSingleton.switchtoMoviePanel();
            }
        });

        mntmHost.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (userService.isLogin() == true) {
                    // mSingleton.switchPanel(ShowEventPanel.getShowEventPanel());
                    ICommand command = new PanelCommand(ShowEventPanel.getShowEventPanel());
                    s.switchPanel(command);
                }
            }
        });

    }

    public void setPanelCreator(PanelCreator pCreator) {
        this.pCreator = pCreator;
    }

    //
    public static MenuSingleton getMenuSingleton() {
        if (mSingleton == null) {
            mSingleton = new MenuSingleton();
        }
        return mSingleton;
    }

    //
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void switchPanel(JPanel newPanel) {

        if (currentPanel == null) {
            this.currentPanel = newPanel;
        } else {
            this.currentPanel.setVisible(false);
        }
        this.currentPanel = newPanel;
        if (ShowEventPanel.class.isInstance(newPanel)) {
            ((ShowEventPanel) newPanel).populateEvents();
            ((ShowEventPanel) newPanel).populateFriends();
        }
        this.currentPanel.repaint();
        this.currentPanel.setVisible(true);
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void switchtoMoviePanel() {
        MoviePanel moviePanel = this.pCreator.createmoviePanel();
        this.frame.getContentPane().add(moviePanel);
        ICommand command = new PanelCommand(moviePanel);
        s.switchPanel(command);
        // this.switchPanel(moviePanel);

    }

    public JPanel currentPanel() {
        return this.currentPanel;
    }

}

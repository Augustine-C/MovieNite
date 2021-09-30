package testtest;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import panels.AddFriendPanel;
import panels.ChangePasswordPanel;
import panels.LoginPanel;
import panels.MoviePanel;
import panels.NonUserPanelCreator;
import panels.PanelCreator;
import panels.PanelFacade;
import panels.ShowAttendEventPanel;
import panels.ShowEventPanel;
import panels.ShowFriendPanel;
import panels.UserPanelCreator;
import service.AddFriendService;
import service.DatabaseConnectionService;
import service.EventService;
import service.FriendService;
import service.MovieService;

import javax.swing.JPasswordField;

/**
 * TODO Put here a description of what this class does.
 *
 * @author cuiy1. Created Apr 24, 2019.
 */
public class App {

    protected JFrame frame;
    protected JTextField userNameText;
    protected DatabaseConnectionService dbService;

    // protected UserService userService;
    protected MovieService movieService;

    private JPasswordField passwordText;
    private Component currentPanel;
    private AddFriendPanel afPanel;
    private ChangePasswordPanel cpPanel;
    private EventService eventService;
    private FriendService friendService;
    private AddFriendService afService;
    private ShowEventPanel sePanel;
    private MoviePanel moviePanel;
    private ShowFriendPanel sfPanel;
    private ShowAttendEventPanel saPanel;
    private Movie currentMovie;
    private MenuSingleton mSingleton;
    private LoginPanel lpPanel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Class.forName("com.mysql.jdbc.Driver");
                    App window = new App();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public App() {

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.dbService = DatabaseConnectionService.getdbConnectionService();

        if (dbService.connect()) {
            System.out.println("Connected to database");
        }

        frame = new JFrame();

        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        PanelFacade pf = new PanelFacade();
        pf.addPaneltoFrame(frame);
        pf.setFrame();
        pf.switchPanel();

    }
}

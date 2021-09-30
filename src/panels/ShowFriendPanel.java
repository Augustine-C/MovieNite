package panels;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import service.AddFriendService;
import service.FriendService;
import service.MovieService;
import testtest.FriendTableModel;
import testtest.IsFriendWith;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowFriendPanel extends JPanel {
    private JTable friendTable;
    private JTable unprocessedFriendTable;
    private ArrayList<String> friends = new ArrayList<>();
    private ArrayList<String> application = new ArrayList<>();
    private FriendService fService;
    public AddFriendService afService = null;
    JTextField friendUsername = null;
    String selectedID = "";

    private static ShowFriendPanel sfPanel;

    public static ShowFriendPanel getShowFriendPanel() {
        if (sfPanel == null) {
            sfPanel = new ShowFriendPanel(FriendService.getFriendService());
        }
        return sfPanel;
    }

    /**
     * Create the panel.
     */
    public ShowFriendPanel(FriendService fService) {
        this.fService = fService;
        setBounds(0, 24, 882, 529);
        setLayout(null);
        setVisible(true);
        setLayout(null);

        friendTable = new JTable();
        friendTable.setFont(new Font("Tahoma", Font.PLAIN, 17));
        friendTable.setModel(new DefaultTableModel(new Object[][] { { "" }, { null }, }, new String[] { "Friends" }));
        // friendTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        friendTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        friendTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        friendTable.setBounds(28, 72, 150, 311);
        JScrollPane scrollPane = new JScrollPane(friendTable);
        friendTable.setFillsViewportHeight(true);
        scrollPane.setBounds(28, 72, 150, 311);
        add(scrollPane);

        JButton btnShowFriends = new JButton("Refresh");
        btnShowFriends.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("Show friends");
                // friends = fService.findFriend();
                friends = new ArrayList<>();

                ArrayList<IsFriendWith> isFriendWith = fService.findFriend();
                for (IsFriendWith ifw : isFriendWith) {
                    if (ifw.getValue("User1").equals(fService.getUser())) {
                        friends.add(ifw.getValue("User2"));
                    } else {
                        friends.add(ifw.getValue("User1"));
                    }
                }
                showFriends();

                application = new ArrayList<>();
                ArrayList<IsFriendWith> isFriendWithUnprocessed = fService.findUnprocessedFriend();
                for (IsFriendWith ifw : isFriendWithUnprocessed) {
                    if (ifw.getValue("User1").equals(fService.getUser())) {
                        application.add(ifw.getValue("User2"));
                        System.out.println(ifw.getValue("User2"));
                    } else {
                        application.add(ifw.getValue("User1"));
                        System.out.println(ifw.getValue("User1"));
                    }
                }
                showUnprocessedFriends();

            }

        });
        btnShowFriends.setBounds(131, 411, 139, 25);
        add(btnShowFriends);

        unprocessedFriendTable = new JTable();
        unprocessedFriendTable.setFont(new Font("Tahoma", Font.PLAIN, 17));
        unprocessedFriendTable
                .setModel(new DefaultTableModel(new Object[][] { { "" }, { null }, }, new String[] { "Application" }));

        // unprocessedFriendTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        unprocessedFriendTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        unprocessedFriendTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        unprocessedFriendTable.setBounds(200, 72, 150, 311);
        JScrollPane scrollPane1 = new JScrollPane(unprocessedFriendTable);
        unprocessedFriendTable.setFillsViewportHeight(true);
        scrollPane1.setBounds(253, 72, 150, 311);
        add(scrollPane1);

        unprocessedFriendTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                int col = table.columnAtPoint(p);
                if ((col == 0) && (me.getClickCount() == 2)) {
                    selectedID = (String) table.getValueAt(row, col);

                }
            }

        });

        JButton approveFriend = new JButton("Approve");
        approveFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (selectedID.equals("")) {
                    System.out.println("Please select an ID");
                } else {
                    fService.approveFriend(selectedID);
                    selectedID = "";
                }
            }

        });
        approveFriend.setBounds(515, 225, 139, 25);
        add(approveFriend);

        JButton rejectFriend = new JButton("Reject");
        rejectFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (selectedID.equals("")) {
                    System.out.println("Please select a user");
                } else {
                    fService.rejectFriend(selectedID);
                    selectedID = "";
                }
            }

        });
        rejectFriend.setBounds(515, 275, 139, 25);
        add(rejectFriend);

        this.afService = afService;

        JLabel label = new JLabel("Add Friend:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label.setBounds(451, 72, 104, 16);
        add(label);

        this.friendUsername = new JTextField();
        this.friendUsername.setColumns(10);
        this.friendUsername.setBounds(451, 101, 309, 22);
        add(this.friendUsername);

        JButton button = new JButton("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addFriend();
            }

        });
        button.setBounds(532, 148, 97, 25);
        add(button);

        friendTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                int col = table.columnAtPoint(p);
                if ((col == 0) && (me.getClickCount() == 2)) {
                    String friend = table.getValueAt(row, col).toString();
                    MovieService.getMovieService().setUser(friend);

                }
            }

        });

    }

    public void showFriends() {
        if (friends == null) {
            System.out.println("NO Friends!");
            return;
        }
        friendTable.setModel(new FriendTableModel(friends, "friends"));

    }

    public void showUnprocessedFriends() {
        if (application == null) {
            System.out.println("NO Unprocessed Friends!");
            return;
        }
        unprocessedFriendTable.setModel(new FriendTableModel(application, "application"));

    }

    private boolean addFriend() {
        String friend = null;
        friend = this.friendUsername.getText();
        if (friend.isEmpty() || friend == null) {
            System.out.println("friend user name cannot be null");
            return false;
        }
        System.out.println(friend);
        if (this.fService.addFriend(friend)) {
            this.friendUsername.setText("");
            return true;
        }
        return false;

    }

}

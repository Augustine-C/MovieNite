package panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import command.ICommand;
import command.PanelCommand;
import command.Switch;
import service.EventService;
import service.VoteService;
import testtest.App;
import testtest.Event;
import testtest.EventTableModel;
import testtest.MenuSingleton;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

public class ShowEventPanel extends TemplatePanel {
    private JTable table;
    private ArrayList<Event> ans;
    private EventService seService;
    private JComboBox comboBox;
    private JComboBox comboBox_1;
    private VotePanel vPanel;
    private VoteService vService;
    JTextField location = null;
    JTextField dateAndTime = null;

    private static ShowEventPanel sePanel;

    public static ShowEventPanel getShowEventPanel() {
        if (sePanel == null) {
            sePanel = new ShowEventPanel();
        }
        return sePanel;
    }

    /**
     * Create the panel.
     */
    private ShowEventPanel() {
        createPanel();
    }

    protected void switchtoVote(String id) {
        // TODO Auto-generated method stub
        if (vPanel == null) {
            this.vPanel = VotePanel.getVotePanel();

            MenuSingleton.getMenuSingleton().getFrame().getContentPane().add(vPanel);
        }
        // this.vPanel.changeMovie(movie);
        this.vPanel.setEvent(id);
        ICommand command = new PanelCommand(vPanel);
        Switch.getSwitch().switchPanel(command);
        // MenuSingleton.getMenuSingleton().switchPanel(vPanel);

    }

    public void populateEvents() {
        comboBox.removeAllItems();
        comboBox.addItem("None");
        if (seService.GetEvents() == null)
            return;
        System.out.println("call populate events");
        for (String s : seService.GetEvents()) {
            comboBox.addItem(s);
        }
    }

    public void populateFriends() {
        comboBox_1.removeAllItems();
        comboBox_1.addItem("None");
        if (seService.GetFriends() == null)
            return;
        for (String s : seService.GetFriends()) {
            comboBox_1.addItem(s);
        }
    }

    public void showHostEvent() {
        if (ans == null) {
            System.out.println("NO EVENT!");
            return;
        }
        table.setModel(new EventTableModel(ans));

    }

    private boolean addEvent() {
        String locat = null;
        String date = null;
        locat = this.location.getText();
        date = this.dateAndTime.getText();
        System.out.println(locat);
        if (locat.isEmpty() || locat == null) {
            System.out.println("location cannot be null");
            return false;
        }
        if (date.isEmpty() || date == null) {
            System.out.println("date cannot be null");
            return false;
        }

        if (this.seService.addEvent(locat, date)) {
            this.location.setText("");
            this.dateAndTime.setText("");
            return true;
        }
        return false;

    }

    @Override
    public void setService() {
        this.seService = EventService.getEventService();

    }

    @Override
    public void init() {
        setVisible(true);
        setLayout(null);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 17));
        table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, },
                new String[] { "EventID", "Location", "Time", "Host" }));
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setBounds(28, 72, 437, 311);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                int col = table.columnAtPoint(p);
                try {
                    if ((col == 0) && (me.getClickCount() == 2)) {
                        String selectID = (String) table.getValueAt(row, col);
                        if (selectID == null || selectID.equals("")) {
                            return;
                        }
                        switchtoVote(selectID);

                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setBounds(215, 84, 437, 311);
        add(scrollPane);
        this.setBounds(0, 24, 882, 529);

        JButton btnShowHostEvent = new JButton("Show Host Event");
        btnShowHostEvent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("SHow event");
                ans = seService.findHostEvent();
                showHostEvent();

            }

        });
        btnShowHostEvent.setBounds(357, 408, 139, 25);
        add(btnShowHostEvent);

        JLabel lblEventid = new JLabel("EventID");
        lblEventid.setBounds(718, 137, 56, 16);
        add(lblEventid);

        JLabel lblUserid = new JLabel("Friend");
        lblUserid.setBounds(718, 195, 56, 16);
        add(lblUserid);

        JButton btnAddAttend = new JButton("Add Attend");
        btnAddAttend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("Add Attend");
                String selectedEvent = (String) comboBox.getSelectedItem();
                String selectedFriend = (String) comboBox_1.getSelectedItem();

                String eventSearch = selectedEvent == "None" ? null : selectedEvent;
                String friendSearch = selectedFriend == "None" ? null : selectedFriend;

                if (seService.addAttend(eventSearch, friendSearch)) {
                    System.out.println("successfull add attend");
                } else {
                    System.err.println("bad add attend");
                }

            }

        });
        btnAddAttend.setBounds(697, 259, 97, 25);
        add(btnAddAttend);

        comboBox = new JComboBox();
        populateEvents();
        comboBox.setBounds(697, 166, 97, 22);
        add(comboBox);

        comboBox_1 = new JComboBox();
        populateFriends();
        comboBox_1.setBounds(697, 224, 97, 22);
        add(comboBox_1);

        this.seService = seService;
        JLabel label = new JLabel("Location");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label.setBounds(10, 110, 104, 16);
        add(label);

        this.location = new JTextField();
        this.location.setColumns(10);
        this.location.setBounds(10, 139, 200, 22);
        add(this.location);

        this.dateAndTime = new JTextField();
        this.dateAndTime.setColumns(10);
        this.dateAndTime.setBounds(10, 236, 200, 22);
        add(this.dateAndTime);

        JLabel label_1 = new JLabel("dateandtime");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_1.setBounds(10, 203, 104, 16);
        add(label_1);

        JButton button = new JButton("Add");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addEvent();
            }

        });
        button.setBounds(40, 290, 97, 25);
        add(button);

    }
}

package panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import service.EventService;
import service.VoteService;
import testtest.Event;
import testtest.EventTableModel;
import testtest.MenuSingleton;

import java.awt.Font;
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
import javax.swing.JComboBox;

public class ShowAttendEventPanel extends JPanel {
    private JTable table;
    private ArrayList<Event> ans;
    private EventService seService;
    private JComboBox comboBox;
    private JComboBox comboBox_1;
    private VotePanel vPanel;
    private VoteService vService;

    private static ShowAttendEventPanel saPanel;

    public static ShowAttendEventPanel getShowAttendEventPanel() {
        if (saPanel == null) {
            saPanel = new ShowAttendEventPanel(EventService.getEventService());
        }
        return saPanel;
    }

    /**
     * Create the panel.
     */
    public ShowAttendEventPanel(EventService seService) {
        this.seService = seService;
        setBounds(0, 24, 882, 529);
        setLayout(null);
        setVisible(true);
        setLayout(null);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 17));
        table.setModel(new DefaultTableModel(new Object[][] { { "", null, null, null }, { null, null, null, null }, },
                new String[] { "EventID", "Location", "Time", "Host" }));
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setBounds(28, 72, 437, 311);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
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
        scrollPane.setBounds(215, 84, 437, 311);
        add(scrollPane);
        this.setBounds(0, 24, 882, 529);

        JButton btnShowHostEvent = new JButton("Show Attend Event");
        btnShowHostEvent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("Show event");
                ans = seService.findAttendEvent();
                showHostEvent();

            }

        });
        btnShowHostEvent.setBounds(357, 408, 191, 25);
        add(btnShowHostEvent);

    }

    public void showHostEvent() {
        if (ans == null) {
            System.out.println("NO EVENT!");
            return;
        }
        table.setModel(new EventTableModel(ans));

    }

    protected void switchtoVote(String id) {
        // TODO Auto-generated method stub
        if (vPanel == null) {
            this.vPanel = VotePanel.getVotePanel();
            MenuSingleton.getMenuSingleton().getFrame().getContentPane().add(vPanel);
        }
        // this.vPanel.changeMovie(movie);
        this.vPanel.setEvent(id);
        MenuSingleton.getMenuSingleton().switchPanel(vPanel);

    }
}

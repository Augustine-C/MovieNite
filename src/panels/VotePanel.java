package panels;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import service.VoteService;
import testtest.Movie;
import testtest.MovieFinder;
import testtest.Vote;
import testtest.VoteTableModel;

public class VotePanel extends JPanel {
    private JTextField textField;
    private MovieFinder movieFinder;
    private String EventID;
    private String Moive;
    private JTable table;
    private VoteService vService;
    private ArrayList<Vote> ans;
    private Movie movie;
    private JTable table_1;

    private static VotePanel vPanel;

    public static VotePanel getVotePanel() {
        if (vPanel == null) {
            vPanel = new VotePanel(VoteService.getVoteService());
        }
        return vPanel;
    }

    /**
     * Create the panel.
     */
    private VotePanel(VoteService vService) {
        this.movie = null;
        this.vService = vService;
        setLayout(null);
        textField = new JTextField();
        textField.setBounds(453, 188, 116, 22);
        add(textField);
        textField.setColumns(10);

        JLabel lblMovieTitle = new JLabel("Movie Title");
        lblMovieTitle.setBounds(476, 160, 62, 16);
        add(lblMovieTitle);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(581, 187, 73, 25);
        add(btnSearch);
        DefaultListModel model = new DefaultListModel();

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                model.clear();
                searchMovie(textField.getText());

            }
        });
        this.movieFinder = new MovieFinder(model);

        JList<String> list = new JList<String>(model);
        this.movieFinder = new MovieFinder(model);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(402, 258, 252, 151);

        list.setBounds(402, 258, 252, 151);
        add(scrollPane);
        this.setBounds(0, 24, 882, 529);

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    // System.out.println(list.getModel().getElementAt(index));
                    // switchToDetail(index);
                    Moive = list.getModel().getElementAt(index);
                    // changeVoteFromList(index, Moive);
                    movie = movieFinder.buildMovieDetail(index + 1);
                }
            }
        };
        list.addMouseListener(mouseListener);

        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(51, 213, 252, 231);
        add(scrollPane2);

        table = new JTable();
        scrollPane2.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 17));
        table.setModel(new DefaultTableModel(new Object[][] { { "", null, null, null }, { null, null, null, null }, },
                new String[] { "Movie", "Vote" }));
        // table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                if (table == null)
                    return;
                if (table.getRowCount() == 0)
                    return;
                if (vService.getListMovie().size() == 0)
                    return;
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                int col = table.columnAtPoint(p);
                try {
                    if ((col == 0) && (me.getClickCount() == 1)) {
                        String selectMovie = (String) table.getValueAt(row, col);
                        // switchtoVote();
                        Moive = selectMovie;
                        // changeVoteFromVotes(row);
                        movie = vService.getListMovie().get(row);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        });
        table.setFillsViewportHeight(true);

        JButton btnShowVotes = new JButton("Show Votes");
        btnShowVotes.setBounds(106, 457, 123, 25);
        btnShowVotes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // System.out.println("SHow event");
                ans = vService.findVotes();
                showVote();

            }

        });
        add(btnShowVotes);
        JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setBounds(51, 66, 252, 110);
        add(scrollPane3);

        table_1 = new JTable();
        table_1.setBounds(51, 66, 252, 110);
        table_1 = new JTable();
        scrollPane3.setViewportView(table_1);
        table_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        table_1.setModel(new DefaultTableModel(new Object[][] { { "", null, null, null }, { null, null, null, null }, },
                new String[] { "Movie", "Vote" }));
        // table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table_1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table_1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                if (table.getRowCount() == 0)
                    return;
                if (vService.getVoteMovie().size() == 0)
                    return;
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                int col = table.columnAtPoint(p);
                try {
                    if ((col == 0) && (me.getClickCount() == 1)) {
                        String selectMovie = (String) table.getValueAt(row, col);
                        // switchtoVote();
                        Moive = selectMovie;
                        movie = vService.getVoteMovie().get(row);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        });
        table_1.setFillsViewportHeight(true);

        JLabel lblYourVotes = new JLabel("Your votes");
        lblYourVotes.setBounds(140, 31, 116, 22);
        add(lblYourVotes);

        JButton btnCancel = new JButton("Show your votes");
        btnCancel.setBounds(51, 175, 139, 25);
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // System.out.println("SHow event");
                ans = vService.findVotesByUser();
                showYourVote();
            }
        });
        add(btnCancel);

        JButton btnAddVote = new JButton("Add Vote");
        btnAddVote.setBounds(307, 457, 97, 25);
        btnAddVote.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // System.out.println("SHow event");
                // ans = vService.findVotesByUser(UserID);
                // showYourVote();
                changeVoteFromList();
            }
        });
        add(btnAddVote);

        JButton btnCancel_1 = new JButton("Cancel");
        btnCancel_1.setBounds(206, 175, 97, 25);
        btnCancel_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // System.out.println("SHow event");
                // ans = vService.findVotesByUser(UserID);
                // showYourVote();
                changeVoteFromList();

            }

        });
        add(btnCancel_1);
    }

    protected void showVote() {
        // TODO Auto-generated method stub
        if (ans == null) {
            System.out.println("NO Vote!");
            return;
        }
        table.setModel(new VoteTableModel(ans));

    }

    protected void showYourVote() {
        // TODO Auto-generated method stub
        if (ans == null) {
            System.out.println("NO Vote!");
            return;
        }
        table_1.setModel(new VoteTableModel(ans));

    }

    public boolean changeVoteFromVotes(int index) {
        if (this.movie == null) {
            JOptionPane.showMessageDialog(null, "Please select a movie to vote");
            return false;
        }
        System.out.println(this.movie.title);
        if (this.vService.AddVotestoMovie(EventID, this.movie.imdbID)) {
            JOptionPane.showMessageDialog(null, "Vote change succesfully");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Vote failed.");
        return false;
    }

    public boolean changeVoteFromList() {
        // this.movie = this.movieFinder.buildMovieDetail(index + 1);
        if (this.movie == null) {
            JOptionPane.showMessageDialog(null, "Please select a movie to vote");
            return false;
        }
        if (this.vService.AddVotestoMovie(EventID, this.movie.imdbID)) {
            JOptionPane.showMessageDialog(null, "Vote change succesfully");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Vote failed.");
        return false;
        // return false;
    }

    public void searchMovie(String title) {
        this.movieFinder.buildResponse(title);
        this.movieFinder.buildResults();
    }

    public void setEvent(String id) {
        this.EventID = id;
        this.vService.setEvent(id);
    }

    public void setMovie(String movie) {
        this.Moive = movie;
    }
}

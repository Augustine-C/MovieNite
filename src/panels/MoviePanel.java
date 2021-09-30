package panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import command.ICommand;
import command.PanelCommand;
import command.Switch;
import service.MovieService;
import testtest.MenuSingleton;
import testtest.Movie;
import testtest.MovieFinder;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.event.ActionEvent;

/**
 * TODO Put here a description of what this class does.
 *
 * @author cuiy1. Created Apr 24, 2019.
 */
public class MoviePanel extends TemplatePanel {
    private JTextField textField;
    private MovieFinder movieFinder;
    public Movie movie;
    private MovieDetails mDetails;
    private MovieService mService;
    private ArrayList<Movie> likedMovies;
    private JButton btnShowliked;
    private JLabel userlabel;
    private static MoviePanel mPanel;

    public static MoviePanel getMoviePanel() {
        if (mPanel == null) {
            mPanel = new MoviePanel();
        }
        return mPanel;
    }

    /**
     * Create the panel.
     */
    private MoviePanel() {
        createPanel();
    }

    protected void switchToDetail(int index) {
        this.movie = this.movieFinder.buildMovieDetail(index + 1);
        if (mDetails == null) {
            this.mDetails = new MovieDetails(movie, mService);
            MenuSingleton.getMenuSingleton().getFrame().getContentPane().add(mDetails);
        }
        this.mDetails.changeMovie(movie);
        ICommand command = new PanelCommand(mDetails);
        Switch.getSwitch().switchPanel(command);

        // MenuSingleton.getMenuSingleton().switchPanel(mDetails);
    }

    protected void switchToDetail(Movie movie) {
        this.movie = movie;
        if (mDetails == null) {
            this.mDetails = new MovieDetails(movie, mService);
            MenuSingleton.getMenuSingleton().getFrame().getContentPane().add(mDetails);
        }
        this.mDetails.changeMovie(movie);

        ICommand command = new PanelCommand(mDetails);
        Switch.getSwitch().switchPanel(command);
        // MenuSingleton.getMenuSingleton().switchPanel(mDetails);

    }

    public void searchMovie(String title) {
        this.movieFinder.buildResponse(title);
        this.movieFinder.buildResults();
    }

    public void showLikedMovie(DefaultListModel movieModel) {
        mService.showLikedMovie(likedMovies, this.movieFinder);
        for (Movie m : likedMovies) {
            movieModel.addElement(m.title);
        }
    }

    public void disableUserFunc() {
        btnShowliked.setEnabled(false);
        userlabel.setEnabled(false);
    }

    public void enableUserFunc() {
        btnShowliked.setEnabled(true);
        userlabel.setEnabled(true);
        userlabel.setText(MovieService.getMovieService().getUser());

    }

    @Override
    public void setService() {
        mService = MovieService.getMovieService();

    }

    @Override
    public void init() {
        likedMovies = new ArrayList<>();

        setLayout(null);
        textField = new JTextField();
        textField.setBounds(200, 141, 205, 22);
        add(textField);
        textField.setColumns(10);

        userlabel = new JLabel("");
        userlabel.setBounds(427, 61, 69, 20);
        add(userlabel);

        JLabel lblMovieTitle = new JLabel("Movie Title");
        lblMovieTitle.setBounds(98, 144, 87, 16);
        add(lblMovieTitle);

        JButton btnSearch = new JButton("Search");

        btnSearch.setBounds(251, 201, 97, 25);
        add(btnSearch);
        DefaultListModel model = new DefaultListModel();

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                model.clear();
                searchMovie(textField.getText());
            }
        });

        JList<String> list = new JList<String>(model);
        this.movieFinder = new MovieFinder(model);

        JScrollPane scrollPane = new JScrollPane(list);

        list.setBounds(289, 263, 270, 132);
        scrollPane.setBounds(160, 262, 280, 140);
        add(scrollPane);
        this.setBounds(0, 24, 882, 529);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(546, 178, 204, 224);
        add(scrollPane_1);

        DefaultListModel movieModel = new DefaultListModel();
        JList showLikeMovieList = new JList(movieModel);
        scrollPane_1.setViewportView(showLikeMovieList);

        btnShowliked = new JButton("ShowLiked");
        btnShowliked.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                likedMovies = new ArrayList<>();
                movieModel.clear();
                showLikedMovie(movieModel);
            }
        });
        btnShowliked.setBounds(589, 140, 97, 25);
        add(btnShowliked);

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    switchToDetail(index);

                }
            }
        };

        MouseListener mouseListenerMovie = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = showLikeMovieList.locationToIndex(e.getPoint());
                    Movie movie = likedMovies.get(index);
                    switchToDetail(movie);

                }
            }
        };
        list.addMouseListener(mouseListener);
        showLikeMovieList.addMouseListener(mouseListenerMovie);

    }

}

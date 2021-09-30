package panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import service.MovieService;
import testtest.Movie;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MovieDetails extends JPanel {

    private JTextArea plotText;
    private JLabel titleText;
    private JLabel yearText;
    private JLabel genreText;
    private JLabel directorText;
    private JTextArea actorsText;
    private JTextField scoreText;
    private JButton btnScore;
    private JButton btnComment;
    private JButton dislikeButton;
    private JButton likeButton;
    private MovieService movieService;
    private Movie movie;
    private JButton btnRefresh;
    private JScrollPane scrollPane;
    private JTextField avgScoreText;
    private DefaultListModel reviewModel;
    private JTextArea txtrT;
    private String friendUser;

    public MovieDetails(MovieService movieService) {

    }

    public void setFriendUser(String user) {
        this.friendUser = user;
    }

    /**
     * Create the panel.
     */
    public MovieDetails(Movie movie, MovieService movieService) {
        this.movieService = movieService;
        this.movie = movie;
        this.setBounds(0, 24, 882, 529);
        setLayout(null);

        JLabel titleTag = new JLabel(" title");
        titleTag.setHorizontalAlignment(SwingConstants.LEFT);
        titleTag.setBounds(69, 33, 69, 30);
        add(titleTag);

        this.titleText = new JLabel("");
        titleText.setBounds(139, 33, 216, 30);
        titleText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        add(titleText);

        JLabel yearTag = new JLabel(" year");
        yearTag.setHorizontalAlignment(SwingConstants.LEFT);
        yearTag.setBounds(69, 69, 69, 30);
        add(yearTag);

        this.yearText = new JLabel("");
        yearText.setBounds(139, 69, 216, 30);
        yearText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        add(yearText);

        JLabel genraTag = new JLabel(" Genre");
        genraTag.setHorizontalAlignment(SwingConstants.LEFT);
        genraTag.setBounds(69, 109, 69, 30);
        add(genraTag);

        this.genreText = new JLabel("");
        genreText.setBounds(139, 109, 216, 30);
        genreText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        add(genreText);

        JLabel directorTag = new JLabel(" director");
        directorTag.setHorizontalAlignment(SwingConstants.LEFT);
        directorTag.setBounds(69, 145, 69, 30);
        add(directorTag);

        this.directorText = new JLabel("");
        directorText.setBounds(139, 145, 216, 30);
        directorText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        add(directorText);

        this.actorsText = new JTextArea("");
        actorsText.setBackground(UIManager.getColor("Button.background"));
        actorsText.setForeground(SystemColor.desktop);
        actorsText.setLineWrap(true);
        actorsText.setEditable(false);
        actorsText.setFont(new Font("Tahoma", Font.PLAIN, 13));
        actorsText.setBounds(139, 181, 216, 67);
        add(actorsText);

        JLabel actorsTag = new JLabel(" actors");
        actorsTag.setHorizontalAlignment(SwingConstants.LEFT);
        actorsTag.setBounds(69, 181, 69, 30);
        add(actorsTag);

        JLabel plotTag = new JLabel(" plot");
        plotTag.setHorizontalAlignment(SwingConstants.LEFT);
        plotTag.setBounds(69, 247, 69, 30);
        add(plotTag);

        scoreText = new JTextField();
        scoreText.setBounds(69, 451, 146, 30);
        add(scoreText);
        scoreText.setColumns(10);

        btnScore = new JButton("Score(1-10)");

        btnScore.setBounds(227, 452, 115, 29);
        add(btnScore);

        this.txtrT = new JTextArea();
        txtrT.setLineWrap(true);
        txtrT.setBounds(413, 33, 216, 120);
        add(txtrT);

        btnComment = new JButton("Comment");
        btnComment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                addComment(txtrT.getText());
            }
        });
        btnComment.setBounds(634, 33, 115, 29);
        add(btnComment);

        JLabel lblReviews = new JLabel("Reviews");
        lblReviews.setBounds(578, 206, 69, 20);
        add(lblReviews);

        dislikeButton = new JButton();
        dislikeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dislikeMovie();
                checkLikeMovie();
            }
        });
        dislikeButton.setBounds(417, 452, 115, 29);
        add(dislikeButton);

        likeButton = new JButton();
        likeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                likeMovie();
                checkLikeMovie();
            }
        });
        likeButton.setBounds(692, 452, 115, 29);
        add(likeButton);

        btnRefresh = new JButton("Refresh");

        btnRefresh.setBounds(659, 205, 97, 25);
        add(btnRefresh);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(413, 246, 408, 179);
        add(scrollPane);
        this.reviewModel = new DefaultListModel<>();
        JList reviewlist = new JList(reviewModel);
        reviewlist.setValueIsAdjusting(true);
        scrollPane.setViewportView(reviewlist);

        avgScoreText = new JTextField();
        avgScoreText.setHorizontalAlignment(SwingConstants.CENTER);
        avgScoreText.setEditable(false);
        avgScoreText.setBounds(170, 422, 172, 26);
        add(avgScoreText);
        avgScoreText.setColumns(10);

        JLabel lblAvgscore = new JLabel("Avg_Score");
        lblAvgscore.setBounds(69, 425, 87, 20);
        add(lblAvgscore);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(136, 256, 235, 155);
        add(scrollPane_1);

        this.plotText = new JTextArea();
        scrollPane_1.setViewportView(plotText);
        plotText.setLineWrap(true);
        plotText.setEditable(false);
        plotText.setBackground(UIManager.getColor("Button.background"));
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showReview(reviewModel);
                checkLikeMovie();
                showAvgScore(avgScoreText);
            }
        });

        btnScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String score = scoreText.getText();
                if (score == null || score.isEmpty()) {
                    return;
                }
                addScore(score);
                showAvgScore(avgScoreText);
                scoreText.setText("");
            }
        });

        checkLikeMovie();
    }

    protected void showAvgScore(JTextField scoreText) {
        float score = this.movieService.showAvgScore(movie);
        String s;
        if (score == -1) {
            s = "be the first one to score!";
        } else if (score == -2) {
            return;
        } else {
            s = String.valueOf(score);
        }
        scoreText.setText(s);

    }

    protected void showReview(DefaultListModel reviewModel) {
        this.movieService.showReview(this.movie, reviewModel);
    }

    protected void showFriendReview(DefaultListModel reviewModel) {
        this.movieService.showFriendReview(this.movie, reviewModel, friendUser);
    }

    protected void addComment(String text) {
        this.movieService.addComment(text, movie.imdbID);
    }

    protected void addScore(String score) {
        if (!this.movieService.addScore(score, movie.imdbID)) {
            JOptionPane.showMessageDialog(null, "please use valid input");
        }
    }

    public void changeMovie(Movie movie) {
        this.movie = movie;
        this.txtrT.setText("");
        this.reviewModel.clear();
        plotText.setText(movie.plot);
        titleText.setText(movie.title);
        directorText.setText(movie.director);
        genreText.setText(movie.type);
        actorsText.setText(movie.actors);
        yearText.setText(movie.year);
        checkLikeMovie();
        showAvgScore(avgScoreText);

        this.repaint();
    }

    public void likeMovie() {
        int result = this.movieService.likeMovie(movie);
        System.out.println(result);
        if (result == 3) {
            this.movieService.revertLike(movie);
        }
    }

    public void dislikeMovie() {
        if (this.movieService.dislikeMovie(movie) == 3) {
            this.movieService.revertDislike(movie);
        }
    }

    public void checkLikeMovie() {
        int numLikes = this.movieService.numLikes(movie);
        int numDislikes = this.movieService.numDislikes(movie);
        String likeS = "", dislikeS = "";
        if (numLikes >= 0) {
            likeS = "(" + numLikes + ")";
        }
        if (numDislikes >= 0) {
            dislikeS = "(" + numDislikes + ")";
        }
        if (this.movieService.checkLike(movie)) {
            likeButton.setText("Liked" + likeS);
        } else {
            likeButton.setText("like" + likeS);
        }
        if (this.movieService.checkDisLike(movie)) {
            dislikeButton.setText("Disliked" + dislikeS);
        } else {
            dislikeButton.setText("Dislike" + dislikeS);
        }
    }
}

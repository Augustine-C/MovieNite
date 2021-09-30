package panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import testtest.MovieFinder;

public class testing extends JPanel {
    public testing() {
        setLayout(null);
        JTextField textField = new JTextField();
        textField.setBounds(200, 141, 205, 22);
        add(textField);
        textField.setColumns(10);

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
                // searchMovie(textField.getText());
            }
        });

        JList<String> list = new JList<String>(model);
        // this.movieFinder = new MovieFinder(model);

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

        JLabel userlabel = new JLabel("");
        userlabel.setBounds(427, 61, 69, 20);
        add(userlabel);

        JButton btnShowliked = new JButton("ShowLiked");
        btnShowliked.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // likedMovies = new ArrayList<>();
                movieModel.clear();
                // showLikedMovie(movieModel);
            }
        });
    }
}

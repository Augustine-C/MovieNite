package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import testtest.Movie;
import testtest.MovieFinder;
import testtest.Vote;

public class VoteService {
    private DatabaseConnectionService dbService = null;
    private String eventId;
    private MovieFinder movieFinder;
    protected ArrayList<Movie> listMovie;
    protected ArrayList<Movie> voteMovie;

    private static VoteService vService;

    public static VoteService getVoteService() {
        if (vService == null) {
            vService = new VoteService();
        }
        return vService;
    }

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    public ArrayList<Movie> getVoteMovie() {
        return voteMovie;
    }

    public void setVoteMovie(ArrayList<Movie> voteMovie) {
        this.voteMovie = voteMovie;
    }

    private VoteService() {
        this.dbService = DatabaseConnectionService.getdbConnectionService();
        DefaultListModel model = new DefaultListModel();
        this.movieFinder = new MovieFinder(model);
        this.listMovie = new ArrayList<Movie>();
        this.voteMovie = new ArrayList<Movie>();
    }

    public void setEvent(String id) {
        this.eventId = id;
    }

    public boolean AddVotestoMovie(String eventid, String Movie) {
        CallableStatement cs = null;
        try {
            cs = dbService.getConnection().prepareCall("{ ? = call dbo.ChangeVote(?,?,?) }");
            cs.setString(2, UserService.getUserService().getUser());
            cs.setString(3, Movie);
            cs.setString(4, eventid);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1 || result == 2) {
                System.err.println("ChangeVote failed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Vote> findVotes() {
        try {
            Statement stmt = this.dbService.getConnection().createStatement();

            String query = "EXEC ShowVotesOnEvent ?";

            // System.out.println("price = " + price.length());
            // String append = String.format("WHERE Price = %s", price);
            // query = query + " Where price = "+price ;
            // query = query + " Where price = '"+price+"'"
            PreparedStatement update = this.dbService.getConnection().prepareStatement(query);

            // Double newprice = null;
            // System.out.println(query);
            update.setString(1, this.eventId);

            this.dbService.getConnection().setAutoCommit(false);
            ResultSet rs = update.executeQuery();

            ArrayList<Vote> result = parseResults(rs);
            ;
            if (update != null) {
                update.close();
            }
            this.dbService.getConnection().setAutoCommit(true);

            // ResultSet rs = stmt.executeQuery(query);
            return result;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve sodas by restaurant.");
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Vote> findVotesByUser() {
        try {
            Statement stmt = this.dbService.getConnection().createStatement();

            String query = "EXEC ShowVotesByUser ? ,?";

            // System.out.println("price = " + price.length());
            // String append = String.format("WHERE Price = %s", price);
            // query = query + " Where price = "+price ;
            // query = query + " Where price = '"+price+"'"
            PreparedStatement update = this.dbService.getConnection().prepareStatement(query);

            // Double newprice = null;
            // System.out.println(query);
            update.setString(1, UserService.getUserService().getUser());
            update.setString(2, this.eventId);

            this.dbService.getConnection().setAutoCommit(false);
            ResultSet rs = update.executeQuery();

            ArrayList<Vote> result = parseResultsWithVote(rs);
            if (update != null) {
                update.close();
            }
            this.dbService.getConnection().setAutoCommit(true);

            // ResultSet rs = stmt.executeQuery(query);
            return result;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve sodas by restaurant.");
            ex.printStackTrace();
            return null;
        }
    }

    private ArrayList<Vote> parseResults(ResultSet rs) {
        try {
            ArrayList<Vote> sodasByRestaurants = new ArrayList<Vote>();
            int Movie = rs.findColumn("Movie");
            int Vote = rs.findColumn("Votes");

            while (rs.next()) {
                String MovieID = rs.getString(Movie);
                Movie tempMovie = this.movieFinder.buildMovieByIMDBID(MovieID);
                this.listMovie.add(tempMovie);
                String MovieName = tempMovie.title;
                Vote temp = new Vote(MovieName, rs.getString(Vote));
                sodasByRestaurants.add(temp);
                // System.out.println(temp.getID());
            }
            // System.out.println(sodasByRestaurants.size());
            return sodasByRestaurants;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "An error ocurred while retrieving sodas by restaurants. See printed stack trace.");
            ex.printStackTrace();
            return new ArrayList<Vote>();
        }

    }

    private ArrayList<Vote> parseResultsWithVote(ResultSet rs) {
        try {
            ArrayList<Vote> sodasByRestaurants = new ArrayList<Vote>();
            int Movie = rs.findColumn("Movie");
            int Vote = rs.findColumn("Votes");

            while (rs.next()) {
                String MovieID = rs.getString(Movie);
                Movie tempMovie = this.movieFinder.buildMovieByIMDBID(MovieID);
                this.voteMovie.add(tempMovie);
                String MovieName = tempMovie.title;
                Vote temp = new Vote(MovieName, rs.getString(Vote));
                sodasByRestaurants.add(temp);
                // System.out.println(temp.getID());
            }
            // System.out.println(sodasByRestaurants.size());
            return sodasByRestaurants;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "An error ocurred while retrieving sodas by restaurants. See printed stack trace.");
            ex.printStackTrace();
            return new ArrayList<Vote>();
        }
    }

}

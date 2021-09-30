package service;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultListModel;

import testtest.Movie;
import testtest.MovieFinder;

public class MovieService extends DataService {

    private static MovieService mService;

    public static MovieService getMovieService() {
        if (mService == null) {
            mService = new MovieService();
        }
        return mService;
    }

    public int likeMovie(Movie movie) {
        try {
            String query = "{ ? = call LikeMovie(?,?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, movie.imdbID);
            cb.setString(3, this.getUser());
            cb.execute();

            int result = cb.getInt(1);
            System.out.println(result == 0);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int dislikeMovie(Movie movie) {
        try {
            String query = "{? = call DislikeMovie(?,?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, movie.imdbID);
            cb.setString(3, this.getUser());
            cb.execute();

            int result = cb.getInt(1);
            System.out.println(result == 0);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean revertLike(Movie movie) {
        try {
            String query = "{? = call revertLike(?,?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(3, movie.imdbID);
            cb.setString(2, this.getUser());
            cb.execute();

            int result = cb.getInt(1);
            System.out.println(result == 0);
            return result == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean revertDislike(Movie movie) {
        try {
            String query = "{? = call revertDislike(?,?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(3, movie.imdbID);
            cb.setString(2, this.getUser());
            cb.execute();

            int result = cb.getInt(1);
            System.out.println("revertDislike" + (result == 0));
            return result == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkLike(Movie movie) {
        try {
            String query = "{? = call checkLikeMovie(?,?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, movie.imdbID);
            cb.setString(3, this.getUser());
            System.out.println("USER " + this.getUser());
            cb.execute();

            int result = cb.getInt(1);
            System.out.println("check" + result);
            return result == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkDisLike(Movie movie) {
        try {
            String query = "{? = call checkDisLikeMovie(?,?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, movie.imdbID);
            cb.setString(3, this.getUser());
            cb.execute();

            int result = cb.getInt(1);
            return result == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showLikedMovie(ArrayList<Movie> arr, MovieFinder mf) {
        try {
            String query = "{? = call ShowLikedMovies(?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, this.getUser());
            ResultSet rs = cb.executeQuery();
            while (rs.next() == true) {
                arr.add(mf.buildMovieByIMDBID(rs.getString(1)));
            }

            int result = cb.getInt(1);
            return result == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showFriendLikedMovie(ArrayList<Movie> arr, MovieFinder mf, String friend) {
        this.setUser(friend);
        boolean result = showLikedMovie(arr, mf);
        this.setUser(UserService.getUserService().getUser());
        return result;
    }

    // public boolean showFriendLikedMovie(ArrayList<Movie> arr, MovieFinder mf,
    // String friend) {
    // this.setUser(friend);
    // boolean result = showLikedMovie(arr, mf);
    // this.setUser(UserService.getUserService().getUser());
    // return result;
    // }

    public boolean addScore(String score, String imdbID) {
        float s = Float.parseFloat(score);
        System.out.println(score);
        try {
            String query = "{? = call add_score(?,?,?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, this.getUser());
            cb.setFloat(3, s);
            cb.setString(4, imdbID);
            cb.execute();
            int result = cb.getInt(1);
            return result == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addComment(String text, String imdbID) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            String query = "{? = call AddReview(?,?,?, ?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, text);
            cb.setString(4, this.getUser());
            cb.setTimestamp(3, timestamp);
            cb.setString(5, imdbID);
            cb.execute();
            int result = cb.getInt(1);
            return result == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showReview(Movie movie, DefaultListModel reviewModel) {
        try {
            reviewModel.clear();
            String query = "{? = call ShowReview(?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, movie.imdbID);

            ResultSet rs = cb.executeQuery();
            while (rs.next()) {
                reviewModel.addElement(rs.getString(1));
            }
            int result = cb.getInt(1);
            return result == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public float showAvgScore(Movie movie) {
        try {
            String query = "{call avg_score(?,?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(2, Types.FLOAT);
            cb.setString(1, movie.imdbID);
            if (cb.execute()) {
                return -2;
            }
            float result = cb.getFloat(2);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -2;
        }
    }

    public int numLikes(Movie movie) {
        try {
            String query = "{? = call numLikes(?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, movie.imdbID);
            cb.execute();
            int result = cb.getInt(1);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int numDislikes(Movie movie) {
        try {
            String query = "{? = call numDislikes(?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, movie.imdbID);
            cb.execute();
            int result = cb.getInt(1);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean showFriendReview(Movie movie, DefaultListModel reviewModel, String friendUser) {
        // TODO Auto-generated method stub
        try {
            reviewModel.clear();
            String query = "{? = call ShowFriendReview(?,?)}";
            CallableStatement cb = this.prepareCall(query);
            cb.registerOutParameter(1, Types.INTEGER);
            cb.setString(2, movie.imdbID);
            cb.setString(3, friendUser);

            ResultSet rs = cb.executeQuery();
            while (rs.next()) {
                reviewModel.addElement(rs.getString(1));
            }
            int result = cb.getInt(1);
            return result == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}

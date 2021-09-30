package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import testtest.IsFriendWith;

//import sodabase.ui.SodaByRestaurant;

public class FriendService extends DataService {

    private static FriendService fService;

    public static FriendService getFriendService() {
        if (fService == null) {
            fService = new FriendService();
        }
        return fService;
    }

    public boolean addFriend(String friendUserName) {
        CallableStatement cs = null;
        try {
            cs = dbService.getConnection().prepareCall("{ ? = call dbo.AddFriend(?, ?) }");
            cs.setString(2, this.getUser());
            cs.setString(3, friendUserName);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            // System.out.println("result is " + result);
            if (result == 1 || result == 2) {
                System.err.println("AddFriend failed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<IsFriendWith> findFriend() {
        try {
            Statement stmt = this.dbService.getConnection().createStatement();

            String query = "SELECT User1, User2, Status\nFROM IsFriendWith\n WHERE (User1 = ? OR User2 = ?) AND Status = 1";

            PreparedStatement update = null;

            update = this.dbService.getConnection().prepareStatement(query);
            update.setString(1, this.getUser());
            update.setString(2, this.getUser());

            this.dbService.getConnection().setAutoCommit(false);
            ResultSet rs = update.executeQuery();

            ArrayList<IsFriendWith> result = null;
            result = parseIsFriendWithResult(rs);
            if (update != null) {
                update.close();
            }
            this.dbService.getConnection().setAutoCommit(true);

            return result;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve isFriendWith.");
            ex.printStackTrace();
            return null;
        }

    }

    public ArrayList<IsFriendWith> findUnprocessedFriend() {
        try {
            Statement stmt = this.dbService.getConnection().createStatement();

            String query = "SELECT User1, User2, Status\nFROM IsFriendWith\n WHERE User2 = ? AND Status = 0";

            PreparedStatement update = null;

            update = this.dbService.getConnection().prepareStatement(query);
            System.out.println(query);
            update.setString(1, this.getUser());

            this.dbService.getConnection().setAutoCommit(false);
            ResultSet rs = update.executeQuery();

            ArrayList<IsFriendWith> result = null;
            result = parseIsFriendWithResult(rs);
            if (update != null) {
                update.close();
            }
            this.dbService.getConnection().setAutoCommit(true);

            return result;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve isFriendWith.");
            ex.printStackTrace();
            return null;
        }

    }

    public boolean approveFriend(String user2) {
        CallableStatement cs = null;
        try {
            cs = this.dbService.getConnection().prepareCall("{?=call dbo.ApproveFriend(?,?)}");
            System.out.println(this.getUser());
            System.out.println(user2);
            cs.setString(3, this.getUser());
            cs.setString(2, user2);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1) {
                System.out.println("successful approve" + user2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean rejectFriend(String user2) {
        CallableStatement cs = null;
        try {
            cs = this.dbService.getConnection().prepareCall("{?=call dbo.RejectFriend(?,?)}");
            System.out.println(this.getUser());
            System.out.println(user2);
            cs.setString(3, this.getUser());
            cs.setString(2, user2);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1) {
                System.out.println("successful reject" + user2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    private ArrayList<IsFriendWith> parseIsFriendWithResult(ResultSet rs) {
        try {
            ArrayList<IsFriendWith> isFriendWith = new ArrayList<IsFriendWith>();
            int user1 = rs.findColumn("User1");
            int user2 = rs.findColumn("User2");
            int status = rs.findColumn("Status");

            while (rs.next()) {
                IsFriendWith temp = new IsFriendWith(rs.getString(user1), rs.getString(user2), rs.getString(status));
                isFriendWith.add(temp);
            }
            System.out.println(isFriendWith.size());
            return isFriendWith;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "An error ocurred while retrieving sodas by restaurants. See printed stack trace.");
            ex.printStackTrace();
            return new ArrayList<IsFriendWith>();
        }

    }

}

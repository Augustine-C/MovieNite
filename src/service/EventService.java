package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import testtest.Event;

//import sodabase.ui.SodaByRestaurant;

public class EventService extends DataService {

    private static EventService eService;

    public static EventService getEventService() {
        if (eService == null) {
            eService = new EventService();
        }
        return eService;
    }

    public ArrayList<Event> findHostEvent() {
        try {
            String query = "EXEC GetHostEventsbyUser ?";

            PreparedStatement update = this.prepareCall(query);

            update.setString(1, this.getUser());

            ResultSet rs = update.executeQuery();

            ArrayList<Event> result = parseResults(rs);
            ;
            if (update != null) {
                update.close();
            }
            return result;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed");
            ex.printStackTrace();
            return null;
        }

    }

    public ArrayList<Event> findAttendEvent() {
        // DONE: Task 3 and Task 4
        try {
            Statement stmt = this.createStatement();

            String query = "EXEC GetAttendEventByUser ?";
            PreparedStatement update = this.prepareStatement(query);

            // Double newprice = null;
            // System.out.println(query);
            update.setString(1, this.getUser());

            this.dbService.getConnection().setAutoCommit(false);
            ResultSet rs = update.executeQuery();

            ArrayList<Event> result = parseResults(rs);
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

    public boolean addEvent(String location, String time) {
        CallableStatement cs = null;
        try {
            cs = this.prepareCall("{ ? = call dbo.HostMovieEvent(?,?,?) }");
            cs.setString(2, location);
            cs.setString(3, time);
            cs.setString(4, this.getUser());
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1 || result == 2) {
                System.err.println("AddEvent failed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addAttend(String event, String user) {
        System.out.println(user);
        if (event == null || event.isEmpty() || user == null || user.isEmpty())
            return false;
        CallableStatement cs = null;
        try {
            cs = this.prepareCall("{ ? = call dbo.addAttend(?,?,?) }");
            cs.setString(2, this.getUser());
            cs.setString(3, event);
            cs.setString(4, user);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1) {
                System.err.println("invalid eventid failed");
                return false;
            }
            if (result == 2) {
                System.err.println("not friend");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                cs.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public ArrayList<String> GetFriends() {
        if (this.getUser() == null)
            return null;
        ArrayList<String> rests = new ArrayList<String>();
        String query = "select user1, user2 " + "from " + "isFriendWith "
                + "where (User1= ? and IsFriendWith.Status = 1) " + "or (User2= ? and IsFriendWith.Status = 1) ";

        try {
            PreparedStatement update = this.prepareStatement(query);
            // System.out.println(query);
            update.setString(1, this.getUser());
            update.setString(2, this.getUser());

            this.dbService.getConnection().setAutoCommit(false);
            ResultSet rs = update.executeQuery();
            while (rs.next()) {
                String resName = rs.getString("user1");
                if (!resName.equals(this.getUser()))
                    rests.add(resName);
                String Name = rs.getString("user2");
                if (!Name.equals(this.getUser()))
                    rests.add(Name);

            }
            if (update != null) {
                update.close();
            }
            this.dbService.getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rests;
    }

    public ArrayList<String> GetEvents() {
        System.out.println(this.getUser());
        if (this.getUser() == null)
            return null;
        ArrayList<String> rests = new ArrayList<String>();
        Statement stmt = null;
        String query = "select eventId " + "from " + "movieEvent" + " where host = ? ";

        try {
            PreparedStatement update = null;
            update = this.prepareStatement(query);
            // System.out.println(query);
            update.setString(1, this.getUser());

            ResultSet rs = update.executeQuery();
            while (rs.next()) {
                String resName = rs.getString("eventId");
                rests.add(resName);

            }
            if (update != null) {
                update.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return rests;
    }

    private ArrayList<Event> parseResults(ResultSet rs) {
        try {
            ArrayList<Event> sodasByRestaurants = new ArrayList<Event>();
            int ID = rs.findColumn("EventID");
            int Loc = rs.findColumn("Location");
            int Time = rs.findColumn("TimeMovieEvent");
            int Host = rs.findColumn("Host");
            while (rs.next()) {
                Event temp = new Event(rs.getString(ID), rs.getString(Loc), rs.getString(Time), rs.getString(Host));
                sodasByRestaurants.add(temp);
                // System.out.println(temp.getID());
            }
            System.out.println(sodasByRestaurants.size());
            return sodasByRestaurants;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "An error ocurred while retrieving sodas by restaurants. See printed stack trace.");
            ex.printStackTrace();
            return new ArrayList<Event>();
        }

    }

}

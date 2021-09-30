package service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AddFriendService extends DataService {

    public AddFriendService() {
        this.dbService = DatabaseConnectionService.getdbConnectionService();
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
            System.out.println("result is " + result);
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

    public boolean addFriend(String user2, String friendUserName) {
        CallableStatement cs = null;
        try {
            cs = dbService.getConnection().prepareCall("{ ? = call dbo.AddFriend(?, ?) }");
            cs.setString(2, user2);
            cs.setString(3, friendUserName);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            System.out.println("result is " + result);
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
}

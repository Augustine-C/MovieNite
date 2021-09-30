package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataService {
    public DatabaseConnectionService dbService;
    private String user;

    public DataService() {
        this.dbService = DatabaseConnectionService.getdbConnectionService();
    }

    public Statement createStatement() throws SQLException {
        return this.dbService.getConnection().createStatement();
    }

    public CallableStatement prepareCall(String query) throws SQLException {
        return this.dbService.getConnection().prepareCall(query);
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return this.dbService.getConnection().prepareStatement(query);
    }

    public String getUser() {
        if (user == null) {
            user = UserService.getUserService().getUser();
        }
        return user;
    }

    public void setUser(String userName) {
        this.user = userName;
    }
}

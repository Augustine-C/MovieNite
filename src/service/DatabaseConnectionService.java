package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {

    // DO NOT EDIT THIS STRING, YOU WILL RECEIVE NO CREDIT FOR THIS TASK IF THIS
    // STRING IS EDITED
    private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";
    private static DatabaseConnectionService dbConnectionService;
    private Connection connection = null;
    private static String serverName = "SERVERNAME";
    private static String databaseName = "MovieNite_S3G7_TEST";

    public static DatabaseConnectionService getdbConnectionService() {
        if (dbConnectionService == null) {
            dbConnectionService = new DatabaseConnectionService();
        }
        return dbConnectionService;
    }

    private DatabaseConnectionService() {

    }

    public boolean connect(String user, String pass) {
        // DONE: Task 1
        // BUILD YOUR CONNECTION STRING HERE USING THE SAMPLE URL ABOVE
        String connectionUrl = "jdbc:sqlserver://" + serverName + ";" + "databaseName=" + databaseName + ";" + "user="
                + user + ";" + "password=" + pass + ";";
        try {
            this.connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connection established!");
            return true;
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        // DONE: Task 1
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean connect() {
        // TODO Auto-generated method stub
        String connectionUrl_Azure = "jdbc:sqlserver://cuiy1.database.windows.net;databaseName=MovieNite_S3G7_TEST;user=cuiy1;password=???;";

        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=MovieNite_S3G7_TEST;user=augustine;password=???;";
        try {
            this.connection = DriverManager.getConnection(connectionUrl_Azure);
            System.out.println("Connection established!");
            return true;
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

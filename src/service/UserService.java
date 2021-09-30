package service;

import java.io.ByteArrayInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class UserService {
    private static final Random RANDOM = new SecureRandom();
    private static final Base64.Encoder enc = Base64.getEncoder();
    private static final Base64.Decoder dec = Base64.getDecoder();
    private DatabaseConnectionService dbService;
    private String user;
    private static UserService userService;
    private boolean isLogin = false;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public String getUser() {
        return this.user;
    }

    private UserService() {
        this.dbService = DatabaseConnectionService.getdbConnectionService();
    }

    public ArrayList<String> showPersonalInfo() {
        try {
            Statement stmt = this.dbService.getConnection().createStatement();

            String query = "EXEC ShowPersonalInformation ?";

            PreparedStatement update = this.dbService.getConnection().prepareStatement(query);

            update.setString(1, this.user);

            this.dbService.getConnection().setAutoCommit(false);
            ResultSet rs = update.executeQuery();

            ArrayList<String> result = parseResultsForList(rs);
            if (update != null) {
                update.close();
            }
            this.dbService.getConnection().setAutoCommit(true);

            return result;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve sodas by restaurant.");
            ex.printStackTrace();
            return null;
        }
    }

    public boolean login(String username, String password) {

        PreparedStatement ps = null;
        String query = " Select [PasswordSalt], [PasswordHash]  FROM [MovieNite_S3G7_TEST].[dbo].[Account] where UserID = ?";

        try {
            ps = dbService.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            byte[] salt = rs.getBytes(1);
            String hashPass = rs.getString(2);

            if (hashPassword(salt, password).equals(hashPass)) {
                this.setLogin(true);
                this.user = username;
                rs.close();
                return true;
            } else {
                System.err.println("Login failed");
                return false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("Login failed");
            return false;
        }

    }

    /*
     * register(UserID, Email, firstname, lastname, password)
     */
    public boolean addUser(String username, String email, String firstName, String lastName, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            System.err.println("Register failed");
            return false;
        }
        byte[] salt = getNewSalt();
        String hashPass = hashPassword(salt, password);
        CallableStatement cs = null;
        try {
            cs = dbService.getConnection().prepareCall("{ ? = call dbo.AddUser(?,?,?,?,?,?) }");
            cs.setString(2, username);
            cs.setString(3, email);
            cs.setString(4, firstName);
            cs.setString(5, lastName);
            cs.setString(6, hashPass);
            cs.setBytes(7, salt);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1 || result == 2 || result == 3 || result == 4) {
                System.err.println("Registration failed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean changeFirst(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            System.err.println("Change failed");
            return false;
        }

        CallableStatement cs = null;
        try {
            cs = dbService.getConnection().prepareCall("{ ? = call dbo.ModifyUserFirstName(?,?) }");
            cs.setString(2, this.user);
            cs.setString(3, newPassword);

            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1 || result == 2 || result == 3) {
                System.err.println("change failed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean changeLast(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            System.err.println("Change failed");
            return false;
        }

        CallableStatement cs = null;
        try {
            cs = dbService.getConnection().prepareCall("{ ? = call dbo.ModifyUserLastName(?,?) }");
            cs.setString(2, this.user);
            cs.setString(3, newPassword);

            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1 || result == 2 || result == 3) {
                System.err.println("change failed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean changeEmail(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            System.err.println("Change failed");
            return false;
        }

        CallableStatement cs = null;
        try {
            cs = dbService.getConnection().prepareCall("{ ? = call dbo.ModifyUserEmail(?,?) }");
            cs.setString(2, this.user);
            cs.setString(3, newPassword);

            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1 || result == 2 || result == 3) {
                System.err.println("change failed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean changePassword(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            System.err.println("Login failed");
            return false;
        }

        byte[] salt = getNewSalt();
        String hashPass = hashPassword(salt, newPassword);
        CallableStatement cs = null;
        try {
            cs = dbService.getConnection().prepareCall("{ ? = call dbo.ModifyUserPassward(?,?,?) }");
            cs.setString(2, this.user);
            cs.setString(3, hashPass);
            cs.setBytes(4, salt);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int result = cs.getInt(1);
            if (result == 1 || result == 2 || result == 3) {
                System.err.println("change failed");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public byte[] getNewSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public String getStringFromBytes(byte[] data) {
        return enc.encodeToString(data);
    }

    public String hashPassword(byte[] salt, String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f;
        byte[] hash = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = f.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
            e.printStackTrace();
        }
        return getStringFromBytes(hash);
    }

    private ArrayList<String> parseResultsForList(ResultSet rs) {
        try {
            ArrayList<String> ans = new ArrayList<String>();
            int FirstName = rs.findColumn("FirstName");
            int LastName = rs.findColumn("LastName");
            int EmailAddr = rs.findColumn("EmailAddr");

            while (rs.next()) {
                String FName = rs.getString(FirstName);
                if (FName == null)
                    FName = "NULL";
                ans.add(FName);

                String LName = rs.getString(LastName);
                if (LName == null)
                    LName = "NULL";
                ans.add(LName);
                String EmailAdd = rs.getString(EmailAddr);
                if (EmailAdd == null)
                    EmailAdd = "NULL";
                ans.add(EmailAdd);

                // System.out.println(temp.getID());
            }
            // System.out.println(sodasByRestaurants.size());
            return ans;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "An error ocurred while retrieving sodas by restaurants. See printed stack trace.");
            ex.printStackTrace();
            return new ArrayList<String>();
        }
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

}
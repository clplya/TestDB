package testdb.DAO;

import Objects.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUserDao implements IUserDao {

    private User user;
    private final ArrayList<User> userList;
    private final ArrayList<User> finalUserList;

    public DBUserDao() {
        userList = new ArrayList<>();
        finalUserList = new ArrayList<>();
        user = null;
    }

    public void selectAllUsers(Connection con, String dbName)
            throws SQLException {
        Statement stmt = null;

        String query
                = "select * from Users";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public boolean addUser(User user) {
        if (userList.contains(user)) {
            finalUserList.add(user);
            return true;
        }
        return false;
    }

    @Override
    public void deleteUser(User deletedUser) {

    }

    @Override
    public ArrayList<User> getAllUsers() {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select userId,userName,password,active from user";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int customerId = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);
                int active = rs.getInt(4);

                user = new User(customerId, userName, password, active);
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        }
        return userList;
    }

    @Override
    public User getUser(int userId) {
        Statement stmt;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("select userId,userName,password,active from user where userId =" + userId);

//            PreparedStatement ps = conn.prepareStatement("select userId,userName,password,active from user where userId = ?");
//            ps.setString(userId, user);
//            ps.execute();

            while (rs.next()) {
                int userID = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);
                int active = rs.getInt(4);

                user = new User(userID, userName, password, active);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return user;
    }

    @Override
    public void updateUser(User oldUser, User updatedUser) {
        Statement stmt;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            int result = stmt.executeUpdate(
                    "insert into User values (4, 'test', 'password', 1)");
            System.out.println(result);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void updateUserInfo(int userId) {
        User updatedUser = getUser(userId);
        Statement stmt;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select a.userId,c.customerId,a.title,a.description,a.location,a.contact,a.URL,a.`start`,a.`end` from customer c join user a on c.customerId = a.customerId\n"
                    + "where c.customerId =" + userId);

            while (rs.next()) {

            }
        } catch (SQLException ex) {

        }

    }
}

/* Use below to compare password entry to DB values
import java.io.*;
import java.util.Arrays;

public class PasswordCompareSample {
   public static void main(String[] args) throws NumberFormatException, IOException {
      Console console = System.console();
      if(console == null) {
         throw new RuntimeException("Console not available");
      } else {
         char[] password = console.readPassword("Enter your password: ");
         console.format("Enter your password again:  ");
         console.flush();
         char[] verify = console.readPassword();
         boolean match = Arrays.equals(password,verify);

         // Immediately clear passwords from memory
         for(int i=0; i<password.length; i++) {
            password[i]='x';
         }
         for(int i=0; i<verify.length; i++) {
            verify[i]='x';
         }

         console.format("Your password was "+(match ? "correct": "incorrect"));
      }
   }
}*/

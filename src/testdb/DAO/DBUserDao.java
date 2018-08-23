package testdb.DAO;

import Objects.User;
import java.sql.Connection;
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
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select userId,userName,password,active from user");

            while (rs.next()) {
                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return userList;
    }

    @Override
    public User getUser(int userId) {
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select userId,userName,password,active from user where userId =" + userId);

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

    }

    @Override
    public void updateUserInfo(int userId) {
        User user = getUser(userId);
        Statement stmt;

        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select a.userId,c.customerId,a.title,a.description,a.location,a.contact,a.URL,a.`start`,a.`end` from customer c join user a on c.customerId = a.customerId\n"
                    + "where c.customerId =" + userId);

            while (rs.next()) {

            }
        } catch (SQLException ex) {

        }

    }
}

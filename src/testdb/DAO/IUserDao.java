package testdb.DAO;

import Objects.User;
import java.util.ArrayList;

public interface IUserDao {

    boolean addUser(User user);

    void deleteUser(User user);

    ArrayList<User> getAllUsers();

    User getUser(int userId);

    void updateUser(User oldUser, User updatedUser);

    void updateUserInfo(int userId);
}

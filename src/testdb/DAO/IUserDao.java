package testdb.DAO;

import Objects.User;
import java.util.ArrayList;

public interface IUserDao {

    public void addUser(User user);

    public void deleteUser(User user);

    public ArrayList<User> getAllUsers();

    public User getUser(int userId);

    public void updateUser(User oldUser, User updatedUser);

    public void updateUserInfo(int userId);
}

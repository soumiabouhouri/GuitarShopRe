package sample.logic;

import sample.DAO.UserDAO;
import sample.Model.User;

public class UserService {
    UserDAO userDAO = new UserDAO();

    public User getUser(String username, String password) {
        return userDAO.getUser(username, password);
    }
}

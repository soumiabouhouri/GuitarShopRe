package sample.DAO;

import sample.Model.User;

public class UserDAO {
    DataBase db = new DataBase();
    public User getUser(String username, String password) {
        User user = null;
        for (User u : db.getUsers()) {
            if (u.getFirstName().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return user;
    }
}

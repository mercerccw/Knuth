package daos;

import config.Database;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "SELECT * FROM users WHERE email = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        User user = null;
        if (result.next()) {
            user = new User();
            user.setFirst_name(result.getString("first_name"));
            user.setLast_name(result.getString("last_name"));
            user.setPosition(result.getString("position"));
            user.setEmail(email);
        }
        connection.close();
        return user;
    }
//    public User register(String first_name, String last_name, String email, String password, String position)
//            throws SQLException, ClassNotFoundException {
//
//    }
}
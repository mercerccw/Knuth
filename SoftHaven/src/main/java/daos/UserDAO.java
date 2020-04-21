package daos;

import config.Database;
import models.User;

import java.sql.*;

public class UserDAO {

    public User checkPosition(String email) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "SELECT position FROM users WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet result = statement.executeQuery();
        User user = null;
        if (result.next()) {
            user = new User();
            user.setPosition(result.getString("position"));
            user.setEmail(email);
        }
        connection.close();
        return user;
    }
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
    public User register(String first_name, String last_name, String email, String password, String position)
            throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String test_sql = "SELECT * FROM users WHERE email = ? ";
        PreparedStatement test_statement = connection.prepareStatement(test_sql);
        test_statement.setString(1, email);
        ResultSet test_result = test_statement.executeQuery();
        if(!test_result.next()){
            String sql = "INSERT INTO users (first_name, last_name, email, password, position) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, position);
            statement.executeUpdate();
            String confirm_sql = "SELECT * from users where email=?";
            PreparedStatement confirm_statement = connection.prepareStatement(confirm_sql);
            confirm_statement.setString(1, email);
            ResultSet result = confirm_statement.executeQuery();
            User user = null;
            if (result.next()) {
                user = new User();
                user.setFirst_name(result.getString("first_name"));
                user.setLast_name(result.getString("last_name"));
                user.setPosition(result.getString("position"));
                user.setEmail(result.getString("email"));
            }
            connection.close();
            return user;
        } else {
            return null;
        }
    }
}
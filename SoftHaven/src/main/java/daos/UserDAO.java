package daos;

import models.User;

import java.sql.*;

public class UserDAO {

    public User checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/bookshop";
        String dbUser = "root";
        String dbPassword = "password";

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
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
}
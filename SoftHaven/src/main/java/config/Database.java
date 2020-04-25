package config;

import java.sql.*;

public final class Database {
    private Database(){

    }
    public static final String jdbcURL = "jdbc:mysql://localhost:3306/softhaven?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
    public static final String dbUser = "root";
    public static final String dbPassword = "root";
    public static Connection dbconnect() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
    }
}

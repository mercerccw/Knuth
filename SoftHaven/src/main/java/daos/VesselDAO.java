package daos;

import config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VesselDAO {
    public ResultSet getAllVessels() throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "select * from vessel";
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }
}

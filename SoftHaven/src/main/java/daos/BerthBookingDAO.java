package daos;

import config.Database;

import java.sql.*;

public class BerthBookingDAO {
    public int verifyVessel(String ship_imo) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "select * from vessel where IMO = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, String.valueOf(ship_imo));
        ResultSet result = statement.executeQuery();
        if (result.next()){
            connection.close();
            return Integer.parseInt(ship_imo);
        }else{
            connection.close();
            return 0;
        }
    }
    public String bookBerth(String berth_number, int ship_imo) throws SQLException, ClassNotFoundException {
        Connection connection= Database.dbconnect();
        String sql = "update berths set ship_imo = ? where number = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        if(ship_imo == 0){
            statement.setNull(1, Types.NULL);
        }else {
            statement.setInt(1, ship_imo);
        }
        statement.setString(2, berth_number);
        statement.executeUpdate();
        connection.close();
        return "Update successful";
    }

}

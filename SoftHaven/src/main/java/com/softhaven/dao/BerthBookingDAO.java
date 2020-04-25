package com.softhaven.dao;

import com.softhaven.config.Database;

import java.sql.*;

public class BerthBookingDAO {
    public int verifyVessel(String ship_imo) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "select * from vessel where IMO = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, String.valueOf(ship_imo));
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            connection.close();
            return Integer.parseInt(ship_imo);
        } else {
            connection.close();
            return 0;
        }
    }

    public String bookBerth(String berth_number, int ship_imo, int real_imo) throws SQLException, ClassNotFoundException {
//        System.out.println(real_imo);
        Connection connection = Database.dbconnect();
        String sql = "update berths set ship_imo = ? where number = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        if (ship_imo == 0) {
            String sql2 = "update vessel set status = 'departed' where IMO = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement.setNull(1, Types.NULL);
            statement2.setInt(1, real_imo);
            statement2.executeUpdate();
        } else {
            String sql2 = "update vessel set status = 'berth' where IMO = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement.setInt(1, ship_imo);
            statement2.setInt(1, ship_imo);
            statement2.executeUpdate();
        }
        statement.setString(2, berth_number);
        statement.executeUpdate();
        connection.close();
        return "Update successful";
    }
}
package com.softhaven.dao;

import com.softhaven.config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BerthDAO {
    public ResultSet getAllBerthingData() throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "select ports.name, quays.name, quays.type, b.number, b.ship_imo from ports\n" +
                "    join quay_list on ports.quay_list_id = quay_list.id\n" +
                "    join quays on quay_list.quay_id = quays.id\n" +
                "    join berth_list bl on quays.berth_list_id = bl.id\n" +
                "    join berths b on bl.berth_id = b.id";
        PreparedStatement statement = connection.prepareStatement(sql);

        return statement.executeQuery();
    }
    public List<String> getAllBerthNumbers() throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "select number from berths where ship_imo IS NULL ORDER BY number";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<String> berths = new ArrayList<>();
        while (resultSet.next()){
            assert false;
            berths.add(resultSet.getString("number"));
        }
        return berths;
    }
    public int getPortNumber(String berth_number) throws SQLException, ClassNotFoundException{
        Connection connection = Database.dbconnect();
        String sql = "select ports.id as port_number from ports\n" +
                "                join quay_list on ports.quay_list_id = quay_list.id\n" +
                "                join quays on quay_list.quay_id = quays.id\n" +
                "                join berth_list bl on quays.berth_list_id = bl.id\n" +
                "                join berths b on bl.berth_id = b.id WHERE number = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,berth_number);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("port_number");
        } else {
            return 0;
        }
    }
}

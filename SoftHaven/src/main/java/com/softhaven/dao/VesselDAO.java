package com.softhaven.dao;

import com.softhaven.config.Database;
import com.softhaven.bean.Vessel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VesselDAO {
    public List<Vessel> getAllVessels(int currentPage, int recordsPerPage) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        currentPage = currentPage * recordsPerPage - recordsPerPage;
        String sql = "select * from vessel LIMIT ?,? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, currentPage);
        statement.setInt(2, recordsPerPage);
        ResultSet resultSet = statement.executeQuery();
        Vessel vessel;
        List<Vessel> vessels = new ArrayList<>();
        while (resultSet.next()){
            vessel = new Vessel();
            vessel.setImo(resultSet.getInt("IMO"));
            vessel.setFlag(resultSet.getString("Flag"));
            vessel.setName(resultSet.getString("Name"));
            vessel.setBuilt(resultSet.getInt("Built"));
            vessel.setCallSign(resultSet.getString("CallSign"));
            vessel.setLength(resultSet.getInt("Length"));
            vessel.setBreadth(resultSet.getInt("Breadth"));
            vessel.setTonnage(resultSet.getInt("Tonnage"));
            vessel.setMmsi(resultSet.getInt("MMSI"));
            vessel.setType(resultSet.getString("Type"));
            vessel.setOwnerCode(resultSet.getInt("Owner_Code"));
            vessel.setStatus(resultSet.getString("status"));
            vessels.add(vessel);
        }
        int size = vessels.size();
        System.out.println(size);
        return vessels;
    }
    public int getNumberOfRows() throws SQLException, ClassNotFoundException {
        int numOfRows = 0;
        Connection connection = Database.dbconnect();
        String sql = "SELECT COUNT(IMO) as count FROM vessel";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("count");
        }
        return numOfRows;
    }
}

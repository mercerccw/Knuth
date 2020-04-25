package com.softhaven.dao;

import com.softhaven.bean.Vessel;
import com.softhaven.config.Database;

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
        while (resultSet.next()) {
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
        connection.close();
        return vessels;
    }
    public void updateVesselStatus(int imo, String status) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "UPDATE vessel set status = ? where IMO = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, status);
        statement.setInt(2, imo);
        statement.executeUpdate();
    }

    public int getNumberOfRows() throws SQLException, ClassNotFoundException {
        int numOfRows = 0;
        Connection connection = Database.dbconnect();
        String sql = "SELECT COUNT(IMO) as count FROM vessel";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("count");
        }
        connection.close();
        return numOfRows;
    }

    public Vessel getVessel(int imo) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "SELECT * from vessel WHERE imo=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, imo);
        ResultSet resultSet = statement.executeQuery();
        Vessel vessel;
        if (resultSet.next()) {
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
            return vessel;
        } else {
            return null;
        }
    }
}

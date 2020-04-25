package com.softhaven.dao;

import com.softhaven.bean.Arrival;
import com.softhaven.config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ArrivalDao {
    public String submitArrivalForm(Arrival arrivalForm) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "insert into prearrival (imo_number, agent_email, arriving_from, eta, berth_number, next_port_id, etd, discharging_cargo_description, discharging_cargo_amount, loading_cargo_description, loading_cargo_amount, number_of_passengers_arrival, number_of_passengers_departure)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, arrivalForm.getImo_number());
        statement.setString(2, arrivalForm.getAgent_email());
        statement.setString(3, arrivalForm.getArriving_from());
        statement.setString(4, arrivalForm.getEta());
        statement.setString(5, arrivalForm.getBerth_number());
        statement.setInt(6, arrivalForm.getNext_port_id());
        statement.setString(7, arrivalForm.getEtd());
        statement.setString(8, arrivalForm.getDischarging_cargo_description());
        statement.setInt(9, arrivalForm.getDischarging_cargo_amount());
        statement.setString(10, arrivalForm.getLoading_cargo_description());
        statement.setInt(11, arrivalForm.getLoading_cargo_amount());
        statement.setInt(12, arrivalForm.getNumber_of_passengers_arrival());
        statement.setInt(13, arrivalForm.getNumber_of_passengers_departure());
        statement.executeUpdate();
        connection.close();
        return "done";
    }
}

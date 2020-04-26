package com.softhaven.dao;

import com.softhaven.bean.Arrival;
import com.softhaven.config.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArrivalDao {
    public String submitArrivalForm(Arrival arrivalForm) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "insert into prearrival (imo_number, agent_email, arriving_from, eta, berth_number, next_port_id, etd, discharging_cargo_description, discharging_cargo_amount, loading_cargo_description, loading_cargo_amount, number_of_passengers_arrival, number_of_passengers_departure, submitted_by)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        statement.setString(14, arrivalForm.getSubmitted_by());
        statement.executeUpdate();
        connection.close();
        return "done";
    }

    public List<Arrival> fetchAllUnApprovedForms() throws SQLException,
            ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "select prearrival.id, imo_number, agent_email, arriving_from, eta, berth_number, p.name as port_name, etd, discharging_cargo_description, discharging_cargo_amount, loading_cargo_description, loading_cargo_amount, number_of_passengers_arrival, number_of_passengers_departure, crew_documents_customs, form_validation, submitted_by from prearrival join ports p on prearrival.next_port_id = p.id where form_validation != 1 and form_validation != 2";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        Arrival arrival;
        List<Arrival> arrivals = new ArrayList<>();
        while (resultSet.next()) {
            arrival = new Arrival();
            arrival.setId(resultSet.getInt("id"));
            arrival.setSubmitted_by(resultSet.getString("submitted_by"));
            arrival.setImo_number(resultSet.getInt("imo_number"));
            arrival.setAgent_email(resultSet.getString("agent_email"));
            arrival.setArriving_from(resultSet.getString("arriving_from"));
            arrival.setEta(resultSet.getString("eta"));
            arrival.setBerth_number(resultSet.getString("berth_number"));
            arrival.setNext_port_name(resultSet.getString("port_name"));
            arrival.setEtd(resultSet.getString("etd"));
            arrival.setDischarging_cargo_description(resultSet.getString("discharging_cargo_description"));
            arrival.setDischarging_cargo_amount(resultSet.getInt("discharging_cargo_amount"));
            arrival.setLoading_cargo_description(resultSet.getString("loading_cargo_description"));
            arrival.setLoading_cargo_amount(resultSet.getInt("loading_cargo_amount"));
            arrival.setNumber_of_passengers_arrival(resultSet.getInt("number_of_passengers_arrival"));
            arrival.setNumber_of_passengers_departure(resultSet.getInt("number_of_passengers_departure"));
            arrival.setForm_validation(resultSet.getInt("form_validation"));
            arrivals.add(arrival);
        }
        connection.close();
        return arrivals;
    }

    public void approveForm(int id, int state) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "UPDATE prearrival SET form_validation = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        if (state == 0){
            state = 2;
        }
        statement.setInt(1, state);
        statement.setInt(2, id);
        statement.executeUpdate();
        connection.close();
    }

    public List<Arrival> fetchAllApprovedForms(String agent_email) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "select prearrival.id, imo_number, agent_email, arriving_from, eta, berth_number, p.name as port_name, etd, discharging_cargo_description, discharging_cargo_amount, loading_cargo_description, loading_cargo_amount, number_of_passengers_arrival, number_of_passengers_departure, crew_documents_customs, form_validation, ship_berthed, submitted_by from prearrival join ports p on prearrival.next_port_id = p.id where form_validation = 1 AND ship_berthed = 0 AND agent_email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, agent_email);
        ResultSet resultSet = statement.executeQuery();
        Arrival arrival;
        List<Arrival> arrivals = new ArrayList<>();
        while (resultSet.next()) {
            arrival = new Arrival();
            arrival.setId(resultSet.getInt("id"));
            arrival.setSubmitted_by(resultSet.getString("submitted_by"));
            arrival.setImo_number(resultSet.getInt("imo_number"));
            arrival.setAgent_email(resultSet.getString("agent_email"));
            arrival.setArriving_from(resultSet.getString("arriving_from"));
            arrival.setEta(resultSet.getString("eta"));
            arrival.setBerth_number(resultSet.getString("berth_number"));
            arrival.setNext_port_name(resultSet.getString("port_name"));
            arrival.setEtd(resultSet.getString("etd"));
            arrival.setDischarging_cargo_description(resultSet.getString("discharging_cargo_description"));
            arrival.setDischarging_cargo_amount(resultSet.getInt("discharging_cargo_amount"));
            arrival.setLoading_cargo_description(resultSet.getString("loading_cargo_description"));
            arrival.setLoading_cargo_amount(resultSet.getInt("loading_cargo_amount"));
            arrival.setNumber_of_passengers_arrival(resultSet.getInt("number_of_passengers_arrival"));
            arrival.setNumber_of_passengers_departure(resultSet.getInt("number_of_passengers_departure"));
            arrival.setForm_validation(resultSet.getInt("form_validation"));
            arrival.setShip_berthed(resultSet.getInt("ship_berthed"));
            arrivals.add(arrival);
        }
        connection.close();
        return arrivals;
    }

    public void approveBerth(int id, int state) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "UPDATE prearrival SET ship_berthed = ?, form_validation = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        int isBerthed = 0;
        if (state == 0){
            state = 2;
        }
        if (state != 2){
            isBerthed = 1;
        }
        statement.setInt(1, isBerthed);
        statement.setInt(2, state);
        statement.setInt(3, id);
        statement.executeUpdate();
        connection.close();
    }

    public List<Arrival> fetchFormsByEmail(String email) throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "select prearrival.id, imo_number, agent_email, arriving_from, eta, berth_number, p.name as port_name, etd, discharging_cargo_description, discharging_cargo_amount, loading_cargo_description, loading_cargo_amount, number_of_passengers_arrival, number_of_passengers_departure, crew_documents_customs, form_validation, ship_berthed, submitted_by from prearrival join ports p on prearrival.next_port_id = p.id where form_validation = 1 AND ship_berthed = 1 AND submitted_by = ? ORDER BY id DESC LIMIT 10";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        Arrival arrival;
        List<Arrival> arrivals = new ArrayList<>();
        while (resultSet.next()) {
            arrival = new Arrival();
            arrival.setId(resultSet.getInt("id"));
            arrival.setSubmitted_by(resultSet.getString("submitted_by"));
            arrival.setImo_number(resultSet.getInt("imo_number"));
            arrival.setAgent_email(resultSet.getString("agent_email"));
            arrival.setArriving_from(resultSet.getString("arriving_from"));
            arrival.setEta(resultSet.getString("eta"));
            arrival.setBerth_number(resultSet.getString("berth_number"));
            arrival.setNext_port_name(resultSet.getString("port_name"));
            arrival.setEtd(resultSet.getString("etd"));
            arrival.setDischarging_cargo_description(resultSet.getString("discharging_cargo_description"));
            arrival.setDischarging_cargo_amount(resultSet.getInt("discharging_cargo_amount"));
            arrival.setLoading_cargo_description(resultSet.getString("loading_cargo_description"));
            arrival.setLoading_cargo_amount(resultSet.getInt("loading_cargo_amount"));
            arrival.setNumber_of_passengers_arrival(resultSet.getInt("number_of_passengers_arrival"));
            arrival.setNumber_of_passengers_departure(resultSet.getInt("number_of_passengers_departure"));
            arrival.setForm_validation(resultSet.getInt("form_validation"));
            arrival.setShip_berthed(resultSet.getInt("ship_berthed"));
            arrivals.add(arrival);
        }
        connection.close();
        return arrivals;

    }
}

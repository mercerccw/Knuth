package com.softhaven.bean;


public class Arrival {
    int id;
    int imo_number;
    String agent_email;
    String arriving_from;
    String eta;
    String berth_number;
    int next_port_id;
    String etd;
    String discharging_cargo_description;
    int discharging_cargo_amount;
    String loading_cargo_description;
    int loading_cargo_amount;
    int number_of_passengers_arrival;
    int number_of_passengers_departure;
    String crew_documents_customs;
    int form_validation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImo_number() {
        return imo_number;
    }

    public void setImo_number(int imo_number) {
        this.imo_number = imo_number;
    }

    public String getAgent_email() {
        return agent_email;
    }

    public void setAgent_email(String agent_email) {
        this.agent_email = agent_email;
    }

    public String getArriving_from() {
        return arriving_from;
    }

    public void setArriving_from(String arriving_from) {
        this.arriving_from = arriving_from;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getBerth_number() {
        return berth_number;
    }

    public void setBerth_number(String berth_number) {
        this.berth_number = berth_number;
    }

    public int getNext_port_id() {
        return next_port_id;
    }

    public void setNext_port_id(int next_port_id) {
        this.next_port_id = next_port_id;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getDischarging_cargo_description() {
        return discharging_cargo_description;
    }

    public void setDischarging_cargo_description(String discharging_cargo_description) {
        this.discharging_cargo_description = discharging_cargo_description;
    }

    public int getDischarging_cargo_amount() {
        return discharging_cargo_amount;
    }

    public void setDischarging_cargo_amount(int discharging_cargo_amount) {
        this.discharging_cargo_amount = discharging_cargo_amount;
    }

    public String getLoading_cargo_description() {
        return loading_cargo_description;
    }

    public void setLoading_cargo_description(String loading_cargo_description) {
        this.loading_cargo_description = loading_cargo_description;
    }

    public int getLoading_cargo_amount() {
        return loading_cargo_amount;
    }

    public void setLoading_cargo_amount(int loading_cargo_amount) {
        this.loading_cargo_amount = loading_cargo_amount;
    }

    public int getNumber_of_passengers_arrival() {
        return number_of_passengers_arrival;
    }

    public void setNumber_of_passengers_arrival(int number_of_passengers_arrival) {
        this.number_of_passengers_arrival = number_of_passengers_arrival;
    }

    public int getNumber_of_passengers_departure() {
        return number_of_passengers_departure;
    }

    public void setNumber_of_passengers_departure(int number_of_passengers_departure) {
        this.number_of_passengers_departure = number_of_passengers_departure;
    }

    public String getCrew_documents_customs() {
        return crew_documents_customs;
    }

    public void setCrew_documents_customs(String crew_documents_customs) {
        this.crew_documents_customs = crew_documents_customs;
    }

    public int getForm_validation() {
        return form_validation;
    }

    public void setForm_validation(int form_validation) {
        this.form_validation = form_validation;
    }
}

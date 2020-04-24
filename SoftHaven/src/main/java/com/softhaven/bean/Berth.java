package com.softhaven.bean;

public class Berth {
    public String port;
    public String quay;
    public String type;
    public String number;
    public int ship_imo;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getQuay() {
        return quay;
    }

    public void setQuay(String quay) {
        this.quay = quay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getShip_imo() {
        return ship_imo;
    }

    public void setShip_imo(int ship_imo) {
        this.ship_imo = ship_imo;
    }
}

package models;

public class Vessel {
    public int imo;
    public String flag;
    public String name;
    public int built;
    public String callSign;
    public int length;
    public int breadth;
    public int tonnage;
    public int mmsi;
    public String type;
    public int ownerCode;

    public int getImo() {
        return imo;
    }

    public void setImo(int imo) {
        this.imo = imo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuilt() {
        return built;
    }

    public void setBuilt(int built) {
        this.built = built;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public int getTonnage() {
        return tonnage;
    }

    public void setTonnage(int tonnage) {
        this.tonnage = tonnage;
    }

    public int getMmsi() {
        return mmsi;
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(int ownerCode) {
        this.ownerCode = ownerCode;
    }
}

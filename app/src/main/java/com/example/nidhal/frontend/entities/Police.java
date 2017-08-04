package com.example.nidhal.frontend.entities;

/**
 * Created by Nidhal on 31/07/2017.
 */

public class Police {



    int num_police;
    int id_user;
    int id_insurance;
    int id_agency;
    String validty;
    String vehicle_brand;
    String vehicle_type;
    String serial_number_vh;

    public int getNum_police() {
        return num_police;
    }

    public void setNum_police(int num_police) {
        this.num_police = num_police;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_insurance() {
        return id_insurance;
    }

    public void setId_insurance(int id_insurance) {
        this.id_insurance = id_insurance;
    }

    public int getId_agency() {
        return id_agency;
    }

    public void setId_agency(int id_agency) {
        this.id_agency = id_agency;
    }

    public String getValidty() {
        return validty;
    }

    public void setValidty(String validty) {
        this.validty = validty;
    }

    public String getVehicle_brand() {
        return vehicle_brand;
    }

    public void setVehicle_brand(String vehicle_brand) {
        this.vehicle_brand = vehicle_brand;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getSerial_number_vh() {
        return serial_number_vh;
    }

    public void setSerial_number_vh(String serial_number_vh) {
        this.serial_number_vh = serial_number_vh;
    }
}

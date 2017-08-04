package com.example.nidhal.frontend.entities;

/**
 * Created by Nidhal on 28/07/2017.
 */

public class Agency {

    int id;
    String name;
    String address;
    String email;
    int id_insurance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_insurance() {
        return id_insurance;
    }

    public void setId_insurance(int id_insurance) {
        this.id_insurance = id_insurance;
    }
}

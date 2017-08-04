package com.example.nidhal.frontend.entities;

/**
 * Created by Nidhal on 01/08/2017.
 */

public class Constat {


    int id;
    int id_user_1;
    int id_user_2;
    int id_insurance_1;
    int id_insurance_2;
    String begining_date;
    String ending_date;
    String accident_location;
    String accident_date;
    int injuries;
    int minor_injuries;
    int damage_to_vhA;
    int damage_to_vhB;
    int witnesses;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user_1() {
        return id_user_1;
    }

    public void setId_user_1(int id_user_1) {
        this.id_user_1 = id_user_1;
    }

    public int getId_user_2() {
        return id_user_2;
    }

    public void setId_user_2(int id_user_2) {
        this.id_user_2 = id_user_2;
    }

    public int getId_insurance_1() {
        return id_insurance_1;
    }

    public void setId_insurance_1(int id_insurance_1) {
        this.id_insurance_1 = id_insurance_1;
    }

    public int getId_insurance_2() {
        return id_insurance_2;
    }

    public void setId_insurance_2(int id_insurance_2) {
        this.id_insurance_2 = id_insurance_2;
    }

    public String getBegining_date() {
        return begining_date;
    }

    public void setBegining_date(String begining_date) {
        this.begining_date = begining_date;
    }

    public String getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(String ending_date) {
        this.ending_date = ending_date;
    }

    public String getAccident_location() {
        return accident_location;
    }

    public void setAccident_location(String accident_location) {
        this.accident_location = accident_location;
    }

    public String getAccident_date() {
        return accident_date;
    }

    public void setAccident_date(String accident_date) {
        this.accident_date = accident_date;
    }

    public int getInjuries() {
        return injuries;
    }

    public void setInjuries(int injuries) {
        this.injuries = injuries;
    }

    public int getMinor_injuries() {
        return minor_injuries;
    }

    public void setMinor_injuries(int minor_injuries) {
        this.minor_injuries = minor_injuries;
    }

    public int getDamage_to_vhA() {
        return damage_to_vhA;
    }

    public void setDamage_to_vhA(int damage_to_vhA) {
        this.damage_to_vhA = damage_to_vhA;
    }

    public int getDamage_to_vhB() {
        return damage_to_vhB;
    }

    public void setDamage_to_vhB(int damage_to_vhB) {
        this.damage_to_vhB = damage_to_vhB;
    }

    public int getWitnesses() {
        return witnesses;
    }

    public void setWitnesses(int witnesses) {
        this.witnesses = witnesses;
    }
}

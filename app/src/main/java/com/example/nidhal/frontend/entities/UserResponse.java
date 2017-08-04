package com.example.nidhal.frontend.entities;


import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by Nidhal on 16/07/2017.
 */

public class UserResponse {




    @Json(name="first_name")
    String first_name;
    @Json(name="last_name")
    String last_name;

    @Json(name="address")
    String address;

    @Json(name="postal_code")
    String postal_code;

    @Json(name="province")
    String province;

    @Json(name="license_number")
    String license_number;

    @Json(name="city")
    String city;



    @Json(name="first_login")
    String first_login;

    public String getFirst_login() {
        return first_login;
    }

    public void setFirst_login(String first_login) {
        this.first_login = first_login;
    }

    public String getCity() {
        return city;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getProvince() {
        return province;
    }

    public String getLicense_number() {
        return license_number;
    }

    @Json(name = "name")
    String name;

    @Json(name = "email")
    String email;

    @Json(name = "created_at")
    String created_at;

    @Json(name = "updated_at")
    String updated_at;
    @Json(name = "id")
    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

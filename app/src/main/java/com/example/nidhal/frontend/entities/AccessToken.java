package com.example.nidhal.frontend.entities;

import com.squareup.moshi.Json;

/**
 * Created by Nidhal on 13/07/2017.
 */

public class AccessToken {

    @Json(name = "token_type")
    String token_type;

    @Json(name = "expires_in")
    String expires_in;

    @Json(name = "access_token")
    String access_token;

    @Json(name = "refresh_token")
    String refresh_token;

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}

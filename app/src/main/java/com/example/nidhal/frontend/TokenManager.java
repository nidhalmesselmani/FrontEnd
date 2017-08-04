package com.example.nidhal.frontend;

/**
 * Created by Nidhal on 14/07/2017.
 */
import android.content.SharedPreferences;

import com.example.nidhal.frontend.entities.AccessToken;


public class TokenManager {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs){
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(AccessToken token){
        editor.putString("ACCESS_TOKEN", token.getAccess_token()).commit();
        editor.putString("REFRESH_TOKEN", token.getRefresh_token()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }

    public AccessToken getToken(){
        AccessToken token = new AccessToken();
        token.setAccess_token(prefs.getString("ACCESS_TOKEN", null));
        token.setRefresh_token(prefs.getString("REFRESH_TOKEN", null));
        return token;
    }



}
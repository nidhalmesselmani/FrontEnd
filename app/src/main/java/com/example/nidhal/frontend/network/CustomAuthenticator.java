package com.example.nidhal.frontend.network;

/**
 * Created by Nidhal on 14/07/2017.
 */

import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.entities.AccessToken;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

public class CustomAuthenticator implements Authenticator {

    private TokenManager tokenManager;
    private static CustomAuthenticator INSTANCE;

    private CustomAuthenticator(TokenManager tokenManager){
        this.tokenManager = tokenManager;
    }

    static synchronized CustomAuthenticator getInstance(TokenManager tokenManager){
        if(INSTANCE == null){
            INSTANCE = new CustomAuthenticator(tokenManager);
        }

        return INSTANCE;
    }


    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {


        //pour limite le nombre de requete
        if(responseCount(response) >= 3){
            return null;
        }
        //recevoire le token
        AccessToken token = tokenManager.getToken();
        //creation de service sans authorization
        ApiService service = RetrofitBuilder.createService(ApiService.class);
        //recevoire le refresh token et passer comme un argument dans notre call
        Call<AccessToken> call = service.refresh(token.getRefresh_token() + "a");

        //faire de maniere synchrone "execute"
        retrofit2.Response<AccessToken> res = call.execute();

        if(res.isSuccessful()){
            AccessToken newToken = res.body();
            tokenManager.saveToken(newToken);

            return response.request().newBuilder().header("Authorization", "Bearer " + res.body().getAccess_token()).build();
        }else{

            //mauvais refresh token
            return null;
        }
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
package com.example.nidhal.frontend;

import com.example.nidhal.frontend.entities.ApiError;
import com.example.nidhal.frontend.network.RetrofitBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;

/**
 * Created by Nidhal on 14/07/2017.
 */

public class Utils {

    public static ApiError convertErrors(ResponseBody response) {
        retrofit2.Converter<ResponseBody, ApiError> converter = RetrofitBuilder.getRetrofit().responseBodyConverter(ApiError.class, new Annotation[0]);
         ApiError apiError = null;
        try {


             apiError = converter.convert(response);
        }catch (IOException e){
            e.printStackTrace();
        }

         return  apiError;
    }
}

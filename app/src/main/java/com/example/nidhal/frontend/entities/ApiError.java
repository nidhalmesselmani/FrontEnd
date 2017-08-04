package com.example.nidhal.frontend.entities;

import java.util.List;
import java.util.Map;

/**
 * Created by Nidhal on 14/07/2017.
 */

public class ApiError {

    String message;
    //list d'erreur qui peut varier
    Map<String,List<String>> errors;


    public Map<String, List<String>> getErrors() {
        return errors;
    }




    public String getMessage() {
        return message;
    }
}

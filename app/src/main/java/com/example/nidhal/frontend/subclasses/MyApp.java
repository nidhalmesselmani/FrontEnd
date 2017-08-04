package com.example.nidhal.frontend.subclasses;


import com.roughike.bottombar.BottomBar;

/**
 * Created by Nidhal on 04/07/2017.
 */

public class MyApp {

    private static String qrCode;
    private static int scan =0;
    public static BottomBar mBottomBar;
    public static int number ;
    public static Boolean scanFirst=false;
    private static String AppUrl="http://192.168.10.33:8000/";

    public static String getAppUrl() {
        return AppUrl;
    }

    public static int getScan() {
        return scan;
    }

    public static void incrementScan() {
        MyApp.scan++;
    }

    public static void setQrCode(String qrCode) {
        MyApp.qrCode = qrCode;
    }


    public static String getQrCode() {
        return qrCode;
    }

    public static void setScan(int scan) {
        MyApp.scan = scan;
    }




}

package com.example.nidhal.frontend;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.roughike.bottombar.BottomBar;
import com.squareup.leakcanary.LeakCanary;



/**
 * Created by Nidhal on 13/07/2017.
 */

public class MyApp extends Application {
 //   public static  BottomBar mBottomBar;




    @Override
    public void onCreate() {
        super.onCreate();
        //initialise Stetho
        Stetho.initializeWithDefaults(this);

        //initialise leak canary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}

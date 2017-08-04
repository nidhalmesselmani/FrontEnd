package com.example.nidhal.frontend.mainclasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nidhal.frontend.R;

/**
 * Created by Nidhal on 13/07/2017.
 */

public class DamageActitivty  extends AppCompatActivity {
     private RelativeLayout myLayout=null;
    private float x;
    private float y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.damage);
  //      myLayout = (RelativeLayout) findViewById(R.id.myLayout);

        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),"I was touched",Toast.LENGTH_SHORT).show();
                x=event.getX();
                y=event.getY();

                return true;
            }
        });




    }


}

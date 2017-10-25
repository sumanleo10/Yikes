package com.example.imran.yikes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class who extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);
        Button Cust = (Button)findViewById(R.id.cBtn);

        Button sm = (Button)findViewById(R.id.sBtn);
        Cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(who.this,register_user.class);
                startActivity(intent);

                // db.execSQL("CREATE TABLE IF NOT EXISTS td(userid VARCHAR,enamee VARCHAR,password VARCHAR,designation VARCHAR,mobile VARCHAR);");
            }
        });
        sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(who.this,smreg.class);
                startActivity(intent);
            }
        });

    }
}

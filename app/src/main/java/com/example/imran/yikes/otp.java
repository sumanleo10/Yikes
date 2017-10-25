package com.example.imran.yikes;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.imran.yikes.MainActivity.id;

public class otp extends AppCompatActivity {
    Button otpbtn;
    String otpp;
    public static  Activity otpact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        final EditText otp=(EditText)findViewById(R.id.otpID);
        otpbtn=(Button)findViewById(R.id.otpBTN);
        otpact=this;
        otpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpp=otp.getText().toString();
                new Background(otp.this).execute("otp",id,otpp);

            }
        });
    }
}

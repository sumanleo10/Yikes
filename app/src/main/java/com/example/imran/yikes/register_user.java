package com.example.imran.yikes;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class register_user extends AppCompatActivity {
    public static Activity sign_up;
    EditText UNAME,UEMAIL,UPASS,UNUMBER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        UNAME=(EditText)findViewById(R.id.crName);
        UEMAIL=(EditText)findViewById(R.id.cuName);
        UPASS=(EditText)findViewById(R.id.editText3);
        UNUMBER=(EditText)findViewById(R.id.editText4);
        sign_up = this;
        Button b = (Button) findViewById(R.id.regSubmit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;

                validation o = new validation();
                String error;
                if (!(error = o.nameValidation(UNAME.getText().toString())).equals("true")) {
                    UNAME.setError(error);
                    return;
                }
                if (!(error = o.nameValidation(UEMAIL.getText().toString())).equals("true")) {
                    UEMAIL.setError(error);
                    return;
                }
                if (!(error = o.passwordValidation(UPASS.getText().toString())).equals("true")) {
                    UPASS.setError(error);
                    return;
                }

                if (!(error = o.phoneValidation(UNUMBER.getText().toString())).equals("true")) {
                    UNUMBER.setError(error);
                    return;
                }
                new Background(register_user.this).execute("userregister", UNAME.getText().toString(), UEMAIL.getText().toString(), UPASS.getText().toString(), UNUMBER.getText().toString());
            }
        });
    }
}

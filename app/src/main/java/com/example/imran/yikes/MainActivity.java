package com.example.imran.yikes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    public static String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText un=(EditText)findViewById(R.id.idUser);
        final EditText ps=(EditText)findViewById(R.id.upass);
        Button signnup=(Button)findViewById(R.id.Rbtn);
        //db=openOrCreateDatabase("ship", Context.MODE_PRIVATE,null);
        final Spinner desig = (Spinner) findViewById(R.id.spinner1);
        String[] items = new String[]{"Who are YOU", "CUSTOMER", "SERVICE MAN"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        desig.setAdapter(adapter);
        Button login=(Button)findViewById(R.id.signinBtn);
        signnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,who.class);
                startActivity(intent);

                // db.execSQL("CREATE TABLE IF NOT EXISTS td(userid VARCHAR,enamee VARCHAR,password VARCHAR,designation VARCHAR,mobile VARCHAR);");
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EditText user_id = (EditText) findViewById(R.id.idUser);
                EditText user_password = (EditText)findViewById(R.id.upass);
                id = user_id.getText().toString();
                String password = user_password.getText().toString();
                String spin=String.valueOf(desig.getSelectedItem());

//                validation validation = new validation();
//                String error="";
//                if(!(error=validation.nameValidation(id)).equals("true")){
//                    user_id.setError(error);
//                    return;
//                }
//                if(!(error=validation.passwordValidation(password)).equals("true")){
//                    user_password.setError(error);
//                    return;
//                }
                new Background(MainActivity.this).execute("login",id,password,spin);
            }
        });
    }
}

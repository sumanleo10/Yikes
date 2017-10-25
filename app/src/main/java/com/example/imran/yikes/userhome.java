package com.example.imran.yikes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class userhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
        final EditText desc=(EditText)findViewById(R.id.desc1);
        final EditText phone=(EditText)findViewById(R.id.usercn);
        final EditText land=(EditText)findViewById(R.id.userlm) ;

        //db=openOrCreateDatabase("ship", Context.MODE_PRIVATE,null);
        final Spinner category = (Spinner) findViewById(R.id.spinner12);
        String[] items = new String[]{"SELECT CATEGORY", "ELECTONICS", "ELECTRICAL", "HOUSEHOLD", "PUMBING","AUTOMOBILES", "CLEANING"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        category.setAdapter(adapter);
        Button usersubmit=(Button)findViewById(R.id.userbtn);

        usersubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String des=desc.getText().toString();
                String phn = phone.getText().toString();
                String landmark=land.getText().toString();
                String spin=String.valueOf(category.getSelectedItem());

                validation validation = new validation();
                String error="";
                if(!(error=validation.userValidation(des)).equals("true")){
                    desc.setError(error);
                    return;
                }
                if(!(error=validation.userValidation(phn)).equals("true")){
                    phone.setError(error);
                    return;
                }
                if(!(error=validation.userValidation(landmark)).equals("true")){
                    land.setError(error);
                    return;
                }

              //
                  new Background(userhome.this).execute("problem",spin,des,phn,landmark);
            }
        });






    }
}

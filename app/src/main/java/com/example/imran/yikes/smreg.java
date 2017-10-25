package com.example.imran.yikes;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class smreg extends AppCompatActivity {
    public static Activity sign_up;
    String design;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smreg);
       final EditText SMNAME = (EditText) findViewById(R.id.editText);
        final EditText SMEMAIL = (EditText) findViewById(R.id.editText2);
        final EditText SMPASS = (EditText) findViewById(R.id.editText5);
        final EditText SMPHONE = (EditText) findViewById(R.id.editText7);
        final EditText SMYOE=(EditText)findViewById(R.id.editText8);
        final Spinner desig = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"SELECT BRANCH", "ELECTRICAL", "ELECTRONICS", "HOUSEHOLD", "PLUMBING","MECHANICAL", "BEAUTY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        desig.setAdapter(adapter);

        sign_up = this;
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                design = String.valueOf(desig.getSelectedItem());

/*

               db=openOrCreateDatabase("ship", Context.MODE_PRIVATE,null);
                db.execSQL("CREATE TABLE IF NOT EXISTS td(empid VARCHAR,enamee VARCHAR,password VARCHAR,designation VARCHAR,mobile VARCHAR);");
                db.execSQL("INSERT INTO td VALUES('"+eid.getText()+"','"+ename.getText()+"','"+password.getText()+"','"+design+"','"+pn.getText()+"');");
Intent intent=new Intent(signup.this,MainActivity.class);
                startActivity(intent);

*/
                validation o = new validation();
                String error;
                if (!(error = o.nameValidation(SMNAME.getText().toString())).equals("true")) {
                    SMNAME.setError(error);
                    return;
                }
                if (!(error = o.nameValidation(SMEMAIL.getText().toString())).equals("true")) {
                    SMEMAIL.setError(error);
                    return;
                }
                if (!(error = o.passwordValidation(SMPASS.getText().toString())).equals("true")) {
                    SMPASS.setError(error);
                    return;
                }
                if ((design).equals("SELECT BRANCH")) {
                    TextView ed = (TextView) desig.getSelectedView();
                    ed.setError("BRANCH must be selected");
                    return;
                }
                if (!(error = o.phoneValidation(SMPHONE.getText().toString())).equals("true")) {
                    SMPHONE.setError(error);
                    return;
                }
                new Background(smreg.this).execute("register", SMNAME.getText().toString(), SMEMAIL.getText().toString(), SMPASS.getText().toString(),  SMPHONE.getText().toString(),SMYOE.getText().toString(),design);
            }
        });
    }
}

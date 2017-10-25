package com.example.imran.yikes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.imran.yikes.register_user.sign_up;


class Background extends AsyncTask<String,Void,String> {
    private Context ctx;
    public static String designation;
    private ProgressDialog progressDialog;
    Background(Context ctx ){
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String user_reg_url = "http://imranshaik.000webhostapp.com/signup.php";
        String user_login_url = "http://imranshaik.000webhostapp.com/login.php";
        String otp_verification_url = "http://imranshaik.000webhostapp.com/verifyotp.php";
        String sm_reg_url = "http://imranshaik.000webhostapp.com/smsignup.php";
        String problem_url = "http://imranshaik.000webhostapp.com/problem.php";


        String method = params[0];
    String username,useremail,userpass,userphone,data,usercat;
        String smname,smemail,smpass,smphone,smyoe,smbranch,type,des,phone,land;
        if(method.equals("userregister")) {
            username=params[1];
            useremail=params[2];
            userpass=params[3];
            userphone=params[4];
            try {
                data = URLEncoder.encode("username","UTF-8") + "=" + URLEncoder.encode(username,"UTF-8") + "&" +
                        URLEncoder.encode("useremail","UTF-8") + "=" + URLEncoder.encode(useremail,"UTF-8") + "&" +
                        URLEncoder.encode("userpass","UTF-8") + "=" + URLEncoder.encode(userpass,"UTF-8") + "&" +
                        URLEncoder.encode("userphone","UTF-8") + "=" + URLEncoder.encode(userphone,"UTF-8");


                return connection(user_reg_url,data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }

        }
        else if (method.equals("login")) {
            useremail = params[1];
            userpass = params[2];
            usercat=params[3];

            try {
                data = URLEncoder.encode("useremail", "UTF-8") + "=" + URLEncoder.encode(useremail, "UTF-8") + "&" +
                        URLEncoder.encode("userpass", "UTF-8") + "=" + URLEncoder.encode(userpass, "UTF-8") +" &" +
                        URLEncoder.encode("usercat", "UTF-8") + "=" + URLEncoder.encode(usercat, "UTF-8") ;

                return connection(user_login_url, data);
            } catch (Exception exception) {
                exception.printStackTrace();
                progressDialog.dismiss();
            }
        }
        else if (method.equals("otp")) {
            useremail = params[1];
            String otp = params[2];

            try {
                data = URLEncoder.encode("useremail", "UTF-8") + "=" + URLEncoder.encode(useremail, "UTF-8") + "&" +
                        URLEncoder.encode("otp", "UTF-8") + "=" + URLEncoder.encode(otp, "UTF-8");
                return connection(otp_verification_url, data);
            } catch (Exception exception) {
                exception.printStackTrace();
                progressDialog.dismiss();
            }
        }
       else if(method.equals("register")) {
            smname=params[1];
            smemail=params[2];
            smpass=params[3];
            smphone=params[4];

            smyoe=params[5];
            smbranch=params[6];
            try {
                data = URLEncoder.encode("smname","UTF-8") + "=" + URLEncoder.encode(smname,"UTF-8") + "&" +
                        URLEncoder.encode("smemail","UTF-8") + "=" + URLEncoder.encode(smemail,"UTF-8") + "&" +
                        URLEncoder.encode("smpass","UTF-8") + "=" + URLEncoder.encode(smpass,"UTF-8") + "&" +
                        URLEncoder.encode("smphone","UTF-8") + "=" + URLEncoder.encode(smphone,"UTF-8")+ "&" +
                        URLEncoder.encode("smyoe","UTF-8") + "=" + URLEncoder.encode(smyoe,"UTF-8")+ "&" +
                        URLEncoder.encode("smbranch","UTF-8") + "=" + URLEncoder.encode(smbranch,"UTF-8");


                return connection(sm_reg_url,data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }

        }
        else if(method.equals("problem")) {
            type=params[1];
            des=params[2];
            phone=params[3];
            land=params[4];

            smyoe=params[5];
            smbranch=params[6];
            try {
                data = URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode(type,"UTF-8") + "&" +
                        URLEncoder.encode("des","UTF-8") + "=" + URLEncoder.encode(des,"UTF-8") + "&" +
                        URLEncoder.encode("phone","UTF-8") + "=" + URLEncoder.encode(phone,"UTF-8") + "&" +
                        URLEncoder.encode("land","UTF-8") + "=" + URLEncoder.encode(land,"UTF-8");


                return connection(problem_url,data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }

        }


        return "Exception raised in encoding";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        JSONObject json;
        int success;
        String message="";
        try {
            json = new JSONObject(result);
            message = json.getString("message");
            Toast.makeText(ctx,message, Toast.LENGTH_SHORT).show();
            switch (message){
               case "registered"      :sign_up.finish();
                                                     Toast.makeText(ctx,message,Toast.LENGTH_LONG).show();
                                                  break;
                case "otp page"             :designation = json.getString("designation");
                                                     Intent intent = new Intent(ctx,otp.class);
                                                    ctx.startActivity(intent);
                                                     break;
                case "Verification Successful"      :  openActivity(designation);
                    break;
                case "problem inserted": Toast.makeText(ctx,message,Toast.LENGTH_LONG).show();




                default                             : Toast.makeText(ctx.getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }
    }

    private String connection(String url_string,String data) {
        try {
            URL url = new URL(url_string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            InputStream is = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            httpURLConnection.disconnect();
            is.close();
            return String.valueOf(buffer);
        } catch (Exception exception) {
            exception.printStackTrace();
            progressDialog.dismiss();
        }

        return "Exception raised in http request";
    }

    private void openActivity(String des) {
        if (des.equals("CUSTOMER")) {
            Toast.makeText(ctx, "HII CUSTOMER", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ctx, userhome.class);
            ctx.startActivity(intent);

        } else if (des.equals("SERVICE MAN")) {
            Toast.makeText(ctx, "HII SERVICeMAN", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ctx,smhome.class);
             ctx.startActivity(intent);

        }
    }
    public void fetchUsers(JSONObject json) {
        try {
            JSONArray jsonArray= json.getJSONArray("array");
            StringBuffer buffer = new StringBuffer();
            for (int counter=0;counter<jsonArray.length();counter++){
                JSONObject jsonObject = jsonArray.getJSONObject(counter);
                buffer.append(jsonObject.getString("id"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

package rest;

import android.os.AsyncTask;
import android.view.View;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import fragments.SignUpFragment;
import limiszewska.projecthavana.activities.R;
import limiszewska.projecthavana.activities.SignInUp;

/**
 * Created by Agnieszka on 2015-11-04.
 */
public class Register extends AsyncTask<String, String, String> {

    SignUpFragment signUpFragment;
    JSONObject jsonObject;


    String method = "register";
    HttpClient client = new DefaultHttpClient();
    HttpPost post = new HttpPost(SettingConnections.apiName.concat(method));
    HttpResponse response;

    public Register(SignUpFragment signUpFragment){
        this.signUpFragment = signUpFragment;
    }

    @Override
    protected String doInBackground(String[] params) {
        //params [0] - login, [2] - email [3] - password

        jsonObject = new JSONObject();

        try{
            jsonObject.put("login", params[0]);
            jsonObject.put("email", params[1]);
            jsonObject.put("password", params[2]);
            //jsonObject.put("name", "niepotrzebne_imie");
            //jsonObject.put("surname", "niepotrzebne_nazwisko");

        }catch (JSONException ex){

        }

        try{

            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));



            post.setEntity(se);
            try {
                response = client.execute(post);
                HttpEntity resultentity = response.getEntity();
                InputStream inputstream = resultentity.getContent();
                String jSONString = SettingConnections.convertStreamToString(inputstream);
                try {
                    JSONObject jsonObject1 = new JSONObject(jSONString);
                    JSONObject data = jsonObject1.getJSONObject("data");
                    return data.getString("token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }





        }catch (IOException ex){

        }



        return SettingConnections.context.getString(R.string.no_internet);


    }


    @Override
    protected  void onPostExecute(String result){
        if(ifError(result)){
            SettingConnections.token = result;
            SignInUp.goToMainActivity(SettingConnections.context);
        }
            signUpFragment.startProgressBar.setVisibility(View.GONE);
            signUpFragment.signUpRelativeLayout.setVisibility(View.VISIBLE);

    }

    protected boolean ifError(String result){
        switch (result){
            case "-1":
                signUpFragment.login.setError(SettingConnections.context.getString(R.string.registration_1_login_empty));
                return false;
            case "-2":
                signUpFragment.password.setError(SettingConnections.context.getString(R.string.registration_2_password_week));
                return false;
            case "-3":
                signUpFragment.email.setError(SettingConnections.context.getString(R.string.registration_3_email_duplicate));
                return false;
            case "-4":
                signUpFragment.email.setError(SettingConnections.context.getString(R.string.registration_4_email_empty));
                return false;
        };
        //signUpFragment.text.setText(result);
        if (result.equals(SettingConnections.context.getString(R.string.no_internet))){
            signUpFragment.text.setText(result);
            return false;
        }
        return true;
    }
}

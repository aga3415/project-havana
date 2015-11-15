package rest;

import android.os.AsyncTask;
import android.view.View;

import org.apache.http.Header;
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
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import fragments.SignInFragment;
import limiszewska.projecthavana.activities.R;
import limiszewska.projecthavana.activities.SignInUp;

/**
 * Created by Agnieszka on 2015-11-02.
 */
public class Login extends AsyncTask<String, String, String> {

    SignInFragment signInFragment;

    public Login(SignInFragment fragment){
        this.signInFragment = fragment;
    }

    JSONObject jsonObject;


    String method = "login";
    HttpClient client = new DefaultHttpClient();
    HttpPost post = new HttpPost(SettingConnections.apiName.concat(method));
    HttpResponse response;

    @Override
    protected String doInBackground(String[] params) {
        //params [0] - login, [1] - password

        jsonObject = new JSONObject();

        try{
            jsonObject.put("email", params[0]);
            jsonObject.put("password", params[1]);

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
                    return SettingConnections.context.getString(R.string.no_internet);
                }


            } catch (IOException e) {
                e.printStackTrace();
                return SettingConnections.context.getString(R.string.no_internet);
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
           signInFragment.startProgressBar.setVisibility(View.GONE);
           signInFragment.signInRelativeLayout.setVisibility(View.VISIBLE);

    }

    protected boolean ifError(String result){
        switch (result){
            case "-1":
                signInFragment.login.setError(SettingConnections.context.getString(R.string.login_1_login_error));
                return false;
            case "-2":
                signInFragment.login.setError(SettingConnections.context.getString(R.string.login_1_login_error));
                return false;
            case "-3":
                signInFragment.text.setText(SettingConnections.context.getString(R.string.login_3_login_nonactive));
                return false;
            case "-4":
                signInFragment.password.setError(SettingConnections.context.getString(R.string.login_4_login_wrong_pass));
                return false;

        };


        if (result.equals(SettingConnections.context.getString(R.string.no_internet))){
            signInFragment.text.setText(result);
            return false;
        }

        return true;
    }
}

package rest;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import dataModel.User;
import limiszewska.projecthavana.activities.UserShowDetails;

/**
 * Created by Agnieszka on 2015-11-26.
 */
public class GetUserDetails extends AsyncTask<String, Boolean, Boolean>{

    String method = "users/";
    HttpClient client = new DefaultHttpClient();
    HttpGet get;
    HttpResponse response;
    String responseString;
    JSONObject responseJSON;
    @Override
    protected Boolean doInBackground(String... params) {
        //params[0] - userID
        get = new HttpGet(SettingConnections.apiName + method + params[0]);
        get.setHeader("Authorization", SettingConnections.token);

        try {
            response = client.execute(get);
            InputStream inputstream = response.getEntity().getContent();
            responseString =  SettingConnections.convertStreamToString(inputstream);

            try {
                responseJSON = new JSONObject(responseString);
                responseJSON = responseJSON.getJSONObject("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    protected void onPostExecute(Boolean result){

        if (result){
            UserShowDetails.setUser(new User(responseJSON));
            UserShowDetails.getInstance().setDetailAboutUser();
        }else{
            //tutaj trzeba bedzie wymyslic jakis blad
        }

    }


}

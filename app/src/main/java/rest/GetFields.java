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

import dataModel.FieldsFactory;


/**
 * Created by Agnieszka on 2015-12-08.
 */
public class GetFields extends AsyncTask<Boolean, Boolean, Boolean> {

    String method = "fields";
    HttpClient client = new DefaultHttpClient();
    HttpGet get = new HttpGet(SettingConnections.apiName.concat(method));
    HttpResponse response;
    String responseString;

    @Override
    protected Boolean doInBackground(Boolean... params) {
        get.setHeader("Authorization", SettingConnections.token);
        try{
            response = client.execute(get);
            InputStream inputstream = response.getEntity().getContent();
            responseString =  SettingConnections.convertStreamToString(inputstream);

            try {
                FieldsFactory.insertInDB(FieldsFactory.prepareFieldsList(new JSONObject(responseString)));
                 //przygotowanie listy boisk
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

            return true;


        }catch(IOException e) {
            e.printStackTrace();
        }


        return false;

    }

    protected void onPostExecute(Boolean result){
        if (result){

        }else{
            //miejsce na blad
        }
    }
}

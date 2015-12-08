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
import java.util.ArrayList;

import dataModel.CitiesFactory;
import dataModel.City;
import dataModel.FieldsFactory;
import limiszewska.projecthavana.activities.Cities;

/**
 * Created by Agnieszka on 2015-12-08.
 */
public class GetCities extends AsyncTask<Boolean, Boolean, Boolean>{

    String method = "cities";
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
                ArrayList<City> cities = CitiesFactory.prepareCitiesList(new JSONObject(responseString));
                CitiesFactory.insertInDB(cities);
                Cities.setCitiesList(cities);

                 //przygotowanie listy miast
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
            Cities.setAdapter();
        }else{
            //miejsce na blad
        }
    }
}

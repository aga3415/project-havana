package rest;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

import limiszewska.projecthavana.activities.EventShowDetails;
import limiszewska.projecthavana.activities.MainActivity;

/**
 * Created by Agnieszka on 2015-11-10.
 */
public class GetEventDetails extends AsyncTask<String, Boolean, Boolean> {


    String method = "events/";
    HttpClient client = new DefaultHttpClient();
    HttpGet get;
    HttpResponse response;
    String responseString;




    @Override
    protected Boolean doInBackground(String... params) {
        //params[0] id eventu ktory chce sciagnac

        get = new HttpGet(SettingConnections.apiName + method + params[0]);
        get.setHeader("Authorization", SettingConnections.token);

        try {
            response = client.execute(get);
            InputStream inputstream = response.getEntity().getContent();
            responseString =  SettingConnections.convertStreamToString(inputstream);



        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return null;
    }

    protected void onPostExecute(Boolean result){
        //MainActivity.goToShowEventDetailsActivity(SettingConnections.context);
        EventShowDetails.getInstance().setDetail("", responseString, "", "", "", "");
    }
}

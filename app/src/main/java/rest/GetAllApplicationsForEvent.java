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

import adapters.ApplicationsListAdapter;
import dataModel.Application;
import dataModel.ApplicationFactory;
import dataModel.Event;
import limiszewska.projecthavana.activities.EventShowDetails;

/**
 * Created by Agnieszka on 2015-11-11.
 */
public class GetAllApplicationsForEvent extends AsyncTask<String, Boolean, Boolean> {

    String method = "events/";
    HttpClient client = new DefaultHttpClient();
    HttpGet get;
    HttpResponse response;
    String responseString;
    JSONObject responseJSON;


    @Override
    protected Boolean doInBackground(String... params) {
        //params[0] id eventu ktory chce sciagnac

        get = new HttpGet(SettingConnections.apiName + method + params[0] + "/applications");
        get.setHeader("Authorization", SettingConnections.token);

        try {
            response = client.execute(get);
            InputStream inputstream = response.getEntity().getContent();
            responseString =  SettingConnections.convertStreamToString(inputstream);
            try {
                responseJSON = new JSONObject(responseString);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return null;
    }

    protected void onPostExecute(Boolean result){
        //MainActivity.goToShowEventDetailsActivity(SettingConnections.context);
        ArrayList<Application> applicationArrayList = ApplicationFactory.createListOfApplicationsForEvent(responseJSON);
        if (applicationArrayList != null){
            Event event = EventShowDetails.getInstance().event;
            Application application = new Application(event.user_name, event.id_user, event.id, "2");
            applicationArrayList.add(application);
            EventShowDetails.getInstance().listOfApplication = applicationArrayList;
            //EventShowDetails.getInstance().adapter.notifyDataSetChanged();
            EventShowDetails.getInstance().adapter = new ApplicationsListAdapter(EventShowDetails.getInstance(),
                    EventShowDetails.getInstance().listOfApplication);
            EventShowDetails.getInstance().membersListView.setAdapter(EventShowDetails.getInstance().adapter);
            EventShowDetails.getInstance().adapter.notifyDataSetChanged();
        }else{
            //tutaj trzeba wymyslic jakis blad
        }
        ;
    }
}

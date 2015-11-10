package rest;

import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import adapters.EventsListAdapter;
import dataModel.Event;
import dataModel.EventFactory;
import fragments.FragmentEvents;
import limiszewska.projecthavana.activities.EventShowDetails;
import limiszewska.projecthavana.activities.MainActivity;
import limiszewska.projecthavana.activities.R;

/**
 * Created by Agnieszka on 2015-11-04.
 */
public class GetAllEvents extends AsyncTask<Boolean, Boolean, Boolean> {

    FragmentEvents fragmentEvents;
    ArrayList<Event> events;



    String method = "events";
    HttpClient client = new DefaultHttpClient();
    HttpGet get = new HttpGet(SettingConnections.apiName.concat(method));
    HttpResponse response;
    String responseString;

    public GetAllEvents(FragmentEvents fragmentEvents){
        this.fragmentEvents = fragmentEvents;
    }

    @Override
    protected Boolean doInBackground(Boolean[] params) {

        get.setHeader("Authorization", SettingConnections.token);
        try{
            response = client.execute(get);
            InputStream inputstream = response.getEntity().getContent();
            responseString =  SettingConnections.convertStreamToString(inputstream);

            try {
                events = EventFactory.createListOfEvents(responseString);
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


    @Override
    protected  void onPostExecute(Boolean result){
        if(result){
            fragmentEvents.eventsListView.setVisibility(View.VISIBLE);
            fragmentEvents.errorTextView.setVisibility(View.GONE);
            fragmentEvents.adapter = new EventsListAdapter(MainActivity.instance, events);
            fragmentEvents.eventsListView.setAdapter(fragmentEvents.adapter);
            fragmentEvents.eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String eventId = fragmentEvents.adapter.getEventId(position);
                    MainActivity.goToShowEventDetailsActivity(SettingConnections.context);
                    GetEventDetails getEventDetails = new GetEventDetails();
                    getEventDetails.execute(eventId);

                }
            });
        }else{
            fragmentEvents.eventsListView.setVisibility(View.GONE);
            fragmentEvents.errorTextView.setVisibility(View.VISIBLE);
        }
        /*fragmentEvents.eventsListView.setVisibility(View.GONE);
        fragmentEvents.errorTextView.setVisibility(View.VISIBLE);
        fragmentEvents.errorTextView.setText(responseString);
        */

    }


}

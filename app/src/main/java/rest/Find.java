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
import fragments.FragmentFindEvents;
import limiszewska.projecthavana.activities.MainActivity;

/**
 * Created by Agnieszka on 2015-11-24.
 */
public class Find extends AsyncTask<String, String, Boolean> {

    FragmentFindEvents fragmentEvents;
    ArrayList<Event> events;
    String [] paramsToFind = {"city=", "field=", "dateMore=", "dateLess=", "priceLess=", "freeSlotsMore=", "hourMore=", "hourLess="};
    String methodConcat = "&";


    public Find(){
        fragmentEvents = FragmentFindEvents.instance;
    }
    String method = "events?";
    HttpClient client = new DefaultHttpClient();
    HttpGet get;
    HttpResponse response;
    String responseString;


    @Override
    protected Boolean doInBackground(String... params) {
        //[0] - city
        //[1] - Field
        //[2] - date from, [3] - date to
        //[4] - price
        //[5] - freeSlots
        //[6] - hourFrom
        //[7] - hourTo

        //fragmentEvents.eventsListView.setVisibility(View.GONE);


        for (int i=0; i<=7; i++){
            if (!params[i].equals("")) method += paramsToFind[i] + params[i] + methodConcat;
        }
        method = method.substring(0, method.length()-1); //pozbywam sie ostatniego &

        //fragmentEvents.errorTextView.setVisibility(View.VISIBLE);
        //fragmentEvents.errorTextView.setText(method);

        get = new HttpGet(SettingConnections.apiName.concat(method));
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
                    GetAllApplicationsForEvent getAllApplicationsForEvent = new GetAllApplicationsForEvent();
                    getAllApplicationsForEvent.execute(eventId);

                }
            });
        }else{
            fragmentEvents.eventsListView.setVisibility(View.GONE);
            fragmentEvents.errorTextView.setVisibility(View.VISIBLE);
        }


    }
}

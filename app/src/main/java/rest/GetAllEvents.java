package rest;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import adapters.EventsListAdapter;
import dataModel.Event;
import dataModel.EventFactory;
import fragments.SignInFragment;
import fragments.SignUpFragment;
import limiszewska.projecthavana.activities.FragmentEvents;
import limiszewska.projecthavana.activities.MainActivity;
import limiszewska.projecthavana.activities.R;
import limiszewska.projecthavana.activities.SignInUp;

/**
 * Created by Agnieszka on 2015-11-04.
 */
public class GetAllEvents extends AsyncTask<String, String, String> {

    FragmentEvents fragmentEvents;
    ArrayList<Event> events;


    String method = "events";
    HttpClient client = new DefaultHttpClient();
    HttpGet get = new HttpGet(SettingConnections.apiName.concat(method));
    HttpResponse response;

    public GetAllEvents(FragmentEvents fragmentEvents){
        this.fragmentEvents = fragmentEvents;
    }

    @Override
    protected String doInBackground(String[] params) {

        get.setHeader("Authorization", SettingConnections.token);
        try{
            response = client.execute(get);
            InputStream inputstream = response.getEntity().getContent();
            /*return*/ String responseString =  SettingConnections.convertStreamToString(inputstream);
            //try {
                //JSONObject jsonObject1 = new JSONObject(responseString);
                //JSONArray jsonArray = jsonObject1.getJSONArray("data");
                //JSONObject jsonObjectFromArray = jsonArray.getJSONObject(0);
                //return jsonObjectFromArray.getString("id");
                events = EventFactory.createListOfEvents(responseString);

                return "ok";

            //} catch (JSONException e) {
              //  e.printStackTrace();
            //}
        }catch(IOException e) {
            e.printStackTrace();
        }

        /*

        try{

            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));



            post.setEntity(se);
            try {
                response = client.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }


            HttpEntity resultentity = response.getEntity();
            InputStream inputstream = resultentity.getContent();
            return SettingConnections.convertStreamToString(inputstream);


        }catch (IOException ex){

        }


*/
        return SettingConnections.context.getString(R.string.server_not_response);


    }


    @Override
    protected  void onPostExecute(String result){
        if(ifError(result)){
            //SettingConnections.token = result;
        };

        fragmentEvents.adapter = new EventsListAdapter(MainActivity.instance, events);
        fragmentEvents.eventsListView.setAdapter(fragmentEvents.adapter);
    }

    protected boolean ifError(String result){

        //signInFragment.text.setText("Dostepne zdarzenia: "+ result);
        //if (signInFragment.text.getText().toString().equals(SettingConnections.context.getString(R.string.server_not_response))){
        //    return false;
        //}
        return true;
    }
}

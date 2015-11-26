package rest;

import android.os.AsyncTask;

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

import dataModel.Event;
import limiszewska.projecthavana.activities.EventShowDetails;
import limiszewska.projecthavana.activities.MainActivity;
import limiszewska.projecthavana.activities.R;
import limiszewska.projecthavana.activities.SignInUp;

/**
 * Created by Agnieszka on 2015-11-11.
 */
public class JoinToGame extends AsyncTask<String,Boolean, Boolean> {

    String method = "events/";
    HttpClient client = new DefaultHttpClient();
    HttpPost post;
    HttpResponse response;
    JSONObject jsonObject;
    Event event;


    public JoinToGame(Event event){
        this.event = event;
    }

    @Override
    protected Boolean doInBackground(String[] params) {
        //params [0] - event.id

        post = new HttpPost(SettingConnections.apiName + method + params[0] + "/applications");
        post.setHeader("Authorization", SettingConnections.token);
        jsonObject = new JSONObject();


        try {

            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));

            post.setEntity(se);

            try {
                response = client.execute(post);
                HttpEntity resultentity = response.getEntity();
                InputStream inputstream = resultentity.getContent();
                String jSONString = SettingConnections.convertStreamToString(inputstream);
                /*try {
                    JSONObject jsonObject1 = new JSONObject(jSONString);
                    JSONObject data = jsonObject1.getJSONObject("data");
                    return true;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return SettingConnections.context.getString(R.string.no_internet);
                }*/

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        } catch (IOException ex) {
            return false;

        }

        return true;

    }





    @Override
    protected  void onPostExecute(Boolean result){
        if (result){
            event.userStatus = "0";
            //MainActivity.goToShowEventDetailsActivity(SettingConnections.context);
            EventShowDetails.getInstance().setDetail(event);
            GetAllApplicationsForEvent getAllApplicationsForEvent = new GetAllApplicationsForEvent();
            getAllApplicationsForEvent.execute(EventShowDetails.getInstance().event.id);
        }else{
            EventShowDetails.getInstance().joinToEventCheckBox.setChecked(false);

        }

    }

}

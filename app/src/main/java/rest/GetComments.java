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

import dataModel.CommentsFactory;
import limiszewska.projecthavana.activities.UserShowDetails;

/**
 * Created by Agnieszka on 2015-11-26.
 */
public class GetComments extends AsyncTask<String, Boolean, Boolean> {

    String method1 = "users/", method2="/comments";
    HttpClient client = new DefaultHttpClient();
    HttpGet get;
    HttpResponse response;
    String responseString;
    JSONObject responseJSON;
    UserShowDetails userShowDetails;

    public GetComments(UserShowDetails userShowDetails){
        this.userShowDetails = userShowDetails;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        //params[0] - id usera ktorego komentarze chce sciagnac

        get = new HttpGet(SettingConnections.apiName +method1 + params[0] + method2);
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

        return true;
    }

    protected void onPostExecute(Boolean result){
        if (result){
            UserShowDetails.setCommentsList(CommentsFactory.prepareCommentsList(responseJSON));
        }else{
            //miejsce na blad
        }
    }


}

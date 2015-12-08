package rest;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import limiszewska.projecthavana.activities.UserShowDetails;

/**
 * Created by Agnieszka on 2015-11-26.
 */
public class SendUserComment extends AsyncTask<String, Boolean, Boolean> {

    String method = "users/comments";
    HttpClient client = new DefaultHttpClient();
    HttpPost post;
    HttpResponse response;
    JSONObject jsonObject;

    @Override
    protected Boolean doInBackground(String... params) {
        //params[0] - idUsera którego oceniamy
        //params[1] - komentarz

        post = new HttpPost(SettingConnections.apiName + method);
        post.setHeader("Authorization", SettingConnections.token);
        jsonObject = new JSONObject();
        try {
            jsonObject.put("idUserTo", params[0]);
            jsonObject.put("comment", params[1]);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

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
                //wyciagam co zwrocila metoda ale póki co nic z tym nie robie

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        } catch (IOException ex) {
            return false;

        }

        return true;
    }

    protected void onPostExecute(Boolean result){
        if (result){
            //tu powinno byc wywolanie odswiezenia listy komentarzy
        }else{
            //tu powinien byc alert o błędzie
        }
    }
}

package rest;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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
public class SendUserMark extends AsyncTask<String, Boolean, Boolean> {

    String methodPrefiks = "users/", methodInfiks ="/reputation";
    HttpClient client = new DefaultHttpClient();
    HttpPut put;
    HttpResponse response;
    JSONObject jsonObject;

    @Override
    protected Boolean doInBackground(String... params) {
        //params[0] - idUsera którego oceniamy
        //params[1] - ocena

        put = new HttpPut(SettingConnections.apiName + methodPrefiks + params[0] + methodInfiks);
        put.setHeader("Authorization", SettingConnections.token);
        jsonObject = new JSONObject();
        try {
            jsonObject.put("idUserTo", params[0]);
            jsonObject.put("note", params[1]);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        try {

            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
            put.setEntity(se);

            try {
                response = client.execute(put);
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
            new GetUserDetails().execute(UserShowDetails.user.id);
        }else{
            //tu powinien byc alert o błędzie
        }
    }
}

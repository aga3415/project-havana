package rest;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Agnieszka on 2015-11-26.
 */
public class SendUserMark extends AsyncTask<String, Boolean, Boolean> {

    String methodPrefiks = "users/", methodInfiks ="/reputation";
    HttpClient client = new DefaultHttpClient();
    HttpPut put;
    HttpResponse response;
    String responseString;
    JSONObject jsonObject;

    @Override
    protected Boolean doInBackground(String... params) {
        //params[0] - idUsera którego oceniamy

        put = new HttpPut(SettingConnections.apiName + methodPrefiks + params[0] + methodInfiks);
        put.setHeader("Authorization", SettingConnections.token);
        jsonObject = new JSONObject();

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

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        } catch (IOException ex) {
            return false;

        }

        return true;

    }
}

package rest;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Agnieszka on 2015-11-02.
 */
public class SettingConnections {

    public static final String apiName = "http://ekipazboiska.pl:50500/api/";
    public static Context context;
    public static String token;
    //private static final String PREFERENCES_NAME = "connectionPreferences";

    public static String convertStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (Exception e) {

        }
        return total.toString();
    }

    public static String getSthFromJSoon(String jsonToString, String param){
        int startNr = jsonToString.indexOf("\""+ param +"\":") + param.length()+ 3 ;
        int stopNr = jsonToString.indexOf(":", startNr);
        if (stopNr == -1){
            stopNr = jsonToString.indexOf("}", startNr);
        }else{
            stopNr = jsonToString.indexOf("\"", startNr);
        }

        String value = jsonToString.substring(startNr, stopNr-1);
        if (value.substring(0,0).equals("\"")){
            value = value.substring(1, value.length());
        }
        return value;

    }




}

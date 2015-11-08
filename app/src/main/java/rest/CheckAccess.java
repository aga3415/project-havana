package rest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Agnieszka on 2015-10-18.
 */
public class CheckAccess {

    public static boolean isOnline(final Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static boolean isServerOnline(){
        try {
            URLConnection urlConnection = new URL("http://www.google.pl").openConnection();
            urlConnection.setConnectTimeout(400);
            urlConnection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}

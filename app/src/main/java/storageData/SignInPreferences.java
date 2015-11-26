package storageData;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Pair;

public class SignInPreferences {

    private static SharedPreferences pref;
    private static final String PREF_NAME= "SignInPreferences";
    private static final String PREF_REMEMBER = "rememberUser";
    private static final String PREF_EMAIL = "email";
    private static final String PREF_PASSWORD = "password";


    public static Pair<String, String> checkPreferences(Activity activity){
        pref = activity.getSharedPreferences(PREF_NAME, 0);
        if (pref.contains(PREF_REMEMBER)){
            if (pref.getBoolean(PREF_REMEMBER, false)){
                String email = pref.getString(PREF_EMAIL,"");
                String password = pref.getString(PREF_PASSWORD, "");
                return new Pair<String, String>(email, password);
            }
        }
        return null;
    }

    public static void setPreferences(Activity activity, String email, String password){
        pref = activity.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(PREF_REMEMBER, true);
        editor.putString(PREF_EMAIL, email);
        editor.putString(PREF_PASSWORD, password);
        editor.commit();


    }

    public static void deletePreferences(Activity activity){
        pref = activity.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}

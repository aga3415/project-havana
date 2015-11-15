package dataModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

/**
 * Created by Agnieszka on 2015-11-11.
 */
public class Application implements Comparable<Application>{

    public String id, login, idUser, idEvent, acceptance, date;

    public Application(String login, String idUser, String idEvent, String acceptance){
        this.login = login;
        this.idUser = idUser;
        this.idEvent = idEvent;
        this.acceptance = acceptance;
    }

    public Application(JSONObject jsonObject){

        try {
            id = jsonObject.getString("id");
            login = jsonObject.getString("login");
            idUser = jsonObject.getString("idUser");
            idEvent = jsonObject.getString("idEvent");
            acceptance = jsonObject.getString("acceptance");
            date = jsonObject.getString("date");
        } catch (JSONException e) {
            e.printStackTrace();

        }


    }

    @Override
    public int compareTo(Application another) {
        return Comparators.ACCEPTANCE.compare(this, another);
    }

    public static class Comparators {
        public static final Comparator<Application> ACCEPTANCE = new Comparator<Application>() {
            @Override
            public int compare(Application lhs, Application rhs) {
                return -Integer.parseInt(lhs.acceptance) + Integer.parseInt(rhs.acceptance);
            }
        };

    }
}

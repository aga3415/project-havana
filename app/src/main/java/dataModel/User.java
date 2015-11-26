package dataModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Agnieszka on 2015-11-26.
 */
public class User {

    public String id;
    public String login;
    public String email;
    public String reputation;
    public String avatar;

    public User(JSONObject jSONObject){

        try {
            id = jSONObject.getString("id");
            login = jSONObject.getString("login");
            email = jSONObject.getString("email");
            reputation = jSONObject.getString("reputation");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

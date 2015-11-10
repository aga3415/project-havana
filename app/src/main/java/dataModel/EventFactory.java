package dataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Agnieszka on 2015-11-08.
 */
public class EventFactory {

    public static ArrayList<Event> createListOfEvents(String JSONString) throws JSONException {
        ArrayList<Event> events = new ArrayList<Event>();


        JSONArray jsonArray = null;

            JSONObject jsonObject1 = new JSONObject(JSONString);
            jsonArray = jsonObject1.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                events.add(new Event(jsonArray.getJSONObject(i)));

            }

        return events;

    }
}

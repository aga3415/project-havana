package dataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Agnieszka on 2015-11-11.
 */
public class ApplicationFactory {



    public static ArrayList createListOfApplicationsForEvent(JSONObject jsonObject){

        ArrayList<Application> listOfApplication = new ArrayList<Application>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject applicationJSON = jsonArray.getJSONObject(i);
                Application application = new Application(applicationJSON);
                if (application != null){
                    listOfApplication.add(application);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listOfApplication;
    }
}

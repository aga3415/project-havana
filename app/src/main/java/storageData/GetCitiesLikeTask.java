package storageData;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import java.util.ArrayList;

import dataModel.CitiesFactory;
import dataModel.City;
import limiszewska.projecthavana.activities.Cities;
import rest.SettingConnections;

/**
 * Created by Agnieszka on 2015-12-08.
 */
public class GetCitiesLikeTask extends AsyncTask<String, Boolean, Boolean> {
    ArrayList<City> cities;
    @Override
    protected Boolean doInBackground(String... params) {
        Query query = new Query();
        SQLiteOpenHelper openHelper = new DatabaseHelper(SettingConnections.context);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        cities = CitiesFactory.getCitiesFromCursor(query.selectCityLikeName(db, params[0]));
        db.close();
        return true;
    }

    protected void onPostExecute(Boolean result){
        if(result){
            Cities.setCitiesList(cities);
            Cities.setAdapter();
        }
    }
}

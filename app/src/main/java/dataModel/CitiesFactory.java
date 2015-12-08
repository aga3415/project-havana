package dataModel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rest.SettingConnections;
import storageData.CitiesTable;
import storageData.DatabaseHelper;
import storageData.FieldsTable;

/**
 * Created by Agnieszka on 2015-12-08.
 */
public class CitiesFactory {
    public static ArrayList<City> citiesList = new ArrayList<>();

    public static ArrayList<City> prepareCitiesList(JSONObject jsonObject){

        citiesList.clear();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i=0; i< jsonArray.length(); i++){
                 citiesList.add(new City(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  citiesList;
    }

    public static void insertInDB(ArrayList<City> list){
        SQLiteOpenHelper openHelper = new DatabaseHelper(SettingConnections.context);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //db.delete(CitiesTable.tableName, null, null); //usuwam cala tabele

        CitiesTable citiesTable = new CitiesTable();
        citiesTable.onCreate(db);
        citiesTable.onUpgrade(db, 0,1);

        for (City city : list){
            CitiesTable.insert(db, city);
        }
        db.close();
    }

    public static ArrayList<City> getCitiesFromCursor(Cursor cities){
        ArrayList<City> cityArrayList = new ArrayList<>();
        cities.moveToFirst();
        while(!cities.isAfterLast()){
            cityArrayList.add(new City(cities.getLong(1), cities.getString(0)));
            cities.moveToNext();
        }
        return cityArrayList;
    }
}

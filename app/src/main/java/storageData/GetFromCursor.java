package storageData;

import android.database.Cursor;

import java.util.ArrayList;

import dataModel.City;

/**
 * Created by Agnieszka on 2015-12-02.
 */
public class GetFromCursor {

    public ArrayList<City> getAllCities(Cursor cursor){
        ArrayList<City> citiesList = new ArrayList<City>();

        cursor.moveToFirst();
               while (!cursor.isAfterLast()){
                    citiesList.add( new City(
                                        cursor.getLong(0),
                                        cursor.getString(1)
                    ));
                   cursor.moveToNext();
        }
        return citiesList;
    }
}

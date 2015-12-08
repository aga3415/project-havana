package storageData;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import java.util.HashMap;

import dataModel.City;

/**
 * Created by Agnieszka on 2015-11-28.
 */
public class CitiesTable extends Table {

    //public static final String tableName = "Cities";

    public CitiesTable(){
        tableName = "Cities";
        attributes = new HashMap<String, Pair<String, Integer>>();
        attributes.put("id", new Pair("INTEGER PRIMARY KEY", 0));
        attributes.put("name", new Pair("TEXT NOT NULL", 1));
    }

    public static long insert(SQLiteDatabase db, City city){
        ContentValues values = new ContentValues();
        values.put("id", city.getId());
        values.put("name", city.getName());
        return db.insert(tableName, null, values);
    }

    public static int update(SQLiteDatabase db, City beforeChange, City afterChange){
        ContentValues values = new ContentValues();
        values.put("name", afterChange.getName());
        return db.update(tableName, values, "id=?", new String[]{String.valueOf(beforeChange.getId())});
    }

    public static int delete(SQLiteDatabase db, City city){
        return db.delete(tableName, "id=?", new String[]{String.valueOf(city.getId())});
    }





}

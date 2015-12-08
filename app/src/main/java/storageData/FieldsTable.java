package storageData;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import java.util.HashMap;

import dataModel.City;
import dataModel.Field;

/**
 * Created by Agnieszka on 2015-11-28.
 */
public class FieldsTable extends Table{
    private long id;
    private String name;
    private City city;
    private String type;
    private Boolean lighting;
    private String description;
    private double lat;
    private double lng;

    //public static final String tableName = "Fields";

    public FieldsTable(){
        tableName = "Fields";
        attributes = new HashMap<String, Pair<String, Integer>>();
        attributes.put("id", new Pair<>("INTEGER PRIMARY KEY", 0));
        attributes.put("name", new Pair<>("TEXT NOT NULL", 1));
        attributes.put("type", new Pair<>("TEXT", 2));
        attributes.put("lighting", new Pair<>("INTEGER", 3));
        attributes.put("description", new Pair<>("TEXT",4));
        attributes.put("lat", new Pair<>("REAL", 5));
        attributes.put("lng", new Pair<>("REAL", 6));
        attributes.put("city", new Pair<>("INTEGER"/*, FOREIGN KEY (city) REFERENCES Cities (id)"*/, 7));


    }

    public static long insert(SQLiteDatabase db, Field field){
        ContentValues values = new ContentValues();
        values.put("id", field.getId());
        values.put("name", field.getName());
        values.put("city", field.getCity().getId());
        values.put("type", field.getType());
        values.put("lighting", field.getLighting());
        values.put("description", field.getDescription());
        values.put("lat", field.getLat());
        values.put("lng", field.getLng());
        return db.insert(tableName, null, values);
    }

    public static int delete(SQLiteDatabase db, Field field){
        return db.delete(tableName, "id=?", new String[]{String.valueOf(field.getId())});
    }
}

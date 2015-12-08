package dataModel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rest.SettingConnections;
import storageData.DatabaseHelper;
import storageData.FieldsTable;

/**
 * Created by Agnieszka on 2015-12-08.
 */
public class FieldsFactory {
    public static ArrayList<Field> fieldsList = new ArrayList<>();

    public static ArrayList<Field> prepareFieldsList(JSONObject jsonObject){

        fieldsList.clear();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i=0; i< jsonArray.length(); i++){
                fieldsList.add(new Field(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fieldsList;
    }

    public static void insertInDB(ArrayList<Field> list){
        SQLiteOpenHelper openHelper = new DatabaseHelper(SettingConnections.context);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //db.delete(FieldsTable.tableName, null, null); //usuwam cala tabele

        FieldsTable fieldsTable = new FieldsTable();
        fieldsTable.onCreate(db);
        fieldsTable.onUpgrade(db, 0, 1);

        for (Field field : list){
            FieldsTable.insert(db, field);
        }
        db.close();
    }

    public static ArrayList<Field> getFieldsFromCursor(Cursor fieldsCursor, String cityId, String cityName){
        ArrayList<Field> fieldsArrayList = new ArrayList<>();
        fieldsCursor.moveToFirst();
        while(!fieldsCursor.isAfterLast()){
            fieldsArrayList.add(new Field(fieldsCursor.getLong(0), fieldsCursor.getString(fieldsCursor.getColumnIndex("name")), new City(Long.parseLong(cityId), cityName)));
            fieldsCursor.moveToNext();
        }
        return fieldsArrayList;
    }
}

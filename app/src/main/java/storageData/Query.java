package storageData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import rest.SettingConnections;

/**
 * Created by Agnieszka on 2015-12-02.
 */
public class Query {

    public Cursor selectCityLikeName(SQLiteDatabase db, String param){
        String queryExample = "SELECT * from " + CitiesTable.tableName +  " WHERE name LIKE '%" +param +"%'";
        return db.rawQuery(queryExample, null);
    }

    public static long getCityId(String cityName){
        SQLiteOpenHelper openHelper = new DatabaseHelper(SettingConnections.context);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.query("Cities", new String[]{"id"}, "name='" + cityName + "'", null, null, null, null, null);
        cursor.moveToFirst();
        long id = cursor.getLong(0);
        db.close();
        return id;
    }

    public Cursor getFieldsByCityId(String id){
        SQLiteOpenHelper openHelper = new DatabaseHelper(SettingConnections.context);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        String queryExample = "SELECT * from Fields " /*+ FieldsTable.tableName*/ +  " WHERE city=" + id;
        Cursor cursor =  db.rawQuery(queryExample, null);
        //db.close();
        return cursor;
    }

    public Cursor getFieldsByCityName(SQLiteDatabase db, String cityName){
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables("Fields, Cities");
        queryBuilder.appendWhere("Fields.city = Cities.id");
        String columnsToReturn[] = {
                "Fields.id",
                "Fields.name"
        };
        String ordered = "name ASC";

        return queryBuilder.query(db, columnsToReturn, "City.name =?", new String[]{cityName}, null, null, ordered);
    }
}

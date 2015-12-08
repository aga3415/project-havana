package storageData;

import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import java.util.HashMap;

/**
 * Created by Agnieszka on 2015-11-28.
 */
public abstract class Table {

    public static String tableName;
    static HashMap<String, Pair<String, Integer>> attributes;
    //nazwa atrybuty, Para<opcje, nr_kolumny>

    public static String createTableQuery(){
        
        String create = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
        for (String attributeName: attributes.keySet()){
            create += attributeName + " " + attributes.get(attributeName).first + ", ";
        };
        create = create.substring(0, create.length()-2);
        create += ");";

        return create;
    }

    public static void onCreate(SQLiteDatabase db){
        db.execSQL(createTableQuery());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }
}

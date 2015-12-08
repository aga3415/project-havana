package storageData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABSE_NAME = "fields.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CitiesTable citiesTable = new CitiesTable();
        FieldsTable fieldsTable = new FieldsTable();
        citiesTable.onCreate(db);
        fieldsTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CitiesTable.onUpgrade(db, oldVersion, newVersion);
        FieldsTable.onUpgrade(db, oldVersion, newVersion);
    }
}

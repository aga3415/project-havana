package storageData;

import android.database.sqlite.SQLiteDatabase;

import dataModel.City;

/**
 * Created by Agnieszka on 2015-12-01.
 */
public class Transactions {

    public static void deleteCityAndFields(SQLiteDatabase db, City city){
        db.beginTransaction();
        try{
            //wywołanie wykonania wszystkich operacji
            db.setTransactionSuccessful(); //zatwierdzenie zmian
        }catch (Exception e){
            //czynności wykonywane w razie niepowodzenia transakcji

        } finally {
            db.endTransaction();
        }
    }
}

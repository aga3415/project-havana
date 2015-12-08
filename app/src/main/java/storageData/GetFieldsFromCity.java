package storageData;

import android.database.Cursor;
import android.os.AsyncTask;

import java.util.ArrayList;

import dataModel.Field;
import dataModel.FieldsFactory;
import limiszewska.projecthavana.activities.Fields;

/**
 * Created by Agnieszka on 2015-12-08.
 */
public class GetFieldsFromCity extends AsyncTask<String, Boolean, Boolean> {

    ArrayList<Field> fieldArrayList;
    @Override
    protected Boolean doInBackground(String... params) {
        //params[0] - id city
        //params[1] - city name

        Query query = new Query();
        Cursor cursor = query.getFieldsByCityId(params[0]);
        fieldArrayList = FieldsFactory.getFieldsFromCursor(cursor, params[0], params[1]);
        return true;
    }

    protected void onPostExecute(Boolean result){
        if (result){
            Fields.setFieldsListArray(fieldArrayList);
        }
    }
}

package limiszewska.projecthavana.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import adapters.CitiesListAdapter;
import adapters.FieldsListAdapter;
import dataModel.Field;

/**
 * Created by Agnieszka on 2015-12-08.
 */
public class Fields extends Activity {

    public static Fields instance;
    public static ArrayList<Field> fieldsListArray;
    public static FieldsListAdapter adapter;
    static ListView fieldsList;

    public static Fields getInstance(){
        return instance == null? new Fields() : instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_fields);
        } else {
            setContentView(R.layout.activity_fields);
        }

        fieldsList = (ListView) findViewById(R.id.fieldsList);

        if (fieldsListArray != null){
            setAdapter();
        }

    }

    public static void setFieldsListArray(ArrayList<Field> list){
        fieldsListArray = list;
    }

    public static void setAdapter(){
        if (fieldsListArray != null){
            adapter = new FieldsListAdapter(getInstance(), fieldsListArray);
            fieldsList.setAdapter(adapter);
            fieldsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

    }
}

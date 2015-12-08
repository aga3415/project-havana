package limiszewska.projecthavana.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.CitiesListAdapter;
import adapters.CommentsListAdapter;
import dataModel.City;
import dataModel.Comment;
import fragments.AddEventFragment;
import rest.GetCities;
import storageData.GetCitiesLikeTask;
import storageData.GetFieldsFromCity;

/**
 * Created by Agnieszka on 2015-12-08.
 */
public class Cities extends Activity {

    static Cities instance;
    static CitiesListAdapter adapter;
    static ListView citiesList;
    public static EditText citiesTitle;
    static ArrayList<City> citiesListArray;
    Button find, add;
    EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;



        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_cities);
        } else {
            setContentView(R.layout.activity_cities);
        }
        citiesList = (ListView) findViewById(R.id.citiesList);
        cityName = (EditText) findViewById(R.id.citiesEditText);
        find = (Button) findViewById(R.id.citiesFindButton);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetCitiesLikeTask getCitiesLikeTask = new GetCitiesLikeTask();
                getCitiesLikeTask.execute(cityName.getText().toString());
            }
        });

        add = (Button) findViewById(R.id.citiesAddButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        citiesTitle = (EditText) findViewById(R.id.citiesEditText);

        if (citiesListArray == null){
            GetCities getCities = new GetCities();
            getCities.execute();
        }else{
            adapter = new CitiesListAdapter(instance, citiesListArray);
            citiesList.setAdapter(adapter);
        }

    }

    public static void setCitiesList(ArrayList<City> list){
        citiesListArray = list;
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(new City(1, "Test"));
        if (adapter != null){
            adapter.notifyDataSetChanged();
        }
        //adapter = new CitiesListAdapter(instance, list);
        //citiesList.setAdapter(adapter);
    }

    public static void setAdapter(){
        adapter = new CitiesListAdapter(instance, citiesListArray);
        citiesList.setAdapter(adapter);
        citiesList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void onBackPressed(){
        City city = adapter.getChoosenCity();
        AddEventFragment.cityData = city;
        AddEventFragment.getInstance().city.setText(city.getName());
        GetFieldsFromCity getFieldsFromCity = new GetFieldsFromCity();
        getFieldsFromCity.execute(city.getId().toString(), city.getName());
        super.onBackPressed();
    }
}

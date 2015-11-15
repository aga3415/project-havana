package limiszewska.projecthavana.activities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.ApplicationsListAdapter;
import dataModel.Application;
import dataModel.Event;
import rest.GetEventDetails;
import rest.JoinToGame;
import rest.SettingConnections;

public class EventShowDetails extends Activity {

    TextView fieldName, fieldAddress;
    TextView date, hour;
    TextView price, freeSlots;
    public static ListView membersListView;
    public CheckBox joinToEventCheckBox;
    public static ArrayList<Application> listOfApplication;
    public static ApplicationsListAdapter adapter;
    public TextView yourApplicationStatusTextView;
    public Context context;

    public static EventShowDetails instance;
    public static Event event;

    public static EventShowDetails getInstance(){
        if (instance == null){
            return new EventShowDetails();
        }else{
            return instance;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_event_show_details_portrait);
        }else {
            setContentView(R.layout.activity_event_show_details_landscape);
        }


        instance = this;
        context = getApplicationContext();

        fieldName = (TextView) findViewById(R.id.fieldNameTextView);
        fieldAddress = (TextView) findViewById(R.id.fieldAddressTextView);
        date = (TextView) findViewById(R.id.dateTextView);
        hour = (TextView) findViewById(R.id.hourTextView);
        price = (TextView) findViewById(R.id.priceValueTextView);
        freeSlots = (TextView) findViewById(R.id.freeSlotsValueTextView);
        membersListView = (ListView) findViewById(R.id.membersListView);
        joinToEventCheckBox = (CheckBox) findViewById(R.id.joinToEventCheckBox);
        yourApplicationStatusTextView = (TextView) findViewById(R.id.yourApplicationStatusTextView);



        joinToEventCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && event.userStatus.equals("-2")){
                    JoinToGame joinToGame = new JoinToGame(event);
                    joinToGame.execute(event.id);
                    joinToEventCheckBox.setFocusable(false);

                }
            }
        });

        if (event != null){
            setDetail(event);
            if (event.userStatus.equals("-2") && event.free_slots.equals("0")){
                joinToEventCheckBox.setVisibility(View.GONE);
                yourApplicationStatusTextView.setVisibility(View.GONE);

            }else{
                joinToEventCheckBox.setVisibility(View.VISIBLE);
                yourApplicationStatusTextView.setVisibility(View.VISIBLE);
            }
        }

        if (adapter != null){
            membersListView.setAdapter(adapter);
        }






    }

    public void setDetail(Event event /*String fieldNameString, String fieldAddressString, String dateString,
                          String hourString, String priceString, String freeSlotsString*/){

        this.event = event;
        fieldName.setText(event.field);
        fieldAddress.setText(event.fieldAddress);
        date.setText(event.date);
        hour.setText(event.hour);
        price.setText(event.price + " zł");
        freeSlots.setText(event.free_slots);
        //yourApplicationStatusTextView.setText(event.userStatus);
        switch (event.userStatus){
            case "-2" :
                yourApplicationStatusTextView.setText(context.getString(R.string.joinToGame));
                joinToEventCheckBox.setChecked(false);
                joinToEventCheckBox.setFocusable(true);
                break;
            case "-1" :
                yourApplicationStatusTextView.setText(context.getString(R.string.yourApplicationRefused));
                joinToEventCheckBox.setVisibility(View.INVISIBLE);
                break;
            case "0" :
                yourApplicationStatusTextView.setText(context.getString(R.string.waitForAccept));
                joinToEventCheckBox.setChecked(true);
                joinToEventCheckBox.setFocusable(false);
                break;
            case "1" :
                yourApplicationStatusTextView.setText(context.getString(R.string.yourApplicationConfirmed));
                joinToEventCheckBox.setChecked(true);
                joinToEventCheckBox.setFocusable(false);
                break;
            case "2" :
                yourApplicationStatusTextView.setVisibility(View.GONE);
                joinToEventCheckBox.setVisibility(View.GONE);
                break;
        }
    }



}

package limiszewska.projecthavana.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import limiszewska.projecthavana.activities.R;
import rest.GetAllEvents;
import rest.GetEventDetails;

public class EventShowDetails extends Activity {

    TextView fieldName, fieldAddress;
    TextView date, hour;
    TextView price, freeSlots;
    ListView membersListView;
    GetEventDetails getEventDetails;
    public static EventShowDetails instance;

    public static EventShowDetails getInstance(){
        if (instance == null){
            //return new EventShowDetails();
        }else{
            return instance;
        }return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_show_details);

        instance = this;

        fieldName = (TextView) findViewById(R.id.fieldNameTextView);
        fieldAddress = (TextView) findViewById(R.id.fieldAddressTextView);
        date = (TextView) findViewById(R.id.dateTextView);
        hour = (TextView) findViewById(R.id.hourTextView);
        price = (TextView) findViewById(R.id.priceValueTextView);
        freeSlots = (TextView) findViewById(R.id.freeSlotsValueTextView);
        membersListView = (ListView) findViewById(R.id.membersListView);



    }

    public void setDetail(String fieldNameString, String fieldAddressString, String dateString,
                          String hourString, String priceString, String freeSlotsString){

        fieldName.setText(fieldNameString);
        fieldAddress.setText(fieldAddressString);
        date.setText(dateString);
        hour.setText(hourString);
        //price.setText(priceString);
        freeSlots.setText(freeSlotsString);
    }



}

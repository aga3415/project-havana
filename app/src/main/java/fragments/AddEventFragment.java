package fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import limiszewska.projecthavana.activities.MainActivity;
import limiszewska.projecthavana.activities.R;
import rest.Find;

/**
 * Created by Agnieszka on 2015-11-16.
 */
public class AddEventFragment extends Fragment {

    public static final String ARG_PAGE = "Fragment Sign In";
    private static AddEventFragment instance = null;
    public static ProgressBar startProgressBar;
    EditText city, field, data1,data2, time1, time2, price, freeSlots;
    View view;
    Button findEvent;
    Button addEvent;
    DatePickerDialog datePickerDialog1, datePickerDialog2;
    TimePickerDialog timePickerDialog1, timePickerDialog2;
    int year, month, day;
    private SimpleDateFormat dateFormatter;

    private int mPage;

    public static AddEventFragment getInstance() {
        if (instance != null){
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, 0);
            AddEventFragment fragment = new AddEventFragment();

            return fragment;
        }else{
            return instance;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = 0;
        instance = this;

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Calendar newCalendar = Calendar.getInstance();
        int hour = newCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = newCalendar.get(Calendar.MINUTE);

        datePickerDialog1 = new DatePickerDialog(MainActivity.instance, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                data1.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog2 = new DatePickerDialog(MainActivity.instance, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                data2.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        timePickerDialog1 = new TimePickerDialog(MainActivity.instance, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time1.setText((hourOfDay<10? "0": "") + hourOfDay + ":" + (minute<10? "0" : "") + minute );
            }
        }, hour, minute, true);

        timePickerDialog2 = new TimePickerDialog(MainActivity.instance, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time2.setText((hourOfDay<10? "0": "") + hourOfDay + ":" + (minute<10? "0" : "") + minute );
            }
        }, hour, minute, true);
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        instance = this;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = inflater.inflate(R.layout.fragment_add_event_portrait, container, false);
        }else {
            view = inflater.inflate(R.layout.fragment_add_event_landscape, container, false);
        }

        city = (EditText) view.findViewById(R.id.cityEditText);
        field = (EditText) view.findViewById(R.id.fieldEditText);
        data1 = (EditText) view.findViewById(R.id.dateTextView);
        data2 = (EditText) view.findViewById(R.id.date2TextView);
        time1 = (EditText) view.findViewById(R.id.hourTextView);
        time2 = (EditText) view.findViewById(R.id.hourTextView2);
        price = (EditText) view.findViewById(R.id.priceTextView);
        freeSlots = (EditText) view.findViewById(R.id.freeSlotsTextView);

        data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog1.show();
            }
        });

        data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog2.show();
            }
        });

        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog1.show();
            }
        });


        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog2.show();
            }
        });

        findEvent = (Button) view.findViewById(R.id.findEventButton);
        addEvent = (Button) view.findViewById(R.id.addEventButton);

        findEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityParam = city.getText().toString();
                String fieldParam = field.getText().toString();
                String dateFrom = data1.getText().toString();
                String dateTo = data2.getText().toString();
                String hourFrom = time1.getText().toString();
                String hourTo = time2.getText().toString();
                String priceParams = price.getText().toString();
                if (priceParams.length() >= 1 ){
                    int price = Integer.parseInt(priceParams) + 1;
                    priceParams = Integer.toString(price);
                }

                if (hourFrom.equals("")) hourFrom = "00:00";
                if (hourTo.equals("")) hourTo = "00:00";

                String fullDateFrom = "", fullDateTo = "";

                if (dateFrom.length()>0){
                    fullDateFrom = dateFrom.substring(6,10) + dateFrom.substring(2,5) + "-" + dateFrom.substring(0,2);
                    fullDateFrom += "T" + hourFrom + ":00.000Z";
                }
                if (dateTo.length()>0){
                    fullDateTo = dateTo.substring(6,10) + dateTo.substring(2,5) + "-" + dateTo.substring(0,2);
                    fullDateTo += "T" + hourTo + ":00.000Z";
                }

                String freeSlotsParams = freeSlots.getText().toString();

                if (freeSlotsParams.length() > 1){
                    int freeSlots = Integer.parseInt(freeSlotsParams) + 1;
                    freeSlotsParams = Integer.toString(freeSlots);
                }

                Find find = new Find();

                MainActivity.instance.changePage(2);
                find.execute(cityParam, fieldParam, fullDateFrom, fullDateTo, priceParams, freeSlotsParams);

            }
        });

        int width = findEvent.getLayoutParams().width;
        int height = findEvent.getLayoutParams().height;

        //addEvent.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        addEvent.setMinimumWidth(width);
        addEvent.requestLayout();



        return view;
    }

}

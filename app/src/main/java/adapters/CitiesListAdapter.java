package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import dataModel.City;
import limiszewska.projecthavana.activities.R;

/**
 * Created by Agnieszka on 2015-12-08.
 */
public class CitiesListAdapter extends ArrayAdapter<City> {

    private Activity context;
    private ArrayList<City> citiesList;
    private static int selectPosition = -1;

    public CitiesListAdapter(Activity context, ArrayList<City> citiesList) {
        super(context, R.layout.list_item_city, citiesList);
        this.context = context;
        this.citiesList = citiesList;

    }

    static class ViewHolder {
        TextView cityName;
        CheckBox chooseCity;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView =  layoutInflater.inflate(R.layout.list_item_city, null, true);

            viewHolder = new ViewHolder();
            viewHolder.cityName = (TextView) rowView.findViewById(R.id.CityTextView);
            viewHolder.chooseCity = (CheckBox) rowView.findViewById(R.id.cityCheckBox);
            rowView.setTag(viewHolder);

            viewHolder.chooseCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.chooseCity.isChecked()) {
                        selectPosition = position;
                        viewHolder.chooseCity.setChecked(true);
                        //viewHolder.cityName.setText("WYBRANE");
                    } else {
                        selectPosition = -1;
                    }
                    notifyDataSetChanged();
                }
            });

            rowView.setClickable(false);
            if (position==selectPosition){
                viewHolder.chooseCity.setChecked(true);
            }else{
                viewHolder.chooseCity.setChecked(false);
            }


        }else{
            viewHolder = (ViewHolder) rowView.getTag();
            if (position==selectPosition){
                viewHolder.chooseCity.setChecked(true);
            }else{
                viewHolder.chooseCity.setChecked(false);
            }
        }

        City currentCity = citiesList.get(position);
        viewHolder.cityName.setText(currentCity.getName());


        return rowView;
    }

    public City getChoosenCity(){
        if (selectPosition != -1){
            return citiesList.get(selectPosition);
        }else{
            return null;
        }

    }
}
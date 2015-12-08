package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import dataModel.City;
import dataModel.Field;
import limiszewska.projecthavana.activities.R;

/**
 * Created by Agnieszka on 2015-12-08.
 */
public class FieldsListAdapter extends ArrayAdapter {

    private Activity context;
    private ArrayList<Field> fieldsList;
    private static int selectPosition = -1;

    public FieldsListAdapter(Activity context, ArrayList<Field> fieldsList) {
        super(context, R.layout.list_item_field, fieldsList);
        this.context = context;
        this.fieldsList = fieldsList;

    }

    static class ViewHolder {
        TextView fieldName;
        TextView cityName;
        CheckBox chooseField;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView =  layoutInflater.inflate(R.layout.list_item_field, null, true);

            viewHolder = new ViewHolder();
            viewHolder.cityName = (TextView) rowView.findViewById(R.id.fieldCityTextView);
            viewHolder.chooseField = (CheckBox) rowView.findViewById(R.id.fieldCheckBox);
            viewHolder.fieldName = (TextView) rowView.findViewById(R.id.fieldNameTextView);
            rowView.setTag(viewHolder);

            viewHolder.chooseField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.chooseField.isChecked()) {
                        selectPosition = position;
                        viewHolder.chooseField.setChecked(true);
                        //viewHolder.cityName.setText("WYBRANE");
                    } else {
                        selectPosition = -1;
                    }
                    notifyDataSetChanged();
                }
            });

            rowView.setClickable(false);
            if (position==selectPosition){
                viewHolder.chooseField.setChecked(true);
            }else{
                viewHolder.chooseField.setChecked(false);
            }



        }else{
            viewHolder = (ViewHolder) rowView.getTag();
            if (position==selectPosition){
                viewHolder.chooseField.setChecked(true);
            }else{
                viewHolder.chooseField.setChecked(false);
            }
        }

        Field currentField = fieldsList.get(position);
        viewHolder.cityName.setText(currentField.getCity().getName());
        viewHolder.fieldName.setText(currentField.getName());


        return rowView;
    }

    public Field getChoosenCity(){
        if (selectPosition != -1){
            return fieldsList.get(selectPosition);
        }else{
            return null;
        }

    }
}

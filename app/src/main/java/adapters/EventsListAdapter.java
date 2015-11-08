package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dataModel.Event;
import limiszewska.projecthavana.activities.R;

/**
 * Created by Agnieszka on 2015-11-08.
 */
public class EventsListAdapter extends ArrayAdapter<Event> {
    private Activity context;
    private ArrayList<Event> eventsList;

    public EventsListAdapter(Activity context, ArrayList<Event> eventsList){
        super(context, R.layout.list_item_event, eventsList);
        this.context = context;
        this.eventsList = eventsList;
    }

    static class ViewHolder {
        TextView cityAndFieldName;
        TextView freeSlotsValue;
        TextView priceValue;
        TextView date;
        TextView hour;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView =  layoutInflater.inflate(R.layout.list_item_event, null, true);

            viewHolder = new ViewHolder();
            viewHolder.cityAndFieldName = (TextView) rowView.findViewById(R.id.cityAndFieldTextView);
            viewHolder.freeSlotsValue = (TextView) rowView.findViewById(R.id.freeSlotsValueTextView);
            viewHolder.priceValue = (TextView) rowView.findViewById(R.id.priceValueTextView);
            viewHolder.date = (TextView) rowView.findViewById(R.id.dateTextView);
            viewHolder.hour = (TextView) rowView.findViewById(R.id.hourTextView);
            rowView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) rowView.getTag();
        }

        Event currentEvent = eventsList.get(position);
        viewHolder.cityAndFieldName.setText(currentEvent.city + ", " + currentEvent.field );
        viewHolder.freeSlotsValue.setText(String.valueOf(currentEvent.free_slots));
        viewHolder.priceValue.setText(String.valueOf(currentEvent.price));
        viewHolder.date.setText(currentEvent.date);
        viewHolder.hour.setText(currentEvent.hour)      ;

        return rowView;
    }


}

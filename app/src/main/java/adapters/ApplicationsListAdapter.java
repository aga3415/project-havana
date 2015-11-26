package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import dataModel.Application;
import dataModel.Event;
import limiszewska.projecthavana.activities.MainActivity;
import limiszewska.projecthavana.activities.R;
import rest.SettingConnections;

/**
 * Created by Agnieszka on 2015-11-11.
 */
public class ApplicationsListAdapter extends ArrayAdapter<Application>{

    private Activity context;
    private ArrayList<Application> applicationsList;

    public ApplicationsListAdapter(Activity context, ArrayList<Application> applicationsList){
        super(context, R.layout.list_item_application, applicationsList);
        this.context = context;
        Collections.sort(applicationsList, Application.Comparators.ACCEPTANCE);
        this.applicationsList = applicationsList;
    }

    static class ViewHolder {
        TextView userNameTextView;
        TextView applicationStatusTextView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView =  layoutInflater.inflate(R.layout.list_item_application, null, true);

            viewHolder = new ViewHolder();
            viewHolder.userNameTextView = (TextView) rowView.findViewById(R.id.userNameTextView);
            viewHolder.applicationStatusTextView = (TextView) rowView.findViewById(R.id.applicationStatusTextView);
            rowView.setTag(viewHolder);

            rowView.setClickable(false);
            /*rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.goToShowUserDetailsActivity(SettingConnections.context);
                }
            });*/
        }else{
            viewHolder = (ViewHolder) rowView.getTag();
        }

        Application currentApplication = applicationsList.get(position);
        viewHolder.userNameTextView.setText(currentApplication.login);
        String acceptance = currentApplication.acceptance;
        switch (acceptance){
            case "1" :
                viewHolder.applicationStatusTextView.setText("Uczestnik");
                break;
            case "0" :
                viewHolder.applicationStatusTextView.setText("Ochotnik");
                break;
            case "-1":
                viewHolder.applicationStatusTextView.setText("Odrzucony");
                break;
            case "2":
                viewHolder.applicationStatusTextView.setText("Organizator");
        }
            ;

        return rowView;
    }

    public String getApplicationId(int position){
        return applicationsList.get(position).id;
    }

    public String getUserId(int position){
        return applicationsList.get(position).idUser;
    }


}

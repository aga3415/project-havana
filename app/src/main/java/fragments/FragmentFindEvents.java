package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import adapters.EventsListAdapter;
import limiszewska.projecthavana.activities.R;
import rest.GetAllEvents;

/**
 * Created by Agnieszka on 2015-11-24.
 */
public class FragmentFindEvents  extends Fragment{
    public static final String ARG_PAGE = "Fragment Find Events";
    public ListView eventsListView;
    public EventsListAdapter adapter;
    public TextView errorTextView;
    public static FragmentFindEvents instance;

    public static int mPage;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;


        //mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        eventsListView = (ListView) view.findViewById(R.id.eventsListView);
        errorTextView = (TextView) view.findViewById(R.id.errorTextView);

        return view;
    }
}

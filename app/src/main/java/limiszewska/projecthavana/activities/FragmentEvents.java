package limiszewska.projecthavana.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import adapters.EventsListAdapter;
import rest.GetAllEvents;
import rest.Login;

/**
 * Created by Agnieszka on 2015-10-19.
 */
public class FragmentEvents extends Fragment {

    public static final String ARG_PAGE = "Fragment Events";
    public ListView eventsListView;
    public EventsListAdapter adapter;

    private int mPage;

    public static FragmentEvents newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentEvents fragment = new FragmentEvents();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_landscape, container, false);
        eventsListView = (ListView) view.findViewById(R.id.eventsListView);
        GetAllEvents getAllEvents = new GetAllEvents(this);
        getAllEvents.execute();



        return view;
    }
}

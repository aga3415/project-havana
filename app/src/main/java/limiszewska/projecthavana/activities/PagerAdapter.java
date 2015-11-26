package limiszewska.projecthavana.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

import fragments.AddEventFragment;
import fragments.FragmentEvents;
import fragments.FragmentFindEvents;


/**
 * Created by Agnieszka on 2015-10-19.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    Context context;
    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int i) {

        //return new FragmentEvents().newInstance(i+1);

        switch (i){

            case 0:
               return new FragmentEvents();

            case 1:
                return new AddEventFragment();

            case 2:
                return new FragmentFindEvents();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();

        switch (position) {

            case 0:

                return context.getResources().getString(R.string.first_tab);
            case 1:

                return context.getResources().getString(R.string.second_tab);
            case 2:

                return context.getResources().getString(R.string.third_tab);
        }
        return null;
    }
}

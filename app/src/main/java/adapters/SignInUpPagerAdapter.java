package adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

import fragments.SignInFragment;
import fragments.SignUpFragment;
import limiszewska.projecthavana.activities.R;

/**
 * Created by Agnieszka on 2015-11-04.
 */
public class SignInUpPagerAdapter extends FragmentPagerAdapter {

    Context context;
    public SignInUpPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
               return new SignInFragment();

            case 1:
                return new SignUpFragment();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();

        switch (position) {

            case 0:

                return context.getResources().getString(R.string.sign_in_tab);
            case 1:

                return context.getResources().getString(R.string.sign_up_tab);

        }
        return null;
    }
}

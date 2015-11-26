package limiszewska.projecthavana.activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.astuetz.PagerSlidingTabStrip;
import adapters.SignInUpPagerAdapter;
import fragments.SignInFragment;
import rest.SettingConnections;
import storageData.SignInPreferences;

public class SignInUp extends FragmentActivity {

    ViewPager viewPager;
    ActionBar actionBar;
    PagerAdapter pagerAdapter;
    public static SignInUp instance;


    public static SignInUp getSignInUpActivity(){
        if (instance == null){
            return new SignInUp();
        }
        return instance;
    }

    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        SettingConnections.context = getApplicationContext();
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SignInUpPagerAdapter(getSupportFragmentManager(), getApplicationContext()));
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setViewPager(viewPager);
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

    }

    public static void goToMainActivity(Context context){
        Intent mainActivity = new Intent(instance, MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mainActivity);

    }



}

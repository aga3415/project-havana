package limiszewska.projecthavana.activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;


import com.astuetz.PagerSlidingTabStrip;



public class MainActivity extends FragmentActivity {

    ViewPager viewPager;
    ActionBar actionBar;
    PagerAdapter pagerAdapter;
    public static MainActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getApplicationContext());
        viewPager.setAdapter(pagerAdapter);


        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setViewPager(viewPager);



        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                pagerAdapter.notifyDataSetChanged();
            }


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

    }

    public static void goToShowEventDetailsActivity(Context context){
        Intent eventDetailsActivity = new Intent(instance, EventShowDetails.class);
        eventDetailsActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(eventDetailsActivity);

    }
}

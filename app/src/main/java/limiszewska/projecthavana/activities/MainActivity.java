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

    static ViewPager viewPager;
    static PagerAdapter pagerAdapter;
    public static MainActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        viewPager = (ViewPager) findViewById(R.id.viewpager);
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

    public static void goToShowUserDetailsActivity(Context context){
        Intent userDetailsActiivity = new Intent(instance, UserShowDetails.class);
        userDetailsActiivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(userDetailsActiivity);
    }

    public void changePage(int page){

        //if (viewPager == null) {
        //    System.out.print("ViewPager jest nullem");
        //} else{
           viewPager.setCurrentItem(page);
            pagerAdapter.notifyDataSetChanged();
        //}

    }
}

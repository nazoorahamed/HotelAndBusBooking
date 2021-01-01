package diluxshan.com.busandhotelbooking.AddHotelFragment;

import android.content.Intent;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import diluxshan.com.busandhotelbooking.AddBusFragment.AddBusTab;
import diluxshan.com.busandhotelbooking.DatabaseHelper;
import diluxshan.com.busandhotelbooking.R;
import diluxshan.com.busandhotelbooking.ViewPagerAdapter;

public class AddHotelTab extends AppCompatActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    ViewPagerAdapter adapter;

    //Fragments

    AddHotelFragment addHotelFragment;
    EditDeleteHotelFragment editDeleteHotelFragment;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel_tab);

        viewPager = (ViewPager) findViewById(R.id.ad_viewpager);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(viewPager);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.ad_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ad_action_bus:
                Intent intent3 = new Intent(getApplicationContext(),AddBusTab.class);
                finish();
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);

                return true;
            case R.id.ad_action_hotels:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager)
    {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        addHotelFragment =new AddHotelFragment();
        editDeleteHotelFragment =new EditDeleteHotelFragment();
        adapter.addFragment(addHotelFragment,"ADD HOTEL");
        adapter.addFragment(editDeleteHotelFragment,"EDIT & DELETE HOTEL");
        viewPager.setAdapter(adapter);
    }
}

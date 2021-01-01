package diluxshan.com.busandhotelbooking.UserviewFragment;

import android.content.Intent;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import diluxshan.com.busandhotelbooking.BookingDetailsFragment.UserBookingsTab;
import diluxshan.com.busandhotelbooking.DatabaseHelper;
import diluxshan.com.busandhotelbooking.LoginActivity;
import diluxshan.com.busandhotelbooking.R;
import diluxshan.com.busandhotelbooking.ViewPagerAdapter;

public class UserDashboardTab extends AppCompatActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    ViewPagerAdapter adapter;

    BusViewFragment busViewFragment;
    HotelViewFragment hotelViewFragment;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard_tab);

        viewPager = (ViewPager) findViewById(R.id.ad_viewpager);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(viewPager);
        db = new DatabaseHelper(this);

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
        getMenuInflater().inflate(R.menu.logout_fragment, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                return true;
            case R.id.action_my_Bookings:
                Intent intent = new Intent(getApplicationContext(),UserBookingsTab.class);
                finish();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                return true;
            case R.id.action_logout:
                db.deleteLoggedUser();
                Intent intent2 = new Intent(getApplicationContext(),LoginActivity.class);
                finish();
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setupViewPager(ViewPager viewPager)
    {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        busViewFragment =new BusViewFragment();
        hotelViewFragment =new HotelViewFragment();
        adapter.addFragment(hotelViewFragment,"HOTELS");
        adapter.addFragment(busViewFragment,"BUS");
        viewPager.setAdapter(adapter);
    }
}

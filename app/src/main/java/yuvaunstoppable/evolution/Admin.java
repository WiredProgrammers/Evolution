package yuvaunstoppable.evolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import yuvaunstoppable.evolution.db.DBHelper;
import yuvaunstoppable.evolution.fragments.AdminSignUp;
import yuvaunstoppable.evolution.fragments.AdminView;
import yuvaunstoppable.evolution.fragments.AdminView2;

/**
 * Created by Yash on 29-May-15.
 */
public class Admin extends AppCompatActivity {

    Intent i;
    String user;
    SlidingTabLayout tabs;
    ViewPager viewPager = null;
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminwelcome);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        i = getIntent();
        user = i.getStringExtra("user");
        getSupportActionBar().setTitle("Hey, "+user);

        viewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new ViewPagerAdapter(fragmentManager));

        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setBackgroundColor(getResources().getColor(R.color.primary));
        tabs.setSelectedIndicatorColors(getResources().getColor(R.color.accent));
        tabs.setViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            DBHelper dbHelper = DBHelper.getInstance(this);
            dbHelper.logout();
            startActivity(new Intent(Admin.this,Main.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter implements AdminView2.selectionDoneListener {

        private final FragmentManager mFragmentManager;
        String[] tabs;
        private Fragment fragmentA;
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
            tabs=getResources().getStringArray(R.array.adminTabs);
        }

        @Override
        public void onDone(int scl_id, String date) {


        }

        @Override
        public Fragment getItem(int position) {
            if(position == 1){
                return new AdminSignUp();
            }
            if(position == 0){
                return new AdminView();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}


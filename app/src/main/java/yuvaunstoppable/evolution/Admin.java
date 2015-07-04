package yuvaunstoppable.evolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import yuvaunstoppable.evolution.db.DBHelper;
import yuvaunstoppable.evolution.fragments.AdminSignUp;
import yuvaunstoppable.evolution.fragments.AdminView;

/**
 * Created by Yash on 29-May-15.
 */
public class Admin extends AppCompatActivity{

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
//            ParseUser.logOut();
            DBHelper dbHelper = DBHelper.getInstance(this);
            dbHelper.logout();
            startActivity(new Intent(Admin.this,Main.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        String[] tabs;
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs=getResources().getStringArray(R.array.adminTabs);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if(position == 1){
                fragment = new AdminSignUp();
            }
            if(position == 0){
                fragment = new AdminView();
            }

            return fragment;
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


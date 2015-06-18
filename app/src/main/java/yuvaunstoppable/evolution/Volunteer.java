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

import com.parse.ParseUser;

import yuvaunstoppable.evolution.fragments.VolunteerCampusRenovation;
import yuvaunstoppable.evolution.fragments.VolunteerOthers;
import yuvaunstoppable.evolution.fragments.VolunteerSanitation;
import yuvaunstoppable.evolution.fragments.VolunteerSweeper;
import yuvaunstoppable.evolution.fragments.VolunteerWater;

/**
 * Created by amit on 10-Jun-15.
 */
public class Volunteer extends AppCompatActivity{
    String user;
    SlidingTabLayout tabs;
    ViewPager viewPager = null;
    Toolbar toolbar;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteerwelcome);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        i = getIntent();
        user = i.getStringExtra("user");
        getSupportActionBar().setTitle("Hey, "+user);

        viewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new volunteerViewPagerAdapter(fragmentManager));

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
            ParseUser.logOut();
            startActivity(new Intent(Volunteer.this,Main.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public class volunteerViewPagerAdapter extends FragmentStatePagerAdapter{
        String tabs[];
        public volunteerViewPagerAdapter(FragmentManager fm) {
            super(fm);

        tabs = getResources().getStringArray(R.array.volunteerTabs);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return tabs[position];
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if(position == 0){
                fragment = new VolunteerSanitation();
            }
            if(position == 1){
                fragment = new VolunteerWater();
            }
            if(position == 2){
                fragment = new VolunteerCampusRenovation();
            }
            if(position == 3){
                fragment = new VolunteerSweeper();
            }
            if(position == 4){
                fragment = new VolunteerOthers();
            }

            return fragment;
        }

        @Override

        public int getCount() {

            return 5;
        }


    }
}

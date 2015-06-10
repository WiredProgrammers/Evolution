package yuvaunstoppable.evolution;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import yuvaunstoppable.evolution.fragments.VolunteerCampusRenovation;
import yuvaunstoppable.evolution.fragments.VolunteerOthers;
import yuvaunstoppable.evolution.fragments.VolunteerSanitation;
import yuvaunstoppable.evolution.fragments.VolunteerSweeper;
import yuvaunstoppable.evolution.fragments.VolunteerWater;

/**
 * Created by amit on 10-Jun-15.
 */
public class Volunteer extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteerwelcome);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);


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

            return null;
        }

        @Override

        public int getCount() {

            return 5;
        }
    }
}

package yuvaunstoppable.evolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

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
    Button submit;
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

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    class Submit extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = ProgressDialog.show(Volunteer.this, "Login", "Loggin In...",
                    true);
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
        super.onPostExecute(result);
            dialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(
                    "//lenovo-pc/sendcontent.php");
            try {
                ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("user", user));
                list.add(new BasicNameValuePair("pass", user));
                httpPost.setEntity(new UrlEncodedFormEntity(list));

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpClient.execute(httpPost,
                        responseHandler);

                if (response.startsWith("User Found")) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Volunteer.this, "Login Successful",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent i = new Intent(Volunteer.this, List.class);
                    i.putExtra("user", user);
                    startActivity(i);
                } else {
                    Toast.makeText(Volunteer.this, "No user found",
                            Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Log.e("Error", "Error");
                e.printStackTrace();
            }
            return null;
        }

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

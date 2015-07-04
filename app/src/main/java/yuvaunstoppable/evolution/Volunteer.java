package yuvaunstoppable.evolution;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yuvaunstoppable.evolution.db.DBHelper;
import yuvaunstoppable.evolution.fragments.VolunteerCampusRenovation;
import yuvaunstoppable.evolution.fragments.VolunteerOthers;
import yuvaunstoppable.evolution.fragments.VolunteerSanitation;
import yuvaunstoppable.evolution.fragments.VolunteerSweeper;
import yuvaunstoppable.evolution.fragments.VolunteerWater;

/**
 * Created by amit on 10-Jun-15.
 */
public class Volunteer extends AppCompatActivity {
    String user, scl_id, scl_name;
    SlidingTabLayout tabs;
    ViewPager viewPager = null;
    Toolbar toolbar;
    Intent i;
    Button submit;
    ArrayList<NameValuePair> values = new ArrayList<NameValuePair>();
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private static final long ONE_MIN = 1000 * 60;
    private static final long TWO_MIN = ONE_MIN * 2;
    private static final long FIVE_MIN = ONE_MIN * 5;
    private static final long MEASURE_TIME = 1000 * 30;
    private static final long POLLING_FREQ = 1000 * 10;
    private static final float MIN_ACCURACY = 25.0f;
    private static final float MIN_LAST_READ_ACCURACY = 500.0f;
    private static final float MIN_DISTANCE = 10.0f;

    private Location mBestReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteerwelcome);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Please Enable Location Services");
            dialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    finish();
                }
            });
            dialog.show();
        }
        i = getIntent();
        user = i.getStringExtra("user");
        scl_id = i.getStringExtra("scl_id");
        Log.d("ID",scl_id);
        scl_name = i.getStringExtra("scl_name");
        getSupportActionBar().setTitle("Hey, " + user);

        viewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new volunteerViewPagerAdapter(fragmentManager));

        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setBackgroundColor(getResources().getColor(R.color.primary));
        tabs.setSelectedIndicatorColors(getResources().getColor(R.color.accent));
        tabs.setViewPager(viewPager);

        if (null == (mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE)))
            finish();
        mBestReading = bestLastKnownLocation(MIN_LAST_READ_ACCURACY, FIVE_MIN);

        mLocationListener = new LocationListener() {

            // Called back when location changes

            public void onLocationChanged(Location location) {

//                ensureColor();

                // Determine whether new location is better than current best
                // estimate

                if (null == mBestReading
                        || location.getAccuracy() < mBestReading.getAccuracy()) {

                    // Update best estimate
                    mBestReading = location;

                    // Update display
//                    updateDisplay(location);
                    Toast.makeText(Volunteer.this, "Latitude : " + mBestReading.getLatitude() + "\nLongitude : " + mBestReading.getLongitude(), Toast.LENGTH_LONG).show();
                    if (mBestReading.getAccuracy() < MIN_ACCURACY)
                        mLocationManager.removeUpdates(mLocationListener);

                }
            }

            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // NA
            }

            public void onProviderEnabled(String provider) {
                // NA
            }

            public void onProviderDisabled(String provider) {
                // NA
            }
        };

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                values.addAll(VolunteerCampusRenovation.getData());
                values.addAll(VolunteerOthers.getData());
                values.addAll(VolunteerSanitation.getData());
                values.addAll(VolunteerSweeper.getData());
                values.addAll(VolunteerWater.getData());
                values.add(new BasicNameValuePair("uname", user));
                values.add(new BasicNameValuePair("date", new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
                values.add(new BasicNameValuePair("lat", Double.toString(mBestReading.getLatitude())));
                values.add(new BasicNameValuePair("lon", Double.toString(mBestReading.getLongitude())));
                values.add(new BasicNameValuePair("scl_id",scl_id));
                boolean flag = false;
                for(int i=0;i<values.size();i++){
                    if(values.get(i).getValue().equalsIgnoreCase("") && !(values.get(i).getName().contains("comment"))){
                        Log.d("Empty",values.get(i).getName());
                        flag = true;
                        break;
                    }
                }

                if(values.size()<6)
                    flag = true;
                if(flag) {
                    Toast.makeText(Volunteer.this, "Please Fill Everything", Toast.LENGTH_LONG).show();
                    values.clear();
                } else
                    new Submit().execute((Void) null);

                Log.d("Values",values.toString());

            }
        });
    }


    private Location bestLastKnownLocation(float minAccuracy, long maxAge) {

        Location bestResult = null;
        float bestAccuracy = Float.MAX_VALUE;
        long bestAge = Long.MIN_VALUE;

        List<String> matchingProviders = mLocationManager.getAllProviders();

        for (String provider : matchingProviders) {

            Location location = mLocationManager.getLastKnownLocation(provider);

            if (location != null) {

                float accuracy = location.getAccuracy();
                long time = location.getTime();

                if (accuracy < bestAccuracy) {

                    bestResult = location;
                    bestAccuracy = accuracy;
                    bestAge = time;

                }
            }
        }

        // Return best reading or null
        if (bestAccuracy > minAccuracy
                || (System.currentTimeMillis() - bestAge) > maxAge) {
            return null;
        } else {
            return bestResult;
        }
    }

    class Submit extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = ProgressDialog.show(Volunteer.this, "Please Wait", "Sending Data",
                    true);
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();
            values.clear();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(getResources().getString(R.string.domain)+"sendcontent.php");
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(values));
                Log.d("sending",httpPost.getEntity().getContent().toString());
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpClient.execute(httpPost,
                        responseHandler);
                Log.d("Send",response);
                if (response.startsWith("success")) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Volunteer.this, "Successful",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Volunteer.this, "No user found",
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } catch (Exception e) {
                Log.e("Error", e.toString());
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
            DBHelper dbHelper = DBHelper.getInstance(this);
            dbHelper.logout();
            startActivity(new Intent(Volunteer.this, Main.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public class volunteerViewPagerAdapter extends FragmentStatePagerAdapter {
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
            if (position == 0) {
                fragment = new VolunteerSanitation();
            }
            if (position == 1) {
                fragment = new VolunteerWater();
            }
            if (position == 2) {
                fragment = new VolunteerCampusRenovation();
            }
            if (position == 3) {
                fragment = new VolunteerSweeper();
            }
            if (position == 4) {
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

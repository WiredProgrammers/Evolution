package yuvaunstoppable.evolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import yuvaunstoppable.evolution.db.DBHelper;

/**
 * Created by Yash on 30-Jun-15.
 */
public class VolunteerSchool extends AppCompatActivity {

    String user;
    String[] school = {"Select School"};
    List<School> list = new ArrayList<>();
    Spinner scl_id;
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_school);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new Fetch().execute((Void) null);
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        scl_id = (Spinner) findViewById(R.id.scl_id);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner3_item,school);
        scl_id.setPrompt("Select School");
        scl_id.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner3_item, this));
        scl_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Intent i = new Intent(VolunteerSchool.this, Volunteer.class);
                    i.putExtra("scl_name", list.get(position - 1).getScl_name());
                    i.putExtra("scl_id", Integer.toString(list.get(position - 1).getScl_id()));
                    Log.d("Sent id", Integer.toString(list.get(position - 1).getScl_id()));
                    i.putExtra("user", user);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void populateSpinner() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            lables.add(list.get(i).getScl_name());
        }
        Log.d("string", lables.toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner3_item, lables);
        scl_id.setPrompt("Select School");
        scl_id.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner3_item, this));
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
            startActivity(new Intent(VolunteerSchool.this, Main.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    class Fetch extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(VolunteerSchool.this);
            progressDialog.setMessage("Fetching Schools");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
            InputStream is = null;
            String response = null;
            try {

                HttpGet httpGet = new HttpGet(getResources().getString(R.string.domain) + "school.php");

                httpResponse = httpClient.execute(httpGet);


                httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                response = sb.toString();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error: " + e.toString());
            }
            try {
                JSONArray jsonObj = new JSONArray(response);
                if (jsonObj != null) {
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject json = (JSONObject) jsonObj.get(i);
                        list.add(new School(json.getString("name"), json.getInt("id")));
                    }
                }
                Log.d("response", list.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populateSpinner();
            progressDialog.dismiss();
        }
    }
}

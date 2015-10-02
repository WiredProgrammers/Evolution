package yuvaunstoppable.evolution.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import yuvaunstoppable.evolution.NothingSelectedSpinnerAdapter;
import yuvaunstoppable.evolution.R;
import yuvaunstoppable.evolution.School;
import yuvaunstoppable.evolution.Volunteer;
import yuvaunstoppable.evolution.VolunteerSchool;

/**
 * Created by amit on 02-Oct-15.
 */
public class AdminView2 extends Fragment {

    String[] school = {"Select School"}, da={"Select Date"};
    List<School> list = new ArrayList<>();
    String[] dates = null;
    Spinner scl_id,date;
    int scl;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_adminview2, container, false);

        new FetchSchool().execute((Void) null);
        scl_id = (Spinner) layout.findViewById(R.id.scl_id);
        date = (Spinner) layout.findViewById(R.id.date);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner3_item,school);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), R.layout.spinner4_item,da);
        scl_id.setPrompt("Select School");
        date.setPrompt("Select Date");
        scl_id.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner3_item, getActivity()));
        date.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner4_item, getActivity()));
        scl_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    scl = list.get(position).getScl_id();
                    new FetchDate().execute((Void) null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


        return layout;
    }

    class FetchSchool extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog =  new ProgressDialog(getActivity());
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
                        list.add(new School(json.getString("name"),json.getInt("id")));
                    }
                }
                Log.d("response",list.toString());

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

    class FetchDate extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog =  new ProgressDialog(getActivity());
            progressDialog.setMessage("Fetching Records");
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

                HttpPost httpGet = new HttpPost(getResources().getString(R.string.domain) + "fetch_date.php");
                List<BasicNameValuePair> send = new ArrayList<>();
                send.add(new BasicNameValuePair("scl_id", Integer.toString(scl)));
                httpGet.setEntity(new UrlEncodedFormEntity(send));
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
                    dates = new String[jsonObj.length()];
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject json = (JSONObject) jsonObj.get(i);
                        dates[i]=json.getString("date");
                    }
                }
                Log.d("response",dates.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populateSpinnerDate();
            progressDialog.dismiss();
        }
    }

    private void populateSpinner() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            lables.add(list.get(i).getScl_name());
        }
        Log.d("string", lables.toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner3_item, lables);
        date.setPrompt("Select School");
        date.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner3_item, getActivity()));
    }

    private void populateSpinnerDate() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < dates.length; i++) {
            lables.add(list.get(i).getScl_name());
        }
        Log.d("string", lables.toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner3_item, lables);
        date.setPrompt("Select School");
        date.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner3_item, getActivity()));
    }
}

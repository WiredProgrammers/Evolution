package yuvaunstoppable.evolution.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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

/**
 * Created by Yash on 03-Jun-15.
 */
public class AdminView extends Fragment {
    static TextView noBasinGirls, noUrinalsGirls, noTapsGirls, noMirrorGirls;
    static RatingBar starBasinGirls, starUrinalGirls, starWashroomGirls, starFlowBasinGirls, starFlowUrinalGirls, starWindowGirls, starMirrorGirls;
    static EditText starTapsGirls, noTumbGirls, noBuckGirls, commentsGirls;
    static CheckBox tumbBuckSameGirls;
    static SwitchCompat doorLatchGirls,drainCloggingGirls,stinkingGirls,roofGirls;
    static RatingBar starCleanMidDay,starCleanCampus;
    static SwitchCompat soundSystem, kitchenMiddayMeals, sportsKits, booksStationery, amenitiesCultural;
    static EditText  otherComments;
    static EditText boys,girls,corridor,campus,water,dishwash,classroom,storageroom,dustbin;
    static SwitchCompat regularTankCleanWater, purifierProperWater, regularFlowWaterArea, tapLeakageWaterArea, drainCloggWaterArea, isStinkingWaterArea, regularFlowDishWashArea, tapLeakageDishWashArea, drainCloggDishWashArea, isStinkingDishWashArea;
    static EditText fillingFrequencyWater, capacityWater, brokenTapsWaterArea, noOfDustBinsWaterArea, commentsWaterArea, commentsDishWashArea, noOfDustBinsDishWashArea;
    static TextView noOfTapsWaterArea, noOfTapsDishWashArea;
    static RatingBar cleanAroundWaterArea, cleanAroundDishWashArea;
    static Spinner how_often_clean;
    static ToggleButton statusWtPurifier, statusDishArea, StatusDwArea;
    static String[] types = {"1 month", "2 months", "3 months", "6 months", "12 months"};
    static TextView noBasinBoys, noUrinalsBoys, noTapsBoys, noMirrorBoys;
    static RatingBar starBasinBoys, starUrinalBoys, starWashroomBoys, starFlowBasinBoys, starFlowUrinalBoys, starWindowBoys, starMirrorBoys;
    static EditText starTapsBoys, noTumbBoys, noBuckBoys, commentsBoys;
    static CheckBox tumbBuckSameBoys;
    static SwitchCompat doorLatchBoys,drainCloggingBoys,stinkingBoys,roofBoys;
    static ToggleButton status_boys, status_girls;
    static CheckBox needRepair,clean;
    static EditText noBlackboard,noDustbin,comments;
    static RatingBar starBlackboard,starColor;
    static ToggleButton statusShade;
    ArrayList<NameValuePair> values = new ArrayList<NameValuePair>();
    int scl_id;
    String date;
    List<School> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_adminview, container, false);
        scl_id = savedInstanceState.getInt("scl_id");
        date = savedInstanceState.getString("date");
        new Fetch().execute((Void) null);

        final View boysView = layout.findViewById(R.id.boys_view);
        final View girlsView = layout.findViewById(R.id.girls_view);
        final View waterView = layout.findViewById(R.id.water_view);
        final View drinkingView = layout.findViewById(R.id.drinking_view);
        final View dishwashView = layout.findViewById(R.id.dishwash_view);
        final View shadeView = layout.findViewById(R.id.shade_view);
        final View otherView = layout.findViewById(R.id.other_view);
        final View sweeperView = layout.findViewById(R.id.sweeper_view);
        View card1 = layout.findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boysView.getVisibility() == View.GONE) {
                    boysView.setVisibility(View.VISIBLE);
                    girlsView.setVisibility(View.GONE);
                    waterView.setVisibility(View.GONE);
                    drinkingView.setVisibility(View.GONE);
                    dishwashView.setVisibility(View.GONE);
                    shadeView.setVisibility(View.GONE);
                    otherView.setVisibility(View.GONE);
                    sweeperView.setVisibility(View.GONE);
                } else {
                    boysView.setVisibility(View.GONE);
                }
            }
        });
        View card2 = layout.findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (girlsView.getVisibility() == View.GONE) {
                    boysView.setVisibility(View.GONE);
                    girlsView.setVisibility(View.VISIBLE);
                    waterView.setVisibility(View.GONE);
                    drinkingView.setVisibility(View.GONE);
                    dishwashView.setVisibility(View.GONE);
                    shadeView.setVisibility(View.GONE);
                    otherView.setVisibility(View.GONE);
                    sweeperView.setVisibility(View.GONE);
                } else {
                    girlsView.setVisibility(View.GONE);
                }
            }
        });
        View card3 = layout.findViewById(R.id.card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (waterView.getVisibility() == View.GONE) {
                    boysView.setVisibility(View.GONE);
                    girlsView.setVisibility(View.GONE);
                    waterView.setVisibility(View.VISIBLE);
                    drinkingView.setVisibility(View.GONE);
                    dishwashView.setVisibility(View.GONE);
                    shadeView.setVisibility(View.GONE);
                    otherView.setVisibility(View.GONE);
                    sweeperView.setVisibility(View.GONE);
                } else {
                    waterView.setVisibility(View.GONE);
                }
            }
        });
        View card4 = layout.findViewById(R.id.card4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drinkingView.getVisibility() == View.GONE) {
                    boysView.setVisibility(View.GONE);
                    girlsView.setVisibility(View.GONE);
                    waterView.setVisibility(View.GONE);
                    drinkingView.setVisibility(View.VISIBLE);
                    dishwashView.setVisibility(View.GONE);
                    shadeView.setVisibility(View.GONE);
                    otherView.setVisibility(View.GONE);
                    sweeperView.setVisibility(View.GONE);
                } else {
                    drinkingView.setVisibility(View.GONE);
                }
            }
        });
        View card5 = layout.findViewById(R.id.card5);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dishwashView.getVisibility() == View.GONE) {
                    boysView.setVisibility(View.GONE);
                    girlsView.setVisibility(View.GONE);
                    waterView.setVisibility(View.GONE);
                    drinkingView.setVisibility(View.GONE);
                    dishwashView.setVisibility(View.VISIBLE);
                    shadeView.setVisibility(View.GONE);
                    otherView.setVisibility(View.GONE);
                    sweeperView.setVisibility(View.GONE);
                } else {
                    dishwashView.setVisibility(View.GONE);
                }
            }
        });
        View card6 = layout.findViewById(R.id.card6);
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shadeView.getVisibility() == View.GONE) {
                    boysView.setVisibility(View.GONE);
                    girlsView.setVisibility(View.GONE);
                    waterView.setVisibility(View.GONE);
                    drinkingView.setVisibility(View.GONE);
                    dishwashView.setVisibility(View.GONE);
                    shadeView.setVisibility(View.VISIBLE);
                    otherView.setVisibility(View.GONE);
                    sweeperView.setVisibility(View.GONE);
                } else {
                    shadeView.setVisibility(View.GONE);
                }
            }
        });
        View card7 = layout.findViewById(R.id.card7);
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otherView.getVisibility() == View.GONE) {
                    boysView.setVisibility(View.GONE);
                    girlsView.setVisibility(View.GONE);
                    waterView.setVisibility(View.GONE);
                    drinkingView.setVisibility(View.GONE);
                    dishwashView.setVisibility(View.GONE);
                    shadeView.setVisibility(View.GONE);
                    otherView.setVisibility(View.VISIBLE);
                    sweeperView.setVisibility(View.GONE);
                } else {
                    otherView.setVisibility(View.GONE);
                }
            }
        });
        View card8 = layout.findViewById(R.id.card8);
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sweeperView.getVisibility() == View.GONE) {
                    boysView.setVisibility(View.GONE);
                    girlsView.setVisibility(View.GONE);
                    waterView.setVisibility(View.GONE);
                    drinkingView.setVisibility(View.GONE);
                    dishwashView.setVisibility(View.GONE);
                    shadeView.setVisibility(View.GONE);
                    otherView.setVisibility(View.GONE);
                    sweeperView.setVisibility(View.VISIBLE);
                } else {
                    sweeperView.setVisibility(View.GONE);
                }
            }
        });

        noBasinGirls = (TextView) layout.findViewById(R.id.no_basin_girls);
        noUrinalsGirls = (TextView) layout.findViewById(R.id.no_urinals_girls);
        noTapsGirls = (TextView) layout.findViewById(R.id.no_taps_girls);
        noMirrorGirls = (TextView) layout.findViewById(R.id.no_mirror_girls);

        starBasinGirls = (RatingBar) layout.findViewById(R.id.star_basin_girls);
        starUrinalGirls = (RatingBar) layout.findViewById(R.id.star_urinal_girls);
        starWashroomGirls = (RatingBar) layout.findViewById(R.id.star_washroom_girls);
        starFlowBasinGirls = (RatingBar) layout.findViewById(R.id.star_flow_basin_girls);
        starFlowUrinalGirls = (RatingBar) layout.findViewById(R.id.star_flow_urinal_girls);
        starWindowGirls = (RatingBar) layout.findViewById(R.id.star_window_girls);
        starMirrorGirls = (RatingBar) layout.findViewById(R.id.star_mirror_girls);
        starTapsGirls = (EditText) layout.findViewById(R.id.star_taps_girls);
        noTumbGirls = (EditText) layout.findViewById(R.id.present_tumbler_girls);
        noBuckGirls = (EditText) layout.findViewById(R.id.present_bucket_girls);
        commentsGirls = (EditText) layout.findViewById(R.id.comments_girls);
        tumbBuckSameGirls = (CheckBox) layout.findViewById(R.id.same_buck_tumb_girls);
        doorLatchGirls = (SwitchCompat) layout.findViewById(R.id.latch_doors_girls);
        roofGirls = (SwitchCompat) layout.findViewById(R.id.roof_girls);
        stinkingGirls = (SwitchCompat) layout.findViewById(R.id.stinking_girls);
        drainCloggingGirls = (SwitchCompat) layout.findViewById(R.id.drainage_clogging_girls);

        noBasinBoys = (TextView) layout.findViewById(R.id.no_basin_boys);
        noUrinalsBoys = (TextView) layout.findViewById(R.id.no_urinals_boys);
        noTapsBoys = (TextView) layout.findViewById(R.id.no_taps_boys);
        noMirrorBoys = (TextView) layout.findViewById(R.id.no_mirror_boys);

        starBasinBoys = (RatingBar) layout.findViewById(R.id.star_basin_boys);
        starUrinalBoys = (RatingBar) layout.findViewById(R.id.star_urinal_boys);
        starWashroomBoys = (RatingBar) layout.findViewById(R.id.star_washroom_boys);
        starFlowBasinBoys = (RatingBar) layout.findViewById(R.id.star_flow_basin_boys);
        starFlowUrinalBoys = (RatingBar) layout.findViewById(R.id.star_flow_urinal_boys);
        starWindowBoys = (RatingBar) layout.findViewById(R.id.star_window_boys);
        starMirrorBoys = (RatingBar) layout.findViewById(R.id.star_mirror_boys);
        starTapsBoys = (EditText) layout.findViewById(R.id.star_taps_boys);
        noTumbBoys = (EditText) layout.findViewById(R.id.present_tumbler_boys);
        noBuckBoys = (EditText) layout.findViewById(R.id.present_bucket_boys);
        commentsBoys = (EditText) layout.findViewById(R.id.comments_boys);
        tumbBuckSameBoys = (CheckBox) layout.findViewById(R.id.same_buck_tumb_boys);
        doorLatchBoys = (SwitchCompat) layout.findViewById(R.id.latch_doors_boys);
        roofBoys = (SwitchCompat) layout.findViewById(R.id.roof_boys);
        stinkingBoys = (SwitchCompat) layout.findViewById(R.id.stinking_boys);
        drainCloggingBoys = (SwitchCompat) layout.findViewById(R.id.drainage_clogging_boys);
        status_boys = (ToggleButton) layout.findViewById(R.id.status_boys);
        status_girls = (ToggleButton) layout.findViewById(R.id.status_girls);
        needRepair = (CheckBox) layout.findViewById(R.id.need_repair);
        clean = (CheckBox) layout.findViewById(R.id.clean);
        noBlackboard = (EditText) layout.findViewById(R.id.no_blackboard);
        noDustbin = (EditText) layout.findViewById(R.id.no_dustbin);
        comments = (EditText) layout.findViewById(R.id.comments);
        starBlackboard = (RatingBar) layout.findViewById(R.id.star_blackboard);
        starColor = (RatingBar) layout.findViewById(R.id.star_color);
        statusShade = (ToggleButton) layout.findViewById(R.id.status_shade);
        starCleanCampus = (RatingBar) layout.findViewById(R.id.star_clean_campus);
        starCleanMidDay = (RatingBar) layout.findViewById(R.id.star_clean_midday);
        soundSystem = (SwitchCompat) layout.findViewById(R.id.sound_system);
        kitchenMiddayMeals = (SwitchCompat) layout.findViewById(R.id.kitchen_midday_meals);
        sportsKits = (SwitchCompat) layout.findViewById(R.id.sports_kits);
        booksStationery = (SwitchCompat) layout.findViewById(R.id.books_stationery);
        amenitiesCultural = (SwitchCompat) layout.findViewById(R.id.amenities_cultural);
        otherComments = (EditText) layout.findViewById(R.id.other_comments);
        boys = (EditText) layout.findViewById(R.id.boys);
        girls = (EditText) layout.findViewById(R.id.girls);
        corridor = (EditText) layout.findViewById(R.id.corridor);
        campus = (EditText) layout.findViewById(R.id.campus);
        water = (EditText) layout.findViewById(R.id.water);
        dishwash = (EditText) layout.findViewById(R.id.dishwash);
        classroom = (EditText) layout.findViewById(R.id.classroom);
        storageroom = (EditText) layout.findViewById(R.id.storageroom);
        dustbin = (EditText) layout.findViewById(R.id.emptydustbin);
        regularTankCleanWater = (SwitchCompat) layout.findViewById(R.id.regularity_cleaning_tank);
        purifierProperWater = (SwitchCompat) layout.findViewById(R.id.water_pure_proper);
        regularFlowWaterArea = (SwitchCompat) layout.findViewById(R.id.regular_flow_water);
        tapLeakageWaterArea = (SwitchCompat) layout.findViewById(R.id.tap_Leakage);
        drainCloggWaterArea = (SwitchCompat) layout.findViewById(R.id.drain_clogg);
        isStinkingWaterArea = (SwitchCompat) layout.findViewById(R.id.stinking);
        regularFlowDishWashArea = (SwitchCompat) layout.findViewById(R.id.reg_flow_water);
        tapLeakageDishWashArea = (SwitchCompat) layout.findViewById(R.id.leakage_of_taps);
        drainCloggDishWashArea = (SwitchCompat) layout.findViewById(R.id.drainnage_clogging);
        isStinkingDishWashArea = (SwitchCompat) layout.findViewById(R.id.Stinking_dish_wash_area);
        cleanAroundWaterArea = (RatingBar) layout.findViewById(R.id.clean_around_water_area);
        cleanAroundDishWashArea = (RatingBar) layout.findViewById(R.id.clean_around_dish_wash_areaa);
        noOfTapsWaterArea = (TextView) layout.findViewById(R.id.no_of_taps);
        noOfTapsDishWashArea = (TextView) layout.findViewById(R.id.no_taps_dish_wash_area);
        fillingFrequencyWater = (EditText) layout.findViewById(R.id.fill_tank_freq_ans);
        capacityWater = (EditText) layout.findViewById(R.id.water_tank_capacity_ans);
        brokenTapsWaterArea = (EditText) layout.findViewById(R.id.broken_taps);
        noOfDustBinsWaterArea = (EditText) layout.findViewById(R.id.no_of_dustbins);
        commentsWaterArea = (EditText) layout.findViewById(R.id.water_comments);
        commentsDishWashArea = (EditText) layout.findViewById(R.id.dish_wash_comments);
        noOfDustBinsDishWashArea = (EditText) layout.findViewById(R.id.no_of_dustbin);
        statusWtPurifier = (ToggleButton) layout.findViewById(R.id.status_wt_purifier);
        statusDishArea = (ToggleButton) layout.findViewById(R.id.status_dish_area);
        StatusDwArea = (ToggleButton) layout.findViewById(R.id.status_dw_area);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner2_item, types);
        Log.d("Adapter", String.valueOf(adapter.isEmpty()));
        how_often_clean = (Spinner) layout.findViewById(R.id.how_often_clean);
        how_often_clean.setPrompt("Select no. of months");
        how_often_clean.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner2_item, getActivity()));



        return layout;

    }

    class Fetch extends AsyncTask<Void, Void, Void> {
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

                HttpPost httpGet = new HttpPost(getResources().getString(R.string.domain) + "fetch_data.php");
                List<BasicNameValuePair> send = new ArrayList<>();
                send.add(new BasicNameValuePair("scl_id", Integer.toString(scl_id)));
                send.add(new BasicNameValuePair("date", date));
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



                    JSONObject json1 = (JSONObject) jsonObj.get(2); //location : 2
                    values.add(new BasicNameValuePair("lat", json1.getString("lat")));
                    values.add(new BasicNameValuePair("lon", json1.getString("lon")));



                    JSONObject json2 = (JSONObject) jsonObj.get(4); //boys
                    values.add(new BasicNameValuePair("basin_boys", json2.getString("basin")));
                    values.add(new BasicNameValuePair("urinal_boys", json2.getString("urinal")));
                    values.add(new BasicNameValuePair("washroom_boys", json2.getString("washroom")));
                    values.add(new BasicNameValuePair("flow_basin_boys", json2.getString("flow_basin")));
                    values.add(new BasicNameValuePair("flow_urinal_boys", json2.getString("flow_urinal")));
                    values.add(new BasicNameValuePair("window_boys",json2.getString("window")));
                    values.add(new BasicNameValuePair("mirror_boys", json2.getString("mirror")));
                    values.add(new BasicNameValuePair("taps_boys",json2.getString("taps")));
                    values.add(new BasicNameValuePair("tumb_boys", json2.getString("tumbler")));
                    values.add(new BasicNameValuePair("buck_boys", json2.getString("bucket")));
                    values.add(new BasicNameValuePair("latch_boys", json2.getString("door_latch")));
                    values.add(new BasicNameValuePair("roof_boys", json2.getString("roof")));
                    values.add(new BasicNameValuePair("stinking_boys", json2.getString("stinking")));
                    values.add(new BasicNameValuePair("clog_boys", json2.getString("clog")));
                    values.add(new BasicNameValuePair("comments_boys", json2.getString("comments")));
                    values.add(new BasicNameValuePair("status_boys",json2.getString("status")));


                    JSONObject json3 = (JSONObject) jsonObj.get(5); //girls
                    values.add(new BasicNameValuePair("basin_girls", json3.getString("basin")));
                    values.add(new BasicNameValuePair("urinal_girls", json3.getString("urinal")));
                    values.add(new BasicNameValuePair("washroom_girls", json3.getString("washroom")));
                    values.add(new BasicNameValuePair("flow_basin_girls", json3.getString("flow_basin")));
                    values.add(new BasicNameValuePair("flow_urinal_girls", json3.getString("flow_urinal")));
                    values.add(new BasicNameValuePair("window_girls", json3.getString("window")));
                    values.add(new BasicNameValuePair("mirror_girls", json3.getString("mirror")));
                    values.add(new BasicNameValuePair("taps_girls", json3.getString("taps")));
                    values.add(new BasicNameValuePair("tumb_girls", json3.getString("tumbler")));
                    values.add(new BasicNameValuePair("buck_girls", json3.getString("bucket")));
                    values.add(new BasicNameValuePair("latch_girls", json3.getString("door_latch")));
                    values.add(new BasicNameValuePair("roof_girls", json3.getString("roof")));
                    values.add(new BasicNameValuePair("stinking_girls", json3.getString("stinking")));
                    values.add(new BasicNameValuePair("clog_girls", json3.getString("clog")));
                    values.add(new BasicNameValuePair("comments_girls", json3.getString("comments")));
                    values.add(new BasicNameValuePair("status_girls", json3.getString("status")));


                    JSONObject json4 = (JSONObject) jsonObj.get(6); //sweeper : 6
                    values.add(new BasicNameValuePair("staus_water_area", json4.getString("status")));
                    values.add(new BasicNameValuePair("boys", json4.getString("boys")));
                    values.add(new BasicNameValuePair("girls", json4.getString("girls")));
                    values.add(new BasicNameValuePair("corridor", json4.getString("corridor")));
                    values.add(new BasicNameValuePair("campus", json4.getString("campus")));
                    values.add(new BasicNameValuePair("water",json4.getString("water")));
                    values.add(new BasicNameValuePair("class", json4.getString("class")));
                    values.add(new BasicNameValuePair("storage", json4.getString("storage")));
                    values.add(new BasicNameValuePair("dustbin", json4.getString("dustbin")));

                    JSONObject json5 = (JSONObject) jsonObj.get(8); //water tank : 8
                    values.add(new BasicNameValuePair("reg_clean", json5.getString("rglr_clean")));
                    values.add(new BasicNameValuePair("purifier_proper", json5.getString("purifier_proper")));
                    values.add(new BasicNameValuePair("freq_fil", json5.getString("f_filling")));
                    values.add(new BasicNameValuePair("capacity_tank", json5.getString("capacity")));
                    values.add(new BasicNameValuePair("f_clean_month", json5.getString("f_clean_month")));
                    values.add(new BasicNameValuePair("staus_water", json5.getString("status")));


                    JSONObject json7 = (JSONObject) jsonObj.get(7); //water area : 7
                    values.add(new BasicNameValuePair("reg_flow_water",json7.getString("reg_flow")));
                    values.add(new BasicNameValuePair("tap_leakage_water",json7.getString("tap_leakage")));
                    values.add(new BasicNameValuePair("drain_clog_water",json7.getString("drain_clog")));
                    values.add(new BasicNameValuePair("stinking_water_area",json7.getString("stink")));
                    values.add(new BasicNameValuePair("broken_taps_water",json7.getString("broken_taps")));
                    values.add(new BasicNameValuePair("dustbins_water",json7.getString("dustbin")));
                    values.add(new BasicNameValuePair("comments_water",json7.getString("comments")));
                    values.add(new BasicNameValuePair("clean_water",json7.getString("clean")));

                    JSONObject json8 = (JSONObject) jsonObj.get(1); //dishwash : 1
                    values.add(new BasicNameValuePair("reg_flow_dish",json8.getString("reg_flow")));
                    values.add(new BasicNameValuePair("tap_leakage_dish",json8.getString("tap_leakage")));
                    values.add(new BasicNameValuePair("drain_clog_dish",json8.getString("drain_clog")));
                    values.add(new BasicNameValuePair("stinking_dish",json8.getString("stink")));
                    values.add(new BasicNameValuePair("dustbins_dish",json8.getString("dustbin")));
                    values.add(new BasicNameValuePair("comments_dish",json8.getString("comments")));
                    values.add(new BasicNameValuePair("clean_dish",json8.getString("clean")));


                    JSONObject json9 = (JSONObject) jsonObj.get(4); //others
                    values.add(new BasicNameValuePair("clean_midday", json9.getString("clean_midday")));
                    values.add(new BasicNameValuePair("clean_campus", json9.getString("clean_campus")));
                    values.add(new BasicNameValuePair("sound system", json9.getString("soundsys")));
                    values.add(new BasicNameValuePair("kitchen midday meals", json9.getString("kitchen")));
                    values.add(new BasicNameValuePair("sports kits", json9.getString("sports")));
                    values.add(new BasicNameValuePair("books stationery", json9.getString("stationery")));
                    values.add(new BasicNameValuePair("amenities cultural", json9.getString("cultural")));
                    values.add(new BasicNameValuePair("Comments", json9.getString("comments")));

                    JSONObject json0 = (JSONObject) jsonObj.get(0); //campus Renovation and 3 commons
                    values.add(new BasicNameValuePair("need_repair",json0.getString("need_repair")));
                    values.add(new BasicNameValuePair("clean", json0.getString("clean")));
                    values.add(new BasicNameValuePair("board", json0.getString("board")));
                    values.add(new BasicNameValuePair("dustbin", json0.getString("dustbin")));
                    values.add(new BasicNameValuePair("cond_board", json0.getString("cond_board")));
                    values.add(new BasicNameValuePair("color", json0.getString("color")));
                    values.add(new BasicNameValuePair("renovation_comments", json0.getString("comments")));
                    values.add(new BasicNameValuePair("status_shade", json0.getString("shade")));
                    values.add(new BasicNameValuePair("uname", json0.getString("uname")));
                    values.add(new BasicNameValuePair("date", json0.getString("date")));
                    values.add(new BasicNameValuePair("scl_id",json0.getString("scl_id")));

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

            progressDialog.dismiss();
        }
    }
}


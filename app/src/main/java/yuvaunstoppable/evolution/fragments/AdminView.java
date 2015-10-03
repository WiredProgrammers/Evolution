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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import java.util.HashMap;
import java.util.List;

import yuvaunstoppable.evolution.R;
import yuvaunstoppable.evolution.School;

/**
 * Created by Yash on 03-Jun-15.
 */
public class AdminView extends Fragment {
    static TextView noBasinGirls, noUrinalsGirls, noTapsGirls, noMirrorGirls;
    static RatingBar starBasinGirls, starUrinalGirls, starWashroomGirls, starFlowBasinGirls, starFlowUrinalGirls, starWindowGirls, starMirrorGirls;
    static TextView starTapsGirls, noTumbGirls, noBuckGirls, commentsGirls;
    static CheckBox tumbBuckSameGirls;
    static SwitchCompat doorLatchGirls,drainCloggingGirls,stinkingGirls,roofGirls;
    static RatingBar starCleanMidDay,starCleanCampus;
    static SwitchCompat soundSystem, kitchenMiddayMeals, sportsKits, booksStationery, amenitiesCultural;
    static TextView  otherComments;
    static TextView boys,girls,corridor,campus,water,dishwash,classroom,storageroom,dustbin;
    static SwitchCompat regularTankCleanWater, purifierProperWater, regularFlowWaterArea, tapLeakageWaterArea, drainCloggWaterArea, isStinkingWaterArea, regularFlowDishWashArea, tapLeakageDishWashArea, drainCloggDishWashArea, isStinkingDishWashArea;
    static TextView fillingFrequencyWater, capacityWater, brokenTapsWaterArea, noOfDustBinsWaterArea, commentsWaterArea, commentsDishWashArea, noOfDustBinsDishWashArea;
    static TextView noOfTapsWaterArea, noOfTapsDishWashArea;
    static RatingBar cleanAroundWaterArea, cleanAroundDishWashArea;
    static TextView how_often_clean;
    static ToggleButton statusWtPurifier, statusDishArea, StatusDwArea;
    static String[] types = {"1 month", "2 months", "3 months", "6 months", "12 months"};
    static TextView noBasinBoys, noUrinalsBoys, noTapsBoys, noMirrorBoys;
    static RatingBar starBasinBoys, starUrinalBoys, starWashroomBoys, starFlowBasinBoys, starFlowUrinalBoys, starWindowBoys, starMirrorBoys;
    static TextView starTapsBoys, noTumbBoys, noBuckBoys, commentsBoys;
    static CheckBox tumbBuckSameBoys;
    static SwitchCompat doorLatchBoys,drainCloggingBoys,stinkingBoys,roofBoys;
    static ToggleButton status_boys, status_girls;
    static CheckBox needRepair,clean;
    static TextView noBlackboard,noDustbin,comments;
    static RatingBar starBlackboard,starColor;
    static ToggleButton statusShade;
    HashMap<String, String> values = new HashMap<>();
    int scl_id;
    String date, scl_name;
    List<School> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_adminview, container, false);
        Bundle bundle = this.getArguments();
        scl_id = bundle.getInt("scl_id");
        date = bundle.getString("date");
        scl_name = bundle.getString("scl_name");
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


        //girls
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
        starTapsGirls = (TextView) layout.findViewById(R.id.star_taps_girls);
        noTumbGirls = (TextView) layout.findViewById(R.id.present_tumbler_girls);
        noBuckGirls = (TextView) layout.findViewById(R.id.present_bucket_girls);
        commentsGirls = (TextView) layout.findViewById(R.id.comments_girls);
        tumbBuckSameGirls = (CheckBox) layout.findViewById(R.id.same_buck_tumb_girls);
        doorLatchGirls = (SwitchCompat) layout.findViewById(R.id.latch_doors_girls);
        roofGirls = (SwitchCompat) layout.findViewById(R.id.roof_girls);
        stinkingGirls = (SwitchCompat) layout.findViewById(R.id.stinking_girls);
        drainCloggingGirls = (SwitchCompat) layout.findViewById(R.id.drainage_clogging_girls);
        status_girls = (ToggleButton) layout.findViewById(R.id.status_girls);

        //boys
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
        starTapsBoys = (TextView) layout.findViewById(R.id.star_taps_boys);
        noTumbBoys = (TextView) layout.findViewById(R.id.present_tumbler_boys);
        noBuckBoys = (TextView) layout.findViewById(R.id.present_bucket_boys);
        commentsBoys = (TextView) layout.findViewById(R.id.comments_boys);
        tumbBuckSameBoys = (CheckBox) layout.findViewById(R.id.same_buck_tumb_boys);
        doorLatchBoys = (SwitchCompat) layout.findViewById(R.id.latch_doors_boys);
        roofBoys = (SwitchCompat) layout.findViewById(R.id.roof_boys);
        stinkingBoys = (SwitchCompat) layout.findViewById(R.id.stinking_boys);
        drainCloggingBoys = (SwitchCompat) layout.findViewById(R.id.drainage_clogging_boys);
        status_boys = (ToggleButton) layout.findViewById(R.id.status_boys);

        //sweeper
        dishwash = (TextView) layout.findViewById(R.id.dishwash);
        boys = (TextView) layout.findViewById(R.id.boys);
        girls = (TextView) layout.findViewById(R.id.girls);
        corridor = (TextView) layout.findViewById(R.id.corridor);
        campus = (TextView) layout.findViewById(R.id.campus);
        water = (TextView) layout.findViewById(R.id.water);
        classroom = (TextView) layout.findViewById(R.id.classroom);
        storageroom = (TextView) layout.findViewById(R.id.storageroom);
        dustbin = (TextView) layout.findViewById(R.id.emptydustbin);


        //water tank
        regularTankCleanWater = (SwitchCompat) layout.findViewById(R.id.regularity_cleaning_tank);
        purifierProperWater = (SwitchCompat) layout.findViewById(R.id.water_pure_proper);
        fillingFrequencyWater = (TextView) layout.findViewById(R.id.fill_tank_freq_ans);
        capacityWater = (TextView) layout.findViewById(R.id.water_tank_capacity_ans);
        how_often_clean = (TextView) layout.findViewById(R.id.how_often_clean);
        statusWtPurifier = (ToggleButton) layout.findViewById(R.id.status_wt_purifier);

        //water area
        StatusDwArea = (ToggleButton) layout.findViewById(R.id.status_dw_area);
        regularFlowWaterArea = (SwitchCompat) layout.findViewById(R.id.regular_flow_water);
        tapLeakageWaterArea = (SwitchCompat) layout.findViewById(R.id.tap_Leakage);
        drainCloggWaterArea = (SwitchCompat) layout.findViewById(R.id.drain_clogg);
        isStinkingWaterArea = (SwitchCompat) layout.findViewById(R.id.stinking);
        brokenTapsWaterArea = (TextView) layout.findViewById(R.id.broken_taps);
        noOfDustBinsWaterArea = (TextView) layout.findViewById(R.id.no_of_dustbins);
        commentsWaterArea = (TextView) layout.findViewById(R.id.water_comments);
        cleanAroundWaterArea = (RatingBar) layout.findViewById(R.id.clean_around_water_area);
        noOfTapsWaterArea = (TextView) layout.findViewById(R.id.no_of_taps);

        //dishwash
        regularFlowDishWashArea = (SwitchCompat) layout.findViewById(R.id.reg_flow_water);
        tapLeakageDishWashArea = (SwitchCompat) layout.findViewById(R.id.leakage_of_taps);
        drainCloggDishWashArea = (SwitchCompat) layout.findViewById(R.id.drainnage_clogging);
        isStinkingDishWashArea = (SwitchCompat) layout.findViewById(R.id.Stinking_dish_wash_area);
        noOfDustBinsDishWashArea = (TextView) layout.findViewById(R.id.no_of_dustbin);
        statusDishArea = (ToggleButton) layout.findViewById(R.id.status_dish_area);
        noOfTapsDishWashArea = (TextView) layout.findViewById(R.id.no_taps_dish_wash_area);
        commentsDishWashArea = (TextView) layout.findViewById(R.id.dish_wash_comments);
        cleanAroundDishWashArea = (RatingBar) layout.findViewById(R.id.clean_around_dish_wash_areaa);
        comments = (TextView) layout.findViewById(R.id.comments);



        needRepair = (CheckBox) layout.findViewById(R.id.need_repair);
        clean = (CheckBox) layout.findViewById(R.id.clean);
        noBlackboard = (TextView) layout.findViewById(R.id.no_blackboard);
        noDustbin = (TextView) layout.findViewById(R.id.no_dustbin);
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
        otherComments = (TextView) layout.findViewById(R.id.other_comments);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner2_item, types);
        Log.d("Adapter", String.valueOf(adapter.isEmpty()));
        how_often_clean = (TextView) layout.findViewById(R.id.how_often_clean);
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
                    values.put("lat", json1.getString("lat"));
                    values.put("lon", json1.getString("lon"));



                    JSONObject json2 = (JSONObject) jsonObj.get(4); //boys
                    values.put("basin_boys", json2.getString("basin"));
                    values.put("urinal_boys", json2.getString("urinal"));
                    values.put("washroom_boys", json2.getString("washroom"));
                    values.put("flow_basin_boys", json2.getString("flow_basin"));
                    values.put("flow_urinal_boys", json2.getString("flow_urinal"));
                    values.put("window_boys",json2.getString("window"));
                    values.put("mirror_boys", json2.getString("mirror"));
                    values.put("taps_boys",json2.getString("taps"));
                    values.put("tumb_boys", json2.getString("tumbler"));
                    values.put("buck_boys", json2.getString("bucket"));
                    values.put("latch_boys", json2.getString("door_latch"));
                    values.put("roof_boys", json2.getString("roof"));
                    values.put("stinking_boys", json2.getString("stinking"));
                    values.put("clog_boys", json2.getString("clog"));
                    values.put("comments_boys", json2.getString("comments"));
                    values.put("status_boys",json2.getString("status"));


                    JSONObject json3 = (JSONObject) jsonObj.get(5); //girls
                    values.put("basin_girls", json3.getString("basin"));
                    values.put("urinal_girls", json3.getString("urinal"));
                    values.put("washroom_girls", json3.getString("washroom"));
                    values.put("flow_basin_girls", json3.getString("flow_basin"));
                    values.put("flow_urinal_girls", json3.getString("flow_urinal"));
                    values.put("window_girls", json3.getString("window"));
                    values.put("mirror_girls", json3.getString("mirror"));
                    values.put("taps_girls", json3.getString("taps"));
                    values.put("tumb_girls", json3.getString("tumbler"));
                    values.put("buck_girls", json3.getString("bucket"));
                    values.put("latch_girls", json3.getString("door_latch"));
                    values.put("roof_girls", json3.getString("roof"));
                    values.put("stinking_girls", json3.getString("stinking"));
                    values.put("clog_girls", json3.getString("clog"));
                    values.put("comments_girls", json3.getString("comments"));
                    values.put("status_girls", json3.getString("status"));


                    JSONObject json4 = (JSONObject) jsonObj.get(6); //sweeper : 6
                    values.put("boys", json4.getString("boys"));
                    values.put("girls", json4.getString("girls"));
                    values.put("corridor", json4.getString("corridor"));
                    values.put("campus", json4.getString("campus"));
                    values.put("water",json4.getString("water"));
                    values.put("class", json4.getString("class"));
                    values.put("storage", json4.getString("storage"));
                    values.put("dustbin", json4.getString("dustbin"));
                    values.put("dishwash", json4.getString("dishwash"));

                    JSONObject json5 = (JSONObject) jsonObj.get(8); //water tank : 8
                    values.put("reg_clean", json5.getString("rglr_clean"));
                    values.put("purifier_proper", json5.getString("purifier_proper"));
                    values.put("freq_fil", json5.getString("f_filling"));
                    values.put("capacity_tank", json5.getString("capacity"));
                    values.put("f_clean_month", json5.getString("f_clean_month"));
                    values.put("staus_water", json5.getString("status"));


                    JSONObject json7 = (JSONObject) jsonObj.get(7); //water area : 7
                    values.put("staus_water_area", json7.getString("status"));
                    values.put("reg_flow_water",json7.getString("reg_flow"));
                    values.put("tap_leakage_water",json7.getString("tap_leakage"));
                    values.put("drain_clog_water",json7.getString("drain_clog"));
                    values.put("stinking_water_area",json7.getString("stink"));
                    values.put("broken_taps_water",json7.getString("broken_taps"));
                    values.put("dustbins_water",json7.getString("dustbin"));
                    values.put("comments_water",json7.getString("comments"));
                    values.put("clean_water",json7.getString("clean"));

                    JSONObject json8 = (JSONObject) jsonObj.get(1); //dishwash : 1
                    values.put("reg_flow_dish",json8.getString("reg_flow"));
                    values.put("tap_leakage_dish",json8.getString("tap_leakage"));
                    values.put("drain_clog_dish",json8.getString("drain_clog"));
                    values.put("stinking_dish",json8.getString("stink"));
                    values.put("dustbins_dish",json8.getString("dustbin"));
                    values.put("comments_dish",json8.getString("comments"));
                    values.put("clean_dish",json8.getString("clean"));


                    JSONObject json9 = (JSONObject) jsonObj.get(4); //others
                    values.put("clean_midday", json9.getString("clean_midday"));
                    values.put("clean_campus", json9.getString("clean_campus"));
                    values.put("sound system", json9.getString("soundsys"));
                    values.put("kitchen midday meals", json9.getString("kitchen"));
                    values.put("sports kits", json9.getString("sports"));
                    values.put("books stationery", json9.getString("stationery"));
                    values.put("amenities cultural", json9.getString("cultural"));
                    values.put("Comments", json9.getString("comments"));

                    JSONObject json0 = (JSONObject) jsonObj.get(0); //campus Renovation and 3 commons
                    values.put("need_repair",json0.getString("need_repair"));
                    values.put("clean", json0.getString("clean"));
                    values.put("board", json0.getString("board"));
                    values.put("dustbin", json0.getString("dustbin"));
                    values.put("cond_board", json0.getString("cond_board"));
                    values.put("color", json0.getString("color"));
                    values.put("renovation_comments", json0.getString("comments"));
                    values.put("status_shade", json0.getString("shade"));
                    values.put("uname", json0.getString("uname"));
                    values.put("date", json0.getString("date"));
                    values.put("scl_id",json0.getString("scl_id"));

                }
                Log.d("response",list.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        protected void setText(){

            //boys
              /*
            noUrinalsBoys = (TextView) layout.findViewById(R.id.no_urinals_boys);
            noTapsBoys = (TextView) layout.findViewById(R.id.no_taps_boys);
            noMirrorBoys = (TextView) layout.findViewById(R.id.no_mirror_boys);
            noTumbBoys = (TextView) layout.findViewById(R.id.present_tumbler_boys);
            noBuckBoys = (TextView) layout.findViewById(R.id.present_bucket_boys);
            */
            commentsBoys.setText("Volunteer Comments:\n" + values.get("comments_boys"));
            starBasinBoys.setRating(Float.parseFloat(values.get("basin_boys")));
            starUrinalBoys.setRating(Float.parseFloat(values.get("urinal_boys")));
            starWashroomBoys.setRating(Float.parseFloat(values.get("washroom_boys")));
            starTapsBoys.setText(values.get("taps_boys"));
            starFlowBasinBoys.setRating(Float.parseFloat(values.get("flow_basin_boys")));
            starFlowUrinalBoys.setRating(Float.parseFloat(values.get("flow_urinal_boys")));
            starWindowBoys.setRating(Float.parseFloat(values.get("window_boys")));
            starMirrorBoys.setRating(Float.parseFloat(values.get("mirror_boys")));
            doorLatchBoys.setChecked(values.get("latch_boys").equalsIgnoreCase("yes"));
            roofBoys.setChecked(values.get("roof_boys").equalsIgnoreCase("yes"));
            stinkingBoys.setChecked(values.get("stinking_boys").equalsIgnoreCase("yes"));
            drainCloggingBoys.setChecked(values.get("clog_boys").equalsIgnoreCase("yes"));
            status_boys.setChecked(values.get(status_boys).equalsIgnoreCase("yes"));

            //girls
            /*
            noTumbGirls = (TextView) layout.findViewById(R.id.present_tumbler_girls);
            noBuckGirls = (TextView) layout.findViewById(R.id.present_bucket_girls);
            noTapsGirls.setText(values.get("taps_girls"));
            noBasinGirls.setText(values.get("basin_girls"));
            noUrinalsGirls.setText(values.get("urinal_girls"));
            noMirrorGirls.setText(values.get("mirror_girls"));
            */
            starTapsGirls.setText("No of Working Taps: " + values.get("taps_girls"));
            starBasinGirls.setRating(Float.parseFloat(values.get("basin_girls")));
            starUrinalGirls.setRating(Float.parseFloat(values.get("urinal_girls")));
            starWashroomGirls.setRating(Float.parseFloat(values.get("washroom_girls")));
            starFlowBasinGirls.setRating(Float.parseFloat(values.get("flow_basin_girls")));
            starFlowUrinalGirls.setRating(Float.parseFloat(values.get("flow_urinal_girls")));
            starWindowGirls.setRating(Float.parseFloat(values.get("window_girls")));
            starMirrorGirls.setRating(Float.parseFloat(values.get("mirror_girls")));
            doorLatchGirls.setChecked(values.get("latch_girls").equalsIgnoreCase("yes"));
            roofGirls.setChecked(values.get("roof_girls").equalsIgnoreCase("yes"));
            stinkingGirls.setChecked(values.get("stinking_girls").equalsIgnoreCase("yes"));
            drainCloggingGirls.setChecked(values.get("clog_girls").equalsIgnoreCase("yes"));
            commentsGirls.setText("Volunteer comments:\n" + values.get("comments_girls"));
            status_girls.setChecked(values.get("status_girls").equalsIgnoreCase("yes"));

            //sweeper
            dishwash.setText("Dishwash " + values.get("dishwash") + " times");
            boys.setText("Boys Washroom " + values.get("boys") + " times");
            girls.setText("Girls Washroom " + values.get("girls") + " times");
            corridor.setText("Corridor " + values.get("corridor") + " times");
            campus.setText("Campus " + values.get("campus") + " times");
            water.setText("Drinking water area " + values.get("water") + " times");
            classroom.setText("Classrooms " + values.get("class") + " times");
            storageroom.setText("Storage room " + values.get("storage") + " times");
            dustbin.setText("Empties the dustbins " + values.get("dustbin") + " times");

            //water tank
            regularTankCleanWater.setChecked(values.get("reg_clean").equalsIgnoreCase("yes"));
            purifierProperWater.setChecked(values.get("purifier_proper").equalsIgnoreCase("yes"));
            fillingFrequencyWater.setText("Frequency of filling is " + values.get("freq_fill") + "/day");
            capacityWater.setText("Capacity of tank is " + values.get("capacity_tank") + " litres");
            how_often_clean.setText("Tank is cleaned every " + values.get("f_clean_month") + "month(s)");
            statusWtPurifier.setChecked(values.get("staus_water").equalsIgnoreCase("yes"));

            //water area
            StatusDwArea.setChecked(values.get("status_water_area").equalsIgnoreCase("yes"));
            regularFlowWaterArea.setChecked(values.get("reg_flow_water").equalsIgnoreCase("yes"));
            tapLeakageWaterArea.setChecked(values.get("tap_leakage_water").equalsIgnoreCase("yes"));
            drainCloggWaterArea.setChecked(values.get("drain_clog_water").equalsIgnoreCase("yes"));
            isStinkingWaterArea.setChecked(values.get("stinking_water_area").equalsIgnoreCase("yes"));
            brokenTapsWaterArea.setText(values.get("broken_taps_water"));
            //noOfDustBinsWaterArea = (TextView) layout.findViewById(R.id.no_of_dustbins);
            commentsWaterArea.setText(values.get("comments_water"));
            cleanAroundWaterArea.setRating(Float.parseFloat(values.get("clean_water")));
            //noOfTapsWaterArea = (TextView) layout.findViewById(R.id.no_of_taps);


            //dishwash
            regularFlowDishWashArea.setChecked(values.get("reg_flow_dish").equalsIgnoreCase("yes"));
            tapLeakageDishWashArea.setChecked(values.get("tap_leakage_dish").equalsIgnoreCase("yes"));
            drainCloggDishWashArea.setChecked(values.get("drain_clog_dish").equalsIgnoreCase("yes"));
            isStinkingDishWashArea.setChecked(values.get("stinking_dish").equalsIgnoreCase("yes"));
          //  noOfDustBinsDishWashArea = (TextView) layout.findViewById(R.id.no_of_dustbin);
            statusDishArea = (ToggleButton) layout.findViewById(R.id.status_dish_area);
           // noOfTapsDishWashArea = (TextView) layout.findViewById(R.id.no_taps_dish_wash_area);
            commentsDishWashArea.setText(values.get("comments_dish"));
            cleanAroundDishWashArea.setRating(Float.parseFloat(values.get("clean_dish")));
            comments.setText(values.get("comments_dish"));





        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressDialog.dismiss();
        }
    }
}


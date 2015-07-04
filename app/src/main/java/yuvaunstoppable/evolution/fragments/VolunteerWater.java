package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import yuvaunstoppable.evolution.NothingSelectedSpinnerAdapter;
import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerWater extends Fragment {

    public VolunteerWater() {

    }

    static SwitchCompat regularTankCleanWater, purifierProperWater, regularFlowWaterArea, tapLeakageWaterArea, drainCloggWaterArea, isStinkingWaterArea, regularFlowDishWashArea, tapLeakageDishWashArea, drainCloggDishWashArea, isStinkingDishWashArea;
    static EditText fillingFrequencyWater, capacityWater, brokenTapsWaterArea, noOfDustBinsWaterArea, commentsWaterArea, commentsDishWashArea, noOfDustBinsDishWashArea;
    static TextView noOfTapsWaterArea, noOfTapsDishWashArea;
    static RatingBar cleanAroundWaterArea, cleanAroundDishWashArea;
    static Spinner how_often_clean;
    static String[] types = {"1 month", "2 months", "3 months", "6 months", "12 months"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_water, container, false);
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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner2_item, types);
        Log.d("Adapter", String.valueOf(adapter.isEmpty()));
        how_often_clean = (Spinner) layout.findViewById(R.id.how_often_clean);
        how_often_clean.setPrompt("Select no. of months");
        how_often_clean.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner2_item, getActivity()));
        return layout;
    }

    public static ArrayList<NameValuePair> getData(){
        ArrayList<NameValuePair> values = new ArrayList<NameValuePair>();
        try {
            values.add(new BasicNameValuePair("reg_clean", regularTankCleanWater.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("purifier_proper", purifierProperWater.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("freq_fil", fillingFrequencyWater.getText().toString()));
            values.add(new BasicNameValuePair("capacity_tank", capacityWater.getText().toString()));
            values.add(new BasicNameValuePair("f_clean_month", types[how_often_clean.getSelectedItemPosition() - 1]));

            values.add(new BasicNameValuePair("reg_flow_water",regularFlowWaterArea.isChecked() ? "yes":"no"));
            values.add(new BasicNameValuePair("tap_leakage_water",tapLeakageWaterArea.isChecked() ? "yes":"no"));
            values.add(new BasicNameValuePair("drain_clog_water",drainCloggWaterArea.isChecked() ? "yes":"no"));
            values.add(new BasicNameValuePair("stinking_water_area",isStinkingWaterArea.isChecked() ? "yes":"no"));
            values.add(new BasicNameValuePair("reg_flow_dish",regularFlowDishWashArea.isChecked() ? "yes":"no"));
            values.add(new BasicNameValuePair("tap_leakage_dish",tapLeakageDishWashArea.isChecked() ? "yes":"no"));
            values.add(new BasicNameValuePair("drain_clog_dish",drainCloggDishWashArea.isChecked() ? "yes":"no"));
            values.add(new BasicNameValuePair("stinking_dish",isStinkingDishWashArea.isChecked() ? "yes":"no"));
            values.add(new BasicNameValuePair("broken_taps_water",brokenTapsWaterArea.getText().toString()));
            values.add(new BasicNameValuePair("dustbins_water",noOfDustBinsWaterArea.getText().toString()));
            values.add(new BasicNameValuePair("comments_water",commentsWaterArea.getText().toString()));
            values.add(new BasicNameValuePair("dustbins_dish",noOfDustBinsDishWashArea.getText().toString()));
            values.add(new BasicNameValuePair("comments_dish",commentsDishWashArea.getText().toString()));
            values.add(new BasicNameValuePair("clean_water",Integer.toString((int) cleanAroundWaterArea.getRating())));
            values.add(new BasicNameValuePair("clean_dish",Integer.toString((int) cleanAroundDishWashArea.getRating())));
        }catch (Exception e){}
        return values;
    }
}

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

import yuvaunstoppable.evolution.NothingSelectedSpinnerAdapter;
import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerWater extends Fragment {

    public VolunteerWater() {

    }

    SwitchCompat regularTankCleanWater, purifierProperWater, regularFlowWaterArea, tapLeakageWaterArea, drainCloggWaterArea, isStinkingWaterArea, regularFlowDishWashArea, tapLeakageDishWashArea, drainCloggDishWashArea, isStinkingDishWashArea;
    EditText fillingFrequencyWater, capacityWater, brokenTapsWaterArea, noOfDustBinsWaterArea, commentsWaterArea, commentsDishWashArea, noOfDustBinsDishWashArea;
    TextView noOfTapsWaterArea, noOfTapsDishWashArea;
    RatingBar cleanAroundWaterArea, cleanAroundDishWashArea;

    String[] types = {"1 month", "2 months", "3 months", "6 months", "12 months"};

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
        noOfTapsWaterArea = (TextView) layout.findViewById(R.id.no_taps);
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
        final Spinner how_often_clean = (Spinner) layout.findViewById(R.id.how_often_clean);
        how_often_clean.setPrompt("Select no. of months");
        how_often_clean.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner2_item, getActivity()));
        return layout;
    }
}

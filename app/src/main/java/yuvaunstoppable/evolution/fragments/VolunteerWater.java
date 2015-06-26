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

    public VolunteerWater(){

    }

    SwitchCompat regularCleaningTank,waterPureProper,regularFlowWater,tapLeakage,drainClog,stinking;
    EditText fillTankFreq,watertankCapacity,noBrokenTaps,noDustbins,comments;
    TextView noOfTaps;
    RatingBar starCleanAroundWater;
    String[] types  = {"1 month","2 months","3 months","6 months","12 months"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_water,container,false);
        regularCleaningTank = (SwitchCompat) layout.findViewById(R.id.regularity_cleaning_tank);
        waterPureProper = (SwitchCompat) layout.findViewById(R.id.water_pure_proper);
        regularFlowWater = (SwitchCompat) layout.findViewById(R.id.reg_flow_water);
        tapLeakage = (SwitchCompat) layout.findViewById(R.id.tap_Leakage);
        drainClog = (SwitchCompat) layout.findViewById(R.id.drain_clogg);
        stinking = (SwitchCompat) layout.findViewById(R.id.stinking);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner2_item, types);
        Log.d("Adapter", String.valueOf(adapter.isEmpty()));
        final Spinner how_often_clean = (Spinner) layout.findViewById(R.id.how_often_clean);
        how_often_clean.setPrompt("Select no. of months");
        how_often_clean.setAdapter(new NothingSelectedSpinnerAdapter(adapter,R.layout.spinner2_item,getActivity()));
        return layout;
    }
}

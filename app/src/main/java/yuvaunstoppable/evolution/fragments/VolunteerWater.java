package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    String[] types  = {"1 month","2 months","3 months","6 months","12 months"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_water,container,false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner2_item, types);
        Log.d("Adapter", String.valueOf(adapter.isEmpty()));
        final Spinner how_often_clean = (Spinner) layout.findViewById(R.id.how_often_clean);
        how_often_clean.setPrompt("Select no. of months");
        how_often_clean.setAdapter(new NothingSelectedSpinnerAdapter(adapter,R.layout.spinner2_item,getActivity()));
        return layout;
    }
}

package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerWater extends Fragment {

    public VolunteerWater(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_water,container,false);

        return layout;
    }
}

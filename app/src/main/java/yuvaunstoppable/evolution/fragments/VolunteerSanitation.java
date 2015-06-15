package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerSanitation extends Fragment {


    public VolunteerSanitation(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_sanitation,container,false);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.list);

        return layout;
    }
}

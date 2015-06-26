package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerCampusRenovation extends android.support.v4.app.Fragment {


    public VolunteerCampusRenovation(){

    }
    CheckBox needRepair,clean;
    EditText noBlackboard,noDustbin,comments;
    RatingBar starBlackboard,starColor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_campus_renovation,container,false);
        needRepair = (CheckBox) layout.findViewById(R.id.need_repair);
        clean = (CheckBox) layout.findViewById(R.id.clean);
        noBlackboard = (EditText) layout.findViewById(R.id.no_blackboard);
        noDustbin = (EditText) layout.findViewById(R.id.no_dustbin);
        comments = (EditText) layout.findViewById(R.id.comments);
        starBlackboard = (RatingBar) layout.findViewById(R.id.star_blackboard);
        starColor = (RatingBar) layout.findViewById(R.id.star_color);

        return layout;
    }
}

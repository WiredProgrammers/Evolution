package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerCampusRenovation extends android.support.v4.app.Fragment {


    public VolunteerCampusRenovation(){

    }
    static CheckBox needRepair,clean;
    static EditText noBlackboard,noDustbin,comments;
    static RatingBar starBlackboard,starColor;
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

    public static ArrayList<NameValuePair> getData(){

        ArrayList<NameValuePair> values = new ArrayList<NameValuePair>();
        try {
            values.add(new BasicNameValuePair("need_repair", needRepair.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("clean", clean.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("board", noBlackboard.getText().toString()));
            values.add(new BasicNameValuePair("dustbin", noDustbin.getText().toString()));
            values.add(new BasicNameValuePair("cond_board", Integer.toString((int) starBlackboard.getRating())));
            values.add(new BasicNameValuePair("color", Integer.toString((int) starColor.getRating())));
            values.add(new BasicNameValuePair("renovation_comments", comments.getText().toString()));
        }catch(Exception e){}
        return values;
    }
}

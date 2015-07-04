package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerOthers extends Fragment {

    public  VolunteerOthers(){

    }
    static RatingBar starCleanMidDay,starCleanCampus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_others,container,false);

        starCleanCampus = (RatingBar) layout.findViewById(R.id.star_clean_campus);
        starCleanMidDay = (RatingBar) layout.findViewById(R.id.star_clean_midday);

        return layout;
    }

    public static ArrayList<NameValuePair> getData(){
        ArrayList<NameValuePair> values = new ArrayList<NameValuePair>();
        try {
            values.add(new BasicNameValuePair("clean_midday", Integer.toString((int) starCleanMidDay.getRating())));
            values.add(new BasicNameValuePair("clean_campus", Integer.toString((int) starCleanCampus.getRating())));
        }catch(Exception e){}
        return values;
    }
}

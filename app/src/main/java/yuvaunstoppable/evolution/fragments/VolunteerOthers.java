package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    static SwitchCompat soundSystem, kitchenMiddayMeals, sportsKits, booksStationery, amenitiesCultural;
    static EditText  otherComments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_others,container,false);

        starCleanCampus = (RatingBar) layout.findViewById(R.id.star_clean_campus);
        starCleanMidDay = (RatingBar) layout.findViewById(R.id.star_clean_midday);
        soundSystem = (SwitchCompat) layout.findViewById(R.id.sound_system);
        kitchenMiddayMeals = (SwitchCompat) layout.findViewById(R.id.kitchen_midday_meals);
        sportsKits = (SwitchCompat) layout.findViewById(R.id.sports_kits);
        booksStationery = (SwitchCompat) layout.findViewById(R.id.books_stationery);
        amenitiesCultural = (SwitchCompat) layout.findViewById(R.id.amenities_cultural);
        otherComments = (EditText) layout.findViewById(R.id.other_comments);

        return layout;
    }

    public static ArrayList<NameValuePair> getData(){
        ArrayList<NameValuePair> values = new ArrayList<NameValuePair>();
        try {
            values.add(new BasicNameValuePair("clean_midday", Integer.toString((int) starCleanMidDay.getRating())));
            values.add(new BasicNameValuePair("clean_campus", Integer.toString((int) starCleanCampus.getRating())));
            values.add(new BasicNameValuePair("sound_system", soundSystem.isChecked()? "Yes" : "No"));
            values.add(new BasicNameValuePair("kitchen_midday_meals", kitchenMiddayMeals.isChecked()? "Yes" : "No"));
            values.add(new BasicNameValuePair("sports_kits", sportsKits.isChecked()? "Yes" : "No"));
            values.add(new BasicNameValuePair("books_stationery", booksStationery.isChecked()? "Yes" : "No"));
            values.add(new BasicNameValuePair("amenities_cultural", amenitiesCultural.isChecked()? "Yes" : "No"));
            values.add(new BasicNameValuePair("comments", otherComments.getText().toString()));

        }catch(Exception e){}
        return values;
    }
}

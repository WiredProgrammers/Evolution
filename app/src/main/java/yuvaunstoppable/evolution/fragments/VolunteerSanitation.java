package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerSanitation extends Fragment {


    public VolunteerSanitation(){

    }

    static TextView noBasinGirls, noUrinalsGirls, noTapsGirls, noMirrorGirls;
    static RatingBar starBasinGirls, starUrinalGirls, starWashroomGirls, starFlowBasinGirls, starFlowUrinalGirls, starWindowGirls, starMirrorGirls;
    static EditText starTapsGirls, noTumbGirls, noBuckGirls, commentsGirls;
    static CheckBox tumbBuckSameGirls;
    static SwitchCompat doorLatchGirls,drainCloggingGirls,stinkingGirls,roofGirls;

    static TextView noBasinBoys, noUrinalsBoys, noTapsBoys, noMirrorBoys;
    static RatingBar starBasinBoys, starUrinalBoys, starWashroomBoys, starFlowBasinBoys, starFlowUrinalBoys, starWindowBoys, starMirrorBoys;
    static EditText starTapsBoys, noTumbBoys, noBuckBoys, commentsBoys;
    static CheckBox tumbBuckSameBoys;
    static SwitchCompat doorLatchBoys,drainCloggingBoys,stinkingBoys,roofBoys;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_sanitation,container,false);
        noBasinGirls = (TextView) layout.findViewById(R.id.no_basin_girls);
        noUrinalsGirls = (TextView) layout.findViewById(R.id.no_urinals_girls);
        noTapsGirls = (TextView) layout.findViewById(R.id.no_taps_girls);
        noMirrorGirls = (TextView) layout.findViewById(R.id.no_mirror_girls);

        starBasinGirls = (RatingBar) layout.findViewById(R.id.star_basin_girls);
        starUrinalGirls = (RatingBar) layout.findViewById(R.id.star_urinal_girls);
        starWashroomGirls = (RatingBar) layout.findViewById(R.id.star_washroom_girls);
        starFlowBasinGirls = (RatingBar) layout.findViewById(R.id.star_flow_basin_girls);
        starFlowUrinalGirls = (RatingBar) layout.findViewById(R.id.star_flow_urinal_girls);
        starWindowGirls = (RatingBar) layout.findViewById(R.id.star_window_girls);
        starMirrorGirls = (RatingBar) layout.findViewById(R.id.star_mirror_girls);
        starTapsGirls = (EditText) layout.findViewById(R.id.star_taps_girls);
        noTumbGirls = (EditText) layout.findViewById(R.id.present_tumbler_girls);
        noBuckGirls = (EditText) layout.findViewById(R.id.present_bucket_girls);
        commentsGirls = (EditText) layout.findViewById(R.id.comments_girls);
        tumbBuckSameGirls = (CheckBox) layout.findViewById(R.id.same_buck_tumb_girls);
        doorLatchGirls = (SwitchCompat) layout.findViewById(R.id.latch_doors_girls);
        roofGirls = (SwitchCompat) layout.findViewById(R.id.roof_girls);
        stinkingGirls = (SwitchCompat) layout.findViewById(R.id.stinking_girls);
        drainCloggingGirls = (SwitchCompat) layout.findViewById(R.id.drainage_clogging_girls);

        noBasinBoys = (TextView) layout.findViewById(R.id.no_basin_boys);
        noUrinalsBoys = (TextView) layout.findViewById(R.id.no_urinals_boys);
        noTapsBoys = (TextView) layout.findViewById(R.id.no_taps_boys);
        noMirrorBoys = (TextView) layout.findViewById(R.id.no_mirror_boys);

        starBasinBoys = (RatingBar) layout.findViewById(R.id.star_basin_boys);
        starUrinalBoys = (RatingBar) layout.findViewById(R.id.star_urinal_boys);
        starWashroomBoys = (RatingBar) layout.findViewById(R.id.star_washroom_boys);
        starFlowBasinBoys = (RatingBar) layout.findViewById(R.id.star_flow_basin_boys);
        starFlowUrinalBoys = (RatingBar) layout.findViewById(R.id.star_flow_urinal_boys);
        starWindowBoys = (RatingBar) layout.findViewById(R.id.star_window_boys);
        starMirrorBoys = (RatingBar) layout.findViewById(R.id.star_mirror_boys);
        starTapsBoys = (EditText) layout.findViewById(R.id.star_taps_boys);
        noTumbBoys = (EditText) layout.findViewById(R.id.present_tumbler_boys);
        noBuckBoys = (EditText) layout.findViewById(R.id.present_bucket_boys);
        commentsBoys = (EditText) layout.findViewById(R.id.comments_boys);
        tumbBuckSameBoys = (CheckBox) layout.findViewById(R.id.same_buck_tumb_boys);
        doorLatchBoys = (SwitchCompat) layout.findViewById(R.id.latch_doors_boys);
        roofBoys = (SwitchCompat) layout.findViewById(R.id.roof_boys);
        stinkingBoys = (SwitchCompat) layout.findViewById(R.id.stinking_boys);
        drainCloggingBoys = (SwitchCompat) layout.findViewById(R.id.drainage_clogging_boys);


        tumbBuckSameBoys.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    noTumbBoys.setVisibility(View.GONE);
                else
                    noTumbBoys.setVisibility(View.VISIBLE);
            }
        });

        tumbBuckSameGirls.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    noTumbGirls.setVisibility(View.GONE);
                else
                    noTumbGirls.setVisibility(View.VISIBLE);
            }
        });

        return layout;
    }

    public static ArrayList<NameValuePair> getData(){
        ArrayList<NameValuePair> values = new ArrayList<NameValuePair>();
        try {
            values.add(new BasicNameValuePair("basin_girls", Integer.toString((int) starBasinGirls.getRating())));
            values.add(new BasicNameValuePair("urinal_girls", Integer.toString((int) starUrinalGirls.getRating())));
            values.add(new BasicNameValuePair("washroom_girls", Integer.toString((int) starWashroomGirls.getRating())));
            values.add(new BasicNameValuePair("flow_basin_girls", Integer.toString((int) starFlowBasinGirls.getRating())));
            values.add(new BasicNameValuePair("flow_urinal_girls", Integer.toString((int) starFlowUrinalGirls.getRating())));
            values.add(new BasicNameValuePair("window_girls", Integer.toString((int) starWindowGirls.getRating())));
            values.add(new BasicNameValuePair("mirror_girls", Integer.toString((int) starMirrorGirls.getRating())));
            values.add(new BasicNameValuePair("taps_girls", starTapsGirls.getText().toString()));
            values.add(new BasicNameValuePair("tumb_girls", tumbBuckSameGirls.isChecked() ? noBuckGirls.getText().toString() : noTumbGirls.getText().toString()));
            values.add(new BasicNameValuePair("buck_girls", noBuckGirls.getText().toString()));
            values.add(new BasicNameValuePair("latch_girls", doorLatchGirls.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("roof_girls", roofGirls.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("stinking_girls", stinkingGirls.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("clog_girls", drainCloggingGirls.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("comments_girls", commentsGirls.getText().toString()));

            values.add(new BasicNameValuePair("basin_boys", Integer.toString((int) starBasinBoys.getRating())));
            values.add(new BasicNameValuePair("urinal_boys", Integer.toString((int) starUrinalBoys.getRating())));
            values.add(new BasicNameValuePair("washroom_boys", Integer.toString((int) starWashroomBoys.getRating())));
            values.add(new BasicNameValuePair("flow_basin_boys", Integer.toString((int) starFlowBasinBoys.getRating())));
            values.add(new BasicNameValuePair("flow_urinal_boys", Integer.toString((int) starFlowUrinalBoys.getRating())));
            values.add(new BasicNameValuePair("window_boys", Integer.toString((int) starWindowBoys.getRating())));
            values.add(new BasicNameValuePair("mirror_boys", Integer.toString((int) starMirrorBoys.getRating())));
            values.add(new BasicNameValuePair("taps_boys", starTapsBoys.getText().toString()));
            values.add(new BasicNameValuePair("tumb_boys", tumbBuckSameBoys.isChecked() ? noBuckBoys.getText().toString() : noTumbBoys.getText().toString()));
            values.add(new BasicNameValuePair("buck_boys", noBuckBoys.getText().toString()));
            values.add(new BasicNameValuePair("latch_boys", doorLatchBoys.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("roof_boys", roofBoys.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("stinking_boys", stinkingBoys.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("clog_boys", drainCloggingBoys.isChecked() ? "yes" : "no"));
            values.add(new BasicNameValuePair("comments_boys", commentsBoys.getText().toString()));
        }catch(Exception e){}
        return values;
    }
}

package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerSanitation extends Fragment {


    public VolunteerSanitation(){

    }

    TextView noBasinGirls, noUrinalsGirls, noTapsGirls, noMirrorGirls;
    RatingBar starBasinGirls, starUrinalGirls, starWashroomGirls, starFlowBasinGirls, starFlowUrinalGirls, starWindowGirls, starMirrorGirls;
    EditText starTapsGirls, noTumbGirls, noBuckGirls, commentsGirls;
    CheckBox tumbBuckSameGirls;
    SwitchCompat doorLatchGirls;

    TextView noBasinBoys, noUrinalsBoys, noTapsBoys, noMirrorBoys;
    RatingBar starBasinBoys, starUrinalBoys, starWashroomBoys, starFlowBasinBoys, starFlowUrinalBoys, starWindowBoys, starMirrorBoys;
    EditText starTapsBoys, noTumbBoys, noBuckBoys, commentsBoys;
    CheckBox tumbBuckSameBoys;
    SwitchCompat doorLatchBoys;

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


        return layout;
    }
}

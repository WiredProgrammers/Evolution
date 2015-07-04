package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import yuvaunstoppable.evolution.R;

/**
 * Created by amit on 10-Jun-15.
 */
public class VolunteerSweeper extends Fragment {

    public VolunteerSweeper(){

    }

    static EditText boys,girls,corridor,campus,water,dishwash,classroom,storageroom,dustbin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_volunteer_sweeper,container,false);
        boys = (EditText) layout.findViewById(R.id.boys);
        girls = (EditText) layout.findViewById(R.id.girls);
        corridor = (EditText) layout.findViewById(R.id.corridor);
        campus = (EditText) layout.findViewById(R.id.campus);
        water = (EditText) layout.findViewById(R.id.water);
        dishwash = (EditText) layout.findViewById(R.id.dishwash);
        classroom = (EditText) layout.findViewById(R.id.classroom);
        storageroom = (EditText) layout.findViewById(R.id.storageroom);
        dustbin = (EditText) layout.findViewById(R.id.emptydustbin);

        return layout;
    }

    public static ArrayList<NameValuePair> getData(){
        ArrayList<NameValuePair> values = new ArrayList<NameValuePair>();
        try {
            values.add(new BasicNameValuePair("boys", boys.getText().toString()));
            values.add(new BasicNameValuePair("girls", girls.getText().toString()));
            values.add(new BasicNameValuePair("corridor", corridor.getText().toString()));
            values.add(new BasicNameValuePair("campus", campus.getText().toString()));
            values.add(new BasicNameValuePair("water", water.getText().toString()));
            values.add(new BasicNameValuePair("dishwash", dishwash.getText().toString()));
            values.add(new BasicNameValuePair("class", classroom.getText().toString()));
            values.add(new BasicNameValuePair("storage", storageroom.getText().toString()));
            values.add(new BasicNameValuePair("dustbin", dustbin.getText().toString()));
        }catch (Exception e){}
        return values;
    }
}

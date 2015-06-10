package yuvaunstoppable.evolution.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import yuvaunstoppable.evolution.NothingSelectedSpinnerAdapter;
import yuvaunstoppable.evolution.R;

/**
 * Created by Yash on 03-Jun-15.
 */
public class AdminSignUp extends Fragment {

    String[] types = {"Admin", "Volunteer"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adminsignup, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, types);
        Log.d("Adapter", String.valueOf(adapter.isEmpty()));
        final Spinner utype = (Spinner) view.findViewById(R.id.utype);
        utype.setPrompt("Select user type");
        utype.setAdapter(new NothingSelectedSpinnerAdapter(adapter,R.layout.spinner_item,getActivity()));
        Button signUp = (Button) view.findViewById(R.id.sign_up_btn);
        final EditText usrname = (EditText) view.findViewById(R.id.uname);
        final EditText pwd = (EditText) view.findViewById(R.id.pass);
        final EditText name = (EditText) view.findViewById(R.id.name);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Adding User");
                progressDialog.setCancelable(false);
                progressDialog.show();
                String uname = usrname.getText().toString();
                String pass = pwd.getText().toString();
                String naam = name.getText().toString();
                ParseUser cuser = ParseUser.getCurrentUser();
                ParseUser puser = new ParseUser();
                puser.put("name", naam);
                puser.put("pass", pass);
                puser.setPassword(pass);
                puser.setUsername(uname);
                puser.put("type", types[utype.getSelectedItemPosition()]);
                puser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        progressDialog.hide();
                        if (e != null) {
                            String exc = e.toString();
                            exc = exc.split(": ")[1];
                            Toast.makeText(getActivity(), exc, Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getActivity(), "User Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
                ParseUser.logOutInBackground();
                Log.d("User",cuser.getUsername()+cuser.get("pass").toString());
                ParseUser.logInInBackground(cuser.getUsername(), cuser.get("pass").toString());
            }
        });
        return view;
    }
}

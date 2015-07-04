package yuvaunstoppable.evolution;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseUser;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import yuvaunstoppable.evolution.db.DBHelper;


public class Main extends AppCompatActivity {

    private Toolbar toolbar;
    private Button loginBtn;
    private ImageButton visibleBtn;
    private EditText uname, pass;
    private String user, password, userType;
    private RadioGroup uType;
    DBHelper dbHelper = DBHelper.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        uname = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pass);
        uType = (RadioGroup) findViewById(R.id.utype);


        User currUser = dbHelper.login();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currUser != null) {
            userType = currUser.getType();
            Intent i;
            if (userType.equalsIgnoreCase("Volunteer"))
                i = new Intent(Main.this, VolunteerSchool.class);
            else
                i = new Intent(Main.this, Admin.class);
            i.putExtra("user", currUser.getName());
            startActivity(i);
            finish();
        } else {
            loginBtn = (Button) findViewById(R.id.loginBtn);
            visibleBtn = (ImageButton) findViewById(R.id.visible);
            visibleBtn.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        pass.setTypeface(Typeface.DEFAULT);
                    }
                    return false;
                }
            });

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!(uname.getText().toString().equals("") || pass.getText().toString().equals(""))) {
                        user = uname.getText().toString();
                        password = pass.getText().toString();
                        userType = ((RadioButton) findViewById(uType.getCheckedRadioButtonId())).getText().toString();
                        new Verify().execute((Void) null);
                    } else {
                        Toast.makeText(Main.this, "Username or Password cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    class Verify extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog =  new ProgressDialog(Main.this);
            progressDialog.setMessage("Verifying Credentials");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String url = getResources().getString(R.string.domain);
            if (userType.equalsIgnoreCase("Volunteer"))
                url +="vollogin.php";
            else
                url +="adminlogin.php";
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            try {
                ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("user", user));
                list.add(new BasicNameValuePair("pass", password));
                httpPost.setEntity(new UrlEncodedFormEntity(list));

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpClient.execute(httpPost, responseHandler);
                Log.d("Response",response);
                if (response.startsWith("User Found")) {
                    String[] u = response.split(", ");
                    Intent i;
                    if (userType.equalsIgnoreCase("Volunteer"))
                        i = new Intent(Main.this, VolunteerSchool.class);
                    else
                        i = new Intent(Main.this, Admin.class);
                    i.putExtra("user", u[1]);
                    dbHelper.addLogin(user,password,userType,u[1]);

                    startActivity(i);
                    finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Main.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } catch (Exception e) {
                Log.e("Error", e.toString());
                e.printStackTrace();
            }
            return null;
        }

    }

}


package yuvaunstoppable.evolution;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class Main extends AppCompatActivity {

    private Toolbar toolbar;
    private Button loginBtn;
    private ImageButton visibleBtn;
    private EditText uname, pass;
    private String user, password, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        uname = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pass);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            userType = currentUser.getString("type");
            Intent i;
            if (userType.equalsIgnoreCase("Volunteer"))
                i = new Intent(Main.this, Volunteer.class);
            else
                i = new Intent(Main.this, Admin.class);
            i.putExtra("user", currentUser.getString("name"));

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
                        final ProgressDialog progressDialog = new ProgressDialog(Main.this);
                        progressDialog.setMessage("Verifying Credentials");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        user = uname.getText().toString();
                        password = pass.getText().toString();
                        ParseUser.logInInBackground(user, password, new LogInCallback() {
                            @Override
                            public void done(ParseUser parseUser, ParseException e) {
                                progressDialog.dismiss();
                                if (parseUser == null) {
                                    Toast.makeText(Main.this, e.toString(), Toast.LENGTH_SHORT).show();
                                } else {
                                    userType = parseUser.getString("type");
                                    Intent i;
                                    if (userType.equalsIgnoreCase("Volunteer"))
                                        i = new Intent(Main.this, Volunteer.class);
                                    else
                                        i = new Intent(Main.this, Admin.class);

                                    i.putExtra("user", parseUser.getString("name"));
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });

                    } else {
                        Toast.makeText(Main.this, "User name or password cannot be empty", Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
    }

}


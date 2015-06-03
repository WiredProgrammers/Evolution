package yuvaunstoppable.evolution;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                && conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("You are not connected to internet.\nSome sections may not work as expected")
                    .setTitle("Warning")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            try {
                Parse.enableLocalDatastore(this);
                Parse.initialize(this, "qG587nkW2CIhPagmglJB5HwOOLzVaBDIzLcC8al5", "Jec5SFXy1YH1fd5Qi3MrRh42kKw82v30Jb7D9qva");
            } catch (Exception e) {

            }
            startActivity(new Intent(MainActivity.this, Main.class));
            finish();
        }
    }
}

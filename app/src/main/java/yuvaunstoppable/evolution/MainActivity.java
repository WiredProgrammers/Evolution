package yuvaunstoppable.evolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "qG587nkW2CIhPagmglJB5HwOOLzVaBDIzLcC8al5", "Jec5SFXy1YH1fd5Qi3MrRh42kKw82v30Jb7D9qva");
        }catch (Exception e){

        }
        startActivity(new Intent(MainActivity.this,Main.class));
        finish();
    }
}

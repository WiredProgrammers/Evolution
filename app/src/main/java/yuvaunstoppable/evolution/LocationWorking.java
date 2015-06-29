package yuvaunstoppable.evolution;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

/**
 * Created by amit on 29-Jun-15.
 */
public class LocationWorking extends Object {


        private static LocationManager locationManager;
//        private static LocationCallback locationCallback;


    public static Location getLocation(Context appContext) {
    locationManager = (LocationManager) appContext.getSystemService(Context.LOCATION_SERVICE);
    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location == null ){
            Toast.makeText(appContext,"Gps not activated",Toast.LENGTH_SHORT).show();
        }
    return  location;
    }



}

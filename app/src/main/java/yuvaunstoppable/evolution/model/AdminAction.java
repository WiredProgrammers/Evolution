package yuvaunstoppable.evolution.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Yash on 29-May-15.
 */
public class AdminAction {
    String action;
    Drawable image;
    public AdminAction(String action, Drawable image){
        this.action = action;
        this.image = image;
    }

    public String getAction(){return action;}

    public Drawable getImage(){return image;}
}

package yuvaunstoppable.evolution.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import yuvaunstoppable.evolution.R;

/**
 * Created by Yash on 29-May-15.
 */
public class AdminActionHolder extends RecyclerView.ViewHolder {

    public TextView actionText;
    public ImageView actionImage;
    public AdminActionHolder(View itemView) {
        super(itemView);
        actionText = (TextView) itemView.findViewById(R.id.acttxt);
        actionImage = (ImageView) actionImage.findViewById(R.id.actimg);
    }
}

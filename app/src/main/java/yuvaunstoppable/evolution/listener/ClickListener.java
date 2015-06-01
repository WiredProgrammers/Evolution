package yuvaunstoppable.evolution.listener;

import android.view.View;

/**
 * Created by Yash on 29-May-15.
 */
public interface ClickListener {
    public void onClick(View view, int position);

    public void onLongClick(View view, int position);
}
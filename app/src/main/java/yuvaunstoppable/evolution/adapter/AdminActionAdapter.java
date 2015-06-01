package yuvaunstoppable.evolution.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import yuvaunstoppable.evolution.R;
import yuvaunstoppable.evolution.model.AdminAction;
import yuvaunstoppable.evolution.viewholder.AdminActionHolder;

/**
 * Created by Yash on 29-May-15.
 */
public class AdminActionAdapter extends RecyclerView.Adapter<AdminActionHolder> {
    private LayoutInflater inflater;
    private List<AdminAction> adminActions = Collections.emptyList();
    public AdminActionAdapter(Context context){inflater = LayoutInflater.from(context);}

    @Override
    public AdminActionHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rowView = inflater.inflate(R.layout.adminactionview, viewGroup, false);
        return new AdminActionHolder(rowView);
    }

    @Override
    public void onBindViewHolder(AdminActionHolder adminActionHolder, int i) {
        AdminAction adminAction = adminActions.get(i);
        adminActionHolder.actionText.setText(adminAction.getAction());
        adminActionHolder.actionImage.setImageDrawable(adminAction.getImage());
    }

    @Override
    public int getItemCount() {
        return adminActions.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

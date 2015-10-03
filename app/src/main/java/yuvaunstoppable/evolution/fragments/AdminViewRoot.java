package yuvaunstoppable.evolution.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yuvaunstoppable.evolution.R;

/**
 * Created by yashtrivedi on 03/10/15.
 */
public class AdminViewRoot extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adminview_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        /*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        transaction.replace(R.id.root_frame, new AdminView2());

        transaction.commit();

        return view;
    }
}

package com.rba.ecommerce.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rba.ecommerce.R;

/**
 * Created by Ricardo Bravo on 02/07/16.
 */

public class TabFragment extends Fragment {

    private TextView lblText;
    private static final String CODE = "code";

    public TabFragment() {
    }

    public TabFragment newInstance(String  code){
        TabFragment tabFragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(CODE, code);
        tabFragment.setArguments(args);
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lblText = (TextView) view.findViewById(R.id.lblText);
        lblText.setText("fragment "+getArguments().getString(CODE));
    }

}

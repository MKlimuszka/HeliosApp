package com.example.mk.helios;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class pv_requirements extends Fragment {





    public static pv_requirements newInstance() {
        pv_requirements fragment = new pv_requirements();

        return fragment;
    }

    public pv_requirements() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pv_requirements, container, false);


        TextView textView = (TextView) view.findViewById(R.id.safetybullet1);
        String line1 = "IEC 61215 in the case of crystalline types";
        String line2 = "IEC 61646 in the case of thin film types";
        String line3 = "IEC 61730 - Photovoltaic (PV) module safety qualification";
        String line4 = "Modules must also carry a CE mark";
        String line5 = "Modules must be certificated and listed on the MCS product database. ";

        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));

        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing,line1,line2,line3,line4,line5);
        textView.setText(bulletedList);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }




}

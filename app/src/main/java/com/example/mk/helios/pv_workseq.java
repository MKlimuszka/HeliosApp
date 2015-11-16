package com.example.mk.helios;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mk.helios.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class pv_workseq extends Fragment {


    public pv_workseq() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pv_workseq, container, false);


        TextView textView = (TextView) view.findViewById(R.id.text);
        String line1 ="d.c. switch-disconnector and d.c. junction boxes";
        String line2 ="String/array positive and negative cables - from the d.c. disconnect/junction box to either end of the PV string/array";
        String line3 ="PV array main cables from d.c. switch to inverter";


        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3);
        textView.setText(bulletedList);
        return view;
    }


}

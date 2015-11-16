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
public class pv_blockingdiode extends Fragment {


    public pv_blockingdiode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pv_blockingdiode, container, false);


        TextView textView = (TextView) view.findViewById(R.id.bulletedlist1);
        String line1 ="The installation of a blocking diode results in a small voltage drop across the diode";
        String line2 ="Blocking diodes may fail as a short-circuit and therefore require regular testing. ";
        String line3 ="Blocking diodes should not be confused with bypass diodes, which are intended to allow current to bypass cells that have a high resistance (usually caused by shading).";

        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing,line1,line2,line3);
        textView.setText(bulletedList);

        return view;
    }


}

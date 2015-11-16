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
public class pv_roofing extends Fragment {


    public pv_roofing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pv_roofing, container, false);


        TextView textView = (TextView) view.findViewById(R.id.bulletlist);
        String line1 ="The bolt or flashing shall not transfer any load on the slates / tiles beneath. ";
        String line2 ="The system shall not rely on silicone or other mastic sealant to provide a weather-tight seal. ";
        String line3 ="The system must durably seal every layer of roof covering that is perforated by the bolt system. ";
        String line4 ="The system shall not rely on a sealing washer or plate that presses down on the slate/tile to ensure a weather tight seal.";
        String line5 ="The bolt fixings shall not be into battens .";


        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5);
        textView.setText(bulletedList);

        textView = (TextView) view.findViewById(R.id.bulletlist2);
        line1 ="Wind loads are higher in the edge zones";
        line2 ="Keeping edge zones clear facilitates better access for maintenance and fire services";
        line3 ="Taking arrays close to the roof edge may adversely affect rain drainage routes";
        line4 ="When retrofitting systems, there is the potential for damage to ridge, hip, valley or eaves details.";



        bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4);
        textView.setText(bulletedList);


        return view;
    }


}

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
public class pv_wind extends Fragment {


    public pv_wind() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pv_wind, container, false);


        TextView textView = (TextView) view.findViewById(R.id.text);
        String line1 ="For PV arrays that are mounted above, and parallel to, an inclined roof where there is a clear gap between the array and the roof - the pressure coefficients shall be taken from BRE digest 489 or from recognised test data commissioned for the specific purpose of determining the wind loads on solar systems.";
        String line2 ="For flat roof systems - the pressure coefficients shall be taken from BRE digest 489 or from recognised test data commissioned for the specific purpose of determining the wind loads on solar systems.";
        String line3 ="For roof integrated, nominally airtight systems - the pressure coefficients shall be taken from Eurocode-1.";
        String line4 ="For roof integrated, air permeable “PV tile” type systems - the pressure coefficients shall be taken from BS5534 and treating the PV array as roof tiles";


        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4);
        textView.setText(bulletedList);

        textView = (TextView) view.findViewById(R.id.bullettext);
        line1 ="For systems approved to MCS012 - ensuring that the imposed loads are within the range specified by the product manufacturer (and then installing according to the manufacturer’s instructions)";
        line2 ="Using fixing data from Eurocode 5 - design of timber structures";
        line3 ="Using fixing bracket test data";


        bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3);
        textView.setText(bulletedList);

        textView = (TextView) view.findViewById(R.id.bullettext3);
        line1 ="Signs of structural distress";
        line2 ="Signs of post construction modification (e.g. removal of timbers, notching, change of roof covering), ";
        line3 ="The roof pitch is particularly shallow (< 30°)";
        line4 ="The roof design has increased potential for snow build-up (e.g. dormers, valleys, parapets etc)";

        bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4);
        textView.setText(bulletedList);
        return view;
    }


}

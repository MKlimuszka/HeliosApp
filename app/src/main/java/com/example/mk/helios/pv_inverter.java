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
public class pv_inverter extends Fragment {


    public pv_inverter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pv_inverter, container, false);


        TextView textView = (TextView) view.findViewById(R.id.text);
        String line1 ="The inverters  available for use in the UK (not all manufacturers have G83 / G59)";
        String line2 ="Array voltage fluctuations due to operating temperature";
        String line3 ="The maximum permissible d.c. input voltage of the inverter";
        String line4 ="The MPP (maximum power point) voltage range of the inverter";
        String line5 ="The desired inverter – array power ratio";
        String line6 ="Inverter matching is to be done using the guidance from the inverter manufacturer – typically using the manufacturer’s system sizing software.  ";


        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5,line6);
        textView.setText(bulletedList);
        return view;
    }


}

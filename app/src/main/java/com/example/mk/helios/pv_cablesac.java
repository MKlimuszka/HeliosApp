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
public class pv_cablesac extends Fragment {


    public pv_cablesac() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pv_cablesac, container, false);


        TextView textView = (TextView) view.findViewById(R.id.text);
        String line1 ="No current-using equipment is connected";
        String line2 ="No provision is made for the connection of current-using equipment";
        String line3 ="No socket-outlets are permitted. ";


        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3);
        textView.setText(bulletedList);
        return view;
    }


}

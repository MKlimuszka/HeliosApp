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
public class st_bacteria extends Fragment {


    public st_bacteria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_st_bacteria, container, false);

        TextView textView = (TextView) view.findViewById(R.id.text);
        String line1 ="Reducing pre-heated storage to below twice the average daily hot water use.";
        String line2 ="Using indirect primary circuits.";
        String line3 ="Replacing old, poorly insulated DHW stores.";
        String line4 ="Using electronic primary pump controls with programming targeted to achieve 60ºC storage. ";
        String line5 ="Reducing long lengths of uninsulated secondary pipework.";

        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5);
        textView.setText(bulletedList);

        return view;
    }


}

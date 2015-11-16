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
public class st_labels extends Fragment {


    public st_labels() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_st_labels, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        String line1 ="Rating of each internal heat exchanger and description of type.";
        String line2 ="Maximum operating pressure of all primary heat exchangers.";
        String line3 ="Stand-by heat loss rate, with allowance for variable solar temperatures.";
        String line4 ="";
        String line5 ="";
        String line6 ="";
        String line7 ="";
        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3);
        textView.setText(bulletedList);
        return view;
    }


}

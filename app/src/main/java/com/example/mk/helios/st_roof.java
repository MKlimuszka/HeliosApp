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
public class st_roof extends Fragment {


    public st_roof() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_st_roof, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        String line1 ="Asbestos cement.";
        String line2 ="Built-up felt, supported by wood-wool boards or insulation.";
        String line3 ="Profiled metal supported on metal rafters.";
        String line4 ="Sheet metal (lead, copper, zinc).";
        String line5 ="Conservatory or greenhouse patent glazing.";
        String line6 ="Wooden shingles.";
        String line7 ="Single ply membrane.";
        String line8 ="Thatch.";
        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5,line6,line7,line8);
        textView.setText(bulletedList);
        return view;
    }


}

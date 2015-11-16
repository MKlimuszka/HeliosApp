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
public class st_lossesnight extends Fragment {


    public st_lossesnight() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_st_lossesnight, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        String line1 ="Warm ambient air heating the fluid in the pipes and causing thermosyphoning to the cooler collector.";
        String line2 ="DHW heated from auxiliary fuel sources entering the solar primary.";
        String line3 ="Solar heat, previously collected, re-entering the pre-heat store.";
        String line4 ="Cold-feed for the store entering the primary.";

        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4);
        textView.setText(bulletedList);

        return view;
    }


}

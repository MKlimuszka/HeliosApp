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
public class st_secsys extends Fragment {


    public st_secsys() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_st_secsys, container, false);
        TextView textView = (TextView) view.findViewById(R.id.bulletedList);
        String line1 ="Available water pressure";
        String line2 ="Existing DHW stores and DHW appliances";
        String line3 ="Water quality";
        String line4 ="Physical space available";
        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4);
        textView.setText(bulletedList);

        return view;
    }


}

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
public class st_types extends Fragment {


    public st_types() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_st_types, container, false);
        TextView textView = (TextView) view.findViewById(R.id.bulletlist);
        String line1 ="Air is displaced from the circuit by the transfer fluid present in the collector. ";
        String line2 ="Under normal conditions, the transfer fluid in the circuit would always be above atmospheric pressure.";
        String line3 ="The system may be open to the atmosphere or sealed. ";
        String line4 ="The transfer fluid is normally an antifreeze solution to ensure continued functioning of safety devices in all conditions.";
        String line5 ="Undesirable overnight heat loss is prevented by one-way check valves. ";
        String line6 ="Pipework can undulate provided air can be released at high points and fluid drained at low points.";
        String line7 ="Fluid expansion is contained by an expansion vessel with an internal, flexible membrane. ";


        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5,line6,line7);
        textView.setText(bulletedList);

        textView = (TextView) view.findViewById(R.id.bulletedlist1);
         line1 ="A reservoir of air only allows the transfer liquid to reach the collector under the action of a pump. ";
         line2 ="The transfer fluid may occasionally drop below atmospheric pressure. ";
         line3 ="The system may be open to the atmosphere or sealed. The transfer fluid may be an antifreeze solution to ensure continued functioning of safety devices in all conditions.";
         line4 ="Undesirable thermosyphon circulation is prevented by switching the pump off: this allows drainback of liquid from the collector. ";
         line5 ="Pipework must always fall towards the drainback vessel.";
         line6 ="Fluid expansion is contained by a vessel with an air pocket.";
         line7 ="";


        bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5,line6,line7);
        textView.setText(bulletedList);
        return view;
    }


}

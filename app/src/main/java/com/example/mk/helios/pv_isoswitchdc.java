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
public class pv_isoswitchdc extends Fragment {


    public pv_isoswitchdc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pv_isoswitchdc, container, false);


        TextView textView = (TextView) view.findViewById(R.id.text);
        String line1 ="The switch must isolate all live conductors (typically double pole to isolate PV array positive and negative conductors)";
        String line2 ="The switch must be rated for d.c. operation at the system voltage maximum as calculated";
        String line3 ="The switch must be rated for d.c. operation at the system current maximum as calculated ";
        String line4 ="The switch must be labelled as ‘PV array d.c. isolator’, with the ON and OFF positions clearly marked.  ";
        String line5 ="Switch enclosures should also be labelled with ‘Danger - contains live parts during daylight’. ";
        String line6 ="A circuit breaker may also be used provided it meets all the above requirements";

        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3, line4, line5, line6);
        textView.setText(bulletedList);

         textView = (TextView) view.findViewById(R.id.text2);
         line1 ="A physically separate switch-disconnecter mounted adjacent to the inverter";
         line2 ="A switch-disconnecter that is mechanically connected to the inverter – that allows the inverter to be removed from the section containing the switch-disconnecter without risk of electrical hazards";
         line3 ="A switch-disconnecter integral to the inverter, if the inverter includes a means of isolation only operable when the switch-disconnecter is in the open position (e.g. plugs only accessible once the switch disconnector  handle is removed)";
         line4 ="A switch-disconnecter integral to the inverter, if the inverter includes a means of isolation (e.g. plugs) which can only be operated with a tool and is labelled with a readily visible warning sign or text indicating (\"Do not disconnect under load\")";


        bulletedList = BulletTextUtil.makeBulletList(spacing,line1,line2,line3,line4);
        textView.setText(bulletedList);

        textView = (TextView) view.findViewById(R.id.text3);
        line1 ="Connects only one module per inverter";
        line2 ="Does not require the extension of the PV module d.c. cables beyond the length of the original factory fitted cables";
        line3 ="Does not exceed the voltages within the band of ELV (Not exceeding 50 V a.c. or 120 V ripple-free d.c. whether between conductors or to Earth.)";
        line4 ="Has a plug and socket type connector arrangement for the d.c. Cables. If it is decided to omit the switch disconnecter this shall be recorded as a departure on the Electrical installation certificate.";

        bulletedList = BulletTextUtil.makeBulletList(spacing,line1,line2,line3,line4);
        textView.setText(bulletedList);


        return view;

    }


}

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
public class st_docmaint extends Fragment {


    public st_docmaint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_st_docmaint, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text1);
        String line1 ="Full system installation instructions.";
        String line2 ="User guide.";
        String line3 ="Maintenance schedule.";
        String line4 ="Decommissioning schedule.";
        String line5 ="Schematic diagram.";
        String line6 ="Commissioning certificate.";
        String line7 ="Commissioning engineer’s contact details.";
        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));
        CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5,line6,line7);
        textView.setText(bulletedList);

        textView = (TextView) view.findViewById(R.id.text2);
        line1 ="Collector glazing is undamaged.";
        line2 ="Collector glazing is reasonably clean.";
        line3 ="Where visible, absorber paintwork or coating is sound.";
        line4 ="The roof fixings are firm and the roof covering satisfactory by visual inspection.";
        line5 ="Fluid levels in the cistern, vessel or pressure gauge checked against commissioned tolerances.";
        line6 ="Electrical controls and temperature sensors are operating sensibly.";
        line7 ="The circulating pump is operating without due noise.";
        String line8 ="Pipework insulation is firmly in place.";
        String line9 ="There are no condensation or damp spots, particularly around the pipework and fixings in the roof space.";
        String line10 ="All safety and information labels are in place.";
        String line11 ="he antifreeze should be tested at least every five years (depending on the type chosen). Some antifreeze products also require regular replacement. Regular de-scaling may be required for the heat exchanger surfaces.";

        bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5,line6,line7,line8,line9,line10,line11);
        textView.setText(bulletedList);

        textView = (TextView) view.findViewById(R.id.text3);
        line1 ="Overheating may have occurred during a period of hot weather if the circulation unintentionally failed or the system was incorrectly designed. When thermal energy is not being removed from the collector, the water temperature will rise; water volume and system pressure could increase beyond safety device limits. In some types of system, this could result in the release of hot water or steam from the pressure relief valve or the automatic/manual vent. When the system returns to normal operating conditions, the pressure will reduce due to the loss in fluid volume.";
        line2 ="If there is a leak in the system, it may require a drain down and repair/replacement of the faulty component(s).";

        bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5,line6,line7);
        textView.setText(bulletedList);

        textView = (TextView) view.findViewById(R.id.text4);
        line1 ="Frost damage to the collector due to degradation of antifreeze.";
        line2 ="Temperature sensors displaced from the correct position.";
        line3 ="Circulating pump seizure.";
        line4 ="Loss of fluid due to open vent evaporation, or slow leak often through automatic air vent.";
        line5 ="Sealed system expansion vessel has lost pre-charge.";
        line6 ="Residue from overheated antifreeze blocking pipes.";
        line7 ="Limescale blocking the collector, pipes or heat exchanger.";
        bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5,line6,line7);
        textView.setText(bulletedList);

        textView = (TextView) view.findViewById(R.id.text5);
        line1 ="Temperature differential between tank and panel wrongly set within solar controller.";
        line2 ="Pump control missing or malfunctioning.";
        line3 ="Another heat appliance interfering with transfer in DHW store.";
        line4 ="Missing or damaged insulation of pipes  and store.";
        line5 ="Incorrect location of temperature sensors.";
        line6 ="Inadequate air removal from pipes.";
        line7 ="Incorrect pump speed.";
        bulletedList = BulletTextUtil.makeBulletList(spacing, line1, line2, line3,line4,line5,line6,line7);
        textView.setText(bulletedList);

        return view;

    }


}

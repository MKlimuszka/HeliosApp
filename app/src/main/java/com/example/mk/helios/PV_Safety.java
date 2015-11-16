package com.example.mk.helios;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mk.helios.R;


public class PV_Safety extends Fragment {





    public static PV_Safety newInstance() {
        PV_Safety fragment = new PV_Safety();

        return fragment;
    }

    public PV_Safety() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pv__safety, container, false);


    TextView textView = (TextView) view.findViewById(R.id.safetybullet1);
        String line1 = "The supply from PV modules cannot be switched off.  A temporary warning sign and barrier must be posted during installation  See requirements of Regulation 14 of Electricity at Work Regulations 1989.";
        String line2 = "PV modules are current-limiting devices, so fuses are not likely to operate under short-circuit conditions.";
        String line3 = "PV systems include d.c. wiring, with which few electrical installers are familiar. ";
        String line4 = "PPE (Personal Protective Equipment), working at height, manual handling, handling glass and CDM regulations.";

        Integer spacing = Math.round(getResources().getDimension(R.dimen.bulletspacing));

    CharSequence bulletedList = BulletTextUtil.makeBulletList(spacing,line1,line2,line3,line4);
    textView.setText(bulletedList);
    return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }




}

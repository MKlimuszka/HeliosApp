package com.example.mk.helios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Matt on 8/31/2015.
 */



public class tilt_orient_info extends DialogFragment {
    private String message =
            "Stand facing the installation site of the panels so that they would be facing you. Hold your device level with the arrow pointing toward the panel location. " +
                    "Tap the compass to lock measurement. Angle between panel orientation and magnetic south is reported. \n\nAlign phone with installation site and tap bottom half of screen to measure slope. " +
                    "\n\nBefore using this utility, please calibrate the sensors.";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Calibrate", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogFragment newFragment = new calibration_info();
                        newFragment.show(getFragmentManager(), "calibration_dialog");
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

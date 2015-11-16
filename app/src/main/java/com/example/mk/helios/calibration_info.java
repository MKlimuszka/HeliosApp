package com.example.mk.helios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Matt on 8/31/2015.
 */



public class calibration_info extends DialogFragment {
    private String message =
            "To calibrate the sensors, hold your device in the palm of your hand and slowly wave it in a figure-8 pattern with your wrist." +
                    "\n\nAlternatively, rotate the device completely around each of its three axes of rotation: pointing upward, pointing forward, and pointing to your right.";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
               ;
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

package com.example.mk.helios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Matt on 8/31/2015.
 */



public class production_info extends DialogFragment {
    private String message =
            "The MCS standard method of photovoltaic production estimation uses the peak wattage of the array (Wp), location, slope of the panels, " +
                    "angle between the direction the panels are facing and south (orientation), and the shade factor (See PV Reference). These values" +
                    " correspond to a specific production ratio between Wp and annual production.";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

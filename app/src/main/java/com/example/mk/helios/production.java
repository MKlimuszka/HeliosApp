package com.example.mk.helios;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;







public class production extends Fragment {





    public static production newInstance(int mparam1, int mparam2, String mparam3) {
        production fragment = new production();
        return fragment;
    }

    public production() {
        // Required empty public constructor
    }


    private Cursor c;
    private int orientationdefault;
    private int pitchdefault;
    private Integer kK = null;
    private String projectname = "Default";
    private String pitch_orient;
    private Double optimum_Production_orient;
    private String pitch_overall;
    private Double optimum_Production_overall;
    private int pitch;
    private int orientation;
    private double Production;
    private double shadefactor;
    private String postregion;
    private int Wp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = new Bundle();
        mBundle = getArguments();
        orientationdefault= mBundle.getInt("mparam1");
        pitchdefault= mBundle.getInt("mparam2");
        projectname =  mBundle.getString("mparam3");
        if (orientationdefault >90){
            orientationdefault = 90;
        }

    }

    private void shareIt() {
//sharing implementation here
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "optimum_slope,production_with_optimum_slope,optimum_slope_for_south_facing_panel,max_production,slope_chosen,orientation_chosen,production_chosen,shade_factor,zone,kK_ratio,peak_watts" + "\r\n"
                +pitch_orient +","
                +optimum_Production_orient +","
                +pitch_overall +","
                +optimum_Production_overall +","
                +pitch +","
                +orientation +","
                +Production +","
                +shadefactor +","
                +postregion +","
                +kK +","
                + Wp
                ;

        String timeStamp = new SimpleDateFormat("yyyy_MMdd_HHmmss").format(new Date());
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, projectname+"_" +timeStamp+".csv");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private class docalculation extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            // your background code here. Don't touch any UI components
             mDb = new RegionDatabase(getActivity());
             db = mDb.getReadableDatabase();

            //Assign field values to variables.





            //assign actual values
            orientation = orientaationsel * 5;
            shadefactor = shadesel / 100.0;
            postregion = "1";



            String[] columns = {"Region"};
            String Selection = "_id=?";
            String[] SelectionArgs = {"" + postcodesel};

            Cursor cursor = db.query("Region_Key", columns, Selection, SelectionArgs, null, null, null, null);
            cursor.moveToNext();
            postregion = cursor.getString(cursor.getColumnIndexOrThrow("Region"));


            String[] columnsx = {String.format("\"%s\"", orientation), "Slope"};
            Selection = "Slope=?";
            String[] SelectionArgsx = {"" + pitch};



            cursor = db.query(String.format("\"%s\"", postregion), columnsx, Selection, SelectionArgsx, null, null, null, null);
            cursor.moveToNext();
            ratio = cursor.getString(cursor.getColumnIndexOrThrow("" + orientation));
            kK = Integer.parseInt(ratio);
            // now ratio contains a string,  pitch and orientation contain the required integers, and shadefactor contains a double.

            //Find optimum values based on location, pitch, and/or orientation.

            // optimum orientation will always be 0 degrees. So this will represent the overall optimum for the location
            String[] optimumcolumn = {"Slope", String.format("\"%s\"", 0)};
            //return all ratios and their slopes for south orientation in descending order
            cursor = db.query(String.format("\"%s\"", postregion), optimumcolumn, null, null, null, null, String.format("\"%s\"", 0) + " DESC", null);
            cursor.moveToNext();
            String optimumratio_overall = cursor.getString(cursor.getColumnIndexOrThrow("" + 0)); //get highest ratio
            //Return entries with this highest ratio
            Selection = String.format("\"%s\"", 0) + "=?";
            String[] SelectionArgsz = {"" + optimumratio_overall};
            cursor = db.query(String.format("\"%s\"", postregion), optimumcolumn, Selection, SelectionArgsz, null, null, null, null);
            //Store these pitches in an array
            List<String> pitcharray_overall = new ArrayList<String>();
            while (cursor.moveToNext()) {
                String temp2 = cursor.getString(cursor.getColumnIndexOrThrow("Slope"));
                pitcharray_overall.add(temp2);
            }

            if (pitcharray_overall.size() == 1) {
                pitch_overall = "" + pitcharray_overall.get(0);
            } else {
                pitch_overall = "" + pitcharray_overall.get(0) + "-" + pitcharray_overall.get(pitcharray_overall.size() - 1);
            }


            Integer optimum_kK_overall = Integer.parseInt(optimumratio_overall);
            optimum_Production_overall = optimum_kK_overall * shadefactor * Wp / 1000; //Gives answer in kWh

            // Find optimum pitch and ratio based on current orientation and location
            //return all ratios and their slopes for set orientation in descending order
            cursor = db.query(String.format("\"%s\"", postregion), columnsx, null, null, null, null, String.format("\"%s\"", orientation) + " DESC", null);
            cursor.moveToNext();
            String optimumratio_orient = cursor.getString(cursor.getColumnIndexOrThrow("" + orientation)); //get highest ratio
            //Return entries with this highest ratio
            Selection = String.format("\"%s\"", orientation) + "=?";
            String[] SelectionArgsa = {"" + optimumratio_orient};
            cursor = db.query(String.format("\"%s\"", postregion), columnsx, Selection, SelectionArgsa, null, null, null, null);
            //Store these pitches in an array
            List<String> pitcharray_orient = new ArrayList<String>();
            while (cursor.moveToNext()) {
                String temp3 = cursor.getString(cursor.getColumnIndexOrThrow("Slope"));
                pitcharray_orient.add(temp3);
            }

            if (pitcharray_orient.size() == 1) {
                pitch_orient = "" + pitcharray_orient.get(0);
            } else {
                pitch_orient = "" + pitcharray_orient.get(0) + "-" + pitcharray_overall.get(pitcharray_overall.size() - 1);
            }

            Integer optimum_kK_orient = Integer.parseInt(optimumratio_orient);
            optimum_Production_orient = optimum_kK_orient * shadefactor * Wp / 1000; //Gives answer in kWh
            db.close();
            cursor.close();
            mDb.close();
            return true;

        }

        protected void onPostExecute(Boolean result) {
            //This is run on the UI thread so you can do as you wish here
            if(result){
                //Show optimum info.
                String optimum_info = "Using the optimum slope (" + pitch_orient + "\u00B0), this would be " + String.format("%.2f", optimum_Production_orient) + " kWh.";

                String optimum_info_overall;
                if (orientation == 0) {
                    optimum_info_overall = "";
                } else {
                    optimum_info_overall = " The maximum possible production is " + String.format("%.2f", optimum_Production_overall) + " kWh, where slope is " + pitch_overall + "\u00B0 and orientation is 0\u00B0";
                }

                TextView optimumview = (TextView) getView().findViewById(R.id.textoptimuminfo);
                optimumview.setText(optimum_info + optimum_info_overall);
                optimumview.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                optimumview.setTextSize(15);

                Production = kK * shadefactor * Wp / 1000; //Gives answer in kWh

                TextView productionview = (TextView) getView().findViewById(R.id.viewproduction);
                productionview.setText(String.format("%.2f", Production));


                mCallback.onSaveDataClicked(pitch_orient, optimum_Production_orient, pitch_overall, optimum_Production_overall, pitch, orientation, Production, shadefactor, postregion, kK, Wp);
                // optimum slope, optimum production with that slope, optimum slope for south facing panel, highest possible production, slope chosen, orientation chosen, production calculated, shading factor, location, ratio, Wp


            }

            else{}

        }
    }
    private RegionDatabase mDb;
    private SQLiteDatabase db;


    private Spinner pitchspinner;
    private Spinner orientationspinner;
    private Spinner shadespinner;
    private Spinner postcodespinner;
    private String ratio;
    private Integer orientaationsel;
    private Integer shadesel;
    private Integer postcodesel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_production, container, false);
        setHasOptionsMenu(true);


        pitchspinner = (Spinner) view.findViewById(R.id.pitchspinner);
        orientationspinner = (Spinner) view.findViewById(R.id.orientationspinner);
        shadespinner = (Spinner) view.findViewById(R.id.shadespinner);
        postcodespinner = (Spinner) view.findViewById(R.id.postcodespinner);

        Button calcbutton = (Button) view.findViewById(R.id.calculatebutton);
        shadespinner.setSelection(100);
        pitchspinner.setSelection(pitchdefault);
        orientationspinner.setSelection(orientationdefault / 5);

        calcbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                EditText temp = (EditText) getView().findViewById(R.id.kwpedittext);
                Wp = Integer.parseInt((temp.getText().toString().replaceAll("[\\D]", "")));



                pitch = pitchspinner.getSelectedItemPosition();
                orientaationsel = orientationspinner.getSelectedItemPosition();
                shadesel = shadespinner.getSelectedItemPosition();
                postcodesel = postcodespinner.getSelectedItemPosition();


                docalculation thing = new docalculation();
                thing.execute();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


            }
        });


        return view;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu items for use in the action bar
        inflater.inflate(R.menu.production_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    backwarninglistener mbackcallback;
    public interface backwarninglistener{
        void setbackwarning (boolean warning);
    }

    @Override
    public void onResume() {
        mbackcallback.setbackwarning(true);
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_share:
                if (!(kK == null)){
                    shareIt();
                }
                else {
                    Toast.makeText(getActivity(), "Please calculate production first.", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.action_info:
                DialogFragment newFragment = new production_info();
                newFragment.show(getFragmentManager(), "production_info_dialog");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (SaveDataListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SaveDataListener");
        }
        try {
            mbackcallback = (backwarninglistener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement backwarninglistener");
        }
    }

    SaveDataListener mCallback;
    public interface SaveDataListener{
        void onSaveDataClicked(String pitch_orient,Double optimum_Production_orient,String pitch_overall,Double optimum_Production_overall,int pitch, int orientation, double Production, double shadefactor, String postregion, int kK, int Wp);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}

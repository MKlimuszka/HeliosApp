package com.example.mk.helios;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        production.SaveDataListener, production.backwarninglistener,
        st1_main.OnFragmentInteractionListener, st1_main.OnSTSelectedListener, st1_main.backwarninglistener,
        pv1_main.OnPVSelectedListener, pv1_main.OnFragmentInteractionListener, pv1_main.backwarninglistener,
        tilt_orient.CompassPitchListener, tilt_orient.CompassOrientationListener, tilt_orient.backwarninglistener,
        project_title.SaveTitleListener, project_title.SaveNotesListener,project_title.backwarninglistener,
        Welcome.backwarninglistener

{

    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        setContentView(R.layout.activity_main);
        getSupportActionBar().show();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        // Welcome Screen as active
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new Welcome())
                .commit();
    }


    public int orientationdefault = 0;
    public int pitchdefault = 25;
    public String projectname = "Default";
    public String projectnotes = "";
    public boolean backwarning = false;

    public void onOrientationClicked (int orientation){
    orientationdefault = orientation;
    }

    public void onSaveTitleClicked(String title){
    projectname = title;
    }




    public class savenotedata extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // your background code here. Don't touch any UI components
            File folder = new  File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+ "/Helios");

            // Create the storage directory if it does not exist
            if (! folder.exists()){
                if (! folder.mkdirs()){
                    Log.d("Helios", "failed to create directory");
                }
            }
            String timeStamp = new SimpleDateFormat("yyyy_MMdd_HHmmss").format(new Date());
            filename = folder.toString() + "/" +projectname+"_" +timeStamp + "_notes.txt";
            try {

                FileWriter fw = new FileWriter(filename);

                fw.append(projectnotes);

                fw.close();
                worked = true;

            } catch (IOException e){e.printStackTrace(); worked = false;}
            if (worked) //if code worked
                return true;
            else {
            }
            return false;
        }
        protected void onPostExecute(Boolean worked) {
            //This is run on the UI thread so you can do as you wish here
            if (worked) {
                Toast.makeText(getApplicationContext(), "Data Saved to " + filename, Toast.LENGTH_LONG).show();
            }            else {
                Toast.makeText(getApplicationContext(), "Save Failed", Toast.LENGTH_LONG).show();
            }
        }
    }



    public void onNoteClicked(String notes){
        projectnotes = notes;
        savenotedata save = new savenotedata();
        save.execute();

    }

    public String optimum_slope;
    public Double production_optimum_slope;
    public String slope_south;
    public Double production_south;
    public int chosen_pitch;
    public int chosen_orientation;
    public double production_chosen;
    public double SF;
    public String postcode_region;
    public int kK_ratio;
    public int peak_watts;
    public String filename;
    public boolean worked;


    public class saveproddata extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            // your background code here. Don't touch any UI components

            File folder = new  File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+ "/Helios");

            // Create the storage directory if it does not exist
            if (! folder.exists()){
                if (! folder.mkdirs()){
                    Log.d("Helios", "failed to create directory");
                }
            }


            String timeStamp = new SimpleDateFormat("yyyy_MMdd_HHmmss").format(new Date());
           filename = folder.toString() + "/" +projectname+"_" +timeStamp + ".csv";


            try {

                FileWriter fw = new FileWriter(filename);

                fw.append("optimum_slope");                                fw.append(',');
                fw.append("production_with_optimum_slope");                fw.append(',');
                fw.append("optimum_slope_for_south_facing_panel");         fw.append(',');
                fw.append("max_production");                               fw.append(',');
                fw.append("slope_chosen");                                 fw.append(',');
                fw.append("orientation_chosen");                           fw.append(',');
                fw.append("production_chosen");                            fw.append(',');
                fw.append("shade_factor");                                 fw.append(',');
                fw.append("zone");                                         fw.append(',');
                fw.append("kK_ratio");                                     fw.append(',');
                fw.append("peak_watts");                                   fw.append("\r\n");
                fw.append(""+ optimum_slope);                              fw.append(',');
                fw.append(""+ production_optimum_slope);                   fw.append(',');
                fw.append(""+ slope_south);                                fw.append(',');
                fw.append(""+ production_south);                           fw.append(',');
                fw.append(""+ chosen_pitch);
                fw.append(',');
                fw.append(""+ chosen_orientation);                         fw.append(',');
                fw.append(""+ production_chosen);                          fw.append(',');
                fw.append(""+ SF);                                         fw.append(',');
                fw.append(""+ postcode_region);                            fw.append(',');
                fw.append(""+ kK_ratio);                                   fw.append(',');
                fw.append("" + peak_watts);

                fw.close();
                worked = true;

            } catch (IOException e){e.printStackTrace();   worked = false; }

            if (worked) //if code worked
                return true;
            else {
            }
            return false;
        }

        protected void onPostExecute(Boolean worked) {
            //This is run on the UI thread so you can do as you wish here
            if (worked) {
                Toast.makeText(getApplicationContext(), "Data Saved to " + filename, Toast.LENGTH_LONG).show();
            }            else {
                Toast.makeText(getApplicationContext(), "Save Failed", Toast.LENGTH_LONG).show();
            }

        }

    }


    public void onSaveDataClicked(String pitch_orient,Double optimum_Production_orient,String pitch_overall,Double optimum_Production_overall,int pitch, int orientation, double Production, double shadefactor, String postregion, int kK, int Wp){
         optimum_slope = pitch_orient;
         production_optimum_slope = optimum_Production_orient;
         slope_south = pitch_overall;
         production_south = optimum_Production_overall;
         chosen_pitch = pitch;
         chosen_orientation = orientation;
         production_chosen = Production;
         SF = shadefactor;
         postcode_region = postregion;
         kK_ratio = kK;
         peak_watts = Wp;
        // optimum slope, optimum production with that slope, optimum slope for south facing panel, highest possible production, slope chosen, orientation chosen, production calculated, shading factor, location, ratio, Wp

        // ADD SAVE DATA FUNCTION HERE
        saveproddata save = new saveproddata();
        save.execute();



    }





    public void onPitchClicked (int pitch){
        pitchdefault = pitch;
    }

    public void setbackwarning (boolean warning) {backwarning = warning;}


    public void onPVSelected(int position) {
        Fragment  fragment = new Welcome();
        FragmentManager fragmentManager = getFragmentManager();
        switch(position) {
            default:
                fragment = new Welcome();
                break;
            case 0:
                fragment = new pv_about();
                break;
            case 1:
                fragment = new pv_glossary();
                break;
            case 2:
                fragment = new pv_blockingdiode();
                break;
            case 3:
                fragment = new pv_cablesac ();
                break;
            case 4:
                fragment = new pv_dccables();
                break;
            case 5:
            fragment = new pv_current_voltage();
            break;
            case 6:
            fragment = new pv_earthingac();
            break;
            case 7:
            fragment = new pv_earthingdc();
            break;
            case 8:
            fragment = new pv_inverter();
            break;
            case 9:
            fragment = new pv_aciso();
            break;
            case 10:
            fragment = new pv_isoswitchdc();
            break;
            case 11:
            fragment = new pv_junctiondc();
            break;
            case 12:
            fragment = new pv_lightning();
            break;
            case 13:
            fragment = new pv_meters();
            break;
            case 14:
            fragment = new pv_requirements();
            break;
            case 15:
            fragment = new pv_rcd();
            break;
            case 16:
            fragment = new pv_roofing();
            break;
            case 17:
            fragment = new PV_Safety();
            break;
            case 18:
            fragment = new pv_workseq();
            break;
            case 19:
            fragment = new pv_shade();
            break;
            case 20:
            fragment = new pv_stringfuses();
            break;
            case 21:
            fragment = new pv_surge();
            break;
            case 22:
            fragment = new pv_temp();
            break;
            case 23:
                fragment = new pv_PEB();
                break;
            case 24:
                fragment = new pv_wind();
                break;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("param1", position);
        fragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }


    public void onSTSelected(int position) {
        Fragment  fragment = new Welcome();
        FragmentManager fragmentManager = getFragmentManager();
        switch(position) {
            default:
                fragment = new Welcome();
                break;
            case 0:
                fragment = new st_about();
                break;
            case 1:
                fragment = new st_auxheat();
                break;
            case 2:
                fragment = new st_access();
                break;
            case 3:
                fragment = new st_collector();
                break;
            case 4:
                fragment = new st_combined();
                break;
            case 5:
                fragment = new st_commission();
                break;
            case 6:
                fragment = new st_sensors();
                break;
            case 7:
                fragment = new st_heatx();
                break;
            case 8:
                fragment = new st_insulation();
                break;
            case 9:
                fragment = new st_isolation();
                break;
            case 10:
                fragment = new st_losses();
                break;
            case 11:
                fragment = new st_lossesnight();
                break;
            case 12:
                fragment = new st_docmaint();
                break;
            case 13:
                fragment = new st_occupantregime();
                break;
            case 14:
                fragment = new st_overp();
                break;
            case 15:
                fragment = new st_pitch();
                break;
            case 16:
                fragment = new st_preheat();
                break;
            case 17:
                fragment = new st_preheatloco();
                break;
            case 18:
                fragment = new st_bacteria();
                break;
            case 19:
                fragment = new st_limescale();
                break;
            case 20:
                fragment = new st_scald();
                break;
            case 21:
                fragment = new st_roof();
                break;
            case 22:
                fragment = new st_secsys();
                break;
            case 23:
                fragment = new st_waterp();
                break;
            case 24:
                fragment = new st_sepstores();
                break;
            case 25:
                fragment = new st_shading();
                break;
            case 26:
                fragment = new st_labels();
                break;
            case 27:
                fragment = new st_types();
                break;
            case 28:
                fragment = new st_tandp();
                break;
            case 29:
                fragment = new st_testinginit();
                break;
            case 30:
                fragment = new st_testingperiodic();
                break;
            case 31:
                fragment = new st_tools();
                break;
            case 32:
                fragment = new st_fluid();
                break;
            case 33:
                fragment = new st_wquality();
                break;

        }

        Bundle bundle = new Bundle();
        bundle.putInt("param1", position );
        fragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public final static String EXTRA_MESSAGE = "com.helios.MESSAGE";

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new Welcome();
        FragmentManager fragmentManager = getFragmentManager(); // For AppCompat use getSupportFragmentManager
        switch(position) {
            default:
                fragment = new Welcome();

                break;
            case 0:
                fragment = new pv1_main();

                break;
            case 1:
                fragment = new st1_main();

                break;
            case 2:
                fragment = new project_title();
                Bundle bundleproj = new Bundle();
                bundleproj.putString("mparam1", projectname);
                bundleproj.putString("mparam2", projectnotes);
                fragment.setArguments(bundleproj);

                break;
            case 3:
                fragment = new tilt_orient();

                break;
            case 4:
                fragment = new production();
                Bundle bundle = new Bundle();
                bundle.putInt("mparam1", orientationdefault);
                bundle.putInt("mparam2", pitchdefault);
                bundle.putString("mparam3", projectname);
                fragment.setArguments(bundle);

                break;
            case 5:
                Intent intent = new Intent(this,camera_activity.class);
                intent.putExtra(EXTRA_MESSAGE, projectname);
                startActivity(intent);

                break;
            case 6:
                Intent intent2 = new Intent(this,shade.class);
                intent2.putExtra(EXTRA_MESSAGE, projectname);
                startActivity(intent2);

                break;

        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }





    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(String id) {    }


    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else if (backwarning){
            DialogFragment newFragment = new backwarning();
            newFragment.show(getFragmentManager(), "backwarning_dialog");
        } else {
            getFragmentManager().popBackStack();
        }

    }
}


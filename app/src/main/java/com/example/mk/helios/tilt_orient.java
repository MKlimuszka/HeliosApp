package com.example.mk.helios;


import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * http://www.techrepublic.com/article/pro-tip-create-your-own-magnetic-compass-using-androids-internal-sensors/
 */
public class tilt_orient extends Fragment  implements SensorEventListener {

    CompassOrientationListener mOrientationCallback;
    public interface CompassOrientationListener{
        void onOrientationClicked(int orientation);
    }

    CompassPitchListener mPitchCallback;
    public interface CompassPitchListener{
        void onPitchClicked(int orientation);
    }


    public tilt_orient() {
        // Required empty public constructor
    }


    backwarninglistener mbackcallback;
    public interface backwarninglistener{
        void setbackwarning (boolean warning);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_tilt_orient, container, false);
        setHasOptionsMenu(true);
        mPointer = (ImageView) view.findViewById(R.id.pointer);
        degreebox = (TextView) view.findViewById(R.id.degreetextview);
        inclinationview = (TextView) view.findViewById(R.id.inclination);

        pitchbutton = view.findViewById(R.id.pitchview);
        pitchbutton.setClickable(true);
        orientationbutton = view.findViewById(R.id.compassview);
        orientationbutton.setClickable(true);
        pitchbutton.setOnClickListener(pitchhandler);
        orientationbutton.setOnClickListener(orientationhandler);


        return view;
    }

    View.OnClickListener pitchhandler = new View.OnClickListener() {
        public void onClick(View view) {
            // it was the 1st button
            pitchchange = !pitchchange;
            if (pitchchange) {
                inclinationview.setBackgroundColor(getResources().getColor(R.color.transparent));
                inclinationview.setTextColor(getResources().getColor(R.color.black));

            }
            if(!pitchchange) {
                inclinationview.setBackgroundColor(getResources().getColor(R.color.appgreen));
                inclinationview.setTextColor(getResources().getColor(R.color.white));
                mPitchCallback.onPitchClicked(inclination);
            }
        }
    };

    View.OnClickListener orientationhandler = new View.OnClickListener() {
        public void onClick(View view) {
            // it was the other button
           orientationchange = !orientationchange;
            if (orientationchange) {
                degreebox.setBackgroundColor(getResources().getColor(R.color.transparent));
                degreebox.setTextColor(getResources().getColor(R.color.black));
            }
            else {
                degreebox.setBackgroundColor(getResources().getColor(R.color.appgreen));
                degreebox.setTextColor(getResources().getColor(R.color.white));
                mOrientationCallback.onOrientationClicked(Math.round(orientation));
            }

        }
    };


    private TextView degreebox;
    private ImageView mPointer;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagnetometer;
    private float[] mLastAccelerometer = new float[3];
    private float[] mLastMagnetometer = new float[3];
    private boolean mLastAccelerometerSet = false;
    private boolean mLastMagnetometerSet = false;
    private float[] mR = new float[9];
    private float[] mOrientation = new float[3];
    private float mCurrentDegree = 0f;
    private TextView inclinationview;
    private Boolean pitchchange = true;
    private Boolean orientationchange = true;
    private View pitchbutton;
    private View orientationbutton;
    float orientation;
    int inclination;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager)getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mAccelerometer == null){
            Toast.makeText(getActivity(), "This device cannot measure tilt." , Toast.LENGTH_LONG).show();
        }
        if (mMagnetometer == null){
            Toast.makeText(getActivity(), "This device cannot measure orientation." , Toast.LENGTH_LONG).show();
        }
    }


    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        pitchchange = true;
        orientationchange = true;
        degreebox.setBackgroundColor(getResources().getColor(R.color.transparent));
        degreebox.setTextColor(getResources().getColor(R.color.black));
        inclinationview.setBackgroundColor(getResources().getColor(R.color.transparent));
        inclinationview.setTextColor(getResources().getColor(R.color.black));
        mbackcallback.setbackwarning(false);



    }

    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mAccelerometer);
        mSensorManager.unregisterListener(this, mMagnetometer);
        pitchchange = true;
        orientationchange = true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor == mAccelerometer) {
            System.arraycopy(event.values, 0, mLastAccelerometer, 0, event.values.length);
            mLastAccelerometerSet = true;

            if (pitchchange) { //update pitch if measurement is not locked
                float[] g ;
                g = event.values.clone();

                float norm_Of_g = (float) Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]);

                // Normalize the accelerometer vector
                g[0] = g[0] / norm_Of_g;
                g[1] = g[1] / norm_Of_g;
                g[2] = g[2] / norm_Of_g;

                //
                inclination = (int) Math.round(Math.toDegrees(Math.asin(g[1])));

                inclinationview.setText(inclination + "°");
            }

        } else if (event.sensor == mMagnetometer) {
            System.arraycopy(event.values, 0, mLastMagnetometer, 0, event.values.length);
            mLastMagnetometerSet = true;
        }


        if (mLastAccelerometerSet && mLastMagnetometerSet) { //do stuff with compass

          if (orientationchange) { //Update compass if measurement is not locked
              // Put rotation matrix into variable mR using the most recent accelerometer and magnetometer readings
              SensorManager.getRotationMatrix(mR, null, mLastAccelerometer, mLastMagnetometer);
              //Use rotation matrix to get the orientation
              SensorManager.getOrientation(mR, mOrientation);
              //by definition, the first value of the orientation array is the azimuth in radians
              float azimuthInRadians = mOrientation[0];
                // normally, azimuth includes directionality of azimuth, but we don't care about that
                //So add 360 and then divide with remainder to get positive azimuth
              float azimuthInDegress = (float) (Math.toDegrees(azimuthInRadians) +360) % 360;
                //A precision of 5 degrees is required for MCS methodology
              float azimuthInDegressRounded = Math.round(azimuthInDegress / 5) * 5;

              //Convert azimuth (degrees from North) to degrees from south
              if (azimuthInDegressRounded <= 180) {
                  orientation = azimuthInDegressRounded;
              } else {
                  orientation = 360 - azimuthInDegressRounded;
              }


              degreebox.setText(String.format("%.0f", orientation) + "°");
              RotateAnimation ra = new RotateAnimation(
                      mCurrentDegree,
                      -azimuthInDegress,
                      Animation.RELATIVE_TO_SELF, 0.5f,
                      Animation.RELATIVE_TO_SELF,
                      0.5f);

              ra.setDuration(250);

              ra.setFillAfter(true);

              mPointer.startAnimation(ra);
              mCurrentDegree = -azimuthInDegress;
          }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mPitchCallback = (CompassPitchListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CompassPitchListener");
        }

        try {
            mOrientationCallback = (CompassOrientationListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CompassOrientationListener");
        }

        try {
            mbackcallback = (backwarninglistener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement backwarninglistener");
        }
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu items for use in the action bar
        inflater.inflate(R.menu.menu_tiltorient, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_info:
                DialogFragment newFragment = new tilt_orient_info();
                newFragment.show(getFragmentManager(), "tiltorient_info_dialog");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



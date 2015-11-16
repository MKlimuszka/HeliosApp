package com.example.mk.helios;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk.helios.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class shade extends AppCompatActivity implements SensorEventListener {

    private TextView indicator;
    private Camera mCamera;
    private CameraPreview mPreview;
    private int cameraId;
    private int color = 1;
    private static String title;


    private int findCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras >0) {
            cameraId = 0;
        }
        else{
            cameraId = -1;
        }
        return cameraId;
    }

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shade);
        degreebox = (TextView) findViewById(R.id.degreetextview);
        inclinationview = (TextView) findViewById(R.id.inclination);
        ActionBar actionBar = getSupportActionBar();

        actionBar.hide();
        Intent intent = getIntent();
        title = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        mSensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mAccelerometer == null){
            Toast.makeText(this, "This device cannot measure tilt." , Toast.LENGTH_LONG).show();
        }


        // do we have a camera?
        if (!getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
                    .show();
        } else {
            cameraId = findCamera();
            if (cameraId < 0) {
                Toast.makeText(this, "No camera found.",
                        Toast.LENGTH_LONG).show();
            } else {
                mCamera = Camera.open(cameraId);
            }
        }



        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        final View fullscreen = findViewById(R.id.fullscreen);

    }

    private String TAG;
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null){
                Log.d(TAG, "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }
    };


    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;


    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM)+"/Helios");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("Helios", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    title +"_"+ timeStamp + ".jpg");




        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        pitchchange = true;
        orientationchange = true;
    }

    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mAccelerometer);
        mSensorManager.unregisterListener(this, mMagnetometer);
        pitchchange = true;
        orientationchange = true;

        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }


    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

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
                inclination = (int) -Math.round(Math.toDegrees(Math.asin(g[2])));

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
                float azimuthInDegress = (float) (Math.toDegrees(azimuthInRadians)+ 360) %360 + 90;

                orientation = azimuthInDegress;

                degreebox.setText(String.format("%.0f", orientation) + "°");

            }
        }
    }



}

package com.example.mk.helios;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;

import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk.helios.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class camera_activity extends AppCompatActivity {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Camera");
        actionBar.hide();
        Intent intent = getIntent();
        title = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

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



       final ImageButton captureButton = (ImageButton) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        fullscreen.setBackgroundColor(getResources().getColor(R.color.cameraindicator));
                        mCamera.takePicture(null, null, mPicture);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //do a thing after a delay
                                fullscreen.setBackgroundColor(getResources().getColor(R.color.transparent));
                            }
                        }, 1000); //1 second


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){

                            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DCIM)+"/Helios")));

                        }else{

                            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DCIM)+"/Helios")));

                        }

                        mCamera.startPreview();
                    }
                }
        );

    }

    private String TAG;
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            savepicture save = new savepicture(data);
            save.execute(data);



        }
    };


    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private boolean worked;



    public class savepicture extends AsyncTask<byte[], Void, Boolean> {

        public savepicture(byte[] data) {

        }


        @Override
        protected Boolean doInBackground(byte[]... params) {
            // your background code here. Don't touch any UI components
            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null){
                worked = false;


            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(params[0]);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }

            if (worked){} //if code worked

            else {

            }
            return false;
        }
        protected void onPostExecute(Boolean worked) {
            //This is run on the UI thread so you can do as you wish here
            if (worked) {

            }            else {
                Log.d(TAG, "Error creating media file, check storage permissions");
            }
        }
    }




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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera_activity, menu);
        return true;
    }




    @Override
    protected void onPause() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        super.onPause();
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
}

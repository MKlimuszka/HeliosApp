package com.example.mk.helios;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Created by Matt on 8/5/2015.
 */
public class RegionDatabase extends SQLiteAssetHelper {


        private static final String TAG = SQLiteAssetHelper.class.getSimpleName();
        private static final String DATABASE_NAME = "PV_database.sqlite";
        private static final int DATABASE_VERSION = 1;



        public RegionDatabase(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }





}

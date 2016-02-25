package com.tabjin.feng.medicalrecord.util;

import android.util.Log;

/**
 * Created by tabjin on 2016/2/25.
 */
public class L {

    private static final String TAG = "------main------";
    private static final boolean isDebug = true;

    public static void i(Object message) {
        if(isDebug){
            Log.i(TAG,message.toString());
        }
    }

    public static void e(Object message) {
        if(isDebug){
            Log.e(TAG,message.toString());
        }
    }

    public static void w(Object message) {
        if(isDebug){
            Log.w(TAG,message.toString());
        }
    }

    public static void d(Object message) {
        if(isDebug){
            Log.d(TAG,message.toString());
        }
    }

}

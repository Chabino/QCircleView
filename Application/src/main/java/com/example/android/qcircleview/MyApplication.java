package com.example.android.qcircleview;

import android.app.Application;

/**
 * Created by Chabino on 28/02/2015.
 */
public class MyApplication extends Application {

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;
}
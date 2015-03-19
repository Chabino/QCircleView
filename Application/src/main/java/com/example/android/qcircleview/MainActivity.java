/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.qcircleview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.WindowManager;

import com.example.android.common.activities.SampleActivityBase;
import com.example.android.qcircleview.simpleweather.WeatherActivity;

/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link android.support.v4.app.Fragment} which can display a view.
 * <p/>
 * For devices with displays with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */
public class MainActivity extends SampleActivityBase {

    private String TAG = this.getClass().getSimpleName();
    //private NotificationReceiver nReceiver;
    public static boolean isVisible;
    public static Activity mainactivity1;
    public static String h[][] = new String[10][4];
    public static Bitmap g[] = new Bitmap[10];
    public static int k[]=new int[10];
    public static Bitmap bm;
    public static Context mainactivity2;


    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        mainactivity1 = this;
        mainactivity2 = mainactivity1.getApplicationContext();
        isVisible = true;
        SharedPreferences prefs = this.getSharedPreferences("com.example.android.qcircleview.testval", MODE_PRIVATE);
        Cover.choosen_clock = prefs.getInt("clock", 0);
        //Settings.System.putLong(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 15000);
        setContentView(R.layout.activity_main);
        /*nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.android.slidingtabsbasic1");
        registerReceiver(nReceiver,filter);
        startService(new Intent(this, Cover.class));
        startService(new Intent(this, NotificationListener.class));*/
        while (NotificationListener.listener = false) {
            Log.i("Listener", "Waiting NotificationListener");
        }
        Intent in3 = new Intent("com.example.android.slidingtabsbasic");
        sendBroadcast(in3);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
        //setContentView(R.layout.activity_main);
        Log.i(TAG, "Activité lancée.");
        if (CallHelper.ring){
            Intent startIntent = new Intent(MainActivity.mainactivity2, IncomingCall.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.mainactivity2.startActivity(startIntent);
            CallHelper.phone_state = true;
        }
    }

    @Override
    protected void onDestroy() {
        isVisible = false;
        if (Menu.menustate) {
            Menu.menu1.finish();
        }
        if (FlashLightActivity.flashlightstate) {
            FlashLightActivity.flashlight1.finish();
        }
        if (Sms.sms) {
            Sms.sms1.finish();
        }
        if (SmsDetails.smsdetails) {
            SmsDetails.smsdetails1.finish();
        }
        if (Camera.camera) {
            Camera.camera1.finish();
        }
        if (StopWatch.chrono) {
            StopWatch.chrono1.finish();
        }
        if (IncomingCall.incoming) {
            IncomingCall.incoming1.finish();
        }
        if (Calc.calc) {
            Calc.calc1.finish();
        }
        if (Contact.contact) {
            Contact.contact1.finish();
        }
        if (DialerAppActivity.dial) {
            DialerAppActivity.dial1.finish();
        }
        if (VoiceRecorder.recv) {
            VoiceRecorder.recv1.finish();
        }
        if (StatusBarToggles.tog) {
            StatusBarToggles.tog1.finish();
        }
        if (WeatherActivity.weather) {
            WeatherActivity.weather1.finish();
        }
        if (SlidingTabsBasicFragment.myThread != null) {
            SlidingTabsBasicFragment.myThread.interrupt();
            try {
                SlidingTabsBasicFragment.myThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SlidingTabsBasicFragment.myThread = null;
        }

        SlidingTabsBasicFragment.stateone=false;
        //Settings.System.putLong(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, Configuration.time_default);
        super.onDestroy();
    }

    /*class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent2) {
            //stopService(new Intent(MainActivity.this, NotificationListener.class));
            finish();
        }
    }*/

}

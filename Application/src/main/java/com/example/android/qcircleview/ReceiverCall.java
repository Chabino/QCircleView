package com.example.android.qcircleview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by Chabino on 01/02/2015.
 */
public class ReceiverCall extends BroadcastReceiver {
    public static boolean service_on;
    public static boolean service_on2;
    public static boolean checkok=false;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Service Stops", "Ohhhhhhh");
        checkok=true;
        Configuration.time_default = android.provider.Settings.System.getLong(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT,-1);
            context.startService(new Intent(context, Cover.class));
            context.startService(new Intent(context, NotificationListener.class));
            context.startService(new Intent(context, CallDetectService.class));
        /*Intent in3 = new Intent("com.example.android.slidingtabsbasic");
        context.sendBroadcast(in3);*/
    }

}
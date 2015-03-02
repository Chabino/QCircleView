package com.example.android.qcircleview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Chabino on 01/02/2015.
 */
public class ReceiverCall extends BroadcastReceiver {
    public static boolean service_on;
    public static boolean service_on2;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Service Stops", "Ohhhhhhh");
            context.startService(new Intent(context, Cover.class));
            context.startService(new Intent(context, NotificationListener.class));
        /*Intent in3 = new Intent("com.example.android.slidingtabsbasic");
        context.sendBroadcast(in3);*/
    }

}
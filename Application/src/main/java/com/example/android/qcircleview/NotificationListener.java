package com.example.android.qcircleview;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationListener extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();
    private NLServiceReceiver nlservicereciver;
    public static int f;
    public static int call_pos;
    public static int media;
    public static boolean listener;

    @Override
    public void onCreate() {
        super.onCreate();
        listener = false;
        MainActivity.bm = Bitmap.createBitmap(25, 25, Bitmap.Config.ARGB_8888);
        Log.i(TAG, "Listener crée");
        nlservicereciver = new NLServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.android.slidingtabsbasic");
        registerReceiver(nlservicereciver, filter);
        listener = true;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(nlservicereciver);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (MainActivity.isVisible) {
            Log.i(TAG, "**********  onNotificationPosted");
            Log.i(TAG, "ID :" + sbn.getId() + "t" + sbn.getNotification().tickerText + "t" + sbn.getPackageName());
            /*int nb=0;
            media=0;
            for (StatusBarNotification sbn2 : NotificationListener.this.getActiveNotifications()) {
                MainActivity.h[nb][0] = sbn2.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT).toString();
                MainActivity.h[nb][1]= sbn2.getPackageName();
                MainActivity.h[nb][2]=sbn2.getNotification().extras.getString(Notification.EXTRA_TITLE);
                if (sbn2.getNotification().largeIcon == null) {
                    MainActivity.g[nb] = MainActivity.bm;
                }
                else{
                    MainActivity.g[nb] = sbn2.getNotification().largeIcon;
                }
                if (sbn2.getNotification().extras.getString(Notification.EXTRA_TEMPLATE) == null) {
                    MainActivity.h[f][3] = "nothing";
                }
                else{
                    MainActivity.h[f][3] = sbn2.getNotification().extras.getString(Notification.EXTRA_TEMPLATE);
                }
                if (MainActivity.h[nb][1].equals("com.android.dialer")) {
                    CallHelper.phone_state=true;
                    call_pos = nb+1;
                }
                Log.i(TAG, "v" + nb + "=" + MainActivity.h[nb][3]);
                nb++;
            }
                    SlidingTabsBasicFragment.setN(nb+1);*/
            Intent in4 = new Intent("com.example.android.slidingtabsbasic");
            sendBroadcast(in4);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        if (MainActivity.isVisible) {
            Log.i(TAG, "********** onNOtificationRemoved");
            Log.i(TAG, "ID :" + sbn.getId() + "t" + sbn.getNotification().tickerText + "t" + sbn.getPackageName());
            /*int b = 0;
            for (StatusBarNotification sbn1 : NotificationListener.this.getActiveNotifications()) {
                MainActivity.h[b][0] = sbn1.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT).toString();
                MainActivity.h[b][1] = sbn1.getPackageName();
                MainActivity.h[b][2] = sbn1.getNotification().extras.getString(Notification.EXTRA_TITLE);
                if (sbn1.getNotification().largeIcon == null) {
                    MainActivity.g[b] = MainActivity.bm;
                }
                else{
                    MainActivity.g[b] = sbn1.getNotification().largeIcon;
                }
                if (sbn1.getNotification().extras.getString(Notification.EXTRA_TEMPLATE) == null) {
                    MainActivity.h[f][3] = "nothing";
                }
                else{
                    MainActivity.h[f][3] = sbn1.getNotification().extras.getString(Notification.EXTRA_TEMPLATE);
                    if (MainActivity.h[f][3].equals("android.app.Notification$MediaStyle")){media=0;}
                }
                Log.i(TAG, "v" + b + "=" + MainActivity.h[b][0]);
                //sendBroadcast(in);
                b++;
            }
            MainActivity.h[b][0] = "";
            MainActivity.h[b][1] = "";
            MainActivity.h[b][2] = "";
            MainActivity.h[b][3]="";
            MainActivity.g[b] = MainActivity.bm;
            SlidingTabsBasicFragment.setN(b + 1);*/
            Intent in5 = new Intent("com.example.android.slidingtabsbasic");
            sendBroadcast(in5);
        }
    }

    class NLServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent1) {
            Log.i(TAG, "something");
            f = 0;
            media = 0;
            for (StatusBarNotification sbn2 : NotificationListener.this.getActiveNotifications()) {
                if (sbn2.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT) == null) {
                    MainActivity.h[f][0] = sbn2.getNotification().tickerText.toString();
                    if (sbn2.getNotification().tickerText == null) {
                        MainActivity.h[f][0] = "";
                    } else {
                        MainActivity.h[f][0] = sbn2.getNotification().tickerText.toString();
                    }
                } else {
                    MainActivity.h[f][0] = sbn2.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT).toString();
                }
                MainActivity.h[f][1] = sbn2.getPackageName();
                MainActivity.h[f][2] = sbn2.getNotification().extras.getString(Notification.EXTRA_TITLE);
                String title = sbn2.getNotification().extras.getString(Notification.EXTRA_TITLE);
                if (sbn2.getNotification().largeIcon == null) {
                    MainActivity.g[f] = MainActivity.bm;
                } else {
                    MainActivity.g[f] = sbn2.getNotification().largeIcon;
                }
                if (sbn2.getNotification().extras.getString(Notification.EXTRA_TEMPLATE) == null) {
                    MainActivity.h[f][3] = "nothing";
                } else {
                    MainActivity.h[f][3] = sbn2.getNotification().extras.getString(Notification.EXTRA_TEMPLATE);
                    if (MainActivity.h[f][3].equals("android.app.Notification$MediaStyle")) {
                        media = f + 1;
                    }
                }
                MainActivity.k[f] = sbn2.getId();
                    /*Log.i(TAG, "v" + f + "=" + MainActivity.h[f][0]);
                    Log.i(TAG, "title: "+title);
                    Log.i(TAG, "text1: "+MainActivity.h[f][3]);*/
                if (MainActivity.h[f][1].equals("com.android.dialer")) {
                    CallHelper.phone_state = true;
                    call_pos = f + 1;
                }
                //sendBroadcast(in);
                f++;
            }
            SlidingTabsBasicFragment.setN(f + 1);
        }
    }
}
package com.example.android.qcircleview;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.FileObserver;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Chabino on 14/01/2015.
 */
public class Cover extends Service {
    private String TAG = this.getClass().getSimpleName();
    private static BroadcastReceiver mReceiver1;
    private static int status;
    private static int am;
    final static int myID = 1234;
    private static Notification notification;
    private int obs;
    public static FileObserver observer;
    public static long time_default;
    public static int choosen_clock;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {

        obs = 0;
        //The intent to launch when the user clicks the expanded notification
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        /*PendingIntent pendIntent = PendingIntent.getActivity(this, 0, intent, 0);

        if (Integer.parseInt(Build.VERSION.SDK) >= Build.VERSION_CODES.DONUT) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setTicker("TICKER").setContentTitle("TITLE").setContentText("CONTENT")
                    .setWhen(System.currentTimeMillis()).setAutoCancel(false)
                    .setOngoing(true).setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(pendIntent);
            notification = builder.build();

        } else {

            Notification notice = new Notification(R.drawable.ic_launcher, "Ticker text", System.currentTimeMillis());
            notice.setLatestEventInfo(this, "Title text", "Content text", pendIntent);

        }
        notification.flags |= Notification.FLAG_NO_CLEAR;
        startForeground(myID, notification);*/

        Log.i(TAG, "Service Lancé");
        Thread myThread1 = new Thread(myRunnable2);
        myThread1.start();
        final IntentFilter filter1 = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter1.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver1 = new ScreenReceiver();
        registerReceiver(mReceiver1, filter1);
        //time_default =60000;
        //time_default = android.provider.Settings.System.getLong(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT,-1);
    }

    Runnable myRunnable2 = new Runnable() {

        @Override
        public void run() {
            observer = new FileObserver("/sys/devices/virtual/switch/smartcover", FileObserver.OPEN) { // set up a file observer to watch this directory on sd card
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

                @Override
                public void onEvent(int event, String file) {
                    Log.i(TAG, "event:" + event);
                    /*Log.i(TAG, "obs:" + obs);
                    obs++;
                    if(obs==10){
                        stopWatching();
                        obs=0;
                    }*/
                    if (event == 1073741856) {
                        am++;
                        Log.i(TAG, "am:" + am);
                        if (pm.isInteractive() && am == 2) {
                            try {
                                Process su = Runtime.getRuntime().exec("su");
                                DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                                outputStream.writeBytes("input keyevent 26\n");
                                outputStream.flush();

                                outputStream.writeBytes("exit\n");
                                outputStream.flush();
                                su.waitFor();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (pm.isInteractive() && am == 6) {
                            if (MainActivity.isVisible) {
                                MainActivity.mainactivity1.finish();
                                PackageManager pm = getApplicationContext().getPackageManager();
                                ComponentName componentName = new ComponentName(getApplicationContext(),
                                        NotificationListener.class);
                                pm.setComponentEnabledSetting(componentName,
                                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                        PackageManager.DONT_KILL_APP);
                                pm.setComponentEnabledSetting(componentName,
                                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                        PackageManager.DONT_KILL_APP);
                                //Settings.System.putLong(getApplicationContext().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, time_default);
                                am = 0;
                            }
                        }
                    }
                }
            };
            observer.startWatching(); //START OBSERVING

        }
    };

    public class ScreenReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                if (Menu.menustate) {
                    Menu.menu1.finish();
                }
                if (SlidingTabsBasicFragment.page_pos != 0) {
                    SlidingTabsBasicFragment.mViewPager.setCurrentItem(0);
                }
                if (!MainActivity.isVisible) {
                    PackageManager pm = getApplicationContext().getPackageManager();
                    ComponentName componentName = new ComponentName(getApplicationContext(),
                            NotificationListener.class);
                    pm.setComponentEnabledSetting(componentName,
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                    pm.setComponentEnabledSetting(componentName,
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);
                    Intent startIntent = new Intent(context, MainActivity.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Settings.System.putLong(getApplicationContext().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 15000);
                    context.startActivity(startIntent);
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
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

                Log.i(TAG, "valeur" + time_default);
                //Settings.System.putLong(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, time);
                try {
                    Scanner sc = new Scanner(new File("/sys/devices/virtual/switch/smartcover/state"));
                    status = Integer.parseInt(sc.nextLine());
                    sc.close();

                } catch (FileNotFoundException e) {
                    Log.e(context.getString(R.string.app_name), "Hall effect sensor device file not found!");
                }
                if (status == 0) {
                    am = 0;
                    if (MainActivity.isVisible) {
                        MainActivity.mainactivity1.finish();
                        //Settings.System.putLong(getApplicationContext().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, time_default);
                        PackageManager pm = getApplicationContext().getPackageManager();
                        ComponentName componentName = new ComponentName(getApplicationContext(),
                                NotificationListener.class);
                        pm.setComponentEnabledSetting(componentName,
                                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                PackageManager.DONT_KILL_APP);

                        pm.setComponentEnabledSetting(componentName,
                                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                PackageManager.DONT_KILL_APP);
                    /*Thread myThread1 = new Thread(myRunnable2);
                    myThread1.start();*/
                    }
                } else if (status == 1) {
                    am = 4;
                    if (CallHelper.phone_state) {
                        SlidingTabsBasicFragment.mViewPager.setCurrentItem(NotificationListener.call_pos);
                    }
                    SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                    SlidingTabsBasicFragment.myThread.start();
                    /*Thread myThread = new Thread(myRunnable);
                    myThread.start();*/
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver1);
    }
}

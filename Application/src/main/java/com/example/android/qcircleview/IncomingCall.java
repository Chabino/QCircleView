package com.example.android.qcircleview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.qcircleview.simpleweather.WeatherActivity;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Chabino on 16/02/2015.
 */
public class IncomingCall extends Activity {
    public static boolean incoming;
    public static Activity incoming1;
    private static ImageView incom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        incoming1 = this;
        incoming = true;
        setContentView(R.layout.incoming_page);
        TextView title = (TextView) findViewById(R.id.textView4);
        title.setText(CallHelper.contactName1);
        incom = ((ImageView) findViewById(R.id.pick2));
        final Float y_pos = incom.getY();
        //final Vibrator mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        incom.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                incom.setTranslationY(event.getRawY() - y_pos);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        if (event.getRawY() < 570) {
                            Log.i("Call", "Accept");
                            //mVibrator.vibrate(300);
                            //incom.setTranslationY(event.getRawY()-600);
                            try {
                                Process su = Runtime.getRuntime().exec("su");
                                DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                                outputStream.writeBytes("input keyevent 79\n");
                                outputStream.flush();

                                outputStream.writeBytes("exit\n");
                                outputStream.flush();
                                su.waitFor();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
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
                            if (Calc.calc) {
                                Calc.calc1.finish();
                            }
                            if (Contact.contact) {
                                Contact.contact1.finish();
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
                            SlidingTabsBasicFragment.mViewPager.setCurrentItem(NotificationListener.call_pos);
                            SlidingTabsBasicFragment.myThread.interrupt();
                            finish();
                        } else if (event.getRawY() > 800) {
                            Log.i("Call", "Reject");
                            //mVibrator.vibrate(300);
                            //incom.setTranslationY(event.getRawY()-730);
                            try {
                                Process su = Runtime.getRuntime().exec("su");
                                DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                                outputStream.writeBytes("input keyevent 6\n");
                                outputStream.flush();

                                outputStream.writeBytes("exit\n");
                                outputStream.flush();
                                su.waitFor();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            finish();
                        }

                        return true;
                    }
                    case MotionEvent.ACTION_DOWN: {
                        //Log.i("Call","Reject");

                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        incoming = false;
        super.onDestroy();
    }
}
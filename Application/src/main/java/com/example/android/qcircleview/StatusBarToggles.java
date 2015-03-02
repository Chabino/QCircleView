package com.example.android.qcircleview;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Chabino on 25/02/2015.
 */
public class StatusBarToggles extends Activity {
    public static boolean tog;
    public static Activity tog1;

    @Override
    public void onDestroy() {
        tog = false;
        super.onDestroy();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        tog = true;
        tog1 = this;
        setContentView(R.layout.quicktoggles);
        Menu.menu1.finish();
        final ToggleButton bluetooth = (ToggleButton) findViewById(R.id.bluetooth);
        final ToggleButton wifi = (ToggleButton) findViewById(R.id.wifi);
        final ToggleButton rotation = (ToggleButton) findViewById(R.id.rotate);
        final ToggleButton airplane = (ToggleButton) findViewById(R.id.flight);
        final ToggleButton brightness = (ToggleButton) findViewById(R.id.bright);
        /*SeekBar brightness = (SeekBar) findViewById(R.id.seekBar);
        int curBrightnessValue1 = 0;
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        try {
            curBrightnessValue1 = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            Log.i("bright", "Birghtness :" + curBrightnessValue1);
            brightness.setProgress(curBrightnessValue1);
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }*/
        if (Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, 0) == 1) {
            brightness.setChecked(true);
            Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.brightness_on);
            brightness.setBackground(on);
        } else {
            brightness.setChecked(false);
            Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.brightness_off);
            brightness.setBackground(off);
        }
        if (Settings.Global.getInt(getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1) {
            airplane.setChecked(true);
            Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.airplane_mode_on);
            airplane.setBackground(on);
        } else {
            airplane.setChecked(false);
            Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.airplane_mode_off);
            airplane.setBackground(off);
        }
        if (android.provider.Settings.System.getInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
            rotation.setChecked(true);
            Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.aotorotate_on);
            rotation.setBackground(on);
        } else {
            rotation.setChecked(false);
            Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.aotorotate_off);
            rotation.setBackground(off);
        }
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            bluetooth.setChecked(true);
            Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.bluetooth_off);
            bluetooth.setBackground(on);
        } else {
            bluetooth.setChecked(false);
            Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.bluetooth_off);
            bluetooth.setBackground(off);
        }
        final WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            wifi.setChecked(true);
            Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.wifi_on);
            wifi.setBackground(on);
        } else {
            wifi.setChecked(false);
            Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.wifi_off);
            wifi.setBackground(off);
        }
        ImageButton retour10 = (ImageButton) findViewById(R.id.retour10);
        retour10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                SlidingTabsBasicFragment.myThread.start();
            }
        });
        brightness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.brightness_on);
                    brightness.setBackground(on);
                    Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
                } else {
                    Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.brightness_off);
                    brightness.setBackground(off);
                    Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                }
            }
        });
        airplane.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.airplane_mode_on);
                    airplane.setBackground(on);
                    try {
                        Process su = Runtime.getRuntime().exec("su");
                        DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                        outputStream.writeBytes("settings put global airplane_mode_on 1\n");
                        outputStream.flush();

                        outputStream.writeBytes("am broadcast -a android.intent.action.AIRPLANE_MODE --ez state true\n");
                        outputStream.flush();

                        outputStream.writeBytes("exit\n");
                        outputStream.flush();
                        su.waitFor();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.airplane_mode_off);
                    airplane.setBackground(off);
                    try {
                        Process su = Runtime.getRuntime().exec("su");
                        DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                        outputStream.writeBytes("settings put global airplane_mode_on 0\n");
                        outputStream.flush();

                        outputStream.writeBytes("am broadcast -a android.intent.action.AIRPLANE_MODE --ez state false\n");
                        outputStream.flush();

                        outputStream.writeBytes("exit\n");
                        outputStream.flush();
                        su.waitFor();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        rotation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.aotorotate_on);
                    rotation.setBackground(on);
                    android.provider.Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 1);
                } else {
                    Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.aotorotate_off);
                    rotation.setBackground(off);
                    android.provider.Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
                }
            }
        });
        bluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.bluetooth_on);
                    bluetooth.setBackground(on);
                    mBluetoothAdapter.enable();
                } else {
                    Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.bluetooth_off);
                    bluetooth.setBackground(off);
                    mBluetoothAdapter.disable();
                }
            }
        });
        wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.wifi_on);
                    wifi.setBackground(on);
                    wifiManager.setWifiEnabled(true);
                } else {
                    Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.wifi_off);
                    wifi.setBackground(off);
                    wifiManager.setWifiEnabled(false);
                }
            }
        });
    }
}

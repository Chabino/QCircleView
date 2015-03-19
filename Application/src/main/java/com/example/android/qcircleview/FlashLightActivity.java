package com.example.android.qcircleview;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

/**
 * Created by Chabino on 01/02/2015.
 */
public class FlashLightActivity extends Activity {

    //flag to detect flash is on or off
    private boolean isLighOn = false;

    private Camera camera;

    private ImageButton button;
    private ImageButton retour;
    public static boolean flashlightstate;
    public static Activity flashlight1;

    @Override
    protected void onStop() {
        super.onStop();

        if (camera != null) {
            camera.release();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        flashlightstate = true;
        flashlight1 = this;
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.flashlight);
        Menu.menu1.finish();
        retour = (ImageButton) findViewById(R.id.retour);
        button = (ImageButton) findViewById(R.id.buttonflashlight);

        Context context = this;
        PackageManager pm = context.getPackageManager();

        // if device support camera?
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Log.e("err", "Device has no camera!");
            return;
        }

        retour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                if (SlidingTabsBasicFragment.myThread != null) {
                    SlidingTabsBasicFragment.myThread.interrupt();
                    try {
                        SlidingTabsBasicFragment.myThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SlidingTabsBasicFragment.myThread = null;
                }
                SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                SlidingTabsBasicFragment.myThread.start();
            }
        });
        camera = Camera.open();
        final Camera.Parameters p = camera.getParameters();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (isLighOn) {

                    Log.i("info", "torch is turn off!");

                    p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(p);
                    camera.stopPreview();
                    Drawable off = getApplicationContext().getResources().getDrawable(R.drawable.torchoff);
                    button.setBackground(off);
                    isLighOn = false;

                } else {

                    Log.i("info", "torch is turn on!");

                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

                    camera.setParameters(p);
                    camera.startPreview();
                    Drawable on = getApplicationContext().getResources().getDrawable(R.drawable.torchon);
                    button.setBackground(on);
                    isLighOn = true;

                }

            }
        });

    }

    @Override
    public void onDestroy() {
        if (camera != null) {
            camera.release();
        }
        flashlightstate = false;
        super.onDestroy();
    }
}

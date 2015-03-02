package com.example.android.qcircleview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class StopWatch extends Activity implements OnClickListener {

    private Chronometer chronometer;
    long timeWhenStopped = 0;
    public static boolean chrono;
    public static Activity chrono1;
    private static boolean start = false;
    private static boolean reset = false;
    private static Button av1, av2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        chrono = true;
        chrono1 = this;
        setContentView(R.layout.stopwatch);
        Menu.menu1.finish();
        ImageButton retour6 = (ImageButton) findViewById(R.id.retour6);
        retour6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                SlidingTabsBasicFragment.myThread.start();
            }
        });
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        av1 = (Button) findViewById(R.id.start_button);
        av2 = (Button) findViewById(R.id.stop_button);
        ((Button) findViewById(R.id.start_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.reset)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                if (!start) {
                    if (!reset) {
                        chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    } else {
                        chronometer.setBase(SystemClock.elapsedRealtime());
                    }
                    reset = false;
                    av1.setBackgroundColor(Color.parseColor("#ff3fff1c"));
                    av2.setBackgroundColor(Color.parseColor("#80ff310b"));
                    chronometer.start();
                    start = true;
                }
                break;
            case R.id.stop_button:
                if (start) {
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();
                    start = false;
                    av1.setBackgroundColor(Color.parseColor("#803fff1c"));
                    av2.setBackgroundColor(Color.parseColor("#ffff310b"));
                    reset = false;
                }
                break;
            case R.id.reset:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.stop();
                av1.setBackgroundColor(Color.parseColor("#803fff1c"));
                av2.setBackgroundColor(Color.parseColor("#80ff310b"));
                start = false;
                reset = true;

                break;
        }
    }

    @Override
    public void onDestroy() {
        chrono = false;
        super.onDestroy();

    }
}

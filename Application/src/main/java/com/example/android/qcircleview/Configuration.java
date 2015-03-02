package com.example.android.qcircleview;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/**
 * Created by Chabino on 01/03/2015.
 */
public class Configuration extends Activity {
    CheckBox box1;
    CheckBox box2;

    @Override
    protected void onResume() {
        if (isNLServiceRunning()) {
            box1.setChecked(true);
        } else {
            box1.setChecked(false);
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration);
        box1 = (CheckBox) findViewById(R.id.checkBox1);
        box2 = (CheckBox) findViewById(R.id.checkBox2);
        if (isNLServiceRunning()) {
            box1.setChecked(true);
            ReceiverCall.service_on = true;
            startService(new Intent(this, NotificationListener.class));
        } else {
            ReceiverCall.service_on = false;
            box1.setChecked(false);
        }
        if (ExecuteAsRootBase.su_on) {
            box2.setChecked(true);
        } else {
            box2.setChecked(false);
        }
        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
        });
        LinearLayout notification = (LinearLayout) findViewById(R.id.notification_access);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
        });
        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ExecuteAsRootBase.canRunRootCommands()) {
                    box2.setChecked(true);
                    ReceiverCall.service_on2 = true;
                    startService(new Intent(Configuration.this, Cover.class));
                } else {
                    ReceiverCall.service_on2 = false;
                    box2.setChecked(false);
                }
            }
        });
        LinearLayout root = (LinearLayout) findViewById(R.id.root_access);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ExecuteAsRootBase.canRunRootCommands()) {
                    box2.setChecked(true);
                    ReceiverCall.service_on2 = true;
                    startService(new Intent(Configuration.this, Cover.class));
                } else {
                    ReceiverCall.service_on2 = false;
                    box2.setChecked(false);
                }
            }
        });
        LinearLayout clock = (LinearLayout) findViewById(R.id.choose_clock);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(Configuration.this, ChooseClock.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startIntent);
            }
        });
    }

    private boolean isNLServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (NotificationListener.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }

        return false;
    }
}

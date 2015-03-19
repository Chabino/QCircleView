package com.example.android.qcircleview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Chabino on 01/02/2015.
 */
public class incoming_close extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (IncomingCall.incoming) {
            IncomingCall.incoming1.finish();
            /*SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
            SlidingTabsBasicFragment.myThread.start();*/
        }
    }

}
package com.example.android.qcircleview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Helper class to detect incoming and outgoing calls.
 *
 * @author Moskvichev Andrey V.
 */
public class CallHelper {

    public static boolean phone_state = false;
    public static String contactName1;
    public static String contactaddress;
    public static boolean callReceived = false;
    public static boolean ring = false;

    /**
     * Listener to detect incoming calls.
     */
    private class CallStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                //Hangup
                case TelephonyManager.CALL_STATE_OFFHOOK:
                /*Toast toast2 = Toast.makeText(ctx,
                        "Hangup! ",
                        Toast.LENGTH_LONG);
                toast2.setGravity(Gravity.TOP, 0, 250);
                toast2.show();*/
                    callReceived = true;
                    ring=false;
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                /*Toast toast2 = Toast.makeText(ctx,
                        "Hangup! ",
                        Toast.LENGTH_LONG);
                toast2.setGravity(Gravity.TOP, 0, 250);
                toast2.show();*/
                    if (ring && !callReceived) {
                        Intent in5 = new Intent("android.intent.incoming_close");
                        MainActivity.mainactivity2.sendBroadcast(in5);
                    }
                    callReceived=false;
                    phone_state = false;
                    ring = false;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    // called when someone is ringing to this phone
                /*Toast toast3 = Toast.makeText(ctx,
                        "Incoming: " + incomingNumber,
                        Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.TOP, 0, 250);
                toast3.show();*/
                    ring = true;
                    contactName1=incomingNumber;
                    contactaddress = incomingNumber;
                    contactName1=incomingNumber;
                    Uri Nameuri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(incomingNumber));
                    Cursor cs = MainActivity.mainactivity2.getContentResolver().query(Nameuri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.Contacts._ID}, ContactsContract.PhoneLookup.NUMBER + "='" + incomingNumber + "'", null, null);
                    if (cs.getCount() > 0) {
                        cs.moveToFirst();
                        if (cs.getString(cs.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME))!=null)
                        {
                            contactName1 = cs.getString(cs.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                        }
                    }
                    if (MainActivity.isVisible) {

                        try {
                            Thread.sleep(900);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent startIntent = new Intent(MainActivity.mainactivity2, IncomingCall.class);
                        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MainActivity.mainactivity2.startActivity(startIntent);
                        phone_state = true;
                    }
                    break;
            }
        }
    }

    /**
     * Broadcast receiver to detect the outgoing calls.
     */
    public class OutgoingReceiver extends BroadcastReceiver {
        public OutgoingReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
	        /*Toast toast4 = Toast.makeText(ctx,
                    "Outgoing: " + number,
                    Toast.LENGTH_LONG);
            toast4.setGravity(Gravity.TOP, 0, 250);
            toast4.show();*/
            SlidingTabsBasicFragment.mViewPager.setCurrentItem(NotificationListener.call_pos);
            phone_state = true;
        }

    }

    private Context ctx;
    private TelephonyManager tm;
    private CallStateListener callStateListener;

    private OutgoingReceiver outgoingReceiver;

    public CallHelper(Context ctx) {
        this.ctx = ctx;

        callStateListener = new CallStateListener();
        outgoingReceiver = new OutgoingReceiver();
    }

    /**
     * Start calls detection.
     */
    public void start() {
        tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
        ctx.registerReceiver(outgoingReceiver, intentFilter);
    }

    /**
     * Stop calls detection.
     */
    public void stop() {
        tm.listen(callStateListener, PhoneStateListener.LISTEN_NONE);
        ctx.unregisterReceiver(outgoingReceiver);
    }

}

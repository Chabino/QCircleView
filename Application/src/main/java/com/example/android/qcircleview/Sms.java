package com.example.android.qcircleview;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chabino on 02/02/2015.
 */
public class Sms extends Activity {
    public static boolean sms;
    public static Activity sms1;
    public static int sms_position;
    public static String sms_address[] = new String[1000];
    private String Id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        sms = true;
        sms1 = this;
        sms_position = 0;
        setContentView(R.layout.sms_read);
        Menu.menu1.finish();
        ImageButton retour1 = (ImageButton) findViewById(R.id.retour2);
        retour1.setOnClickListener(new View.OnClickListener() {

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
        ListView list = (ListView) findViewById(R.id.textview2);
        List<String> msgList = getSMS();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, msgList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("position", "p: " + position);
                sms_position = position;
                Intent startIntent = new Intent(getApplication(), SmsDetails.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startIntent);

            }
        });
    }

    public List<String> getSMS() {
        List<String> sms = new ArrayList<String>();
        Uri uriSMSURI = Uri.parse("content://mms-sms/conversations/");
        Cursor cursor = getContentResolver().query(uriSMSURI, null, null, null, "date desc");
        int pos = 0;
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String address1 = cursor.getString(cursor.getColumnIndex("address"));
            String address = address1.replaceAll("\\s", "");
            String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
            String read = cursor.getString(cursor.getColumnIndexOrThrow("read"));
            sms_address[pos] = address;


            //to fetch the contact name of the conversation
            String contactName = address;
            Uri Nameuri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(address));
            Cursor cs = getContentResolver().query(Nameuri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.Contacts._ID}, ContactsContract.PhoneLookup.NUMBER + "='" + address + "'", null, null);

            if (cs.getCount() > 0) {
                cs.moveToFirst();
                contactName = cs.getString(cs.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            }
            pos++;
            sms.add(contactName + "\n" + body);
        }
        return sms;
    }

    @Override
    public void onDestroy() {
        sms = false;
        super.onDestroy();

    }
}
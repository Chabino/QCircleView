package com.example.android.qcircleview;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Chabino on 14/02/2015.
 */
public class SmsDetails extends Activity {
    public static boolean smsdetails;
    public static Activity smsdetails1;
    public static int cur_pos = 0;
    public static List<String> listClone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        smsdetails = true;
        smsdetails1 = this;
        setContentView(R.layout.sms_details);
        ImageButton retour1 = (ImageButton) findViewById(R.id.retour3);
        retour1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ListView list = (ListView) findViewById(R.id.listView2);
        List<String> msgList = getSMS();
        Collections.sort(msgList);
        /*listClone = new ArrayList<>();
        int cur_prev_pos=0;
        for (String string : msgList){
            cur_prev_pos++;
            if(string.contains("====you====")){
                cur_pos=cur_prev_pos-1;
                listClone.add(String.valueOf(cur_pos));
                Log.i("cur","cur_pos :"+ cur_pos);
            }

        }*/

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, msgList);
        SmsDetailsAdapter adapter = new SmsDetailsAdapter(msgList, this);
        list.setAdapter(adapter);
        list.setSelection(adapter.getCount() - 1);
    }

    public List<String> getSMS() {
        List<String> sms = new ArrayList<String>();
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        //Cursor cursor = getContentResolver().query(uriSMSURI, null, null, null, "date desc");
        Cursor cursor = getContentResolver().query(uriSMSURI, new String[]{"_id", "address", "date", "body"}, null, null, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String address1 = cursor.getString(1);
            String address = address1.replaceAll("\\s", "");
            String body = cursor.getString(3);
            String date = cursor.getString(2);
            if (address.equals(Sms.sms_address[Sms.sms_position])) {
                System.out.println("====== Mobile number = " + address);
                System.out.println("===== SMS Text = " + body);

                sms.add(date + "\n" + "====contact====" + "\n" + body);
            }
        }
        Uri uriSMSURI2 = Uri.parse("content://sms/sent");
        //Cursor cursor = getContentResolver().query(uriSMSURI, null, null, null, "date desc");
        Cursor cursor2 = getContentResolver().query(uriSMSURI2, new String[]{"_id", "address", "date", "body"}, null, null, null);
        cursor.moveToFirst();
        while (cursor2.moveToNext()) {
            String address1 = cursor2.getString(1);
            String address = address1.replaceAll("\\s", "");
            String body = cursor2.getString(3);
            String date = cursor2.getString(2);
            if (address.equals(Sms.sms_address[Sms.sms_position])) {
                //System.out.println("====== Mobile number = " + address);
                //System.out.println("===== SMS Text = " + body);

                sms.add(date + "\n" + "====you====" + "\n" + body);
            }
        }
        return sms;
    }

    @Override
    public void onDestroy() {
        smsdetails = false;
        super.onDestroy();

    }
}

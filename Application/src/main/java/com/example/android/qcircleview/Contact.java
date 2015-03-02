package com.example.android.qcircleview;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Chabino on 15/02/2015.
 */
public class Contact extends Activity {
    public static boolean contact;
    public static Activity contact1;
    private int pos_cont = 0;
    private List<String> contactList;
    private ArrayAdapter<String> adapter;
    private ListView list;
    private Button cont;
    private Button rec;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        contact = true;
        contact1 = this;
        setContentView(R.layout.contact_view);
        Menu.menu1.finish();
        ImageButton retour5 = (ImageButton) findViewById(R.id.retour5);
        retour5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                SlidingTabsBasicFragment.myThread.start();
            }
        });
        ImageButton phoneapp = (ImageButton) findViewById(R.id.dialer);
        phoneapp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplication(), DialerAppActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startIntent);
            }
        });
        list = (ListView) findViewById(R.id.contact_list);
        contactList = getcontact();
        Collections.sort(contactList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
        list.setAdapter(adapter);
        list.setBackgroundColor(Color.MAGENTA);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object test = parent.getItemAtPosition(position);
                Scanner keyboard = new Scanner(test.toString());
                keyboard.nextLine();
                String input = keyboard.nextLine();
                Log.i("test", "test" + input);
                MainActivity.mainactivity1.finish();
                startActivity(new Intent(Intent.ACTION_CALL, Uri
                        .parse("tel:" + input)));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent startIntent = new Intent(getApplication(), MainActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startIntent);
                SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                SlidingTabsBasicFragment.myThread.start();
                CallHelper.phone_state = true;
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
                finish();
            }
        });
        cont = (Button) findViewById(R.id.cont);
        cont.setBackgroundColor(Color.MAGENTA);
        cont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pos_cont = 0;
                contactList = getcontact();
                Collections.sort(contactList);
                adapter = new ArrayAdapter<String>(Contact.this, android.R.layout.simple_list_item_1, contactList);
                list.setAdapter(adapter);
                cont.setBackgroundColor(Color.MAGENTA);
                rec.setBackgroundColor(Color.WHITE);
                list.setBackgroundColor(Color.MAGENTA);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object test = parent.getItemAtPosition(position);
                        Scanner keyboard = new Scanner(test.toString());
                        keyboard.nextLine();
                        String input = keyboard.nextLine();
                        Log.i("test", "test" + input);
                        MainActivity.mainactivity1.finish();
                        startActivity(new Intent(Intent.ACTION_CALL, Uri
                                .parse("tel:" + input)));
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent startIntent = new Intent(getApplication(), MainActivity.class);
                        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startIntent);
                        SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                        SlidingTabsBasicFragment.myThread.start();
                        CallHelper.phone_state = true;
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
                        finish();
                    }
                });
            }
        });
        rec = (Button) findViewById(R.id.rec);
        rec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pos_cont = 1;
                contactList = getrecent();
                Collections.reverse(contactList);
                adapter = new ArrayAdapter<String>(Contact.this, android.R.layout.simple_list_item_1, contactList);
                list.setAdapter(adapter);
                rec.setBackgroundColor(Color.BLUE);
                cont.setBackgroundColor(Color.WHITE);
                list.setBackgroundColor(Color.BLUE);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object test = parent.getItemAtPosition(position);
                        Scanner keyboard = new Scanner(test.toString());
                        keyboard.nextLine();
                        String input = keyboard.nextLine();
                        Log.i("test", "test" + input);
                        MainActivity.mainactivity1.finish();
                        startActivity(new Intent(Intent.ACTION_CALL, Uri
                                .parse("tel:" + input)));
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent startIntent = new Intent(getApplication(), MainActivity.class);
                        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startIntent);
                        SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                        SlidingTabsBasicFragment.myThread.start();
                        CallHelper.phone_state = true;
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
                        finish();
                    }
                });
            }
        });
    }

    public List<String> getcontact() {
        List<String> contact = new ArrayList<String>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor people = getContentResolver().query(uri, projection, null, null, null);

        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        people.moveToFirst();
        do {
            String name = people.getString(indexName);
            String number = people.getString(indexNumber);
            contact.add(name + "\n" + number);
        } while (people.moveToNext());
        return contact;
    }

    public List<String> getrecent() {
        List<String> recent = new ArrayList<String>();
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.DATE};

        Cursor cursor = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, null, null, null, null);

        int indexName = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int indexNumber = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int indexDate = cursor.getColumnIndex(CallLog.Calls.DATE);

        cursor.moveToFirst();
        do {
            if (cursor.getString(indexName) != null) {
                String name = cursor.getString(indexName);
                String number = cursor.getString(indexNumber);
                String date = cursor.getString(indexDate);
                recent.add(name + "\n" + number);
            } else {
                String name = "unknown";
                String number = cursor.getString(indexNumber);
                String date = cursor.getString(indexDate);
                recent.add(name + "\n" + number);
            }
        } while (cursor.moveToNext());
        return recent;
    }

    @Override
    public void onDestroy() {
        contact = false;
        super.onDestroy();

    }
}

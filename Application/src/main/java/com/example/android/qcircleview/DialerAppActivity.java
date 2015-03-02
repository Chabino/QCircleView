package com.example.android.qcircleview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

public class DialerAppActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    ImageButton dialBtn;
    EditText numTxt;
    Button one, two, three, four, five, six, seven, eight, nine, zero, dieze, mul;
    public static boolean dial, dialstate = false;
    public static Activity dial1;

    @Override
    protected void onDestroy() {
        dial = false;
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        dial = true;
        dial1 = this;
        Menu.menu1.finish();
        setContentView(R.layout.dialer);
        ImageButton retour8 = (ImageButton) findViewById(R.id.retour8);
        retour8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialBtn = (ImageButton) findViewById(R.id.call);
        numTxt = (EditText) findViewById(R.id.editText1);
        ImageButton supp1 = (ImageButton) findViewById(R.id.supp);
        supp1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                numTxt.setText("");
            }
        });
        one = (Button) findViewById(R.id.one1);
        two = (Button) findViewById(R.id.two1);
        three = (Button) findViewById(R.id.three1);
        four = (Button) findViewById(R.id.four1);
        five = (Button) findViewById(R.id.five1);
        six = (Button) findViewById(R.id.six1);
        seven = (Button) findViewById(R.id.seven1);
        eight = (Button) findViewById(R.id.eight1);
        nine = (Button) findViewById(R.id.nine1);
        zero = (Button) findViewById(R.id.zero1);
        dieze = (Button) findViewById(R.id.dieze1);
        mul = (Button) findViewById(R.id.mul1);

        try {
            one.setOnClickListener(this);

            two.setOnClickListener(this);

            three.setOnClickListener(this);

            four.setOnClickListener(this);

            five.setOnClickListener(this);

            six.setOnClickListener(this);

            seven.setOnClickListener(this);

            eight.setOnClickListener(this);

            nine.setOnClickListener(this);

            zero.setOnClickListener(this);

            dieze.setOnClickListener(this);

            mul.setOnClickListener(this);

        } catch (Exception e) {

        }
        zero.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Editable str = numTxt.getText();
                str = str.append("+");
                numTxt.setText(str);
                return true;
            }
        });
        dialBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (numTxt != null && numTxt.getText().length() == 0) {
                        Toast.makeText(getApplicationContext(), "You missed to type the number!", Toast.LENGTH_SHORT).show();
                        numTxt.setText("");
                    }/*else if (numTxt != null && numTxt.getText().length() < 10) {
                        Toast.makeText(getApplicationContext(), "Check whether you entered correct number!", Toast.LENGTH_SHORT).show();
                    }*/ else {
                        if (numTxt != null) {
                            dial = false;
                            MainActivity.mainactivity1.finish();
                            startActivity(new Intent(Intent.ACTION_CALL, Uri
                                    .parse("tel:" + numTxt.getText())));
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
                            Contact.contact1.finish();
                        }
                    }
                } catch (Exception e) {
                    Log.e("DialerAppActivity", "error: " + e.getMessage(),
                            e);
                }
            }
        });
    }

    @Override
    public void onClick(View arg0) {
        Editable str = numTxt.getText();
        switch (arg0.getId()) {
            case R.id.one1:
                str = str.append("1");
                numTxt.setText(str);
                break;
            case R.id.two1:
                str = str.append("2");
                numTxt.setText(str);
                break;
            case R.id.three1:
                str = str.append("3");
                numTxt.setText(str);
                break;
            case R.id.four1:
                str = str.append("4");
                numTxt.setText(str);
                break;
            case R.id.five1:
                str = str.append("5");
                numTxt.setText(str);
                break;
            case R.id.six1:
                str = str.append("6");
                numTxt.setText(str);
                break;
            case R.id.seven1:
                str = str.append("7");
                numTxt.setText(str);
                break;
            case R.id.eight1:
                str = str.append("8");
                numTxt.setText(str);
                break;
            case R.id.nine1:
                str = str.append("9");
                numTxt.setText(str);
                break;
            case R.id.zero1:
                str = str.append("0");
                numTxt.setText(str);
                break;
            case R.id.mul1:
                str = str.append("*");
                numTxt.setText(str);
                break;
            case R.id.dieze1:
                str = str.append("#");
                numTxt.setText(str);
                break;
        }
    }
}
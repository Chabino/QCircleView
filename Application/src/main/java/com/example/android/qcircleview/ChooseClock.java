package com.example.android.qcircleview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/**
 * Created by Chabino on 01/03/2015.
 */
public class ChooseClock extends Activity implements View.OnClickListener {
    LinearLayout one, two, three, four, five, six, seven, eight, nine;
    RadioButton rad1, rad2, rad3, rad4, rad5, rad6, rad7, rad8, rad9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseclock);
        one = (LinearLayout) findViewById(R.id.clock1);
        two = (LinearLayout) findViewById(R.id.clock2);
        three = (LinearLayout) findViewById(R.id.clock3);
        four = (LinearLayout) findViewById(R.id.clock4);
        five = (LinearLayout) findViewById(R.id.clock5);
        six = (LinearLayout) findViewById(R.id.clock6);
        seven = (LinearLayout) findViewById(R.id.clock7);
        eight = (LinearLayout) findViewById(R.id.clock8);
        nine = (LinearLayout) findViewById(R.id.clock9);
        rad1 = (RadioButton) findViewById(R.id.radio1);
        rad2 = (RadioButton) findViewById(R.id.radio2);
        rad3 = (RadioButton) findViewById(R.id.radio3);
        rad4 = (RadioButton) findViewById(R.id.radio4);
        rad5 = (RadioButton) findViewById(R.id.radio5);
        rad6 = (RadioButton) findViewById(R.id.radio6);
        rad7 = (RadioButton) findViewById(R.id.radio7);
        rad8 = (RadioButton) findViewById(R.id.radio8);
        rad9 = (RadioButton) findViewById(R.id.radio9);
        if (Cover.choosen_clock==R.layout.clock_page){rad1.setChecked(true);}
        else if(Cover.choosen_clock==R.layout.clock_page1){rad2.setChecked(true);}
        else if(Cover.choosen_clock==R.layout.clock_page2){rad3.setChecked(true);}
        else if(Cover.choosen_clock==R.layout.clock_page3){rad4.setChecked(true);}
        else if(Cover.choosen_clock==R.layout.clock_page4){rad5.setChecked(true);}
        else if(Cover.choosen_clock==R.layout.clock_page5){rad6.setChecked(true);}
        else if(Cover.choosen_clock==R.layout.clock_page6){rad7.setChecked(true);}
        else if(Cover.choosen_clock==R.layout.clock_page7){rad8.setChecked(true);}
        else if(Cover.choosen_clock==R.layout.clock_page8){rad9.setChecked(true);}

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

            rad1.setOnClickListener(this);
            rad2.setOnClickListener(this);
            rad3.setOnClickListener(this);
            rad4.setOnClickListener(this);
            rad5.setOnClickListener(this);
            rad6.setOnClickListener(this);
            rad7.setOnClickListener(this);
            rad8.setOnClickListener(this);
            rad9.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.clock1:
                rad1.setChecked(true);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page;

                break;
            case R.id.clock2:
                rad1.setChecked(false);
                rad2.setChecked(true);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page1;

                break;
            case R.id.clock3:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(true);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page2;

                break;
            case R.id.clock4:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(true);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page3;

                break;
            case R.id.clock5:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(true);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page4;

                break;
            case R.id.clock6:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(true);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page5;

                break;
            case R.id.clock7:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(true);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page6;

                break;
            case R.id.clock8:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(true);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page7;

                break;
            case R.id.clock9:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(true);
                Cover.choosen_clock = R.layout.clock_page8;

                break;
            case R.id.radio1:
                rad1.setChecked(true);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page;

                break;
            case R.id.radio2:
                rad1.setChecked(false);
                rad2.setChecked(true);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page1;

                break;
            case R.id.radio3:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(true);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page2;

                break;
            case R.id.radio4:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(true);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page3;

                break;
            case R.id.radio5:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(true);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page4;

                break;
            case R.id.radio6:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(true);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page5;

                break;
            case R.id.radio7:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(true);
                rad8.setChecked(false);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page6;

                break;
            case R.id.radio8:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(true);
                rad9.setChecked(false);
                Cover.choosen_clock = R.layout.clock_page7;

                break;
            case R.id.radio9:
                rad1.setChecked(false);
                rad2.setChecked(false);
                rad3.setChecked(false);
                rad4.setChecked(false);
                rad5.setChecked(false);
                rad6.setChecked(false);
                rad7.setChecked(false);
                rad8.setChecked(false);
                rad9.setChecked(true);
                Cover.choosen_clock = R.layout.clock_page8;

                break;
        }
    }
}

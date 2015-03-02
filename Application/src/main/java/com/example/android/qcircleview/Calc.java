package com.example.android.qcircleview;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Calc extends Activity implements View.OnClickListener {

    Button one, two, three, four, five, six, seven, eight, nine, zero, add, sub, mul, div, cancel, equal;
    EditText disp;
    float op1, res;
    float op2;
    String optr = "a", optr1 = "a";
    Boolean ty = false;
    Boolean ty2 = true;
    Boolean ty3 = false;
    Boolean ty4 = false;
    Boolean opstart = false;
    public static boolean calc;
    public static Activity calc1;

    @Override
    protected void onDestroy() {
        calc = false;
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        calc = true;
        calc1 = this;
        Menu.menu1.finish();
        setContentView(R.layout.calc);
        ImageButton retour7 = (ImageButton) findViewById(R.id.retour7);
        retour7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                SlidingTabsBasicFragment.myThread.start();
            }
        });

        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        cancel = (Button) findViewById(R.id.cancel);
        equal = (Button) findViewById(R.id.equal);

        disp = (EditText) findViewById(R.id.display);

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

            cancel.setOnClickListener(this);

            add.setOnClickListener(this);

            sub.setOnClickListener(this);

            mul.setOnClickListener(this);

            div.setOnClickListener(this);

            equal.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    /*public void operation(){
        if(optr.equals("+")){
            op2 = Integer.parseInt(disp.getText().toString());
            disp.setText("");
            op1 = op1 + op2;
            disp.setText("Result : " + Integer.toString(op1));
        }
        else if(optr.equals("-")){
            op2 = Integer.parseInt(disp.getText().toString());
            disp.setText("");
            op1 = op1 - op2;
            disp.setText("Result : " + Integer.toString(op1));
        }
        else if(optr.equals("*")){
            op2 = Integer.parseInt(disp.getText().toString());
            disp.setText("");
            op1 = op1 * op2;
            disp.setText("Result : " + Integer.toString(op1));
        }
        else if(optr.equals("/")){
            op2 = Integer.parseInt(disp.getText().toString());
            disp.setText("");
            op1 = op1 / op2;
            disp.setText("Result : " + Integer.toString(op1));
        }
    }*/
    public void operation() {
        if (optr.equals("+")) {
            op2 = Float.parseFloat(disp.getText().toString());
            disp.setText("");
            op1 = op1 + op2;
            ty = true;
            disp.setText(Float.toString(op1));
            optr = optr1;
            op2 = 0;
        } else if (optr.equals("-")) {
            op2 = Float.parseFloat(disp.getText().toString());
            disp.setText("");
            op1 = op1 - op2;
            ty = true;
            disp.setText(Float.toString(op1));
            optr = optr1;
            op2 = 0;
        } else if (optr.equals("*")) {
            op2 = Float.parseFloat(disp.getText().toString());
            disp.setText("");
            op1 = op1 * op2;
            ty = true;
            disp.setText(Float.toString(op1));
            optr = optr1;
            op2 = 1;
        } else if (optr.equals("/")) {
            op2 = Float.parseFloat(disp.getText().toString());
            disp.setText("");
            op1 = op1 / op2;
            ty = true;
            disp.setText(Float.toString(op1));
            optr = optr1;
            op2 = 1;
        }
    }

    @Override
    public void onClick(View arg0) {
        Editable str = disp.getText();
        switch (arg0.getId()) {
            case R.id.one:
                str = str.append(one.getText());
                if (!ty2) {
                    if (!ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("1");
                    }
                } else {
                    disp.setText("1");
                }
                ty2 = false;
                ty4 = false;
                break;
            case R.id.two:
                str = str.append(two.getText());
                if (!ty2) {
                    if (!ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("2");
                    }
                } else {
                    disp.setText("2");
                }
                ty2 = false;
                ty4 = false;
                break;
            case R.id.three:
                str = str.append(three.getText());
                if (!ty2) {
                    if (!ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("3");
                    }
                } else {
                    disp.setText("3");
                }
                ty2 = false;
                ty4 = false;
                break;
            case R.id.four:
                str = str.append(four.getText());
                if (!ty2) {
                    if (!ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("4");
                    }
                } else {
                    disp.setText("4");
                }
                ty2 = false;
                ty4 = false;
                break;
            case R.id.five:
                str = str.append(five.getText());
                if (!ty2) {
                    if (!ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("5");
                    }
                } else {
                    disp.setText("5");
                }
                ty2 = false;
                ty4 = false;
                break;
            case R.id.six:
                str = str.append(six.getText());
                if (!ty2) {
                    if (!ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("6");
                    }
                } else {
                    disp.setText("6");
                }
                ty2 = false;
                ty4 = false;
                break;
            case R.id.seven:
                str = str.append(seven.getText());
                if (!ty2) {
                    if (!ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("7");
                    }
                } else {
                    disp.setText("7");
                }
                ty2 = false;
                ty4 = false;
                break;
            case R.id.eight:
                str = str.append(eight.getText());
                if (!ty2) {
                    if (!ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("8");
                    }
                } else {
                    disp.setText("8");
                }
                ty2 = false;
                ty4 = false;

                break;
            case R.id.nine:
                str = str.append(nine.getText());
                if (!ty2) {
                    if (!ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("9");
                    }
                } else {
                    disp.setText("9");
                }
                ty2 = false;
                ty4 = false;

                break;
            case R.id.zero:
                str = str.append(zero.getText());
                if (!ty2 && !opstart) {
                    if (ty3) {
                        disp.setText(str);
                    } else {
                        ty3 = false;
                        disp.setText("");
                        disp.setText("0");
                    }
                } else {
                    opstart = false;
                    disp.setText("0");
                }
                ty2 = false;
                ty4 = false;

                break;
            case R.id.cancel:
                op1 = 0;
                op2 = 0;
                optr = "a";
                ty = false;
                ty2 = true;
                disp.setText("");
                disp.setHint("Enter Values");

                break;
            case R.id.add:
                if (optr.equals("a") && !ty2) {
                    ty3 = true;
                    op1 = Float.parseFloat(disp.getText().toString());
                    optr = "+";
                    opstart = true;
                    ty = true;
                    //disp.setText("");
                } else if (!optr.equals("a") && op1 != 0) {
                    ty3 = true;
                    ty4 = true;
                    optr1 = "+";
                    operation();
                }
                break;
            case R.id.sub:
                if (optr.equals("a") && !ty2) {
                    ty3 = true;
                    op1 = Float.parseFloat(disp.getText().toString());
                    optr = "-";
                    opstart = true;
                    ty = true;
                    //disp.setText("");
                } else if (!optr.equals("a") && op1 != 0) {
                    ty3 = true;
                    ty4 = true;
                    optr1 = "-";
                    operation();
                }
                break;
            case R.id.mul:
                if (optr.equals("a") && !ty2) {
                    ty3 = true;
                    op1 = Float.parseFloat(disp.getText().toString());
                    optr = "*";
                    opstart = true;
                    ty = true;
                    //disp.setText("");
                } else if (!optr.equals("a") && op1 != 0) {
                    ty3 = true;
                    ty4 = true;
                    optr1 = "*";
                    operation();
                }
                break;
            case R.id.div:
                if (optr.equals("a") && !ty2) {
                    ty3 = true;
                    op1 = Float.parseFloat(disp.getText().toString());
                    optr = "/";
                    opstart = true;
                    ty = true;
                    //disp.setText("");
                } else if (!optr.equals("a") && op1 != 0) {
                    ty3 = true;
                    ty4 = true;
                    optr1 = "/";
                    operation();
                }
                break;
            case R.id.equal:
                if (ty) {
                    ty3 = false;
                    if (optr.equals("+")) {
                        if (!ty4) {
                            op2 = Float.parseFloat(disp.getText().toString());
                        }
                        disp.setText("");
                        res = op1 + op2;
                        disp.setText(Float.toString(res));
                        optr = "a";
                        ty = false;
                        ty2 = true;
                    } else if (optr.equals("-")) {
                        if (!ty4) {
                            op2 = Float.parseFloat(disp.getText().toString());
                        }
                        disp.setText("");
                        res = op1 - op2;
                        disp.setText(Float.toString(res));
                        optr = "a";
                        ty = false;
                        ty2 = true;
                    } else if (optr.equals("*")) {
                        if (!ty4) {
                            op2 = Float.parseFloat(disp.getText().toString());
                        }
                        disp.setText("");
                        res = op1 * op2;
                        disp.setText(Float.toString(res));
                        optr = "a";
                        ty = false;
                        ty2 = true;
                    } else if (optr.equals("/")) {
                        if (!ty4) {
                            op2 = Float.parseFloat(disp.getText().toString());
                        }
                        disp.setText("");
                        res = op1 / op2;
                        disp.setText(Float.toString(res));
                        optr = "a";
                        ty = false;
                        ty2 = true;
                    }
                }
                break;
        }
    }
}
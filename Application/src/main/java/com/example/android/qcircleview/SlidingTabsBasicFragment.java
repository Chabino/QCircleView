/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.qcircleview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.common.logger.Log;
import com.example.android.common.view.SlidingTabLayout;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A basic sample which shows how to use {@link com.example.android.common.view.SlidingTabLayout}
 * to display a custom {@link ViewPager} title strip which gives continuous feedback to the user
 * when scrolling.
 */
public class SlidingTabsBasicFragment extends Fragment {

    private String LOG_TAG = this.getClass().getSimpleName();

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    public static ViewPager mViewPager;
    public static int page_pos;
    public static ImageView rotate_anim,end_rotate_anim;
    public static Animation rotate;

    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */
    private static int NUM_VIEWS = NotificationListener.f;
    private static boolean swipe;
    private ImageView img;
    View viewtest;
    public static Thread myThread=null;
    public static Runnable runnable;
    public static boolean state3=false;
    private TextView bat;
    public static boolean stateone=false;

    public void onPause(){stateone=false;
    super.onPause();}
    public void onResume(){
        state3=true;
        stateone=true;
    super.onResume();}
    public void onDestroy() {

        stateone=false;
        super.onDestroy();
    }

    public static void test() {
        Activity act = (Activity) mViewPager.getContext();
        if (act != null) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mViewPager.getAdapter().notifyDataSetChanged();
                }
            });
        }
    }

    public static void setN(int N) {
        NUM_VIEWS = N;
        test();
        //mViewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    // BEGIN_INCLUDE (fragment_onviewcreated)

    /**
     * This is called after the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has finished.
     * Here we can pick out the {@link View}s we need to configure from the content view.
     * <p/>
     * We set the {@link ViewPager}'s adapter to be an instance of {@link SamplePagerAdapter}. The
     * {@link SlidingTabLayout} is then given the {@link ViewPager} so that it can populate itself.
     *
     * @param view View created in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        myThread = null;
        runnable = new CountDownRunner();
        myThread = new Thread(runnable);
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        stateone=true;
        if(FirstPage.first) {
            FirstPage.first1.finish();
        }
        // END_INCLUDE (setup_slidingtablayout)
    }

    public void doRotate() {
        if (getActivity() == null)
            return;

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {

                    Date dt = new Date();
                    int hours = dt.getHours();
                    int minutes = dt.getMinutes();
                    int seconds = dt.getSeconds();
                    String curTime = hours + ":" + minutes + "::" + seconds;
                    //Log.v("log_tag", "Log is here Time is now" + curTime);
                    img = (ImageView) viewtest.findViewById(R.id.imgsecond);
                    RotateAnimation rotateAnimation = new RotateAnimation(
                            (seconds - 1) * 6, seconds * 6,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);

                    rotateAnimation.setInterpolator(new LinearInterpolator());
                    rotateAnimation.setDuration(1000);
                    rotateAnimation.setFillAfter(true);

                    img.startAnimation(rotateAnimation);
                   /* IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                    Intent batteryStatus = getActivity().registerReceiver(null, ifilter);
                    int level = 0;
                    if (batteryStatus != null) {
                        level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                    }
                    int scale = 0;
                    if (batteryStatus != null) {
                        scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                    }

                    float batteryPct = level / (float)scale;
                    float battstate = batteryPct* 100;
                    bat=(TextView) getActivity().findViewById(R.id.textbat);
                    bat.setText(String.valueOf(battstate)+"%");*/
                } catch (Exception e) {

                }
            }
        });
    }

    class CountDownRunner implements Runnable {
        // @Override
        public void run() {
            Log.i("Thread Animation","Thread started");
            int anim =0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    anim++;
                    if (anim==9){
                        if (CallHelper.callReceived){
                            if (SlidingTabsBasicFragment.myThread != null) {
                                SlidingTabsBasicFragment.myThread.interrupt();
                                /*try {
                                    SlidingTabsBasicFragment.myThread.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }*/
                                SlidingTabsBasicFragment.myThread = null;
                            }
                            if (MainActivity.isVisible) {
                                try {
                                    Process su = Runtime.getRuntime().exec("su");
                                    DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                                    outputStream.writeBytes("input keyevent 26\n");
                                    outputStream.flush();

                                    outputStream.writeBytes("exit\n");
                                    outputStream.flush();
                                    //su.waitFor();
                                } catch (IOException t) {
                                    t.printStackTrace();
                                }
                            }
                        }
                    }
                    if (anim==5){
                        end_rotate_anim.setVisibility(View.INVISIBLE);
                        if (CallHelper.callReceived) {
                            if (MainActivity.isVisible) {
                                SlidingTabsBasicFragment.mViewPager.setCurrentItem(NotificationListener.call_pos);
                            }
                        }
                    }
                    if (anim == 2){
                        end_rotate_anim = (ImageView) viewtest.findViewById(R.id.animate_plain);
                        rotate_anim = (ImageView) viewtest.findViewById(R.id.animate1);
                    rotate = new RotateAnimation(0,360,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            0.5f);
                    rotate.setInterpolator(new LinearInterpolator());
                    rotate.setDuration(500);
                    rotate.setRepeatCount(4);
                        final int finalAnim = anim;
                        rotate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            rotate_anim.setVisibility(View.VISIBLE);
                            end_rotate_anim.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            android.util.Log.i("test", "Animation ended");
                            //if (finalAnim ==4) {
                            /*try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/
                                end_rotate_anim.setVisibility(View.VISIBLE);
                            rotate_anim.setVisibility(View.INVISIBLE);
                            /*if (Cover.threadon){
                                    try {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        end_rotate_anim.setVisibility(View.VISIBLE);
                                        try {
                                            Thread.sleep(600);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
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
                                    Cover.threadon=false;
                                }*/
                            }

                        //}

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    rotate_anim.startAnimation(rotate);
                    }
                    doRotate();
                    Thread.sleep(1000);
                    rotate_anim.clearAnimation();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    //Log.e("log_tag", "Error is " + e.toString());
                }
            }
        }
    }
    // END_INCLUDE (fragment_onviewcreated)

    /**
     * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link SlidingTabLayout}.
     */
    class SamplePagerAdapter extends PagerAdapter {

        /**
         * @return the number of pages to display
         */


        @Override
        public int getCount() {
            return NUM_VIEWS;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)
        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */

        // END_INCLUDE (pageradapter_getpagetitle)

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        class TapGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {


            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    if (e1.getRawY() > e2.getRawY() && e1.getRawX()< e2.getRawX()+60 && e1.getRawX()>e2.getRawX()-60){
                        Log.i("test", "FLING!!!!!!!!!!!");
                    if (SlidingTabsBasicFragment.myThread != null) {
                        myThread.interrupt();
                        try {
                            myThread.join();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                        myThread = null;
                    }
                    Intent startIntent = new Intent(getActivity(), Menu.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startIntent);
                }
                }catch (NullPointerException e){

                }
                return false;

            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                try {
                    Process su = Runtime.getRuntime().exec("su");
                    DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                    outputStream.writeBytes("input keyevent 26\n");
                    outputStream.flush();

                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();
                } catch (IOException t) {
                    t.printStackTrace();
                } catch (InterruptedException t) {
                    t.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final GestureDetectorCompat tapGestureDetector = new GestureDetectorCompat(getActivity(), new TapGestureListener());

            container.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    tapGestureDetector.onTouchEvent(event);
                    return false;
                }
            });
            page_pos = position;

            if (position == 0) {
                if (Cover.choosen_clock == 0) {
                    Cover.choosen_clock = R.layout.clock_page;
                }
                // Inflate a new layout from our resources
                viewtest = getActivity().getLayoutInflater().inflate(Cover.choosen_clock,
                        container, false);
                if (Cover.choosen_clock==R.layout.clock_page21){
                String timeStamp = new SimpleDateFormat("EEEE dd MMMM yyyy").format(new Date());
                TextView today = (TextView) viewtest.findViewById(R.id.date1);
                today.setText(timeStamp);
                    if (ChooseClock.drawabler!=null) {
                        if (ChooseClock.drawabler.getMinimumHeight()<4096 && ChooseClock.drawabler.getMinimumWidth()<4096) {
                            viewtest.setBackground(ChooseClock.drawabler);
                        }
                        else{
                            Toast toast = Toast.makeText(getActivity(), "Bitmap too large to be \n uploaded into a texture \n (4160x3120, max=4096x4096)", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP, 0, 250);
                            toast.show();
                        }
                    }
                }
                /*String timeStamp = new SimpleDateFormat("EEEE dd MMMM yyyy").format(new Date());
                TextView today = (TextView) viewtest.findViewById(R.id.date1);
                today.setText(timeStamp);*/
                container.addView(viewtest);
                Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");
                // Return the View
                return viewtest;

            } else {
                if ((NotificationListener.media > 0 && position == NotificationListener.media) || MainActivity.h[position - 1][1].equals("com.spotify.music")) {
                    // Inflate a new layout from our resources
                    View view = getActivity().getLayoutInflater().inflate(R.layout.pager_music,
                            container, false);
                    Drawable d = new BitmapDrawable(getResources(), MainActivity.g[position - 1]);
                    view.setBackground(d);
                    if(MainActivity.h[position - 1][1].equals("com.spotify.music")) {
                        Drawable ico = getResources().getDrawable(R.drawable.audio_pic);
                        view.setBackground(ico);
                    }
                    ImageView im2 = (ImageView) view.findViewById(R.id.imageView);
                    PackageManager pm = getActivity().getPackageManager();
                    String pckg = MainActivity.h[position - 1][1];
                    Drawable icon = null;
                    try {
                        icon = pm.getApplicationIcon(pckg);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                        Log.i(LOG_TAG, "Problème");
                    }
                    im2.setImageDrawable(icon);

                    // Add the newly created View to the ViewPager
                    container.addView(view);

                    // Retrieve a TextView from the inflated View, and update it's text
                    TextView title = (TextView) view.findViewById(R.id.item_title);
                    title.setText(MainActivity.h[position - 1][2]);
                    TextView title2 = (TextView) view.findViewById(R.id.textView);
                    title2.setText(MainActivity.h[position - 1][0]);
                    Log.i(LOG_TAG, "instantiateItem() [nposition: " + MainActivity.h[position] + "]");

                    Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");
                    ImageButton bouton = ((ImageButton) view.findViewById(R.id.imageButton));
                    bouton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(0);
                            Log.i(LOG_TAG, "Bouton cliqué");
                        }
                    });
                    ImageButton previous = ((ImageButton) view.findViewById(R.id.previous));
                    previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AudioManager am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                            long eventtime = SystemClock.uptimeMillis() - 1;
                            KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PREVIOUS, 0);
                            am.dispatchMediaKeyEvent(downEvent);

                            eventtime++;
                            KeyEvent upEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PREVIOUS, 0);
                            am.dispatchMediaKeyEvent(upEvent);
                        }
                    });
                    ImageButton play_pause = ((ImageButton) view.findViewById(R.id.playpause));
                    play_pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AudioManager am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                            long eventtime = SystemClock.uptimeMillis() - 1;
                            KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
                            am.dispatchMediaKeyEvent(downEvent);

                            eventtime++;
                            KeyEvent upEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
                            am.dispatchMediaKeyEvent(upEvent);
                        }
                    });
                    ImageButton next = ((ImageButton) view.findViewById(R.id.next));
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AudioManager am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                            long eventtime = SystemClock.uptimeMillis() - 1;
                            KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_NEXT, 0);
                            am.dispatchMediaKeyEvent(downEvent);

                            eventtime++;
                            KeyEvent upEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_NEXT, 0);
                            am.dispatchMediaKeyEvent(upEvent);
                        }
                    });

                    // Return the View
                    return view;
                } else if (MainActivity.h[position - 1][1].equals("com.android.dialer")) {
                    // Inflate a new layout from our resources
                    View view = getActivity().getLayoutInflater().inflate(R.layout.call_page,
                            container, false);
                    Drawable d = new BitmapDrawable(getResources(), MainActivity.g[position - 1]);
                    view.setBackground(d);

                    // Add the newly created View to the ViewPager
                    container.addView(view);


                    // Retrieve a TextView from the inflated View, and update it's text
                    TextView title = (TextView) view.findViewById(R.id.item_title);
                    title.setText(MainActivity.h[position - 1][2]);
                    TextView title2 = (TextView) view.findViewById(R.id.textView);
                    title2.setText(MainActivity.h[position - 1][0]);
                    Log.i(LOG_TAG, "instantiateItem() [nposition: " + MainActivity.h[position] + "]");

                    Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");
                    ImageButton bouton = ((ImageButton) view.findViewById(R.id.imageButton));
                    bouton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(0);
                            Log.i(LOG_TAG, "Bouton cliqué");
                        }
                    });
                    ImageView previous = ((ImageView) view.findViewById(R.id.pick));
                    previous.setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(final View v, final MotionEvent event) {
                            /*switch (event.getAction()) {
                                case MotionEvent.ACTION_MOVE: {
                                    if(event.getRawY()<670){
                                        Log.i("Call","Accept");}
                                    else if (event.getRawY()>780){
                                        Log.i("Call","Reject");
                                    }

                                    return true;
                                }
                                case MotionEvent.ACTION_DOWN: {
                                    //Log.i("Call","Reject");

                                    return true;
                                }
                            }*/
                            try {
                                Process su = Runtime.getRuntime().exec("su");
                                DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                                outputStream.writeBytes("input keyevent 6\n");
                                outputStream.flush();

                                outputStream.writeBytes("exit\n");
                                outputStream.flush();
                                su.waitFor();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mViewPager.setCurrentItem(0);
                            return true;
                        }
                    });

                    // Return the View
                    return view;
                } else {
                    swipe = false;
                    // Inflate a new layout from our resources
                    View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                            container, false);
                    Drawable d = new BitmapDrawable(getResources(), MainActivity.g[position - 1]);
                    view.setBackground(d);
                    ImageView im2 = (ImageView) view.findViewById(R.id.imageView);
                    PackageManager pm = getActivity().getPackageManager();
                    String pckg = MainActivity.h[position - 1][1];
                    Drawable icon = null;
                    try {
                        icon = pm.getApplicationIcon(pckg);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                        Log.i(LOG_TAG, "Problème");
                    }
                    im2.setImageDrawable(icon);

                    // Add the newly created View to the ViewPager
                    container.addView(view);

                    // Retrieve a TextView from the inflated View, and update it's text
                    TextView title = (TextView) view.findViewById(R.id.item_title);
                    title.setText(MainActivity.h[position - 1][2]);
                    TextView title2 = (TextView) view.findViewById(R.id.textView);
                    title2.setText(MainActivity.h[position - 1][0]);
                    Log.i(LOG_TAG, "instantiateItem() [nposition: " + MainActivity.h[position] + "]");

                    Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");
                    ImageButton bouton = ((ImageButton) view.findViewById(R.id.imageButton));
                    bouton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(0);
                            Log.i(LOG_TAG, "Bouton cliqué");
                        }
                    });
                    // Return the View
                    return view;
                }
            }
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            Log.i(LOG_TAG, "destroyItem() [position,l': " + position + "]");
        }

    }
}

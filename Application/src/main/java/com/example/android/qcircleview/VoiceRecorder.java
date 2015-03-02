package com.example.android.qcircleview;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VoiceRecorder extends Activity {

    private MediaRecorder myRecorder;
    private MediaPlayer myPlayer;
    private String outputFile = null;
    private Button startBtn1;
    private Button stopBtn;
    private Button playBtn;
    private Button stopPlayBtn;
    private TextView text;
    public static boolean recv;
    public static Activity recv1;

    @Override
    protected void onDestroy() {
        recv = false;
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        recv = true;
        recv1 = this;
        setContentView(R.layout.voice_recorder);
        text = (TextView) findViewById(R.id.text1);
        Menu.menu1.finish();
        ImageButton retour9 = (ImageButton) findViewById(R.id.retour9);
        retour9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                SlidingTabsBasicFragment.myThread = new Thread(SlidingTabsBasicFragment.runnable);
                SlidingTabsBasicFragment.myThread.start();
            }
        });
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //and make a media file:
        String mediaFile2 = "AUD_" + timeStamp + ".mp3";
        // store it to sd card
        outputFile = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/Music/" + mediaFile2;

        myRecorder = new MediaRecorder();
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myRecorder.setOutputFile(outputFile);

        startBtn1 = (Button) findViewById(R.id.start2);
        startBtn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                start(v);
            }
        });

        stopBtn = (Button) findViewById(R.id.stop2);
        stopBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                stop(v);
            }
        });

        playBtn = (Button) findViewById(R.id.play);
        playBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                play(v);
            }
        });

        stopPlayBtn = (Button) findViewById(R.id.stopPlay);
        stopPlayBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                stopPlay(v);
            }
        });
    }

    public void start(View view) {
        try {
            myRecorder.prepare();
            myRecorder.start();
        } catch (IllegalStateException e) {
            // start:it is called before prepare()
            // prepare: it is called after start() or before setOutputFormat()
            e.printStackTrace();
        } catch (IOException e) {
            // prepare() fails
            e.printStackTrace();
        }

        text.setText("Recording Point: Recording");
        startBtn1.setEnabled(false);
        stopBtn.setEnabled(true);

        Toast to1 = Toast.makeText(getApplicationContext(), "Start recording...",
                Toast.LENGTH_SHORT);
        to1.setGravity(Gravity.TOP, 0, 250);
        to1.show();
    }

    public void stop(View view) {
        try {
            myRecorder.stop();
            myRecorder.release();
            myRecorder = null;

            stopBtn.setEnabled(false);
            playBtn.setEnabled(true);
            text.setText("Recording Point: Stop recording");

            Toast to2 = Toast.makeText(getApplicationContext(), "Stop recording... Audio saved in" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/",
                    Toast.LENGTH_SHORT);
            to2.setGravity(Gravity.TOP, 0, 250);
            to2.show();
        } catch (IllegalStateException e) {
            //  it is called before start()
            e.printStackTrace();
        } catch (RuntimeException e) {
            // no valid audio/video data has been received
            e.printStackTrace();
        }
    }

    public void play(View view) {
        try {
            myPlayer = new MediaPlayer();
            myPlayer.setDataSource(outputFile);
            myPlayer.prepare();
            myPlayer.start();

            playBtn.setEnabled(false);
            stopPlayBtn.setEnabled(true);
            text.setText("Recording Point: Playing");

            Toast to3 = Toast.makeText(getApplicationContext(), "Start play the recording...",
                    Toast.LENGTH_SHORT);
            to3.setGravity(Gravity.TOP, 0, 250);
            to3.show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void stopPlay(View view) {
        try {
            if (myPlayer != null) {
                myPlayer.stop();
                myPlayer.release();
                myPlayer = null;
                playBtn.setEnabled(true);
                stopPlayBtn.setEnabled(false);
                text.setText("Recording Point: Stop playing");

                Toast to4 = Toast.makeText(getApplicationContext(), "Stop playing the recording...",
                        Toast.LENGTH_SHORT);
                to4.setGravity(Gravity.TOP, 0, 250);
                to4.show();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

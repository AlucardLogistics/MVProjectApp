package com.logistics.alucard.mvprojectapp.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.logistics.alucard.mvprojectapp.R;

public class MusicPlayer extends Service {
    private static final String TAG = "MusicPlayer";

    MediaPlayer mediaPlayer;
    int[] playList = {R.raw.warbringersjaina, R.raw.linkinparknumb, R.raw.metallicafadetoblack};
    String[] playListNames = {"Warbringer Jaina", "Linkin Park - Numb", "Metallica - Fade To Black"};
    int trackNo = 0;
    int playlistLenght = playList.length;
    String btCommand, lvCommand;

    public MusicPlayer() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {

        btCommand = intent.getStringExtra("btCommand");
        lvCommand = intent.getStringExtra("lvCommand");
        trackNo = intent.getIntExtra("lvPosition", 0);
        Log.d(TAG, "onStartCommand: trackNO: " + trackNo);
        Log.d(TAG, "onStartCommand: btCommand: " + btCommand + " lvComand: " + lvCommand);
        if(btCommand == null) {
            listViewControls();
        } else {
            buttonControls();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void listViewControls() {
        Log.d(TAG, "listViewControls: started");
        btCommand = null;

        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        mediaPlayer = MediaPlayer.create(MusicPlayer.this, playList[trackNo]);
        mediaPlayer.start();
        Toast.makeText(this, playListNames[trackNo], Toast.LENGTH_SHORT).show();
    }

    private void buttonControls() {
        lvCommand = null;
        if(trackNo < playlistLenght && trackNo >= 0) {
            if(btCommand.equals("next")){
                Log.d(TAG, "onNext: " + btCommand);
                if(trackNo == playlistLenght - 1){
                    Log.d(TAG, "onStartCommand: if track is last");
                    trackNo = playlistLenght - 1;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MusicPlayer.this, playList[trackNo]);
                    mediaPlayer.start();
                    Toast.makeText(this, "Last Tune", Toast.LENGTH_SHORT).show();
                } else {
                    trackNo += 1;
                    Log.d(TAG, "onNext trackNo " + trackNo);
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MusicPlayer.this, playList[trackNo]);
                    mediaPlayer.start();
                }
            } else if(btCommand.equals("prev")){
                Log.d(TAG, "onPrev: " + btCommand);
                if(trackNo == 0){
                    Log.d(TAG, "onStartCommand: if track is 0");
                    trackNo = 0;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MusicPlayer.this, playList[trackNo]);
                    mediaPlayer.start();
                    Toast.makeText(this, "First Tune", Toast.LENGTH_SHORT).show();
                } else {
                    trackNo -= 1;
                    Log.d(TAG, "onPrev trackNo " + trackNo);
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MusicPlayer.this, playList[trackNo]);
                    mediaPlayer.start();
                }
            } else if (btCommand.equals("play")) {
                Log.d(TAG, "onPlay trackNo " + trackNo);
                mediaPlayer = MediaPlayer.create(MusicPlayer.this, playList[trackNo]);
                mediaPlayer.start();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

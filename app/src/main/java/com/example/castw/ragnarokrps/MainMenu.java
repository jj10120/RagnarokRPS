package com.example.castw.ragnarokrps;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.media.MediaPlayer;


public class MainMenu extends ActionBarActivity implements View.OnClickListener {

    ImageButton btnstart;
    ImageButton btnhtp;
    ImageButton btnmp;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2activity_layout);


        player = MediaPlayer.create(this, R.raw.theme);
        player.setLooping(true);
        player.setVolume(100,100);
        player.start();

        btnstart = (ImageButton) findViewById(R.id.btnstart);
        btnhtp = (ImageButton) findViewById(R.id.btnhtp);
        btnmp = (ImageButton) findViewById(R.id.btnmp);


        btnstart.setOnClickListener(this);
        btnhtp.setOnClickListener(this);
        btnmp.setOnClickListener(this);
    }



    public void onClick(View v) {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
        mp.start();

        switch (v.getId()) {

            case R.id.btnstart:
                Intent intent = new Intent(MainMenu.this, SinglePlayer.class);
                this.startActivity(intent);
               mp.start();
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                break;
            case R.id.btnhtp:
                Intent intent2 = new Intent(MainMenu.this, HowToPlay.class);
                this.startActivity(intent2);
                mp.start();
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                break;
            case R.id.btnmp:
                Intent intent3 = new Intent(MainMenu.this, WifiDirectActivity.class);
                this.startActivity(intent3);
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                mp.start();
                break;
        }

    }

@Override
    public void onResume() {
        super.onResume();
        if(player != null  && !player.isPlaying())
            player.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.isFinishing()){
            player.pause();
        }
    }



    public void onRestart() {
        super.onRestart();


    }
    public void onDestroy() {
        super.onDestroy();
player.stop();
    }
    }




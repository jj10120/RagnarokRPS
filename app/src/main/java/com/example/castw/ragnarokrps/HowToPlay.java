package com.example.castw.ragnarokrps;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowToPlay extends AppCompatActivity {
    MediaPlayer player;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtoplayinterface);
        player = MediaPlayer.create(this, R.raw.themehtp);
        player.setLooping(true);
        player.setVolume(100,100);
        player.start();
        btn = (Button) findViewById(R.id.button3);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/Viking.ttf");
        btn.setTypeface(myCustomFont);
    }
    public void back (View view) {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
        mp.start();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
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

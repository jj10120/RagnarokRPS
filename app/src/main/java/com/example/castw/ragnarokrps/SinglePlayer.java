package com.example.castw.ragnarokrps;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;


public class SinglePlayer extends AppCompatActivity {

    MediaPlayer player;

    Button b_odin, b_thor, b_loki;
    Button b_reset;
    Button back;
    ImageView iv_cpu, iv_me;
    String myChoice, cpuChoice, result;

    int HumanScore = 0;
    int ComputerScore = 0;
    TextView playerscoreView;
    TextView cpuscoreView;

    Random r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_layout);

        player = MediaPlayer.create(this, R.raw.themegame);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();
        iv_cpu = (ImageView) findViewById(R.id.iv_cpu);
        iv_me = (ImageView) findViewById(R.id.iv_me);


        b_odin = (Button) findViewById(R.id.b_odin);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Viking.ttf");
        b_odin.setTypeface(myCustomFont);
        b_thor = (Button) findViewById(R.id.b_thor);
        b_thor.setTypeface(myCustomFont);
        b_loki = (Button) findViewById(R.id.b_loki);
        b_loki.setTypeface(myCustomFont);
        b_reset = (Button) findViewById(R.id.resetscore);
        b_reset.setTypeface(myCustomFont);
        back = (Button) findViewById(R.id.button2);
        back.setTypeface(myCustomFont);

        HumanScore = 0;
        ComputerScore = 0;
        this.playerscoreView = (TextView) findViewById(R.id.playscore);

        playerscoreView.setTypeface(myCustomFont);

        this.cpuscoreView = (TextView) findViewById(R.id.compscore);
        cpuscoreView.setTypeface(myCustomFont);

        playerscoreView.setText("Your Score: " + HumanScore);
        cpuscoreView.setText("CPU Score: " + ComputerScore);


        r = new Random();

        b_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                mp.start();
                HumanScore = 0;
                playerscoreView.setText("Your Score: " + 0);
                ComputerScore = 0;
                cpuscoreView.setText("CPU Score: " + 0);
            }
        });


        b_odin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                mp.start();
                myChoice = "odin";
                iv_me.setImageResource(R.drawable.odincard);
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                calculate();

            }
        });

        b_thor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                mp.start();
                myChoice = "thor";
                iv_me.setImageResource(R.drawable.thorcard);
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                calculate();

            }
        });

        b_loki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                mp.start();
                myChoice = "loki";
                iv_me.setImageResource(R.drawable.lokicard);
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                calculate();

            }
        });
    }

    public void back(View view) {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
        mp.start();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }

    public void calculate() {
        int cpu = r.nextInt(3);
        if (cpu == 0) {
            cpuChoice = "odin";
            iv_cpu.setImageResource(R.drawable.odincard);
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        } else if (cpu == 1) {
            cpuChoice = "thor";
            iv_cpu.setImageResource(R.drawable.thorcard);
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        } else if (cpu == 2) {
            cpuChoice = "loki";
            iv_cpu.setImageResource(R.drawable.lokicard);
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        }


        if (myChoice.equals("odin") && cpuChoice.equals("loki")) {
            result = "Defeat!";
            ScoreComputer();
        } else if (myChoice.equals("odin") && cpuChoice.equals("thor")) {
            result = "Victory!";
            Scoreplayer();
        } else if (myChoice.equals("odin") && cpuChoice.equals("loki")) {
            result = "Defeat!";
            ScoreComputer();
        } else if (myChoice.equals("thor") && cpuChoice.equals("odin")) {
            result = "Defeat!";
            ScoreComputer();
        } else if (myChoice.equals("thor") && cpuChoice.equals("loki")) {
            result = "Victory!";
            Scoreplayer();
        } else if (myChoice.equals("loki") && cpuChoice.equals("odin")) {
            result = "Victory!";
            Scoreplayer();
        } else if (myChoice.equals("loki") && cpuChoice.equals("thor")) {
            result = "Defeat!";
            ScoreComputer();
        } else if (myChoice.equals(cpuChoice)) {
            result = "Draw!";
        }
        Toast.makeText(SinglePlayer.this, result, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (player != null && !player.isPlaying())
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
        if (this.isFinishing()) {
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

    public void Scoreplayer() {
        HumanScore++;
        playerscoreView.setText("Your Score: " + HumanScore);
    }

    public void ScoreComputer() {
        ComputerScore++;
        cpuscoreView.setText("CPU Score: " + ComputerScore);
    }
}

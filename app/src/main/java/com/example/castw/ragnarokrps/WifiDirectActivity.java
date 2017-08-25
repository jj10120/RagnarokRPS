package com.example.castw.ragnarokrps;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.castw.ragnarokrps.netfrag.WifiDirectBroadcastReceiver;


public class WifiDirectActivity extends AppCompatActivity {
    private MediaPlayer player;
    private Button btn;
    private boolean isWifiP2pEnabled = false;
    private final IntentFilter intentFilter = new IntentFilter();
    private WifiP2pManager manager;
    private Channel channel;
    private WifiDirectBroadcastReceiver receiver = null;


    public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifidirectactivity);
        player = MediaPlayer.create(this, R.raw.multiplayertheme);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        // add necessary intent values to be matched.
        //  Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        // Indicates a change in the list of available peers
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        // Indicates the state of Wi-Fi P2P connectivity has changed
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        // Indicates this device's details have changed
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        registerReceiver(receiver, intentFilter);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);

        btn = (Button) findViewById(R.id.button);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Viking.ttf");
        btn.setTypeface(myCustomFont);





    }
    public void back(View view) {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
        mp.start();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }
    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.wifiaction_items, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.wifiaction_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.enablep2p:
            if (manager != null && channel != null) {
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            } else {
                Log.d("TAG", "channel or manager is null");
            }
            return true;

        case R.id.direct_discover:
            if (!isWifiP2pEnabled) {
                Toast.makeText(WifiDirectActivity.this, "Enable P2P from Action bar or in system settings", Toast.LENGTH_SHORT).show();
                return true;
            }
            manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(WifiDirectActivity.this, "Discovery Initiated", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int reasonCode) {
                    Toast.makeText(WifiDirectActivity.this, "Discovery Failed :" + reasonCode, Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}

        @Override
        public void onResume() {
            super.onResume();
            if(player != null  && !player.isPlaying())
                player.start();
            receiver = new WifiDirectBroadcastReceiver(manager, channel, this);
            registerReceiver(receiver, intentFilter);
        }

        @Override
        protected void onPause() {
            super.onPause();
            player.pause();
            unregisterReceiver(receiver);

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
            player.reset();

        }
        public void onDestroy() {
            super.onDestroy();
            player.stop();
        }
    }

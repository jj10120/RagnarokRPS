package com.example.castw.ragnarokrps.utilities;

import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;

/**
 * Created by castw on 13-Apr-17.
 */

public interface DeviceActionListener {
    void configureDetails(WifiP2pDevice device);
    void cancelDisconnect();
    void connect(WifiP2pConfig config);
    void disconnect();
}

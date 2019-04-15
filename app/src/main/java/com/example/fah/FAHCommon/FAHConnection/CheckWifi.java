package com.example.fah.FAHCommon.FAHConnection;

import android.view.View;
import android.widget.TextView;
import com.example.fah.FAHCommon.FAHConnection.ConnectionReceiver;

public class CheckWifi {

    public static boolean isConnect(TextView isConnect) {
        if (ConnectionReceiver.isConnected()) {
            isConnect.setVisibility(View.GONE);
            return true;
        } else {
            isConnect.setVisibility(View.VISIBLE);
            return false;
        }
    }
}

package com.example.fah.FAHCommon;

import android.view.View;
import android.widget.TextView;
import com.example.fah.FAHControl.FAHConnection.ConnectionReceiver;

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

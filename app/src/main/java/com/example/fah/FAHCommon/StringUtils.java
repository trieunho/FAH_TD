package com.example.fah.FAHCommon;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.fah.FAHCommon.FAHConnection.ConnectionReceiver;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    private boolean checkConnection(@NonNull TextView isConnect) {
        if (ConnectionReceiver.isConnected()) {
            isConnect.setVisibility(View.GONE);
            return true;
        } else {
            isConnect.setVisibility(View.VISIBLE);
            return false;
        }
    }
}

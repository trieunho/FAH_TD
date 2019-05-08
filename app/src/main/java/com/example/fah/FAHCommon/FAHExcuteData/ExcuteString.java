package com.example.fah.FAHCommon.FAHExcuteData;

import android.text.TextUtils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ExcuteString {
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static String GetUrlData(String... listParam) {
        return TextUtils.join("/", listParam);
    }

    public static Boolean IsNullOrEmpty(String string){
        return string == null || string == "";
    }
}

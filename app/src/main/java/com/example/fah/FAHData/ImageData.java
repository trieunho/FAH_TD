package com.example.fah.FAHData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.example.fah.FAHCommon.CommonUtils.ImageUtils;
import com.example.fah.FAHModel.Models.IEventData;

public class ImageData {

    public static void binDingImageControl(Context context, ImageView imageView, String link, final IEventData eventData){
        imageView.setImageDrawable(ImageUtils.roundedImage(context, getImageAvatar(link, new IEventData() {
            @Override
            public void EventSuccess() {

            }

            @Override
            public void EventFail(String message) {
                eventData.EventFail(message);
            }
        })));
    }

    public static void binDingImageControl(Context context, ImageButton imageView, String link, final IEventData eventData){
        imageView.setImageDrawable(ImageUtils.roundedImage(context, getImageAvatar(link, new IEventData() {
            @Override
            public void EventSuccess() {

            }

            @Override
            public void EventFail(String message) {
                eventData.EventFail(message);
            }
        })));
    }

    public static Bitmap getImageAvatar(String imgBase64, IEventData eventData) {
        Bitmap src = null;
        try {
            byte[] decodedString = Base64.decode(imgBase64, Base64.DEFAULT);
            src = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } catch (Exception e) {
            eventData.EventFail(e.getMessage());
        }
        return src;
    }

}

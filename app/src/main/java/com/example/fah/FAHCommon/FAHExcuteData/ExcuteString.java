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

    public static Boolean IsNullOrEmpty(String string) {
        return string == null || string == "";
    }

    public static String GetContentNotification(int type, String... params) {
        switch (type) {
            case 1:
                /**
                 *  {Tên user} đã ứng tuyền vào bài đăng {Tên bài đăng} của bạn
                 *  param[0]: {Tên user}
                 *  param[1]: {Tên bài đăng}
                 */
                return String.format("%s đã ứng tuyền vào bài đăng %s của bạn.", params[0], params[1]);
            case 2:
                /**
                 *  {Tên user company} vừa đăng bài mới
                 *  param[0]: {Tên user company}
                 */
                return String.format("%s vừa đăng bài mới", params[0]);
            case 3:
                /**
                 *  Việc ứng tuyển vào bài đăng {Tên bài đăng} đã được chấp thuận
                 *  param[0]: {Tên bài đăng}
                 */
                return String.format("Việc ứng tuyển vào bài đăng %s đã được chấp thuận", params[0]);
            case 4:
                /**
                 *  Bài đăng {Tên bài viêt} đã được duyệt bởi admin
                 *  param[0]: {Tên bài viêt}
                 */
                return String.format("Bài đăng %s đã được duyệt bởi admin", params[0]);
            case 5:
                /**
                 *  Bài đăng {Tên bài viết} đã bị xóa bởi Admin do vi phạm điều khoản của ứng dụng
                 *  param[0]: {Tên bài viết}
                 */
                return String.format("Bài đăng %s đã bị xóa bởi Admin do vi phạm điều khoản của ứng dụng", params[0]);
            default:
                return "";
        }
    }
}

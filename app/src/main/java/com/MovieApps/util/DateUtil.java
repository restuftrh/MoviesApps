package com.MovieApps.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    public static String getStringDate(String time) {
        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        Date sdate = null;
        try {
            sdate = sdfSource.parse(time);
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
        sdfSource = new SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault());
        return sdfSource.format(sdate);
    }

    public static String getStringDate(Long milis) {
        return getStringDate(milis, "dd/MM/yyyy HH:mm");
    }


    public static String getStringDate(Long milis, String format) {
        SimpleDateFormat df1 = new SimpleDateFormat(format, Locale.getDefault());
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(milis);
        return df1.format(c.getTime());
    }


    public static String getNowStringDate() {
        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault());
        sdfSource.setTimeZone(TimeZone.getTimeZone("UTC+7"));
        Date sdate = new Date();
        return sdfSource.format(sdate);
    }

    public static String getDIfferentStringValue(long diffSecond) {
        String time = "";
        if (diffSecond > (365 * 60 * 60 * 24 * 10)) {
            time = "-";
        } else if (diffSecond > (365 * 60 * 60 * 24)) {
            time = diffSecond / (365 * 60 * 60 * 24) + " year ago";
        } else if (diffSecond > (60 * 60 * 24)) {
            time = diffSecond / (60 * 60 * 24) + " days ago";
        } else if (diffSecond > (60 * 60)) {
            time = diffSecond / (60 * 60) + " hours ago";
        } else if (diffSecond > 60) {
            time = diffSecond / (60) + " minutes ago";
        } else if (diffSecond == 0) {
            time = "-";
        } else {
            time = "Seconds ago";
        }

        int count;
        try {
            count = Integer.parseInt(time.substring(0, 2).trim());
            if (count == 1) {
                if (!time.contains("second")) {
                    time = time.replace("s", "");
                }
            }
        } catch (Exception e) {
        }

        return time;
    }


    public static long getMilisFromTimeStamp(String time) {
        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault());
        sdfSource.setTimeZone(TimeZone.getTimeZone("UTC+7"));
        Date sdate = null;
        try {
            sdate = sdfSource.parse(time);
            return sdate.getTime();
        } catch (ParseException e) {
            System.out.println(e.toString());
            return 0;
        }
    }

    public static long getDifference(String timestamp) {
        long milis = getMilisFromTimeStamp(timestamp);
        return (new Date().getTime() - new Date(milis).getTime()) / 1000;
    }
}

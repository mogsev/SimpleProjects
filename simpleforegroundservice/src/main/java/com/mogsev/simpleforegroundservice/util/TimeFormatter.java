package com.mogsev.simpleforegroundservice.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.mogsev.simpleforegroundservice.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Zwei on 01.04.2016.
 */
public final class TimeFormatter {
    private static final String TAG = TimeFormatter.class.getSimpleName();

    public static int DAY_IN_MILLIS = 86400000;
    private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;

    private static final long MILLIS_IN_SECONDS = 1000;
    private static final long MILLIS_IN_MINUTE = MILLIS_IN_SECONDS * 60;
    private static final long MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60;

    public static final String TIME_PATTERN_HH_MM_SS = "%02d:%02d:%02d";

    public static final String DATE_FORMAT_HHMMSS = "HH:mm:ss";
    public static final String DATE_FORMAT_DDMMYYYY = "dd.MM.yyyy";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy.MM.dd";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_DDMMYYYYHHMM = "dd.MM.yyyy HH:mm";
    public static final String DATE_FORMAT_DDMMMMYYYYHHMMA = "dd, MMMM yyyy, hh:mm a";

    public static final String TIME_PATTERN = "%1$02d:%2$02d:%3$02d";

    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    private TimeFormatter() {
        // empty
    }

    public static String format(long millis) {
        return format(new Date(millis));
    }

    public static String format(Date date) {
        synchronized (dateTimeFormatter) {
            return dateTimeFormatter.format(date);
        }
    }

    public static long parseMillis(String date) {
        synchronized (dateTimeFormatter) {
            try {
                return dateTimeFormatter.parse(date).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public static
    @Nullable
    Date parseDate(String date) {
        synchronized (dateTimeFormatter) {
            try {
                return dateTimeFormatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static String dateToText(Context context, Date date) {
        String result;
        Calendar now = Calendar.getInstance();
        Calendar original = Calendar.getInstance();
        original.setTime(date);
        long duration = now.getTime().getTime() - date.getTime();
        truncateCalendar(now);
        truncateCalendar(original);
        Calendar yesterday = (Calendar) now.clone();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);

        if (now.equals(original)) {
            result = context.getString(R.string.time_today, date);
        } else if (yesterday.equals(original)) {
            result = context.getString(R.string.time_yesterday, date);
        } else {
            result = context.getString(R.string.time_and_day, date);
        }
        return result;
    }

    public static void truncateCalendar(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static String getTime(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMM);
        return format.format(date);
    }

    public static String getTime(long timestamp, String dateFormat) {
        if (TextUtils.isEmpty(dateFormat)) {
            return getTime(timestamp);
        }
        Date date = new Date(timestamp);
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }


    public static String getTimeFormat(long millis) {
        return getTimeFormat(millis, TIME_PATTERN_HH_MM_SS);
    }

    public static String getTimeFormat(long millis, String timePattern) {
        long seconds = millis / SECOND;
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / 60 / 60) % 24;
        return String.format(timePattern, h, m, s);
    }


    public static long extractHours(long millis) {
        return millis / MILLIS_IN_HOUR;
    }

    public static long extractMinutes(long millis) {
        return ((millis) - ((extractHours(millis)) * MILLIS_IN_HOUR)) / MILLIS_IN_MINUTE;
    }

    public static long extractSeconds(long millis) {
        long hours = extractHours(millis);
        long minutes = extractMinutes(millis);
        return (millis - hours * MILLIS_IN_HOUR - minutes * MILLIS_IN_MINUTE) / MILLIS_IN_SECONDS;
    }

}

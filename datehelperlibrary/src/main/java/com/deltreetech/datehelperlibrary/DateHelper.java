package com.deltreetech.datehelperlibrary;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    private static final int DAY = 1;
    private static final int MONTH = 2;
    private static final int YEAR = 3;

    private static final int SECOND = 4;
    private static final int MINUTE = 5;
    private static final int HOUR = 6;

    private static final String NGDateTimeFormat = "dd-MMM-yyyy hh:mm a";
    private static final String USDateTimeFormat = "MM/dd/yyyy HH:mm";
    private static final String CurrentDateTimeFormat = NGDateTimeFormat;

    public static int DateDiff(Calendar firstDate, Calendar secondDate, int pattern) {
        long diff = firstDate.getTimeInMillis() - secondDate.getTimeInMillis();
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        int result = 0;
        if (pattern == DAY) {
            if (firstDate.get(Calendar.DAY_OF_MONTH) == secondDate.get(Calendar.DAY_OF_MONTH)
                    && firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH)
                    && firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR)) {
                return result;
            }
        }
        switch (pattern) {
            case DAY:
                result = (int) dayCount;
                break;
            case MONTH:
                result = (int) dayCount / 52;
                break;
            case YEAR:
                result = (int) dayCount / 365;
                break;
        }
        return result;
    }

    public static int DateDiff(Date firstDate, Date secondDate, int pattern) {
        long diff = firstDate.getTime() - secondDate.getTime();
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        int result = 0;
        if (pattern == DAY) {
            if (firstDate.getDay() == secondDate.getDay()
                    && firstDate.getMonth() == secondDate.getMonth()
                    && firstDate.getYear() == secondDate.getYear()) {
                return result;
            }
        }
        switch (pattern) {
            case DAY:
                result = (int) dayCount;
                break;
            case MONTH:
                result = (int) dayCount / 52;
                break;
            case YEAR:
                result = (int) dayCount / 365;
                break;
        }
        return result;
    }

    public static int DateDiffWithToday(Calendar myDate, int pattern) {
        Calendar TodayDate = Calendar.getInstance();
        return DateDiff(myDate, TodayDate, pattern);
    }

    public static int DateDiffWithToday(Date myDate, int pattern) {
        Date TodayDate = new Date();
        return DateDiff(myDate, TodayDate, pattern);
    }

    public static int TimeDiff(Calendar firstDate, Calendar secondDate, int pattern) {
        long diff = firstDate.getTimeInMillis() - secondDate.getTimeInMillis();
        float secondCount = (float) diff / (1000);
        int result = 0;
        switch (pattern) {
            case SECOND:
                result = (int) secondCount;
                break;
            case MINUTE:
                result = (int) secondCount / 60;
                break;
            case HOUR:
                result = (int) secondCount / 360;
                break;
        }
        return result;
    }

    public static int TimeDiff(Date firstDate, Date secondDate, int pattern) {
        long diff = firstDate.getTime() - secondDate.getTime();
        float secondCount = (float) diff / (1000);
        int result = 0;
        switch (pattern) {
            case SECOND:
                result = (int) secondCount;
                break;
            case MINUTE:
                result = (int) secondCount / 60;
                break;
            case HOUR:
                result = (int) secondCount / 360;
                break;
        }
        return result;
    }

    public static int TimeDiffWithNow(Calendar myDate, int pattern) {
        Calendar TodayDate = Calendar.getInstance();
        return TimeDiff(myDate, TodayDate, pattern);
    }

    public static int TimeDiffWithNow(Date myDate, int pattern) {
        Date TodayDate = new Date();
        return TimeDiff(myDate, TodayDate, pattern);
    }

    public static Calendar getCalendarDateFromString(String myDate) {
        Calendar CalendarDate = Calendar.getInstance();

        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy", Locale.UK).parse(myDate);
        } catch (ParseException e) {
            //e.printStackTrace();
            Log.e("tacss", e.getMessage());
        }
        CalendarDate.setTime(date);
        return CalendarDate;
    }

    public static String getStringDate(Calendar myDate) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
        return sdf.format(myDate.getTime());
    }

    private static Calendar getCalendarDateTimeFromString(String myDate) {
        Calendar CalendarDate = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US).parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CalendarDate.setTime(date);
        return CalendarDate;
    }

    public static String getStringDateTime(Calendar myDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSS z", Locale.UK);
        return sdf.format(myDate.getTime());
    }

    public static String getStringDateTime(String myDate, boolean noSecond) {
        Calendar CalendarDate = getCalendarDateTimeFromString(myDate);
        String format = noSecond ? "dd-MMM-yyyy hh:mm a" : "dd-MMM-yyyy hh:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
        return sdf.format(CalendarDate.getTime()).replace("am", "AM").replace("pm", "PM");
    }

    public static String getDateStringDateTime(String myDate, boolean noSecond) {
        String format = noSecond ? "dd-MMM-yyyy hh:mm a" : "dd-MMM-yyyy hh:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
        Date newDate;
        try {
            newDate = sdf.parse(myDate);
            return getStringDateTime(newDate, noSecond);
        } catch (ParseException ex) {
            return myDate;
        }
    }

    public static String getStringTime(String myDate, boolean noSecond) {
        Calendar CalendarDate = getCalendarDateTimeFromString(myDate);
        String format = noSecond ? "hh:mm a" : "hh:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
        return sdf.format(CalendarDate.getTime()).replace("am", "AM").replace("pm", "PM");
    }

    static String getStringDateTime(Date myDate, boolean noSecond) {
        String format = noSecond ? "dd-MMM-yyyy hh:mm a" : "dd-MMM-yyyy hh:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(myDate).replace("am", "AM").replace("pm", "PM");
    }

    public static String getFormattedDate(long milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.
        DateFormat formatter = new SimpleDateFormat(CurrentDateTimeFormat, Locale.getDefault());

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Date result = new Date(milliSeconds);
        return formatter.format(result);
    }

    public static String getNewFormattedDate(Context ctx, long milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.
        //DateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return DateUtils.formatDateTime(ctx, milliSeconds,
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR |
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_WEEKDAY);
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
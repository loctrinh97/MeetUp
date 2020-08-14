package com.example.meetup.ulti;

import android.annotation.SuppressLint;

import com.example.meetup.model.dataLocal.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Define {
    public static final int STATUS_CODE_SUCCESS = 1;
    public static final int REQUEST_CODE_GPS_PERMISSION = 100;
    public static final String EVENT_STATUS_PERMANENT = "permanent";
    public static final int STATUS_DEFAULT = 0;
    public static final int STATUS_GOING = 1;
    public static final int STATUS_WENt = 2;
    public static final int PAGE_SIZE_DEFAULT = 10;
    private static  Date currentTime = Calendar.getInstance().getTime();
    private static  Locale locale = new Locale("vi");
    @SuppressLint("SimpleDateFormat")
    private static DateFormat dateFormat = new SimpleDateFormat("E, yyyy-MM-dd", locale);
    public static String checkDate(Event event){
        if (event.getSchedulePermanent() == null) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date startDate = simpleDateFormat.parse(event.getScheduleStartDate());
                Date endDate = simpleDateFormat.parse(event.getScheduleEndDate());

                if (currentTime.compareTo(startDate) < 0) {
                    return dateFormat.format(startDate);
                } else return dateFormat.format(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;

        } else {
            return Define.EVENT_STATUS_PERMANENT;
        }

    }
}

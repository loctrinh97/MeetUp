package com.example.meetup.ulti;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.example.meetup.R;
import com.example.meetup.model.dataLocal.Event;
import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Define {
    public static HashMap<Integer,Integer> mapImages ;
    public static void initMap(){
        mapImages = new HashMap<>();
        mapImages.put(ART_CUL,R.drawable.item1);
        mapImages.put(CAR_BUS, R.drawable.item2);
        mapImages.put(CAR_MOR,R.drawable.item3);
        mapImages.put(COM_ENV,R.drawable.item4);
        mapImages.put(DANCING,R.drawable.item5);
        mapImages.put(EDU,R.drawable.item6);
        mapImages.put(FASHION,R.drawable.item8);
        mapImages.put(FITNESS,R.drawable.item9);
        mapImages.put(FOOD,R.drawable.item10);
        mapImages.put(GAME,R.drawable.item11);
        mapImages.put(LGBT,R.drawable.item12);
        mapImages.put(MOV_POL,R.drawable.item13);
        mapImages.put(HEALTH,R.drawable.item14);
        mapImages.put(HOBBIES,R.drawable.item15);
        mapImages.put(LANG,R.drawable.item16);
        mapImages.put(LIFE,R.drawable.item17);
        mapImages.put(BOOK,R.drawable.item18);
        mapImages.put(MOVIE,R.drawable.item20);
        mapImages.put(MUSIC,R.drawable.item21);
        mapImages.put(NEW_AGE,R.drawable.item22);
        mapImages.put(OUTDOOR,R.drawable.item23);
        mapImages.put(PARANORMAL,R.drawable.item24);
        mapImages.put(PARENT,R.drawable.item25);
        mapImages.put(PET,R.drawable.item26);
        mapImages.put(PHOTO,R.drawable.item27);
        mapImages.put(RELIGION,R.drawable.item28);
        mapImages.put(FANTASY,R.drawable.item29);
        mapImages.put(SINGLES,R.drawable.item30);
        mapImages.put(SOCIAL,R.drawable.item31);
        mapImages.put(SPORT,R.drawable.item32);
        mapImages.put(SUPPORT,R.drawable.item33);
        mapImages.put(TECH,R.drawable.item34);
        mapImages.put(WRITING,R.drawable.item36);
    }
    public static int getImage(int key){
        return mapImages.get(key);
    }
    public static final int ART_CUL = 1;
    public static final int CAR_BUS = 2;
    public static final int CAR_MOR = 3;
    public static final int COM_ENV = 4;
    public static final int DANCING = 5;
    public static final int EDU = 6;
    public static final int FASHION = 8;
    public static final int FITNESS = 9;
    public static final int FOOD = 10;
    public static final int GAME = 11;
    public static final int LGBT = 12;
    public static final int MOV_POL = 13;
    public static final int HEALTH = 14;
    public static final int HOBBIES = 15;
    public static final int LANG = 16;
    public static final int LIFE = 17;
    public static final int BOOK = 18;
    public static final int MOVIE = 20;
    public static final int MUSIC = 21;
    public static final int NEW_AGE = 22;
    public static final int OUTDOOR =23;
    public static final int PARANORMAL = 24;
    public static final int PARENT = 25;
    public static final int PET = 26;
    public static final int PHOTO = 27;
    public static final int RELIGION = 28;
    public static final int FANTASY = 29;
    public static final int SINGLES = 30;
    public static final int SOCIAL = 31;
    public static final int SPORT = 32;
    public static final int SUPPORT = 33;
    public static final int TECH = 34;
    public static final int WRITING = 36;
    public static final int STATUS_CODE_SUCCESS = 1;
    public static final int REQUEST_CODE_GPS_PERMISSION = 100;
    public static final String EVENT_STATUS_PERMANENT = "permanent";
    public static final int STATUS_DEFAULT = 0;
    public static final int STATUS_GOING = 1;
    public static final int STATUS_WENT = 2;
    public static final int PAGE_SIZE_DEFAULT = 10;
    public static final int ERROR_CODE_TOKEN = 101;
    public static double CURRENT_LOCATION_LAT;
    public static double CURRENT_LOCATION_LONG;
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

                if (currentTime.compareTo(startDate) <= 0) {
                    assert startDate != null;
                    return dateFormat.format(startDate);
                } else {
                    assert endDate != null;
                    return dateFormat.format(endDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;

        } else {
            return Define.EVENT_STATUS_PERMANENT;
        }

    }

    public static int TODAY =0;
    public static int TOMORROW =1;
    public static int SAME_WEEK =2;
    public static int NEXT_WEEK =3;
    public static int SAME_MONTH =4;
    public static int NEXT_MONTH =5;
    public static int PERMANENT =6;
    public static String PRE_TOKEN ="tokenPref";
    public static String TOKEN ="token";
    public static boolean tokenExpired;


}

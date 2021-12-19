package com.example.aie.utilities;

import android.content.Context;
import android.util.Log;

import com.example.aie.database.RoomDB;
import com.example.aie.model.Day;
import com.example.aie.model.MainData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utilities {

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String day = dateFormat.format(date);
        return day;
    }

    public static ArrayList<String> fillArrayListTime() {
        ArrayList<String> hourTimeArrayL = new ArrayList<String>();

        hourTimeArrayL.add("0");
        hourTimeArrayL.add("1");
        hourTimeArrayL.add("2");
        hourTimeArrayL.add("3");
        hourTimeArrayL.add("4");
        hourTimeArrayL.add("5");
        hourTimeArrayL.add("6");
        hourTimeArrayL.add("7");
        hourTimeArrayL.add("8");
        hourTimeArrayL.add("9");
        hourTimeArrayL.add("10");
        hourTimeArrayL.add("11");
        hourTimeArrayL.add("12");
        hourTimeArrayL.add("1");
        hourTimeArrayL.add("2");
        hourTimeArrayL.add("3");
        hourTimeArrayL.add("4");
        hourTimeArrayL.add("5");
        hourTimeArrayL.add("6");
        hourTimeArrayL.add("7");
        hourTimeArrayL.add("8");
        hourTimeArrayL.add("9");
        hourTimeArrayL.add("10");
        hourTimeArrayL.add("11");
        hourTimeArrayL.add("12");

        return hourTimeArrayL;
    }

    public static ArrayList<String> fillArrayListTime2() {
        ArrayList<String> hourTimeArrayL2 = new ArrayList<String>();

        hourTimeArrayL2.add("0");
        hourTimeArrayL2.add("1");
        hourTimeArrayL2.add("2");
        hourTimeArrayL2.add("3");
        hourTimeArrayL2.add("4");
        hourTimeArrayL2.add("5");
        hourTimeArrayL2.add("6");
        hourTimeArrayL2.add("7");
        hourTimeArrayL2.add("8");
        hourTimeArrayL2.add("9");
        hourTimeArrayL2.add("10");
        hourTimeArrayL2.add("11");
        hourTimeArrayL2.add("12");
        hourTimeArrayL2.add("13");
        hourTimeArrayL2.add("14");
        hourTimeArrayL2.add("15");
        hourTimeArrayL2.add("16");
        hourTimeArrayL2.add("17");
        hourTimeArrayL2.add("18");
        hourTimeArrayL2.add("19");
        hourTimeArrayL2.add("20");
        hourTimeArrayL2.add("21");
        hourTimeArrayL2.add("22");
        hourTimeArrayL2.add("23");
        hourTimeArrayL2.add("24");

        return hourTimeArrayL2;
    }

    public static int getHourNow() {
        SimpleDateFormat format = new SimpleDateFormat("HH", Locale.US);
        String hour = format.format(new Date());

        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        return hourOfDay;
    }

    public static ArrayList<Day> getDateTo7DayAfterThisDay() {
        ArrayList<Day> daysWeekArrayL = new ArrayList<Day>();
        for (int i=0;i<8;i++)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i);
            Date date = calendar.getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String nextDay = dateFormat.format(date);

            daysWeekArrayL.add(new Day((String) android.text.format.DateFormat.format("EEEE", date),nextDay));

        }
        return daysWeekArrayL;
    }

    public static Date getDateToday() {
        ArrayList<Day> daysWeekArrayL = new ArrayList<Day>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date date = calendar.getTime();

        return date;
    }

    public static ArrayList<MainData> fillMainData(Context context) {
        ArrayList<MainData> mainDataArrayList = new ArrayList<MainData>();

        List<MainData> mainDataList;
        RoomDB database = RoomDB.getInstance(context);
        mainDataList = database.mainDao().getAll();
        mainDataArrayList.addAll(mainDataList);
        Collections.reverse(mainDataArrayList);

        return mainDataArrayList;
    }

    public static boolean checkIfReservationUsedBefore(Context context,String reservation_name) {
        boolean used=true;
        ArrayList<MainData> mainDataArrayList = new ArrayList<MainData>();

        List<MainData> mainDataList;
        RoomDB database = RoomDB.getInstance(context);
        mainDataList = database.mainDao().getAll();
        mainDataArrayList.addAll(mainDataList);

        for (int i =0;i<mainDataArrayList.size();i++)
        {
            if (mainDataArrayList.get(i).getName().equals(reservation_name))
                used =false;
        }
        return used;
    }
}

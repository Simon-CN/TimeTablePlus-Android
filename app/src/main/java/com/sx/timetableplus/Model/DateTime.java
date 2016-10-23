package com.sx.timetableplus.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sx on 2016/10/23.
 */

public class DateTime {
    private Calendar time;
    private int week;

    public DateTime(Calendar time, int week) {
        this.time = time;
        this.week = week;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getWeek() {
        return "第" + week + "周";
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDayofMonth() {
        return time.get(Calendar.DAY_OF_MONTH);
    }

    public String getDayofWeek() {
        String s;
        switch (time.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                s = "星期一";
                break;
            case Calendar.TUESDAY:
                s = "星期二";
                break;
            case Calendar.WEDNESDAY:
                s = "星期三";
                break;
            case Calendar.THURSDAY:
                s = "星期四";
                break;
            case Calendar.FRIDAY:
                s = "星期五";
                break;
            case Calendar.SATURDAY:
                s = "星期六";
                break;
            case Calendar.SUNDAY:
                s = "星期日";
                break;
            default:
                s = "";
                break;
        }
        return s;
    }

    public int getDayofWeekNum() {
        int s = time.get(Calendar.DAY_OF_WEEK);
        return s;
    }

    public String getDate() {
        return time.get(Calendar.YEAR) + "." + (time.get(Calendar.MONTH) + 1);
    }

}

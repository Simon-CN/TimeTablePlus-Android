package com.sx.timetableplus.ViewModel;

import com.sx.timetableplus.Model.LessonInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sx on 2016/10/20.
 */

public class TimetableViewModel {
    private Date currentTime;
    private int weekNum;
    private List<ArrayList<LessonInfo>> timetable;

    public TimetableViewModel() {
        timetable = new ArrayList<>();
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public List<ArrayList<LessonInfo>> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<ArrayList<LessonInfo>> timetable) {
        this.timetable = timetable;
    }
}

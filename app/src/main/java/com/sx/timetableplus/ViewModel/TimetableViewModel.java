package com.sx.timetableplus.ViewModel;

import com.sx.timetableplus.Model.DateTime;
import com.sx.timetableplus.Model.LessonInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sx on 2016/10/20.
 */

public class TimetableViewModel {
    private DateTime time;
    private List<ArrayList<LessonInfo>> timetable;

    public TimetableViewModel() {
        timetable = new ArrayList<>();
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public List<ArrayList<LessonInfo>> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<ArrayList<LessonInfo>> timetable) {
        this.timetable = timetable;
    }
}

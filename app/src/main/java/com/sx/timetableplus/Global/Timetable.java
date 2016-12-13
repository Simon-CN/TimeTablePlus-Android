package com.sx.timetableplus.Global;

import com.sx.timetableplus.Model.LessonInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by sx on 2016/10/27.
 */

public class Timetable {
    public static int weekNum;
    public static List<ArrayList<LessonInfo>> timetable;

    public static void AddLesson(LessonInfo lesson) {
        if (IsEmpty())
            timetable = new ArrayList<>(7);
        InsertLesson(lesson);
    }

    public static boolean IsEmpty() {
        return timetable.isEmpty();
    }

    private static void InsertLesson(LessonInfo lesson) {
        int week = lesson.getDayofweek();
        int flag = 0;
        for (int i = timetable.get(week).size() - 1; i >= 0; i--) {
            if (timetable.get(week).get(i).getEndTime() < lesson.getEndTime()) {
                timetable.get(week).add(i, lesson);
                flag++;
                break;
            }
        }
        if (flag != 1) {
            timetable.get(week).add(0, lesson);
        }
    }
}

package com.sx.timetableplus.Model;

import com.sx.timetableplus.Global.StaticResource;

import java.io.Serializable;

/**
 * Created by sx on 2016/10/21.
 */

public class LessonInfo {
    private int id;
    private String name;
    private String classroom;
    private String teacher;
    private int dayofweek;
    private int startTime;
    private int endTime;
    private int startWeek;
    private int endWeek;
    private int term;


    public LessonInfo() {
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getSchoolTerm() {
        return term == 1 ? "春季学期" : "秋季学期";
    }

    public String getLessonTime() {
        return startTime + "-" + endTime;
    }

    public String getWeekNum() {
        return startWeek + "-" + endWeek + "周";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(int dayofweek) {
        this.dayofweek = dayofweek;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}

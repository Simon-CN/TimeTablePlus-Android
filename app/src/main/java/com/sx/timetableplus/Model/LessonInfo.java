package com.sx.timetableplus.Model;

import java.io.Serializable;

/**
 * Created by sx on 2016/10/21.
 */

public class LessonInfo implements Serializable{
    private String name;
    private String classroom;

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

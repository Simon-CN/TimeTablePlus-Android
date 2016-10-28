package com.sx.timetableplus.Model;

/**
 * Created by sx on 2016/10/28.
 */

public class School {
    private int id;
    private String name;

    public School(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

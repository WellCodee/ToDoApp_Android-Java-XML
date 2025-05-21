package com.example.todoapp_wellcodee;

import java.io.Serializable;

public class ToDo implements Serializable {
    String task;
    String date;
    boolean isUrget;

    public ToDo(String task, String date, boolean isUrget) {
        this.task = task;
        this.date = date;
        this.isUrget = isUrget;
    }

    public void setUrget(boolean urget) {
        isUrget = urget;
    }
}

package com.example.shamsad.tutionhub.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shamsad on 11/13/17.
 */

public class InfiniteFeedInfo {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("caption")
    @Expose
    private String caption;

    @SerializedName("salary")
    @Expose
    private String salary;

    @SerializedName("routine")
    @Expose
    private String routine;

    @SerializedName("time")
    @Expose
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

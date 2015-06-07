package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.text.format.Time;

/**
 * Created by jgee on 6/6/15.
 */
public class Entry {
    private String startTime;
    private String endTime;
    private Integer cat;
    private String label;
    private String annotation;
    private Integer color;

    public Entry(String startTime, String endTime, Integer cat, String label, String annotation, Integer color){
        this.startTime = startTime;
        this.endTime = endTime;
        this.cat = cat;
        this.label = label;
        this.annotation = annotation;
        this.color = color;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    public Integer getCat(){
        return this.cat;
    }

    public String getLabel(){
        return this.label;
    }

    public String getAnnotation(){
        return this.annotation;
    }

    public Integer getColor(){
        return this.color;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public void setCat(Integer cat){
        this.cat = cat;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public void setAnnotation(String annotation){
        this.annotation = annotation;
    }

    public void setColor(Integer color){
        this.color = color;
    }

}

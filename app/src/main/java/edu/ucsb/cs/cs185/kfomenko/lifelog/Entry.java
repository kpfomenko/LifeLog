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

    public Entry(String startTime, String endTime, Integer cat, String label, String annotation){
        this.startTime = startTime;
        this.endTime = endTime;
        this.cat = cat;
        this.label = label;
        this.annotation = annotation;
    }

    public void changeLabel(String newLabel){
        this.label = newLabel;
    }

    public void changeAnnotation(String newAnnotation){
        this.annotation = newAnnotation;
    }

}

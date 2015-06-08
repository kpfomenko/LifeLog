package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.Time;


/**
 * Created by jgee on 6/6/15.
 */
public class Entry implements Parcelable{
    private String startTime;
    private String endTime;
    private String cat;
    private String label;
    private String annotation;
    private Integer color;

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeString(this.startTime);
        out.writeString(this.endTime);
        out.writeString(this.cat);
        out.writeString(this.label);
        out.writeString(this.annotation);
        out.writeInt(this.color);
    }

    public Entry(String startTime, String endTime, String cat, String label, String annotation, Integer color) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cat = cat;
        this.label = label;
        this.annotation = annotation;
        this.color = color;
    }

    public Entry(Parcel in){
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.cat = in.readString();
        this.label = in.readString();
        this.annotation = in.readString();
        this.color = in.readInt();
    }

    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>(){
        public Entry createFromParcel(Parcel in){
            return new Entry(in);
        }
        public Entry[] newArray(int size){
            return new Entry[size];
        }
    };

    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    public String getCat(){
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

    public void setCat(String cat){
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

    @Override
    public boolean equals(Object other){
        if(other == null) return false;
        if(other == this) return true;
        if(!(other instanceof Entry)) return false;
        Entry o = (Entry) other;
        return this.startTime.equals(o.getStartTime()) && this.endTime.equals(o.getEndTime()) && this.cat.equals(o.getCat()) && this.label.equals(o.getLabel()) && this.annotation.equals(o.getAnnotation());
    }

}

package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by jgee on 6/7/15.
 */
public class HomeCard extends android.support.v7.widget.CardView {
    private Entry entry;

    public HomeCard(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public Entry getEntry(){
        return this.entry;
    }

    public void setEntry(Entry entry){
        this.entry = new Entry(entry.getStartTime(), entry.getEndTime(), entry.getCat(), entry.getLabel(), entry.getAnnotation(), entry.getColor());
    }
}

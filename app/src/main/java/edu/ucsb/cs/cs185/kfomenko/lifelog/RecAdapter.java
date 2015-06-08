package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by jgee on 5/9/15.
 */
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{
    private ArrayList<Entry> myData;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public edu.ucsb.cs.cs185.kfomenko.lifelog.HomeCard card;
        public TextView startTime;
        public TextView category;
        public TextView endTime;
        public TextView label;
        public TextView anno;
        public RelativeLayout header;

        public ViewHolder(CardView v){
            super(v);
            card = (edu.ucsb.cs.cs185.kfomenko.lifelog.HomeCard) v;
            startTime = (TextView) v.findViewById(R.id.home_card_start_time);
            category = (TextView) v.findViewById(R.id.home_card_cat);
            endTime = (TextView) v.findViewById(R.id.home_card_end_time);
            label = (TextView) v.findViewById(R.id.home_card_label);
            anno = (TextView) v.findViewById(R.id.home_card_anno);
            header = (RelativeLayout) v.findViewById(R.id.home_card_header);
        }

    }

    public RecAdapter(ArrayList<Entry> data){
        myData = data;
    }

    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        //create a new view
        context = parent.getContext();
        CardView v = (CardView) LayoutInflater.from(context).inflate(R.layout.home_card, parent, false);
        //set views parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        //get element from dataset at this position
        //replace contents of your view with that element
        Entry temp = myData.get(position);
        holder.card.setEntry(temp);
        holder.startTime.setText(temp.getStartTime());
        holder.category.setText(temp.getCat());
        holder.endTime.setText(temp.getEndTime());
        holder.label.setText(temp.getLabel());
        holder.anno.setText(temp.getAnnotation());
        holder.header.setBackgroundResource(temp.getColor());
        int multiplyer = 1;
//        multiplyer = getMinutes(temp.getEndTime()) - getMinutes(temp.getStartTime());
//        multiplyer = multiplyer/15;
        float density  = context.getResources().getDisplayMetrics().density;
        int singlesize = (int) (Math.round((float)50 * density));
        int height = (int) (singlesize * multiplyer);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        params.setMargins(0, 0, 0, 0);
        holder.card.setLayoutParams(params);
        holder.card.setPreventCornerOverlap(false);
    }

    @Override
    public int getItemCount(){
        if(myData == null){
            return 0;
        }
        return myData.size();
    }

    public void update(ArrayList<Entry> data){
        myData = data;
        this.notifyDataSetChanged();
    }

    public int getMinutes(String time) {
        //have startTime and endTime --> need to make sure they are realistic
        String timeArray[] = time.split(":");

        int hour = Integer.parseInt(timeArray[0]);

        String tempTimeArr[] = timeArray[1].split(" ");

        int min = Integer.parseInt(tempTimeArr[0]);

        String ampm = tempTimeArr[1];


        //Convert to 24 Hour Format
        if (ampm.equals("PM") && hour != 12) {
            hour += 12;
        }
        if (hour == 12 && ampm.equals("AM")) {
            hour = 0;
        }
        return hour*60 + min;
    }
}

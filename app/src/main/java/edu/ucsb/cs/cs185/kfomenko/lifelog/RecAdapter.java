package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        public TextView label;
        public LinearLayout header;

        public ViewHolder(CardView v){
            super(v);
            card = (edu.ucsb.cs.cs185.kfomenko.lifelog.HomeCard) v;
            startTime = (TextView) v.findViewById(R.id.home_card_start_time);
            category = (TextView) v.findViewById(R.id.home_card_category);
            label = (TextView) v.findViewById(R.id.home_card_label);
            header = (LinearLayout) v.findViewById(R.id.home_card_header);
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
        float density  = context.getResources().getDisplayMetrics().density;
        int fifty = (int) (Math.round((float)50 * density));
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, fifty);
        params.setMargins(0, 0, 0, 0);
        holder.card.setLayoutParams(params);
        holder.card.setPreventCornerOverlap(false);
        Entry temp = myData.get(position);
        holder.card.setEntry(temp);
        holder.startTime.setText(temp.getStartTime());
//        holder.startTime.setBackgroundResource(temp.getColor());
        holder.category.setText("@");
//        holder.category.setBackgroundResource(temp.getColor());
        holder.label.setText(temp.getLabel());
        holder.header.setBackgroundResource(temp.getColor());
        //TODO: set background of header to category color
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
}


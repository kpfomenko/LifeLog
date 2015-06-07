package edu.ucsb.cs.cs185.kfomenko.lifelog;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jgee on 5/9/15.
 */
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{
    private ArrayList<String> myData;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView card;

        public ViewHolder(CardView v){
            super(v);
            card = (CardView) v;
        }

    }

    public RecAdapter(ArrayList<String> data){
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
        int six = (int) (Math.round((float)6 * density));
        int twohundo = (int) (Math.round((float)200 * density));
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, twohundo);
        if(position == 0){
            params.setMargins(six, six, six, six);
            holder.card.setLayoutParams(params);
        }
        holder.card.setPreventCornerOverlap(false);
        File temp = new File(myData.get(position));
    }

    @Override
    public int getItemCount(){
        if(myData == null){
            return 0;
        }
        return myData.size();
    }

    public void update(ArrayList<String> data){
        myData = data;
        this.notifyDataSetChanged();
    }
}


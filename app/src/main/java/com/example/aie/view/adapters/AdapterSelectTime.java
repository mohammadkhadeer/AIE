package com.example.aie.view.adapters;

import static com.example.aie.utilities.Utilities.getHourNow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aie.R;
import java.util.ArrayList;

public class AdapterSelectTime extends
    RecyclerView.Adapter<AdapterSelectTime.ViewHolder>{
    private final Context context;
    public ArrayList<String> hourTimeArrayL = new ArrayList<String>();
    public ArrayList<String> hourTimeArrayL2 = new ArrayList<String>();
    int flagDayOrNotToDay,StartAtOrEndAt;
    PassTime passTime;
    public AdapterSelectTime(Context context
            , ArrayList<String> hourTimeArrayL
            , int flagDayOrNotToDay
            , ArrayList<String> hourTimeArrayL2
            , int StartAtOrEndAt
            ,PassTime passTime
    )
    {
        this.context = context;
        this.hourTimeArrayL = hourTimeArrayL;
        this.flagDayOrNotToDay = flagDayOrNotToDay;
        this.hourTimeArrayL2 = hourTimeArrayL2;
        this.StartAtOrEndAt = StartAtOrEndAt;
        this.passTime = passTime;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_select_time, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        fillInformationCard(context,position,holder);
        fillBackgroundCard(holder,position,context);
        actionListenerToSelectTime(position,holder,context);
    }

    private void actionListenerToSelectTime(int position, ViewHolder holder, Context context) {
        holder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passTime.onClickedTime(hourTimeArrayL.get(position),hourTimeArrayL2.get(position),position);
            }
        });
    }

    private void fillBackgroundCard(ViewHolder holder, int position, Context context) {
        //Log.i("TAG flagDayOrNotToDay",String.valueOf(flagDayOrNotToDay));
        if (flagDayOrNotToDay ==1) {
            int x = Integer.parseInt(hourTimeArrayL2.get(position));
            if (getHourNow() >=x && StartAtOrEndAt==500)
            {
                holder.relativeLayout_h.setBackgroundColor(ContextCompat.getColor
                        (context, R.color.iron3));
                holder.relativeLayout_h.setAlpha((float) 0.8);
            }else{
                if (getHourNow() >=x && StartAtOrEndAt !=500)
                {
                    holder.relativeLayout_h.setBackgroundColor(ContextCompat.getColor
                            (context, R.color.iron3));
                    holder.relativeLayout_h.setAlpha((float) 0.8);
                }else{
                    if ((getHourNow() <x && x<=StartAtOrEndAt) && StartAtOrEndAt !=500)
                    {
                        holder.relativeLayout_h.setBackgroundColor(ContextCompat.getColor
                                (context, R.color.custom_progress_red_progress));
                        holder.relativeLayout_h.setAlpha((float) 0.9);
                    }else{
                        holder.textView_Time.setTextColor(ContextCompat.getColor(context, R.color.black));
                    }
                }
            }
        }else{
            int x = Integer.parseInt(hourTimeArrayL2.get(position));
            if (x<=StartAtOrEndAt &&StartAtOrEndAt !=500)
            {
                holder.relativeLayout_h.setBackgroundColor(ContextCompat.getColor
                        (context, R.color.custom_progress_red_progress));
                holder.relativeLayout_h.setAlpha((float) 0.9);
            }else{
                holder.textView_Time.setTextColor(ContextCompat.getColor(context, R.color.black));
            }
            holder.textView_Time.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
    }

    private void fillInformationCard(Context context, int position, ViewHolder holder) {
        if (flagDayOrNotToDay ==1)
        {
            int x =Integer.parseInt(hourTimeArrayL2.get(position));
            if (x<12)
            {
                holder.textView_Time.setText(hourTimeArrayL.get(position)+":"+"00"+" "+"AM");
            }else{
                holder.textView_Time.setText(hourTimeArrayL.get(position)+":"+"00"+" "+"PM");}
        }else{
            fillNewDay(holder,position,context);
        }
    }

    public interface PassTime {
        void onClickedTime(String time1,String time2,int position);
    }

    @Override
    public int getItemCount() {
        return hourTimeArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_Time;
        RelativeLayout relativeLayout_h,cover;
        public ViewHolder(View itemView) {
            super(itemView);
            textView_Time = (TextView) itemView.findViewById(R.id.timeTV) ;
            relativeLayout_h = (RelativeLayout) itemView.findViewById(R.id.h) ;
            cover = (RelativeLayout) itemView.findViewById(R.id.cover) ;
        }
    }
    private void fillNewDay(ViewHolder holder, int position, Context context) {
        if (position<12)
        {
            holder.textView_Time.setText(hourTimeArrayL.get(position)+":"+"00"+" "+"AM");
        }else{
            holder.textView_Time.setText(hourTimeArrayL.get(position)+":"+"00"+" "+"PM");
        }
    }


}

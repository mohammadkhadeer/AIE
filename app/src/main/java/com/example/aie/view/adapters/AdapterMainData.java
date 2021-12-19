package com.example.aie.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aie.R;
import com.example.aie.model.Day;
import com.example.aie.model.MainData;

import java.util.ArrayList;

public class AdapterMainData extends
        RecyclerView.Adapter<AdapterMainData.ViewHolder>{

    private final Context context;
    public ArrayList<MainData> mainDataArrayList = new ArrayList<MainData>();
    PassMainData passMainData;
    public AdapterMainData(Context context
            , ArrayList<MainData> mainDataArrayList
            , PassMainData passMainData
    )
    {
        this.context = context;
        this.mainDataArrayList = mainDataArrayList;
        this.passMainData = passMainData;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_main_data, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        fillInformationCard(context,position,holder);
        changeColorCoverWhenTutch(holder,position,context);
    }

    private void changeColorCoverWhenTutch(final ViewHolder holder, final int position, final Context context) {
        holder.relativeLayout_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passMainData.onClickedMainDate(mainDataArrayList.get(position));
            }
        });

    }

    private void fillInformationCard(Context context, int position, ViewHolder holder) {
        holder.reservation_name.setText(": "+mainDataArrayList.get(position).getName());
        holder.creator_name.setText(": "+mainDataArrayList.get(position).getCreator_name());

        holder.reservation_date_and_day.setText(": "+mainDataArrayList.get(position).getTime_range_date()
                +"  "+mainDataArrayList.get(position).getName_of_day());
        holder.reservation_time_range.setText(
                ": ["+ mainDataArrayList.get(position).getTime_range_start()+" : "

                +mainDataArrayList.get(position).getTime_range_end()+"]");
    }

    public interface PassMainData {
        void onClickedMainDate(MainData mainData);
    }

    @Override
    public int getItemCount() {
        return mainDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout_cover;
        TextView reservation_name,creator_name,reservation_date_and_day,reservation_time_range;
        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout_cover = (RelativeLayout) itemView.findViewById(R.id.cover) ;
            reservation_name = (TextView) itemView.findViewById(R.id.reservation_name) ;
            creator_name = (TextView) itemView.findViewById(R.id.creator_name) ;
            reservation_date_and_day = (TextView) itemView.findViewById(R.id.reservation_date_and_day) ;
            reservation_time_range = (TextView) itemView.findViewById(R.id.reservation_time_range) ;
        }
    }


}

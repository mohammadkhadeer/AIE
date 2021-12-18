package com.example.aie.view.adapters;

import android.content.Context;
import android.content.SharedPreferences;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aie.R;
import com.example.aie.model.Day;
import java.util.ArrayList;

public class AdapterSelectDate extends
        RecyclerView.Adapter<AdapterSelectDate.ViewHolder>{

    private final Context context;
    public ArrayList<Day> daysWeekArrayL = new ArrayList<Day>();
    PassDate passDate;
    public AdapterSelectDate(Context context
            , ArrayList<Day> daysWeekArrayL
            ,PassDate passDate
    )
    {
        this.context = context;
        this.daysWeekArrayL = daysWeekArrayL;
        this.passDate = passDate;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_select_date, viewGroup, false);
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
                passDate.onClickedDate(daysWeekArrayL.get(position));
            }
        });

    }

    private void fillInformationCard(Context context, int position, ViewHolder holder) {
        holder.textView_nameOfDay.setText(daysWeekArrayL.get(position).getName_of_day());
        holder.textView_dateOfDay.setText(daysWeekArrayL.get(position).getDate_of_day());
    }

    public interface PassDate {
        void onClickedDate(Day day);
    }

    @Override
    public int getItemCount() {
        return daysWeekArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout_cover;
        TextView textView_nameOfDay,textView_dateOfDay;
        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout_cover = (RelativeLayout) itemView.findViewById(R.id.cover) ;
            textView_nameOfDay = (TextView) itemView.findViewById(R.id.nameOfDayTV) ;
            textView_dateOfDay = (TextView) itemView.findViewById(R.id.dateOfDayTV) ;
        }
    }


}

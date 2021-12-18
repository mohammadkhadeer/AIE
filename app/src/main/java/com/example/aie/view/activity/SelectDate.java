package com.example.aie.view.activity;

import static com.example.aie.utilities.Utilities.fillArrayListTime;
import static com.example.aie.utilities.Utilities.fillArrayListTime2;
import static com.example.aie.utilities.Utilities.getDate;
import static com.example.aie.utilities.Utilities.getDateTo7DayAfterThisDay;
import static com.example.aie.utilities.Utilities.getDateToday;
import static com.example.aie.utilities.Utilities.getHourNow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aie.R;
import com.example.aie.model.Day;
import com.example.aie.view.adapters.AdapterSelectDate;
import com.example.aie.view.adapters.AdapterSelectTime;

import java.util.ArrayList;

public class SelectDate extends AppCompatActivity implements AdapterSelectTime.PassTime, AdapterSelectDate.PassDate {
    int flagDayOrNotToDay=-1,startAtOrEndAtRVNow;
    LinearLayout select_date_reservation_ll,select_date_reservation_date_ll
            ,select_date_back_ll,select_date_date_ll,select_date_back2_ll;
    Button yes_btn,no_btn,select_date_start_at_btn,select_date_end_at_btn;
    TextView select_date_reservation_date_tv,select_date_name_of_day_tv
            ,select_date_start_at_tv,select_date_end_at_tv;
    Dialog myDialog;
    public ArrayList<String> hourTimeArrayL = new ArrayList<String>();
    public ArrayList<String> hourTimeArrayL2 = new ArrayList<String>();
    public ArrayList<Day> dayWeekArrayL = new ArrayList<Day>();
    AdapterSelectTime adapterSelectTime;
    RelativeLayout select_date_reservation_rl;

    RecyclerView recyclerViewTime,select_date_recycler_view;
    private GridLayoutManager mGridLayoutManager;
    int timeStartAt;

    RecyclerView.LayoutManager layoutManagerDate;

    AdapterSelectDate adapterSelectDate;
    EditText editText;

    String date,name_of_day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        statusBarColor();
        cast();
        actionListener();
    }

    private void actionListener() {
        actionListenerToYes();
        actionListenerToDeleteLL();
        actionListenerToStartAt();
        actionListenerToEndAt();
        actionListenerToNo();
        actionListenerToBack2();
        actionListenerToReservationNow();
    }

    private void actionListenerToReservationNow() {
        select_date_reservation_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("reservation_name", editText.getText().toString());
                resultIntent.putExtra("date", date);
                resultIntent.putExtra("name_of_day", name_of_day);
                resultIntent.putExtra("start_at", select_date_start_at_tv.getText());
                resultIntent.putExtra("end_at", select_date_end_at_tv.getText());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void actionListenerToBack2() {
        select_date_back2_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_date_date_ll.setVisibility(View.GONE);
                select_date_reservation_ll.setVisibility(View.VISIBLE);
            }
        });
    }

    private void actionListenerToNo() {
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_date_reservation_ll.setVisibility(View.GONE);
                select_date_date_ll.setVisibility(View.VISIBLE);
                createDateRV();
            }
        });
    }

    private void createDateRV() {
        dayWeekArrayL = getDateTo7DayAfterThisDay();
        dayWeekArrayL.remove(0);

        select_date_recycler_view.setHasFixedSize(true);
        layoutManagerDate = new LinearLayoutManager(SelectDate.this,
                LinearLayoutManager.HORIZONTAL, false);
        select_date_recycler_view.setLayoutManager(layoutManagerDate);

        adapterSelectDate = new AdapterSelectDate(SelectDate.this,
                dayWeekArrayL,this);
        select_date_recycler_view.setAdapter(adapterSelectDate);
    }

    private void actionListenerToEndAt() {
        select_date_end_at_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAtOrEndAtRVNow =2;
                myDialog = new Dialog(SelectDate.this);
                ShowPopup(timeStartAt);
            }
        });
    }

    private void actionListenerToStartAt() {
        select_date_start_at_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAtOrEndAtRVNow =1;
                myDialog = new Dialog(SelectDate.this);
                ShowPopup(500);
            }
        });
    }

    private void actionListenerToDeleteLL() {
        select_date_back_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reSetSTime();
                select_date_reservation_ll.setVisibility(View.VISIBLE);
                select_date_reservation_date_ll.setVisibility(View.GONE);
            }
        });
    }

    private void reSetSTime() {
        select_date_start_at_btn.setVisibility(View.VISIBLE);
        select_date_start_at_tv.setVisibility(View.GONE);
        select_date_end_at_btn.setVisibility(View.GONE);
        select_date_end_at_tv.setText("--");
    }

    private void actionListenerToYes() {
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagDayOrNotToDay =1;

                select_date_reservation_ll.setVisibility(View.GONE);
                select_date_reservation_date_ll.setVisibility(View.VISIBLE);
                String reservation_date = getResources().getString(R.string.reservation_date);
                date = getDate();
                name_of_day =(String) android.text.format.DateFormat.format("EEEE", getDateToday());
                select_date_reservation_date_tv.setText(reservation_date+" "+getDate());
                String today = getResources().getString(R.string.today);
                select_date_name_of_day_tv.setText("\""+today+"\"");
            }
        });
    }

    private void cast() {
        yes_btn = (Button) findViewById(R.id.select_date_yes_btn);
        no_btn = (Button) findViewById(R.id.select_date_no_btn);
        select_date_start_at_btn = (Button) findViewById(R.id.select_date_start_at_btn);
        select_date_end_at_btn= (Button) findViewById(R.id.select_date_end_at_btn);
        select_date_reservation_date_tv = (TextView) findViewById(R.id.select_date_reservation_date_tv);
        select_date_name_of_day_tv= (TextView) findViewById(R.id.select_date_name_of_day_tv);
        select_date_start_at_tv= (TextView) findViewById(R.id.select_date_start_at_tv);
        select_date_end_at_tv= (TextView) findViewById(R.id.select_date_end_at_tv);
        select_date_reservation_ll = (LinearLayout) findViewById(R.id.select_date_reservation_ll);
        select_date_reservation_date_ll = (LinearLayout) findViewById(R.id.select_date_reservation_date_ll);
        select_date_back_ll= (LinearLayout) findViewById(R.id.select_date_back_ll);
        select_date_date_ll= (LinearLayout) findViewById(R.id.select_date_date_ll);
        select_date_back2_ll= (LinearLayout) findViewById(R.id.select_date_back2_ll);
        select_date_recycler_view= (RecyclerView) findViewById(R.id.select_date_recycler_view);
        select_date_reservation_rl= (RelativeLayout) findViewById(R.id.select_date_reservation_rl);
        editText = (EditText) findViewById(R.id.select_date_edt);
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void ShowPopup(int StartAtOrEndAt) {
        hourTimeArrayL = fillArrayListTime();
        hourTimeArrayL2 = fillArrayListTime2();
        myDialog.setContentView(R.layout.detect_time_popup);

        intiPopupComp(myDialog,StartAtOrEndAt);
        //actionListenerToSelectTime();
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private void intiPopupComp(Dialog myDialog,int StartAtOrEndAt) {
        recyclerViewTime = (RecyclerView) myDialog.findViewById(R.id.suggested_time_recycler_view);
        createRV3(StartAtOrEndAt);
    }

    private void createRV3(int StartAtOrEndAt) {
        recyclerViewTime.setNestedScrollingEnabled(false);
        recyclerViewTime.setHasFixedSize(true);

        mGridLayoutManager = new GridLayoutManager(SelectDate.this, 4);
        recyclerViewTime.setLayoutManager(mGridLayoutManager);

        adapterSelectTime = new AdapterSelectTime(SelectDate.this,hourTimeArrayL
                ,flagDayOrNotToDay,hourTimeArrayL2,StartAtOrEndAt,this);
        recyclerViewTime.setAdapter(adapterSelectTime);
    }

    @Override
    public void onClickedTime(String time1, String time2, int position) {
        if (startAtOrEndAtRVNow==1) {
            checkHourWhenClick(position);
        }else {
            if (startAtOrEndAtRVNow==2)
            {
                int x = Integer.parseInt(hourTimeArrayL2.get(position));
                if (x > timeStartAt)
                {
                    select_date_end_at_btn.setVisibility(View.GONE);
                    select_date_end_at_tv.setVisibility(View.VISIBLE);
                    fillEndAtTV(position);
                    myDialog.dismiss();
                }else{
                    Toast.makeText(SelectDate.this,"Illegal",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void checkHourWhenClick(int position) {
        if (flagDayOrNotToDay ==1) {
            int x = Integer.parseInt(hourTimeArrayL2.get(position));
            if (getHourNow() >= x) {
                Toast.makeText(SelectDate.this,"Illegal",Toast.LENGTH_SHORT).show();
            } else {
                timeStartAt = Integer.parseInt(hourTimeArrayL2.get(position));
                goneAndShowComp(position);
            }
        }else{
            timeStartAt = Integer.parseInt(hourTimeArrayL2.get(position));
            goneAndShowComp(position);
        }
    }
    private void goneAndShowComp(int position) {
        select_date_start_at_btn.setVisibility(View.GONE);
        select_date_end_at_tv.setVisibility(View.GONE);
        select_date_start_at_tv.setVisibility(View.VISIBLE);
        fillTimeInStartAt(position);
        select_date_end_at_btn.setVisibility(View.VISIBLE);
        myDialog.dismiss();
    }

    private void fillTimeInStartAt(int position) {
        int x =Integer.parseInt(hourTimeArrayL2.get(position));
        if (x<12)
        {
            select_date_start_at_tv.setText(hourTimeArrayL.get(position)+":"+"00"+" "+"AM");
        }else{
            select_date_start_at_tv.setText(hourTimeArrayL.get(position)+":"+"00"+" "+"PM");
        }
    }

    private void fillEndAtTV(int position) {
        int x =Integer.parseInt(hourTimeArrayL2.get(position));
        if (x<12)
        {
            select_date_end_at_tv.setText(hourTimeArrayL.get(position)+":"+"00"+" "+"AM");
        }else{
            select_date_end_at_tv.setText(hourTimeArrayL.get(position)+":"+"00"+" "+"PM");
        }
    }


    @Override
    public void onClickedDate(Day day) {
        flagDayOrNotToDay =2;

        select_date_date_ll.setVisibility(View.GONE);
        select_date_reservation_date_ll.setVisibility(View.VISIBLE);
        String reservation_date = getResources().getString(R.string.reservation_date);
        select_date_reservation_date_tv.setText(reservation_date+" "+day.getDate_of_day());
        select_date_name_of_day_tv.setText("\""+day.getName_of_day()+"\"");
        name_of_day = day.getName_of_day();
        date=day.getDate_of_day();
    }
}
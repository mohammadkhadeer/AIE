package com.example.aie.view.activity;

import static com.example.aie.utilities.Utilities.checkIfReservationUsedBefore;
import static com.example.aie.utilities.Utilities.fillMainData;
import static com.example.aie.utilities.Utilities.getAllAsList;
import static com.example.aie.utilities.Utilities.getItemID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aie.R;
import com.example.aie.database.RoomDB;
import com.example.aie.model.Day;
import com.example.aie.model.MainData;
import com.example.aie.view.adapters.AdapterMainData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterMainData.EditName {
    private static final int UPDATE_MAIN_LIST = 100;
    RelativeLayout add_new_rl;
    public ArrayList<MainData> mainDataArrayList = new ArrayList<MainData>();
    AdapterMainData adapterMainData;
    RecyclerView recyclerView;
    RoomDB database;
    RelativeLayout main_activity_reset_all_rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusBarColor();
        database = RoomDB.getInstance(getApplicationContext());
        cast();
        actionListener();
        createRV();
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void createRV() {
        mainDataArrayList = fillMainData(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapterMainData = new AdapterMainData(getApplicationContext(), mainDataArrayList,this);
        recyclerView.setAdapter(adapterMainData);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_MAIN_LIST) {
            if(resultCode == Activity.RESULT_OK){
                //receive data from select activity
                String reservation_name = data.getStringExtra("reservation_name");
                String creator_name = data.getStringExtra("creator_name");
                String date = data.getStringExtra("date");
                String name_of_day = data.getStringExtra("name_of_day");
                String start_at = data.getStringExtra("start_at");
                String end_at = data.getStringExtra("end_at");

                //init main_data obeject
                MainData mainData =new MainData();
                getIntent().getSerializableExtra("MyClass");
                mainData.setName(reservation_name);
                mainData.setCreator_name(creator_name);
                mainData.setTime_range_date(date);
                mainData.setName_of_day(name_of_day);
                mainData.setTime_range_start(start_at);
                mainData.setTime_range_end(end_at);

                database.mainDao().insert(mainData);
                mainDataArrayList = new ArrayList<MainData>();

                createRV();
            }

        }

    } //onActivityResult

    private void actionListener() {
        actionListenerToAddNew();
        actionListenerToResetAll();
    }

    private void actionListenerToResetAll() {
        main_activity_reset_all_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.mainDao().reset(getAllAsList(getApplicationContext()));
                mainDataArrayList = new ArrayList<MainData>();
                createRV();
            }
        });
    }

    private void actionListenerToAddNew() {
        add_new_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectDate.class);
                startActivityForResult(intent, UPDATE_MAIN_LIST);
                overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
            }
        });
    }

    private void cast() {
        add_new_rl = (RelativeLayout) findViewById(R.id.main_activity_add_rl);
        recyclerView = (RecyclerView) findViewById(R.id.main_data_recycler_view);
        main_activity_reset_all_rl= (RelativeLayout) findViewById(R.id.main_activity_reset_all_rl);
    }


    @Override
    public void onClickedEdit(MainData mainData,int position) {
        createEditPopup(mainData,position);
    }

    EditText editText;
    RelativeLayout relativeLayout_edt;
    private void createEditPopup(MainData mainData,int position) {
        Dialog myDialog = new Dialog(MainActivity.this);
        myDialog.setContentView(R.layout.edit_popup);
        intiPopupComp(myDialog);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

        int ID = getItemID(database,mainDataArrayList.get(position).getName());
        editText.setText(mainDataArrayList.get(position).getName());
        actionListenerToPopupEdt(ID,myDialog,MainActivity.this);
    }

    private void actionListenerToPopupEdt(int id,Dialog myDialog,Context context) {
        relativeLayout_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().isEmpty() &&
                        checkIfReservationUsedBefore(getApplicationContext(),editText.getText().toString())!=false)
                {
                    myDialog.dismiss();
                    String new_name = editText.getText().toString();
                    database.mainDao().update(id,new_name);
                    mainDataArrayList = new ArrayList<MainData>();

                    createRV();
                }else{
                    Toast.makeText(MainActivity.this,"reservation can't be empty or use it in another reservation",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intiPopupComp(Dialog myDialog) {
        editText = (EditText) myDialog.findViewById(R.id.reservation_name_edt);
        relativeLayout_edt = (RelativeLayout) myDialog.findViewById(R.id.edit_reservation_rl);
    }

}
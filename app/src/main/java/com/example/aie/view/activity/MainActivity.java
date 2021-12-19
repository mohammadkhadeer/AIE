package com.example.aie.view.activity;

import static com.example.aie.utilities.Utilities.fillMainData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aie.R;
import com.example.aie.database.RoomDB;
import com.example.aie.model.Day;
import com.example.aie.model.MainData;
import com.example.aie.view.adapters.AdapterMainData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterMainData.PassMainData {
    private static final int UPDATE_MAIN_LIST = 100;
    RelativeLayout add_new_rl;
    public ArrayList<MainData> mainDataArrayList = new ArrayList<MainData>();
    AdapterMainData adapterMainData;
    RecyclerView recyclerView;
    RoomDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cast();
        actionListener();
        createRV();
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

                database = RoomDB.getInstance(getApplicationContext());
                database.mainDao().insert(mainData);
                mainDataArrayList = new ArrayList<MainData>();

                createRV();
            }

        }

    } //onActivityResult

    private void actionListener() {
        actionListenerToAddNew();
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
    }

    @Override
    public void onClickedMainDate(MainData mainData) {

    }
}
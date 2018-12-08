package com.example.david.hr;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FullList extends AppCompatActivity {

    private  ArrayList<Data_Model> dataList = new ArrayList<>();
    List<Data_Model> data;
    Handler handler = new Handler();
    private Button jump_to_bottom;
    private Button jump_to_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_list);

        final ListView listView = (ListView) findViewById(R.id.listViewAlpha);

        jump_to_bottom = (Button) findViewById(R.id.button);
        jump_to_up = (Button) findViewById(R.id.button2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Data_Model>> call =  api.getData();

        call.enqueue(new Callback<List<Data_Model>>() {
            @Override
            public void onResponse(Call<List<Data_Model>> call, retrofit2.Response<List<Data_Model>> response) {
                data = response.body();

                for(Data_Model d: data){
                    dataList.add(d);
                }

                Log.d("name",dataList.get(0).getEmployee_name());

            }

            @Override
            public void onFailure(Call<List<Data_Model>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        final DataListAdapter adapter = new DataListAdapter(this, R.layout.adapter_view_layout, dataList);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adapter);
            }
        }, 2000);

        jump_to_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setSelection(adapter.getCount()-1);
            }
        });

        jump_to_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                listView.setSelection(0);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}

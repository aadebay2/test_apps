package com.example.david.hr;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SearchList extends AppCompatActivity {

    private  ArrayList<Data_Model> dataList = new ArrayList<>();
    List<Data_Model> data;
    Handler handler = new Handler();
    private Button all_employee;
    private Button jump_to_up, search_button;
    public EditText searchText;
    public static String temp, NAME,AGE,ID,SALARY;
    TextView search_name_field,search_id_field,search_age_field,search_salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        final ListView listView = (ListView) findViewById(R.id.listViewAlpha);
        //get search string
        String searchString= getIntent().getStringExtra("SEARCH");
        searchText = (EditText) findViewById(R.id.button_search_text);
        searchText.setText(searchString);
        temp = searchText.getText().toString();
        //Log.d("intent string",sessionId);


        search_name_field = (TextView)findViewById(R.id.search_name);
        search_id_field = (TextView)findViewById(R.id.search_id_field);
        search_age_field = (TextView)findViewById(R.id.search_age_field);
        search_salary = (TextView)findViewById(R.id.search_salary);

        String _name= NAME;
        String _age= AGE;
        String _id= ID;
        String _salary= SALARY;
        search_name_field.setText(_name);
        search_id_field.setText(_id);
        search_age_field.setText(_age);
        search_salary.setText(_salary);

        all_employee = (Button) findViewById(R.id.All_employee);
        jump_to_up = (Button) findViewById(R.id.search_back_to_top);
        search_button = (Button) findViewById(R.id.button_search);

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

        final SearchListAdapter adapter = new SearchListAdapter(this, R.layout.search_adapter_view_layout, dataList);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adapter);
            }

        }, 2000);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = searchText.getText().toString();
                listView.setAdapter(adapter);
            }
        });

        all_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeList();
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

    public void openEmployeeList() {
        Intent intent = new Intent(this, FullList.class);
        startActivity(intent);
    }

}

package com.example.david.hr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    TextView name,id,age,salary;
    String sign = "$";
    Bitmap profilepic;
    private Button allEmployees, search;
    List<Data_Model> data;
    private ArrayList<Data_Model> dataList;
    public static String intentVariable = "Com.example.david.hr.intentVariable";
    public EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allEmployees = findViewById(R.id.All_employee);
        search = findViewById(R.id.Seach_button);
        searchText = findViewById(R.id.search_text);

        allEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeList();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEmployeeList();
            }
        });


        name = (TextView)findViewById(R.id.nameField);
        id = (TextView)findViewById(R.id.idField);
        age = (TextView)findViewById(R.id.ageField);
        salary = (TextView)findViewById(R.id.salaryField);

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

                Data_Model d1 = data.get(0);

                Log.d("name", d1.getEmployee_name());
                Log.d("name", String.valueOf(d1.getId()));
                Log.d("name",String.valueOf(d1.getEmployee_age()));
                Log.d("name",String.valueOf(d1.getEmployee_salary()));

                name.setText(d1.getEmployee_name());
                id.setText(String.valueOf(d1.getId()));
                age.setText(String.valueOf(d1.getEmployee_age()));
                salary.setText(String.format("%s%s", sign, String.valueOf(d1.getEmployee_salary())));

                //try to load the image and place it in the image view
                //profilepic = BitmapFactory.decodeStream(url.openConnetion().getInputStream());

                dataList = new ArrayList<>();
                for(Data_Model d: data){
                    dataList.add(d);
                }
            }

            @Override
            public void onFailure(Call<List<Data_Model>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void openEmployeeList() {
        Intent intent = new Intent(this, FullList.class);
        startActivity(intent);
    }
    public void searchEmployeeList(){
        Intent intent = new Intent(this, SearchList.class);
        intent.putExtra("SEARCH",searchText.getText().toString());

        intent.putExtra("NAME",name.getText().toString());
        intent.putExtra("ID",id.getText().toString());
        intent.putExtra("AGE",age.getText().toString());
        intent.putExtra("SALARY",salary.getText().toString());

        startActivity(intent);
    }


}

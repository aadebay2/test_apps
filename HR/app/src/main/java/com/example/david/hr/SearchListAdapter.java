package com.example.david.hr;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchListAdapter extends ArrayAdapter<Data_Model> {

    private static final String TAG = "SearchListAdapter";

    private Context mContext;
    int mResource;
    final Handler handler = new Handler();
    TextView search_name_field,search_id_field,search_age_field,search_salary;
    int count =0;

    public SearchListAdapter(Context context, int resource, ArrayList<Data_Model> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get employee info

        int id = getItem(position).getId();
        String name = getItem(position).getEmployee_name();
        int age = getItem(position).getEmployee_age();
        double salary = getItem(position).getEmployee_salary();
        //count =0;

        Data_Model data_model = new Data_Model(id,name,age,salary,null);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView _id = (TextView) convertView.findViewById(R.id.search_id);
        TextView _name = (TextView) convertView.findViewById(R.id.search_name);
        TextView _age = (TextView) convertView.findViewById(R.id.search_age);
        TextView _salary = (TextView) convertView.findViewById(R.id.search_wage);

        if(  SearchList.temp.equalsIgnoreCase(Integer.toString(data_model.getId()))
           | SearchList.temp.equalsIgnoreCase(data_model.getEmployee_name())
           | SearchList.temp.equalsIgnoreCase(Integer.toString(data_model.getEmployee_age()))
           | SearchList.temp.equalsIgnoreCase(Double.toString(data_model.getEmployee_salary()))  )
        {

            _id.setText(Integer.toString(data_model.getId()));
            _name.setText(data_model.getEmployee_name());
            _age.setText(Integer.toString(data_model.getEmployee_age()));
            _salary.setText(Double.toString(data_model.getEmployee_salary()));

            count++;
            if(count == 1){
                SearchList.AGE = _age.getText().toString();
                SearchList.ID = _id.getText().toString();
                SearchList.NAME = _name.getText().toString();
                SearchList.SALARY= _salary.getText().toString();
            }

            return convertView;

        }else
            return null;




    }
}

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

public class DataListAdapter extends ArrayAdapter<Data_Model> {

    private static final String TAG = "DataListAdapter";

    private Context mContext;
    int mResource;
    final Handler handler = new Handler();

    public DataListAdapter(Context context, int resource, ArrayList<Data_Model> objects) {
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

        Data_Model data_model = new Data_Model(id,name,age,salary,null);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView _id = (TextView) convertView.findViewById(R.id.idtv);
        TextView _name = (TextView) convertView.findViewById(R.id.nametv);
        TextView _age = (TextView) convertView.findViewById(R.id.agetv);
        TextView _salary = (TextView) convertView.findViewById(R.id.wagetv);


        _id.setText(Integer.toString(data_model.getId()));
        _name.setText(data_model.getEmployee_name());
        _age.setText(Integer.toString(data_model.getEmployee_age()));
        _salary.setText(Double.toString(data_model.getEmployee_salary()));

        return convertView;
    }
}

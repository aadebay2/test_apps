package com.example.david.hr;

public class Data_Model {

    private int id;
    private String employee_name;
    private double employee_salary;
    private int employee_age;
    private String profile_image;
    private String temp="";

    //hold list for better scrolling
    /*static class ViewHolder{
        TextView id;
        TextView name;
        TextView age;
        TextView salary;
    }*/

    public Data_Model(int id, String employee_name, int employee_age, double employee_salary, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public int getId() {
        return id;
    }

    public String getEmployee_name() {
        if(employee_name !=""){
            temp = employee_name;
        }else
            temp = "No Name";
        return temp;
        //return employee_name;
    }

    public double getEmployee_salary() {
        return employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }
}

package com.example.david.hr;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL ="http://dummy.restapiexample.com/api/v1/";

    @GET("employees")
    Call<List<Data_Model>> getData();
}

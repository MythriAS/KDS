package com.example.mmjava.api;

import com.example.mmjava.model.Orders;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("json/v1/1/filter.php?c=Seafood")
    Call<Orders> getModel();
}

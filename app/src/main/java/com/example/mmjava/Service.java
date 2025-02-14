package com.example.mmjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("json/v1/1/filter.php?c=Seafood")
    Call<ModelClass> getModel();
}

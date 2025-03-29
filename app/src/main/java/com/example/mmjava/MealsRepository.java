package com.example.mmjava;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRepository {
    private final Service service;

    public MealsRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
    }

    public LiveData<List<Meals>> fetchMeals() {
        MutableLiveData<List<Meals>> mealsLiveData = new MutableLiveData<>();
        service.getModel().enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mealsLiveData.setValue(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable throwable) {
                mealsLiveData.setValue(null);
            }
        });
        return mealsLiveData;
    }
}


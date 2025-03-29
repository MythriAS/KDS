package com.example.mmjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mmjava.api.Service;
import com.example.mmjava.model.Meals;
import com.example.mmjava.model.Orders;

import java.util.List;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MealsRepository {
    private final Service service;
    private final Realm realm;
    public MealsRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
        realm = Realm.getDefaultInstance();
    }

    public LiveData<List<Meals>> fetchMeals() {
        MutableLiveData<List<Meals>> mealsLiveData = new MutableLiveData<>();
        service.getModel().enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mealsLiveData.setValue(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable throwable) {
                mealsLiveData.setValue(null);
            }
        });
        return mealsLiveData;
    }

    public LiveData<List<Meals>> getCompletedOrders() {
        MutableLiveData<List<Meals>> data = new MutableLiveData<>();
        realm.executeTransactionAsync(r -> {
            List<Meals> meals = r.where(Meals.class).equalTo("isCompleted", true).findAll();
            data.postValue(realm.copyFromRealm(meals));
        });
        return data;
    }

    public void moveToCompleted(Meals item) {
        realm.executeTransactionAsync(realmInstance -> {
            Meals meal = realmInstance.where(Meals.class).equalTo("strMeal", item.getStrMeal()).findFirst();
            if (meal != null) {
                meal.setCompleted(true);
            }
        });
    }
}


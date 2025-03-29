package com.example.mmjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mmjava.model.Meals;

import java.util.List;
public class SharedViewModel extends ViewModel {
    private final MealsRepository repository;
    private final LiveData<List<Meals>> mealsLiveData;
    private final LiveData<List<Meals>> itemsInSecondRecycler;
    private final MutableLiveData<Integer> currentOrderCount = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> completeOrderCount = new MutableLiveData<>(0);

    public SharedViewModel() {
        repository = new MealsRepository();
        mealsLiveData = repository.fetchMeals();
        itemsInSecondRecycler = repository.getCompletedOrders();
    }
    public LiveData<List<Meals>> getMeals() {
        return mealsLiveData;
    }

    public LiveData<List<Meals>> getItemsInSecondRecycler() {
        return itemsInSecondRecycler;
    }

    public LiveData<Integer> getCurrentOrderCount() {
        return currentOrderCount;
    }

    public LiveData<Integer> getCompleteOrderCount() {
        return completeOrderCount;
    }

    public void moveItemToCompleted(Meals item) {
        repository.moveToCompleted(item);
    }
}


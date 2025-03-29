package com.example.mmjava;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends androidx.lifecycle.ViewModel {

    private final MutableLiveData<List<Meals>> itemsInFirstRecycler = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<Meals>> itemsInSecondRecycler = new MutableLiveData<>(new ArrayList<>());

    private final MutableLiveData<Integer> currentOrderCount = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> completeOrderCount = new MutableLiveData<>(0);
    private final MutableLiveData<List<Meals>> mealsLiveData = new MutableLiveData<>();

    private final MealsRepository repository;

    public SharedViewModel() {
        repository = new MealsRepository();
    }

    public void fetchMeals() {
        repository.fetchMeals().observeForever(mealsLiveData::setValue);
    }

    public LiveData<List<Meals>> getMeals() {
        return mealsLiveData;
    }
    public MutableLiveData<List<Meals>> getItemsInFirstRecycler() {
        return itemsInFirstRecycler;
    }

    public MutableLiveData<List<Meals>> getItemsInSecondRecycler() {
        return itemsInSecondRecycler;
    }

    public MutableLiveData<Integer> getCurrentOrderCount() {
        return currentOrderCount;
    }

    public MutableLiveData<Integer> getCompleteOrderCount() {
        return completeOrderCount;
    }


    public void addItemToSecondRecycler(Meals item) {
        List<Meals> firstList = new ArrayList<>(itemsInFirstRecycler.getValue());
        List<Meals> secondList = new ArrayList<>(itemsInSecondRecycler.getValue());

        if (firstList != null && secondList != null) {
            if (firstList.contains(item)) {
                firstList.remove(item); //
                secondList.add(item); //


                currentOrderCount.setValue(firstList.size());
                completeOrderCount.setValue(secondList.size());


                itemsInFirstRecycler.setValue(firstList);
                itemsInSecondRecycler.setValue(secondList);
            }
        }
    }

    private boolean isInitialized = false;


    public void setInitialFirstList(List<Meals> initialList) {

        if (!isInitialized) {
            itemsInFirstRecycler.setValue(initialList);
            currentOrderCount.setValue(initialList.size());
            isInitialized = true;
        }
    }
}

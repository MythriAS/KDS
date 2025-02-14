package com.example.mmjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrentOrderFragment extends Fragment {
    RecyclerView recyclerView;
    List<Meals> mealsList;
    FirstRecyclerAdapter firstRecyclerAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_order_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<ModelClass> listCall = service.getModel();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getItemsInFirstRecycler().observe(getViewLifecycleOwner(), new Observer<List<Meals>>() {
            @Override
            public void onChanged(List<Meals> meals) {
                mealsList = meals;
                if (firstRecyclerAdapter == null) {
                    firstRecyclerAdapter = new FirstRecyclerAdapter(mealsList, sharedViewModel);
                    recyclerView.setAdapter(firstRecyclerAdapter);
                } else {
                    firstRecyclerAdapter.updateList(meals);
                }
            }
        });

        listCall.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meals> meals = response.body().getMeals();

                    sharedViewModel.setInitialFirstList(meals);
                } else {
                    Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable throwable) {
                Toast.makeText(getContext(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}

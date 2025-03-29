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
    private RecyclerView recyclerView;
    private FirstRecyclerAdapter firstRecyclerAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_order_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getMeals().observe(getViewLifecycleOwner(), meals -> {
            if (firstRecyclerAdapter == null) {
                firstRecyclerAdapter = new FirstRecyclerAdapter(meals, sharedViewModel);
                recyclerView.setAdapter(firstRecyclerAdapter);
            } else {
                firstRecyclerAdapter.updateList(meals);
            }
        });
        sharedViewModel.fetchMeals();
        return view;
    }
}

package com.example.mmjava.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mmjava.view.adapter.CurrentOrderRecyclerAdapter;
import com.example.mmjava.R;
import com.example.mmjava.viewmodel.SharedViewModel;

public class CurrentOrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private CurrentOrderRecyclerAdapter firstRecyclerAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_order_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        sharedViewModel.getMeals().observe(getViewLifecycleOwner(), meals -> {
            if (firstRecyclerAdapter == null) {
                firstRecyclerAdapter = new CurrentOrderRecyclerAdapter(meals, sharedViewModel);
                recyclerView.setAdapter(firstRecyclerAdapter);
            } else {
                firstRecyclerAdapter.updateList(meals);
            }
        });

        return view;
    }
}
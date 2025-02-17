package com.example.mmjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CompleteOrderFragment extends Fragment{

    private RecyclerView recyclerViewSecond;
    private SecondRecyclerAdapter secondRecyclerAdapter;
    private SharedViewModel sharedViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.complete_order_fragment,container,false);

        recyclerViewSecond = view.findViewById(R.id.rv_added_product_list);
        recyclerViewSecond.setLayoutManager(new GridLayoutManager(getContext(), 2));
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);  // Shared ViewModel

        secondRecyclerAdapter = new SecondRecyclerAdapter(sharedViewModel );
        recyclerViewSecond.setAdapter(secondRecyclerAdapter);

        secondRecyclerAdapter.observeItems(getViewLifecycleOwner());
        return view;
    }
}
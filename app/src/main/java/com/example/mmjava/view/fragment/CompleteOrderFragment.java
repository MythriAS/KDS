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

import com.example.mmjava.R;
import com.example.mmjava.view.adapter.CompleteOrderRecyclerAdapter;
import com.example.mmjava.viewmodel.SharedViewModel;

public class CompleteOrderFragment extends Fragment{

    private RecyclerView recyclerViewSecond;
    private CompleteOrderRecyclerAdapter completeOrderRecyclerAdapter;
    private SharedViewModel sharedViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.complete_order_fragment,container,false);

        recyclerViewSecond = view.findViewById(R.id.rv_added_product_list);
        recyclerViewSecond.setLayoutManager(new GridLayoutManager(getContext(), 2));
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);  // Shared ViewModel

        completeOrderRecyclerAdapter = new CompleteOrderRecyclerAdapter(sharedViewModel );
        recyclerViewSecond.setAdapter(completeOrderRecyclerAdapter);

        completeOrderRecyclerAdapter.observeItems(getViewLifecycleOwner());
        return view;
    }
}
package com.example.mmjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SecondRecyclerAdapter extends RecyclerView.Adapter<SecondRecyclerAdapter.ViewHolder> {
    List<Meals> itemRealmClassList;
    SharedViewModel sharedViewModel;

    public SecondRecyclerAdapter(SharedViewModel sharedViewModel) {
        this.sharedViewModel = sharedViewModel;
        this.itemRealmClassList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complete_order_recycler_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meals meals = itemRealmClassList.get(position);
        holder.txtAddedItem.setText(meals.getStrMeal());

        Glide.with(holder.itemView.getContext())
                .load(meals.getStrMealThumb())
                .into(holder.imgAdded);

    }

    @Override
    public int getItemCount() {
        return itemRealmClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imgAdded;
        private AppCompatTextView txtAddedItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAdded = itemView.findViewById(R.id.added_image);
            txtAddedItem = itemView.findViewById(R.id.txt_added_product_name);
        }
    }
    public void observeItems(LifecycleOwner lifecycleOwner) {
        sharedViewModel.getItemsInSecondRecycler().observe(lifecycleOwner, items -> {
            itemRealmClassList.clear();
            if (items != null) {
                itemRealmClassList.addAll(items);
            }
            notifyDataSetChanged();
        });
    }
}

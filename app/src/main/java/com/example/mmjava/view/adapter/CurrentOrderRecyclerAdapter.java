package com.example.mmjava.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mmjava.model.Meals;
import com.example.mmjava.R;
import com.example.mmjava.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrderRecyclerAdapter extends RecyclerView.Adapter<CurrentOrderRecyclerAdapter.ViewHolder> {
    private List<Meals> mealsList;
    private SharedViewModel sharedViewModel;

    public CurrentOrderRecyclerAdapter(List<Meals> mealsList, SharedViewModel sharedViewModel) {
        this.mealsList = mealsList;
        this.sharedViewModel = sharedViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_order_recycler_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meals meal = mealsList.get(position);
        holder.txtdesc.setText(meal.getStrMeal());

        Glide.with(holder.itemView.getContext())
                .load(meal.getStrMealThumb())
                .into(holder.img);

        holder.btnBump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedViewModel.moveItemToCompleted(meal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    public void updateList(List<Meals> newList) {
        mealsList = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txtdesc;
        private ImageView img;
        private AppCompatButton btnBump;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdesc = itemView.findViewById(R.id.strMeal);
            img=itemView.findViewById(R.id.image_strMeal_Thumb);
            btnBump=itemView.findViewById(R.id.btn_product_add);
        }
    }
}



package com.example.foodapp_ssoapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.Viewholder> {


    private List<HomeModel> FoodListModelList= new ArrayList<>();

    public FoodListAdapter(List<FoodListModel> HomeModelList) {

        this.FoodListModelList = FoodListModelList;
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list,parent,false);

        return new FoodListAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setData(FoodListModelList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if(FoodListModelList.equals(null)){
            return 0;
        }
        else
            return FoodListModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView food_name;
        public TextView food_price;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            food_name = (TextView)itemView.findViewById(R.id.food_name);
            food_price = (TextView)itemView.findViewById(R.id.food_price);
            itemView.setOnClickListener(this);
        }

        public View setData(final String name) {
            this.food_name.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent foodList = new Intent(itemView.getContext(),FoodDetail_.class);

                    foodList.putExtra("MenuId", getAdapterPosition());
                    startActivity(foodList);

                }
            });

            return itemView;
        }

        public void onClick(View v) {

            itemClickListener.onClick(v,getAdapterPosition(),false);

        }
    }

    private void startActivity(Intent foodList) {
    }

}

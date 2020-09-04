package com.example.foodapp_ssoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodList_ extends AppCompatActivity {

    RecyclerView recyclerView;
    List<FoodListModel> list;
    FirebaseDatabase database;
    DatabaseReference foodList;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<FoodListModel, FoodListAdapter.Viewholder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Home");

        database = FirebaseDatabase.getInstance();
        foodList = database.getInstance().getReference().child("FoodList");

        recyclerView = findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        final FoodListAdapter adapter = new FoodListAdapter(list);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String categoryId= intent.getStringExtra("categoryId");
        //String categoryId="0";

        Toast.makeText(FoodList_.this,"Content : "+categoryId,Toast.LENGTH_LONG).show();

       foodList.child(categoryId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()){
                   String name= dataSnapshot.child("name").getValue().toString();
                   String price=dataSnapshot.child("price").getValue().toString();

                   list.add(dataSnapshot.getValue(FoodListModel.class));
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FoodList_.this, databaseError.getMessage(),Toast.LENGTH_LONG).show();

            }
        });



    }

}

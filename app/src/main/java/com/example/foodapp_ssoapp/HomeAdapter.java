package com.example.foodapp_ssoapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.core.Context;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Viewholder> {


    private List<HomeModel> HomeModelList;

    public HomeAdapter(List <HomeModel> HomeModelList) {

        this.HomeModelList = HomeModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.setData(HomeModelList.get(position).getImage(),HomeModelList.get(position).getName());
    }

    @Override
    public int getItemCount() {

        return HomeModelList.size();
    }

    public interface onItemListener{
        void onItemListener (int position);
    }

    class Viewholder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title;

        View mView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.txt_menutitle);
        }

        private View setData(String url, final String title) {
            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.title.setText(title);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(),"This is toast : "+getAdapterPosition(),Toast.LENGTH_LONG).show();

//                   itemView.getContext().startActivity(new Intent(itemView.getContext(), FoodList_.class));

                    String catePosition ="";

                    //validate
                    switch (getAdapterPosition()){
                        case 0: catePosition="01";
                             break;
                        case 1: catePosition="02";
                            break;
                        case 2: catePosition="03";
                            break;
                        case 3: catePosition="04";
                            break;
                        case 4: catePosition="05";
                            break;
                        case 5: catePosition="06";
                            break;
                        case 6: catePosition="07";
                            break;
                        case 7: catePosition="08";
                            break;
                        case 8: catePosition="09";
                            break;
                        default: catePosition=String.valueOf(getAdapterPosition()+1);
                            break;
                    }

                    Intent intentFood= new Intent(itemView.getContext(),FoodList_.class);
                    intentFood.putExtra("categoryId", catePosition);
                    itemView.getContext().startActivity(intentFood);

                }
            });





            return itemView;
        }


    }

    private void startActivity(Intent intent) {
    }
}
package com.octaitsolutions.rahal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.City.PlaceActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TouristLocationAdapter extends RecyclerView.Adapter<TouristLocationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TouristLocation> list;

    public TouristLocationAdapter(Context context, ArrayList<TouristLocation> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TouristLocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TouristLocationAdapter.ViewHolder holder, int position) {
        TouristLocation model = list.get(position);

        holder.name.setText(model.getName());
        holder.des.setText(model.getDescription());
        holder.des.setVisibility(View.GONE);
        holder.rate.setText(model.getRating());

        Picasso.get().load(model.getUri()).placeholder(R.drawable.hotel).error(R.drawable.hotel).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaceActivity.class);
                intent.putExtra("Location", model);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name  , des  ,rate;
        RoundedImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_tl_name);
            des = itemView.findViewById(R.id.item_tl_des);
            rate = itemView.findViewById(R.id.item_num_rate);
            imageView = itemView.findViewById(R.id.item_tl_image);

        }
    }
}

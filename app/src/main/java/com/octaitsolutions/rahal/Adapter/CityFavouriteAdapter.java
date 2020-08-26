package com.octaitsolutions.rahal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.octaitsolutions.rahal.Model.CityFavourite;
import com.octaitsolutions.rahal.Model.Favourite;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.City.TouristLocationsActivity;
import com.octaitsolutions.rahal.User.CityActivity;
import com.octaitsolutions.rahal.User.HomeActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class CityFavouriteAdapter extends RecyclerView.Adapter<CityFavouriteAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CityFavourite> list;

    DatabaseReference rootReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    public CityFavouriteAdapter(Context context, ArrayList<CityFavourite> list) {
        this.context = context;
        this.list = list;

        firebaseAuth = FirebaseAuth.getInstance();
        rootReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();

    }

    @NonNull
    @Override
    public CityFavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityFavouriteAdapter.ViewHolder holder, int position) {
        CityFavourite model = list.get(position);

        final Bitmap  grayFav, redFav;
        Drawable myDrawable = context.getResources().getDrawable(R.drawable.ic_favorite_black);
        grayFav = Bitmap.createBitmap(myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Drawable myDraw = context.getResources().getDrawable(R.drawable.ic_favorite_black_pink);
        redFav = Bitmap.createBitmap(myDraw.getIntrinsicWidth(), myDrawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);


        holder.name.setText(model.getName());
        holder.imageView.setImageResource(model.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context , TouristLocationsActivity.class);
                intent.putExtra("name", model.getName());
                intent.putExtra("image", model.getImage());
                intent.putExtra("selectedCountry",model.getCountry());
                context.startActivity(intent);

            }
        });

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());
                holder.itemView.setVisibility(View.GONE);

                rootReference.child("FavouriteCity").child(HomeActivity.ID).child(model.getId()).removeValue();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView imageView, fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.home_country_name);
            imageView = itemView.findViewById(R.id.home_country_image);
            fav = itemView.findViewById(R.id.home_country_fav);
        }
    }
}

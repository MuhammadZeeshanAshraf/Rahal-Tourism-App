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
import com.octaitsolutions.rahal.Model.City;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.City.TouristLocationsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context;
    private ArrayList<City> list;
    private String selectedCountry;

    DatabaseReference rootReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    public CityAdapter(Context context, ArrayList<City> list, String selectedCountry) {
        this.context = context;
        this.list = list;
        this.selectedCountry = selectedCountry;

        firebaseAuth = FirebaseAuth.getInstance();
        rootReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        City model = list.get(position);

        final Bitmap grayFav, redFav;
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
                intent.putExtra("selectedCountry",selectedCountry);
                context.startActivity(intent);
            }
        });

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(firebaseUser == null || firebaseUser.getUid().equalsIgnoreCase("bz5WFcZAEsewUNucgi0lsAxOcQE2"))
                {
                    Toasty.info(context , "You have to login to use this feature..." , Toasty.LENGTH_SHORT).show();
                }else
                {
                    final Bitmap bitmap;
                    bitmap = Bitmap.createBitmap(holder.fav.getDrawable().getIntrinsicWidth(), holder.fav.getDrawable().getIntrinsicHeight(),
                            Bitmap.Config.ARGB_8888);

                    if (bitmap.sameAs(grayFav)) {
                        holder.fav.setImageResource(R.drawable.ic_favorite_black_pink);

                        DatabaseReference userKeyRef = rootReference.child("FavouriteCity").child(firebaseUser.getUid()).push();

                        final String PushID = userKeyRef.getKey();
                        Map MessageMap = new HashMap<>();
                        MessageMap.put("ID", PushID);
                        MessageMap.put("Country", selectedCountry);

                        rootReference.child("FavouriteCity").child(firebaseUser.getUid()).child(PushID).setValue(model);
                        rootReference.child("FavouriteCity").child(firebaseUser.getUid()).child(PushID).updateChildren(MessageMap);


                    } else {
                        holder.fav.setImageResource(R.drawable.ic_favorite_black);
                    }
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name ;
        ImageView imageView ,  fav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.home_country_name);
            imageView = itemView.findViewById(R.id.home_country_image);
            fav = itemView.findViewById(R.id.home_country_fav);
        }
    }
}

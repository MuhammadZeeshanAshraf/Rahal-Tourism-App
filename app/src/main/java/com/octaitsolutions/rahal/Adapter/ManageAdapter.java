package com.octaitsolutions.rahal.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.octaitsolutions.rahal.Admin.EditShopping;
import com.octaitsolutions.rahal.Model.City;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.City.PlaceActivity;
import com.octaitsolutions.rahal.User.City.TouristLocationsActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TouristLocation> list;
    private Activity activity;

    DatabaseReference rootRef;
    String selectedCountry = "", selectedCity = "" , category = "";


    public ManageAdapter(Context context, ArrayList<TouristLocation> list, Activity activity , String selectedCountry , String selectedCity , String category) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.selectedCity = selectedCity;
        this.selectedCountry =selectedCountry;
        this.category = category;
        rootRef = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ManageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageAdapter.ViewHolder holder, int position) {
        final TouristLocation model = list.get(position);

        holder.name.setText(model.getName());
        holder.no.setText((position+ 1)+ ".");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaceActivity.class);
                intent.putExtra("Location", model);
                context.startActivity(intent);
            }
        });

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view, model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView no, name;
        ImageView more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            no = itemView.findViewById(R.id.item_can_no);
            name = itemView.findViewById(R.id.item_can_name);
            more = itemView.findViewById(R.id.item_can_more);
        }
    }

    public void showMenu(View view, final TouristLocation model) {
        PopupMenu popup = new PopupMenu(context, view);
        popup.inflate(R.menu.manage_place_menu);
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.can_detail:
                        Intent intent = new Intent(context, PlaceActivity.class);
                        intent.putExtra("Location", model);
                        context.startActivity(intent);
                        break;
                    case R.id.can_edit:
                        Intent i = new Intent(context, EditShopping.class);
                        i.putExtra("Location", model);
                        i.putExtra("cat", category);
                        context.startActivity(i);
                        activity.finish();
                        break;
                    case R.id.can_del:
                        DeletePlace(model);
                        break;

                }

                return true;
            }
        });

    }

    private void DeletePlace(TouristLocation model) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.getUri());

        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                rootRef.child(category).child(selectedCountry).child(selectedCity).child(model.getId()).removeValue();
                Toasty.warning(context , "Place Deleted Successfully.....!" , Toasty.LENGTH_SHORT).show();
                activity.finish();
            }
        });
    }

}

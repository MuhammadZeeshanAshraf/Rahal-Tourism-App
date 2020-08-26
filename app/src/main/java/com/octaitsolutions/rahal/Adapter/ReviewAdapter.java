package com.octaitsolutions.rahal.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.codesgood.views.JustifiedTextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.octaitsolutions.rahal.Admin.EditShopping;
import com.octaitsolutions.rahal.Model.Review;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.City.PlaceActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Review> list;

    public ReviewAdapter(Context context, ArrayList<Review> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        final Review model = list.get(position);

        if(!(model.getUri().equals("Null")))
        {
            Picasso.get().load(model.getUri()).placeholder(R.drawable.face_1).error(R.drawable.face_1).into(holder.profileImage);
        }

        holder.name.setText(model.getName());
        holder.ratingBar.setRating(Float.parseFloat(model.getRating()));
        holder.comment.setText(model.getComment());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profileImage;
        TextView name;
        RatingBar ratingBar;
        JustifiedTextView comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.item_review_profile_image);
            name = itemView.findViewById(R.id.item_review_username);
            ratingBar = itemView.findViewById(R.id.item_review_rating);
            comment = itemView.findViewById(R.id.item_review_comment);
        }
    }



}

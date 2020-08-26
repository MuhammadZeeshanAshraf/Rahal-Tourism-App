package com.octaitsolutions.rahal.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.octaitsolutions.rahal.Adapter.ManageAdapter;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;

import java.util.ArrayList;

public class ManageRestaurant extends AppCompatActivity {

    DatabaseReference rootReference;

    RecyclerView recyclerView;
    ArrayList<TouristLocation> list;
    ManageAdapter adapter;

    String selectedCountry = "", selectedCity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_manage_restaurant);


        selectedCountry = getIntent().getStringExtra("selectedCountry");
        selectedCity = getIntent().getStringExtra("selectedCity");
        rootReference = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.shopping_list);

        RetriveDataFromDatabase();
    }

    private void RetriveDataFromDatabase()
    {
        rootReference.child("Restaurant").child(selectedCountry).child(selectedCity).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String key = snapshot.getKey();
                    list.clear();

                    if(key != null)
                    {
                        rootReference.child("Restaurant").child(selectedCountry).child(selectedCity).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                String id = dataSnapshot.child("ID").getValue().toString();
                                String country = dataSnapshot.child("Country").getValue().toString();
                                String city = dataSnapshot.child("City").getValue().toString();
                                String name = dataSnapshot.child("Name").getValue().toString();
                                String phone = dataSnapshot.child("Phone").getValue().toString();
                                String address = dataSnapshot.child("Address").getValue().toString();
                                String timing = dataSnapshot.child("Timing").getValue().toString();
                                String link = dataSnapshot.child("Link").getValue().toString();
                                String location = dataSnapshot.child("Location").getValue().toString();
                                String description = dataSnapshot.child("Description").getValue().toString();
                                String rating = dataSnapshot.child("Rating").getValue().toString();
                                String uri = dataSnapshot.child("Uri").getValue().toString();


                                TouristLocation model = new TouristLocation(id , country , city , name , phone
                                        , address , timing , link , location , description , uri, rating);
                                list.add(model);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(ManageRestaurant.this, LinearLayoutManager.VERTICAL, false);

                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());

                                adapter = new ManageAdapter(ManageRestaurant.this, list , ManageRestaurant.this , selectedCountry , selectedCity , "Restaurant");

                                recyclerView.setAdapter(adapter);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
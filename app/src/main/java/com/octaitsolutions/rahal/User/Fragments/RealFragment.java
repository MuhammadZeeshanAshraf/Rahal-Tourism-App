package com.octaitsolutions.rahal.User.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.octaitsolutions.rahal.Adapter.CityFavouriteAdapter;
import com.octaitsolutions.rahal.Adapter.CountryAdapter;
import com.octaitsolutions.rahal.Adapter.FavouriteAdapter;
import com.octaitsolutions.rahal.Adapter.ManageAdapter;
import com.octaitsolutions.rahal.Admin.ManageShopping;
import com.octaitsolutions.rahal.Model.CityFavourite;
import com.octaitsolutions.rahal.Model.Country;
import com.octaitsolutions.rahal.Model.Favourite;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;

import java.util.ArrayList;

public class RealFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ArrayList<Favourite> list;
    private FavouriteAdapter adapter;

    private RecyclerView recyclerViewCity;
    private ArrayList<CityFavourite> listCity;
    private CityFavouriteAdapter adapterCity;

    DatabaseReference rootReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String currentUserId;

    ShimmerFrameLayout mShimmerViewContainer;
    LinearLayout emtpyFavourite;

    int checker = 0 ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_real, container, false);
        emtpyFavourite = view.findViewById(R.id.emptyFavourite);
        emtpyFavourite.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();
        rootReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            currentUserId = firebaseAuth.getCurrentUser().getUid();

        }

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container_home);
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        recyclerView = view.findViewById(R.id.real_recyclerview);
        list = new ArrayList<>();

        recyclerViewCity = view.findViewById(R.id.city_recyclerview);
        listCity = new ArrayList<>();

        RetriveDataFromDatabase();

        return view;
    }


    public void RetriveDataFromDatabase() {
        list.clear();
        if (firebaseUser != null) {
            rootReference.child("Favourite").child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.exists())
                    {
                        checker = 1;
                    }
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        final String key = snapshot.getKey();
                        list.clear();

                        if (key != null) {
                            rootReference.child("Favourite").child(currentUserId).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    String id = dataSnapshot.child("ID").getValue().toString();
                                    String image = dataSnapshot.child("image").getValue().toString();
                                    String name = dataSnapshot.child("name").getValue().toString();

                                    int intImage = Integer.parseInt(image);
                                    int intName = Integer.parseInt(name);

                                    Favourite model = new Favourite(id, intImage, intName);
                                    list.add(model);


                                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                                    adapter = new FavouriteAdapter(getContext(), list);

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

            rootReference.child("FavouriteCity").child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        checker = 1;
                    }
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        final String key = snapshot.getKey();
                        list.clear();

                        if (key != null) {
                            rootReference.child("FavouriteCity").child(currentUserId).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    String id = dataSnapshot.child("ID").getValue().toString();
                                    String country = dataSnapshot.child("Country").getValue().toString();
                                    String image = dataSnapshot.child("image").getValue().toString();
                                    String name = dataSnapshot.child("name").getValue().toString();

                                    int intImage = Integer.parseInt(image);
                                    int intName = Integer.parseInt(name);

                                    CityFavourite model = new CityFavourite(id, country, intImage, intName);
                                    listCity.add(model);

                                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                                    recyclerViewCity.setLayoutManager(layoutManager);
                                    recyclerViewCity.setItemAnimator(new DefaultItemAnimator());

                                    adapterCity = new CityFavouriteAdapter(getContext(), listCity);

                                    recyclerViewCity.setAdapter(adapterCity);


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                    }

                    mShimmerViewContainer.setVisibility(View.GONE);
                    if(checker == 0)
                    {
                        emtpyFavourite.setVisibility(View.VISIBLE);
                    } else {
                        emtpyFavourite.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else {
            mShimmerViewContainer.setVisibility(View.GONE);
            if(checker == 0)
            {
                emtpyFavourite.setVisibility(View.VISIBLE);
            } else {
                emtpyFavourite.setVisibility(View.GONE);

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

//       RetriveDataFromDatabase();
    }
}

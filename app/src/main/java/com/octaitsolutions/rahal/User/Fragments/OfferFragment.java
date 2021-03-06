package com.octaitsolutions.rahal.User.Fragments;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.octaitsolutions.rahal.Adapter.TouristLocationAdapter;
import com.octaitsolutions.rahal.Auth.LoginActivity;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.City.TouristLocationsActivity;
import com.octaitsolutions.rahal.User.HomeActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import im.delight.android.location.SimpleLocation;


public class OfferFragment extends Fragment {

    LottieAnimationView animLocation, animInternet, animFailed;
    View view;
    FloatingActionButton fabLocation;
    RecyclerView recyclerView;
    ArrayList<TouristLocation> list;
    TouristLocationAdapter adapter;
    ShimmerFrameLayout mShimmerViewContainer;

    SimpleLocation location;

    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String currentUserId;
    int checker = 0;

    ImageView SearchIcon;
    androidx.appcompat.widget.SearchView SearchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_offer, container, false);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(), FragmentPagerItems.with(getContext())
                .add("Shopping Mall", ShoppingMallFragment.class)
                .add("Restaurant", ResturantFragment.class)
                .add("Cafe", CafeFragment.class)
                .add("Things To Do", ThingsFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.tabPager);
        viewPager.setAdapter(adapter);


        SmartTabLayout viewPagerTab = (SmartTabLayout) view.findViewById(R.id.viewpapertab);
        viewPagerTab.setViewPager(viewPager);

        SearchIcon = view.findViewById(R.id.offer_searchIcon);
        SearchView = view.findViewById(R.id.searchView);

        SearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchAnyPlace();
            }
        });

        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchAnyPlace();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        {
            animInternet = view.findViewById(R.id.animation_viewb);
            animInternet.setVisibility(View.GONE);
            animLocation = view.findViewById(R.id.animation_view);
            animLocation.setVisibility(View.GONE);
            animFailed = view.findViewById(R.id.animation_viewc);
            animFailed.setVisibility(View.GONE);

            ConnectivityManager connManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            ConnectivityManager cm =
                    (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();


            if (mWifi.isConnected() || isConnected) {

                animInternet.setVisibility(View.GONE);
            } else {
                animInternet.setVisibility(View.VISIBLE);
                Toasty.warning(getContext(), "Check Your Internet ! Make Sure Your are Connected to Internet ", Toasty.LENGTH_SHORT).show();

            }

            firebaseAuth = FirebaseAuth.getInstance();
            rootRef = FirebaseDatabase.getInstance().getReference();
            firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                currentUserId = firebaseAuth.getCurrentUser().getUid();

            }


            mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container_home);
            fabLocation = view.findViewById(R.id.fab_location);
            recyclerView = view.findViewById(R.id.near_by_recyclerview);
            list = new ArrayList<>();

            location = new SimpleLocation(getActivity());

            if (!location.hasLocationEnabled()) {
                if (mWifi.isConnected() || isConnected) {

                    animInternet.setVisibility(View.GONE);
                    animLocation.setVisibility(View.VISIBLE);
                } else {
                    animLocation.setVisibility(View.GONE);
                }

            }

            if (HomeActivity.LOCATION == 1) {
                animLocation.setVisibility(View.VISIBLE);
            } else {

                if (!location.hasLocationEnabled()) {
                    // ask the user to enable location access
                    SimpleLocation.openSettings(getContext());
                } else {
                    mShimmerViewContainer.setVisibility(View.VISIBLE);

                    final double latitude = location.getLatitude();
                    final double longitude = location.getLongitude();
                    getAddress(getContext(), latitude, longitude);
                }


            }

        }
        return view;
    }

    public void getAddress(Context context, double LATITUDE, double LONGITUDE) {

//Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {


                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                GetNearByLocations(country);

            } else {
                animFailed.setVisibility(View.VISIBLE);
                mShimmerViewContainer.stopShimmer();
                Toasty.warning(getActivity(), "We  are unable to find any near by Location..!", Toasty.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return;
    }

    private void GetNearByLocations(String country) {

        list.clear();
        rootRef.child("Shopping").child(country).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String citykey = snapshot.getKey();

                    if (citykey != null) {
                        rootRef.child("Shopping").child(country).child(citykey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    checker = 1;
                                }
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    final String key = snapshot.getKey();

                                    if (key != null) {
                                        rootRef.child("Shopping").child(country).child(citykey).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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


                                                TouristLocation model = new TouristLocation(id, country, city, name, phone
                                                        , address, timing, link, location, description, uri, rating);
                                                list.add(model);

                                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                                                recyclerView.setLayoutManager(layoutManager);
                                                recyclerView.setItemAnimator(new DefaultItemAnimator());

                                                adapter = new TouristLocationAdapter(getContext(), list);

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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rootRef.child("Restaurant").child(country).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    checker = 1;
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String citykey = snapshot.getKey();

                    if (citykey != null) {
                        rootRef.child("Restaurant").child(country).child(citykey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    final String key = snapshot.getKey();

                                    if (key != null) {
                                        rootRef.child("Restaurant").child(country).child(citykey).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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
                                                String uri = dataSnapshot.child("Uri").getValue().toString();
                                                String rating = dataSnapshot.child("Rating").getValue().toString();


                                                TouristLocation model = new TouristLocation(id, country, city, name, phone
                                                        , address, timing, link, location, description, uri, rating);
                                                list.add(model);


                                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                                                recyclerView.setLayoutManager(layoutManager);
                                                recyclerView.setItemAnimator(new DefaultItemAnimator());

                                                adapter = new TouristLocationAdapter(getContext(), list);

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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rootRef.child("Cafe").child(country).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    checker = 1;
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String citykey = snapshot.getKey();

                    if (citykey != null) {
                        rootRef.child("Cafe").child(country).child(citykey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    final String key = snapshot.getKey();

                                    if (key != null) {
                                        rootRef.child("Cafe").child(country).child(citykey).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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
                                                String uri = dataSnapshot.child("Uri").getValue().toString();
                                                String rating = dataSnapshot.child("Rating").getValue().toString();


                                                TouristLocation model = new TouristLocation(id, country, city, name, phone
                                                        , address, timing, link, location, description, uri, rating);
                                                list.add(model);

                                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                                                recyclerView.setLayoutManager(layoutManager);
                                                recyclerView.setItemAnimator(new DefaultItemAnimator());

                                                adapter = new TouristLocationAdapter(getContext(), list);

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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rootRef.child("Things").child(country).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    checker = 1;
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String citykey = snapshot.getKey();

                    if (citykey != null) {
                        rootRef.child("Things").child(country).child(citykey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    final String key = snapshot.getKey();

                                    if (key != null) {
                                        rootRef.child("Things").child(country).child(citykey).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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
                                                String uri = dataSnapshot.child("Uri").getValue().toString();
                                                String rating = dataSnapshot.child("Rating").getValue().toString();

                                                TouristLocation model = new TouristLocation(id, country, city, name, phone
                                                        , address, timing, link, location, description, uri, rating);
                                                list.add(model);

                                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                                                recyclerView.setLayoutManager(layoutManager);
                                                recyclerView.setItemAnimator(new DefaultItemAnimator());

                                                adapter = new TouristLocationAdapter(getContext(), list);

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
                }

                mShimmerViewContainer.setVisibility(View.GONE);
                if (checker == 0) {
                    animFailed.setVisibility(View.VISIBLE);
                    mShimmerViewContainer.stopShimmer();
                    Toasty.warning(getActivity(), "We  are unable to find any near by Location..!", Toasty.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    @Override
    public void onPause() {
        super.onPause();
        ConnectivityManager connManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (mWifi.isConnected() || isConnected) {

            animInternet.setVisibility(View.GONE);
        } else {
            animInternet.setVisibility(View.VISIBLE);
            Toasty.warning(getContext(), "Check Your Internet ! Make Sure Your are Connected to Internet ", Toasty.LENGTH_SHORT).show();

        }

        if (!location.hasLocationEnabled()) {
            if (mWifi.isConnected() || isConnected) {

                animInternet.setVisibility(View.GONE);
                animLocation.setVisibility(View.VISIBLE);
            } else {
                animLocation.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ConnectivityManager connManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (mWifi.isConnected() || isConnected) {

            animInternet.setVisibility(View.GONE);
        } else {
            animInternet.setVisibility(View.VISIBLE);
            Toasty.warning(getContext(), "Check Your Internet ! Make Sure Your are Connected to Internet ", Toasty.LENGTH_SHORT).show();

        }

        if (!location.hasLocationEnabled()) {
            if (mWifi.isConnected() || isConnected) {

                animInternet.setVisibility(View.GONE);
                animLocation.setVisibility(View.VISIBLE);
            } else {
                animLocation.setVisibility(View.GONE);
            }

        }

    }


    private void SearchAnyPlace() {
        String address = SearchView.getQuery().toString().trim();

        List<Address> addressList = null;
        MarkerOptions userMarkerOptions = new MarkerOptions();
        if (!TextUtils.isEmpty(address)) {
            Geocoder geocoder = new Geocoder(getContext());

            try {
                addressList = geocoder.getFromLocationName(address, 6);
                if (addressList != null) {

                    for (int i = 0; i < addressList.size(); i++) {
                        Address userAddress = addressList.get(i);
                        LatLng latLng = new LatLng(userAddress.getLatitude(), userAddress.getLongitude());
                        userMarkerOptions.position(latLng);
                        userMarkerOptions.title(address);
                        userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

                        if(ShoppingMallFragment.mMap != null)
                        {
                            ShoppingMallFragment.mMap.addMarker(userMarkerOptions);
                            ShoppingMallFragment.mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            ShoppingMallFragment.mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                        }

                        if(ResturantFragment.mMap != null)
                        {
                            ResturantFragment.mMap.addMarker(userMarkerOptions);
                            ResturantFragment.mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            ResturantFragment.mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                        }

                        if(CafeFragment.mMap != null)
                        {
                            CafeFragment.mMap.addMarker(userMarkerOptions);
                            CafeFragment.mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            CafeFragment.mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                        }

                        if(ThingsFragment.mMap != null)
                        {
                            ThingsFragment.mMap.addMarker(userMarkerOptions);
                            ThingsFragment.mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            ThingsFragment.mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                        }





                    }
                } else {
                    Toast.makeText(getContext(), "Location Not Found....", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getContext(), "Please write any location name ....", Toast.LENGTH_SHORT).show();
        }

    }

}

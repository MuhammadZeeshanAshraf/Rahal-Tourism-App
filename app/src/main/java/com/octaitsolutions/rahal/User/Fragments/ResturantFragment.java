package com.octaitsolutions.rahal.User.Fragments;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.octaitsolutions.rahal.Adapter.TouristLocationAdapter;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.HomeActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import im.delight.android.location.SimpleLocation;

public class ResturantFragment extends Fragment implements OnMapReadyCallback {


    View view;
    LottieAnimationView animLocation, animInternet;
    private RecyclerView recyclerView;
    private ArrayList<TouristLocation> list;
    private TouristLocationAdapter adapter;
    ShimmerFrameLayout mShimmerViewContainer;

    SimpleLocation location;

    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String currentUserId;
    int checker = 0;

    LinearLayout emtpyFavourite;
    RelativeLayout rootLayout;
    Button listView, mapViewButton;

    public static GoogleMap mMap;
    private MapView mapView;
    private FrameLayout mapFrame;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_resturant, container, false);
        emtpyFavourite = view.findViewById(R.id.emptyFavourite);
        rootLayout = view.findViewById(R.id.rootLayout);
        listView = view.findViewById(R.id.ListView);
        mapViewButton = view.findViewById(R.id.MapView);
        mapFrame = view.findViewById(R.id.map_frame);
        mapFrame.setVisibility(View.GONE);
        emtpyFavourite.setVisibility(View.GONE);

        animInternet = view.findViewById(R.id.animation_viewb);
        animInternet.setVisibility(View.GONE);

        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.tourist_location_recyclerview);

        animInternet = view.findViewById(R.id.animation_viewb);
        animInternet.setVisibility(View.GONE);
        animLocation = view.findViewById(R.id.animation_view);
        animLocation.setVisibility(View.GONE);

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

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootLayout.setVisibility(View.VISIBLE);
                mapFrame.setVisibility(View.GONE);
            }
        });

        mapViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootLayout.setVisibility(View.GONE);
                mapFrame.setVisibility(View.VISIBLE);
            }
        });

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                LatLng lahore = new LatLng(31.520370, 74.358749);
                LatLng shah = new LatLng(31.617720,74.292834);
                LatLng uet = new LatLng(31.586652,74.382433);
                googleMap.addMarker(new MarkerOptions().position(lahore).title("Resturant"));
                googleMap.addMarker(new MarkerOptions().position(shah).title("Resturant"));
                googleMap.addMarker(new MarkerOptions().position(uet).title("Resturant"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uet, 0));
                mapView.onResume();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(uet, 10));
                    }
                }, 500);

            }
        });

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
                emtpyFavourite.setVisibility(View.VISIBLE);
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

        rootRef.child("Restaurant").child(country).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        final String citykey = snapshot.getKey();


                        if (citykey != null) {
                            rootRef.child("Restaurant").child(country).child(citykey).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        checker = 1;
                                    }
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        final String key = snapshot.getKey();
                                        checker = 1;
                                        if (key != null) {
                                            rootRef.child("Restaurant").child(country).child(citykey).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    emtpyFavourite.setVisibility(View.GONE);

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
                        emtpyFavourite.setVisibility(View.VISIBLE);
                        mShimmerViewContainer.stopShimmer();
//                        Toasty.warning(getActivity(), "We  are unable to find any near by Location..!", Toasty.LENGTH_SHORT).show();

                    }
                }else
                {
//                    animFailed.setVisibility(View.VISIBLE);
//                    mShimmerViewContainer.stopShimmer();
//                    mShimmerViewContainer.setVisibility(View.GONE);
//                    Toasty.warning(getActivity(), "We  are unable to find any near by Location..!", Toasty.LENGTH_SHORT).show();

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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            MapsInitializer.initialize(getContext());
            mMap = googleMap;
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            LatLng sydney = new LatLng(23, 73);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 0));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
                }
            }, 500);

        }catch (Exception e)
        {
            Toast.makeText(getContext(), "On Ready" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
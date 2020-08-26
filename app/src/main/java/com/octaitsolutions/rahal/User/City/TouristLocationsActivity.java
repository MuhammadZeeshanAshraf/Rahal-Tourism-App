package com.octaitsolutions.rahal.User.City;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kwabenaberko.openweathermaplib.constants.Lang;
import com.kwabenaberko.openweathermaplib.constants.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;
import com.octaitsolutions.rahal.Adapter.CountryAdapter;
import com.octaitsolutions.rahal.Adapter.TouristLocationAdapter;
import com.octaitsolutions.rahal.Model.Country;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.CityActivity;
import com.octaitsolutions.rahal.User.HomeActivity;

import java.util.ArrayList;
import java.util.Random;

public class TouristLocationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView , rest_recyclerview , cafe_recyclerview , things_recyclerview;
    private ArrayList<TouristLocation> list ,restList , cafeList , thingsList;
    private TouristLocationAdapter adapter;

    ImageView cityImage, info;
    ScrollView scrollView;
    TextView cityName , weather;
    ShimmerFrameLayout mShimmerViewContainer;

    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String currentUserId;

    String selectedCountry = "", selectedCity = "";
    int city_name;
    int city_image;

    RelativeLayout catShopping  , catRestaurant , catCafe , catTings;
    ImageView back;


    TextView ar_shop , ar_rest , ar_cafe , ar_things;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tourist_locations);

        OpenWeatherMapHelper helper = new OpenWeatherMapHelper(getString(R.string.OPEN_WEATHER_MAP_API_KEY));
        helper.setUnits(Units.METRIC);
        helper.setLang(Lang.ENGLISH);


        back = findViewById(R.id.iv_back);
        catShopping = findViewById(R.id.cat_shopping);
        catRestaurant= findViewById(R.id.cat_rest);
        catCafe = findViewById(R.id.cat_cafe);
        catTings = findViewById(R.id.cat_things);

        weather = findViewById(R.id.tl_weather);
        cityImage = findViewById(R.id.tl_city_image);
        recyclerView = findViewById(R.id.tourist_location_recyclerview);

        initializeVariables();

        cafe_recyclerview = findViewById(R.id.cat_cafe_recyclerview);
        cafe_recyclerview.setVisibility(View.GONE);
        rest_recyclerview = findViewById(R.id.cat_rest_recyclerview);
        rest_recyclerview.setVisibility(View.GONE);
        things_recyclerview = findViewById(R.id.cat_things_recyclerview);
        things_recyclerview.setVisibility(View.GONE);

        scrollView = findViewById(R.id.scroll_view);
        cityName = findViewById(R.id.tl_city_name);
        info = findViewById(R.id.tl_info);
        info.setVisibility(View.GONE);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container_home);
        mShimmerViewContainer.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            currentUserId = firebaseAuth.getCurrentUser().getUid();

        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        list = new ArrayList<>();
        restList = new ArrayList<>();
        cafeList = new ArrayList<>();
        thingsList = new ArrayList<>();

        city_name = getIntent().getIntExtra("name", 0);
        city_image = getIntent().getIntExtra("image", 0);
        selectedCountry = getIntent().getStringExtra("selectedCountry");

        cityName.setText(city_name);
        cityImage.setImageResource(city_image);
        scrollView.scrollTo(0, 0);
        selectedCity = cityName.getText().toString();

        helper.getCurrentWeatherByCityName(selectedCity, new CurrentWeatherCallback() {
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
//                Toast.makeText(TouristLocationsActivity.this, "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon() +"\n"
//                        +"Weather Description: " + currentWeather.getWeather().get(0).getDescription() + "\n"
//                        +"Temperature: " + currentWeather.getMain().getTempMax()+"\n"
//                        +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
//                        +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry(), Toast.LENGTH_SHORT
//                ).show();

                if(HomeActivity.LANG.equals("en"))
                {
                    weather.setText("Weather " + currentWeather.getMain().getTempMax() +  " C");
                }else
                {
                    weather.setText(getResources().getString(R.string.ar_weather_c) + currentWeather.getMain().getTempMax() +  " C");
                }

            }

            @Override
            public void onFailure(Throwable throwable) {
                Random r = new Random();
                int i1 = r.nextInt(40 - 5) + 5;
                weather.setText("Weather " + i1 + " C");
            }
        });

        catShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                rest_recyclerview.setVisibility(View.GONE);
                cafe_recyclerview.setVisibility(View.GONE);
                things_recyclerview.setVisibility(View.GONE);
            }
        });

        catRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                rest_recyclerview.setVisibility(View.VISIBLE);
                cafe_recyclerview.setVisibility(View.GONE);
                things_recyclerview.setVisibility(View.GONE);
            }
        });

        catCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                rest_recyclerview.setVisibility(View.GONE);
                cafe_recyclerview.setVisibility(View.VISIBLE);
                things_recyclerview.setVisibility(View.GONE);
            }
        });

        catTings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                rest_recyclerview.setVisibility(View.GONE);
                cafe_recyclerview.setVisibility(View.GONE);
                things_recyclerview.setVisibility(View.VISIBLE);
            }
        });

        if (city_name == R.string.Qatar ||
                city_name == R.string.Singapore ||
                city_name == R.string.Maldives ||
                city_name == R.string.Oman ||
                city_name == R.string.Mauritius ||
                city_name == R.string.Bahrain ||
                city_name == R.string.Kuwait ||
                city_name == R.string.Jordan ||
                city_name == R.string.Algeria ||
                city_name == R.string.Tunisia) {
            info.setVisibility(View.VISIBLE);

            if (city_name == R.string.Qatar) {
                selectedCountry = "Qatar";
                selectedCity = "Qatar";
            }
            if (city_name == R.string.Singapore) {
                selectedCountry = "Singapore";
                selectedCity = "Singapore";
            }
            if (city_name == R.string.Maldives) {
                selectedCountry = "Maldives";
                selectedCity = "Maldives";
            }
            if (city_name == R.string.Oman) {
                selectedCountry = "Oman";
                selectedCity = "Oman";
            }
            if (city_name == R.string.Mauritius) {
                selectedCountry = "Mauritius";
                selectedCity = "Mauritius";
            }
            if (city_name == R.string.Bahrain) {
                selectedCountry = "Bahrain";
                selectedCity = "Bahrain";
            }
            if (city_name == R.string.Kuwait) {
                selectedCountry = "Kuwait";
                selectedCity = "Kuwait";
            }
            if (city_name == R.string.Jordan) {
                selectedCountry = "Jordan";
                selectedCity = "Jordan";
            }
            if (city_name == R.string.Algeria) {
                selectedCountry = "Algeria";
                selectedCity = "Algeria";
            }
            if (city_name == R.string.Tunisia) {
                selectedCountry = "Tunisia";
                selectedCity = "Tunisia";
            }

        }

        GetDataFromDatabase();

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TouristLocationsActivity.this, CityInformationActivity.class);
                intent.putExtra("name", city_name);
                startActivity(intent);
            }
        });

    }

    private void initializeVariables()
    {
        ar_shop =findViewById(R.id.lang_shop);
        ar_rest =findViewById(R.id.lang_rest);
        ar_cafe =findViewById(R.id.lang_cafe);
        ar_things =findViewById(R.id.lang_things);
        if(HomeActivity.LANG.equals("ar"))
        {
            ar_shop.setText(R.string.ar_shopping);
            ar_rest.setText(R.string.ar_restaurant);
            ar_cafe.setText(R.string.ar_cafes);
            ar_things.setText(R.string.ar_things_to_do);
            weather.setText(R.string.ar_weather_c);
        }
        else
        {
            ar_shop.setText(R.string.shopping);
            ar_rest.setText(R.string.restaurant);
            ar_cafe.setText(R.string.cafes);
            ar_things.setText(R.string.things_to_do);
            weather.setText(R.string.weather_c);
        }
    }

    private void GetDataFromDatabase() {
        list.clear();
        restList.clear();
        cafeList.clear();
        thingsList.clear();

        rootRef.child("Shopping").child(selectedCountry).child(selectedCity).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String key = snapshot.getKey();
                    list.clear();

                    if(key != null)
                    {
                        rootRef.child("Shopping").child(selectedCountry).child(selectedCity).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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
                                , address , timing , link , location , description , uri,rating);
                                list.add(model);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(TouristLocationsActivity.this, LinearLayoutManager.VERTICAL, false);

                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());

                                adapter = new TouristLocationAdapter(TouristLocationsActivity.this, list);

                                recyclerView.setAdapter(adapter);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }

                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        list.clear();
        restList.clear();
        cafeList.clear();
        thingsList.clear();

        rootRef.child("Restaurant").child(selectedCountry).child(selectedCity).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String key = snapshot.getKey();
                    list.clear();

                    if(key != null)
                    {
                        rootRef.child("Restaurant").child(selectedCountry).child(selectedCity).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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


                                TouristLocation model = new TouristLocation(id , country , city , name , phone
                                        , address , timing , link , location , description , uri,rating);
                                restList.add(model);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(TouristLocationsActivity.this, LinearLayoutManager.VERTICAL, false);

                                rest_recyclerview.setLayoutManager(layoutManager);
                                rest_recyclerview.setItemAnimator(new DefaultItemAnimator());

                                adapter = new TouristLocationAdapter(TouristLocationsActivity.this, restList);

                                rest_recyclerview.setAdapter(adapter);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }

                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        rootRef.child("Cafe").child(selectedCountry).child(selectedCity).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String key = snapshot.getKey();
                    list.clear();

                    if(key != null)
                    {
                        rootRef.child("Cafe").child(selectedCountry).child(selectedCity).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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


                                TouristLocation model = new TouristLocation(id , country , city , name , phone
                                        , address , timing , link , location , description , uri,rating);
                                cafeList.add(model);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(TouristLocationsActivity.this, LinearLayoutManager.VERTICAL, false);

                                cafe_recyclerview.setLayoutManager(layoutManager);
                                cafe_recyclerview.setItemAnimator(new DefaultItemAnimator());

                                adapter = new TouristLocationAdapter(TouristLocationsActivity.this, cafeList);

                                cafe_recyclerview.setAdapter(adapter);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }

                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        rootRef.child("Things").child(selectedCountry).child(selectedCity).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String key = snapshot.getKey();
                    list.clear();

                    if(key != null)
                    {
                        rootRef.child("Things").child(selectedCountry).child(selectedCity).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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


                                TouristLocation model = new TouristLocation(id , country , city , name , phone
                                        , address , timing , link , location , description , uri,rating);
                                thingsList.add(model);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(TouristLocationsActivity.this, LinearLayoutManager.VERTICAL, false);

                                things_recyclerview.setLayoutManager(layoutManager);
                                things_recyclerview.setItemAnimator(new DefaultItemAnimator());

                                adapter = new TouristLocationAdapter(TouristLocationsActivity.this, thingsList);

                                things_recyclerview.setAdapter(adapter);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }

                mShimmerViewContainer.setVisibility(View.GONE);
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

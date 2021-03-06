package com.octaitsolutions.rahal.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.makeramen.roundedimageview.RoundedImageView;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import id.zelory.compressor.Compressor;

public class EditShopping extends AppCompatActivity {
    RoundedImageView previewImage;
    ImageView camera;
    Button addShoppingPlace;
    EditText Name , Phone , Address , OpenTime, WebsiteLink , LocationLatitude , Description;
    Spinner countrySpinner , citySpinner;
    ProgressBar loadingBar;
    Uri productUri;

    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    StorageReference ShopImageReference;
    String currentUserId;

    String selectedCountry = "" , selectedCity = "" , category="Shopping";
    TouristLocation model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_shopping);

        try {
            Intent intent = getIntent();
            model = (TouristLocation) intent.getSerializableExtra("Location");
            category = intent.getStringExtra("cat");
        }catch (Exception e)
        {
            category = "Shopping";
        }

        productUri = null;
        firebaseAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        currentUserId = firebaseAuth.getCurrentUser().getUid();
        ShopImageReference = FirebaseStorage.getInstance().getReference().child(category);

        countrySpinner =  findViewById(R.id.country_spinner);
        citySpinner =  findViewById(R.id.city_spinner);
        previewImage = findViewById(R.id.as_preview_image);
        camera = findViewById(R.id.as_camera);
        addShoppingPlace = findViewById(R.id.as_add_shopping);
        Name =findViewById(R.id.as_name);
        Phone =findViewById(R.id.as_phone);
        Address =findViewById(R.id.as_address);
        OpenTime =findViewById(R.id.as_open_time);
        WebsiteLink =findViewById(R.id.as_website_link);
        LocationLatitude =findViewById(R.id.as_loc_lat);
        Description =findViewById(R.id.as_desc);
        loadingBar = findViewById(R.id.as_loading_bar);
        Wave wave = new Wave();
        loadingBar.setIndeterminateDrawable(wave);
        loadingBar.setVisibility(View.INVISIBLE);

        Name.setText(model.getName());
        Phone.setText(model.getPhone());
        Address.setText(model.getAddress());
        OpenTime.setText(model.getTiming());
        WebsiteLink.setText(model.getLink());
        LocationLatitude.setText(model.getLocation());
        Description.setText(model.getDescription());

        Picasso.get().load(model.getUri()).placeholder(R.drawable.hotel).error(R.drawable.hotel).into(previewImage);
        productUri= Uri.parse(model.getUri());




        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(EditShopping.this);
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                R.array.Country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countrySpinner.setAdapter(adapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedCountry = countrySpinner.getSelectedItem().toString().trim();
                //1
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Argentina)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Argentina_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }
                //2
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Australia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Australia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //3
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Austria)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Austria_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //4
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Belgium)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Belgium_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }
                //5
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Bhutan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Bhutan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //6
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Brazil)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Brazil_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //7
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Colombia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Colombia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //8
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Colombia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Colombia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //9
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.canada)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Canada_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //10
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.China)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.China_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //11
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Egypt)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Egypt_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //11
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.France)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.France_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //12
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Germany)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Germany_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //13
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Greece)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Greece_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //14
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.India)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.India_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //14
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Indonesia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Indonesia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //15
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Ireland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Ireland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //16
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Japan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Japan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //17
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Italy)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Italy_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //18
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Kenya)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Kenya_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //19
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Korea)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Korea_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //20
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Malaysia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Malaysia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //21
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Netherlands)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Netherlands_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //22
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.New_Zealand)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.New_Zealand_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //23
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Philippines)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Philippines_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //24
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Poland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Poland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //25
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Portugal)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Portugal_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //26
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Russia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Russia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //27
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Saudi_Arabia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Saudi_Arabia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //28
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Scotland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Scotland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //29
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Seychelles)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Seychelles_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //30
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.South_Africa)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.South_Africa_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //31
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Spain)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Spain_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //32
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Sri_Lanka)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Sri_Lanka_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //33
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Switzerland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Switzerland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //34
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Bahamas)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Bahamas_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //35
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Czech_Republic)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Czech_Republic_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //36
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Denmark)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Denmark_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //37
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Finland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Finland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //38
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Hungary)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Hungary_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //39
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Iceland)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.IceLand_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //40
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Luxembourg)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Luxembourg_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //41
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Mexico)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Mexico_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //42
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Morocoo)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Morocoo_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //43
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Nepal)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Nepal_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //44
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Norway)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Norway_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //45
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Peru)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Peru_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //46
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Serbia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Serbia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //47
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Slovakia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Sweden_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //48
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Turkey)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Turkey_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //49
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.UAE)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.UAE_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //50
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.United_Kingdom)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.United_Kingdom_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //51
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.USA)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.USA_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //52
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Uzbekistan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Uzbekistan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //53
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Vitnam)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Vitnam_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //54
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Thailand)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Thiland_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //55
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Pakistan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Pakistan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //56
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Croatia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Croatia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //57
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Estonia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Estonia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //58
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Bulgaria)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Bulgaria_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //59
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Costa_Rica)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Costa_Rice_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                //60
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Cambodia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Cambodia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }


                // Special Countries
                if (countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Qatar)) )
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Qatar_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if( countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Singapore)) )
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Singapore_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if( countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Maldives)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Maldives_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if(countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Oman)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Oman_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if(countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Bahrain)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Bahrain_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

                if(countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Kuwait)))
                { ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                        R.array.Kuwait_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);

                }
                if(countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Jordan)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Jordan_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }
                if( countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Algeria)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Algeria_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }
                if( countrySpinner.getSelectedItem().toString().trim().equals(getString(R.string.Tunisia)))
                {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditShopping.this,
                            R.array.Tunisia_cities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpinner.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = citySpinner.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addShoppingPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    final String name = Name.getText().toString();
                    final String phone = Phone.getText().toString();
                    final String address = Address.getText().toString();
                    final String opentime = OpenTime.getText().toString();
                    final String link = WebsiteLink.getText().toString();
                    final String lat = LocationLatitude.getText().toString();
                    final String desc = Description.getText().toString();

                    if (TextUtils.isEmpty(name)) {
                        Name.setError("Name Required");
                        Toasty.error(EditShopping.this, "Enter Shopping Place Name", Toasty.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(phone)) {
                        Phone.setError("Mobile Number Required");
                        Toasty.error(EditShopping.this, "Enter Shopping Place Mobile Number", Toasty.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(address)) {
                        Address.setError("Address Required");
                        Toasty.error(EditShopping.this, "Enter Shopping Place Address", Toasty.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(opentime)) {
                        OpenTime.setError("Opening Time Required");
                        Toasty.error(EditShopping.this, "Enter Shopping Place Opening Time", Toasty.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(link)) {
                        WebsiteLink.setError("WebLink Required");
                        Toasty.error(EditShopping.this, "Enter Shopping Place WebLink", Toasty.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(lat)) {
                        LocationLatitude.setError("Location Latitude Required");
                        Toasty.error(EditShopping.this, "Enter Shopping Place Location Latitude", Toasty.LENGTH_SHORT).show();
                    }

                    else if (TextUtils.isEmpty(desc)) {
                        Description.setError("Location Longitude Required");
                        Toasty.error(EditShopping.this, "Enter Shopping Place Description", Toasty.LENGTH_SHORT).show();
                    }
                    else
                    {
                        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                        ConnectivityManager cm =
                                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        boolean isConnected = activeNetwork != null &&
                                activeNetwork.isConnectedOrConnecting();


                        if (mWifi.isConnected() || isConnected) {


                            if (Uri.EMPTY.equals(productUri) || productUri == null) {
                                Toasty.error(EditShopping.this, "Select Preview Image of the Shopping Place", Toasty.LENGTH_SHORT).show();

                            } else {


                                EditShoppingPlaceToDatabase(name, phone, address, opentime ,link , lat, desc );
                            }

                        } else {
                            Toasty.warning(EditShopping.this, "Check Your Internet ! Make Sure Your are Connected to Internet ", Toasty.LENGTH_SHORT).show();
                        }

                    }

                }catch (Exception e)
                {
                    loadingBar.setVisibility(View.GONE);
                    if (Uri.EMPTY.equals(productUri) || productUri == null) {
                        Toasty.error(EditShopping.this, "Select Preview Image of the shopping place", Toasty.LENGTH_SHORT).show();

                    }else
                    {
                        Toasty.error(EditShopping.this, "Some Problem happen will adding the Picture...!" + e.getMessage(), Toasty.LENGTH_SHORT).show();

                    }

                }
            }
        });

    }

    private void EditShoppingPlaceToDatabase(String name, String phone, String address, String opentime, String link, String loc, String desc) {
        loadingBar.setVisibility(View.VISIBLE);
        File actualImage = new File(productUri.getPath());

        if(productUri.equals(Uri.parse(model.getUri())))
        {

            Map MessageMap = new HashMap<>();
            MessageMap.put("ID", model.getId());
            MessageMap.put("Country", model.getCountry());
            MessageMap.put("City", model.getCity());
            MessageMap.put("Name", name);
            MessageMap.put("Phone", phone);
            MessageMap.put("Address", address);
            MessageMap.put("Timing", opentime);
            MessageMap.put("Link", link);
            MessageMap.put("Location", loc);
            MessageMap.put("Description", desc);
            MessageMap.put("Rating", "5");
            MessageMap.put("Uri", model.getUri() + "");

            rootRef.child(category).child(model.getCountry()).child(model.getCity()).child(model.getId()).updateChildren(MessageMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if (task.isSuccessful()) {
                        Toasty.info(EditShopping.this, "Place is edited Successfully.....", Toasty.LENGTH_SHORT).show();

                        finish();
                    } else {
                        loadingBar.setVisibility(View.GONE);
                        Toasty.error(EditShopping.this, "Some Problem happen will editing Place...!", Toasty.LENGTH_SHORT).show();

                    }

                }
            });

        }
        else
        {
            try {

                StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.getUri());

                storageReference.delete();

                Bitmap compressedImage = new Compressor(this)
                        .setMaxWidth(300)
                        .setMaxHeight(300)
                        .setQuality(100)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .compressToBitmap(actualImage);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                compressedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] final_image = baos.toByteArray();

                final StorageReference filePath = ShopImageReference.child(productUri.getLastPathSegment() + ".jpg");

                UploadTask uploadTask = filePath.putBytes(final_image);

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                Map MessageMap = new HashMap<>();
                                MessageMap.put("ID", model.getId());
                                MessageMap.put("Country", model.getCountry());
                                MessageMap.put("City", model.getCity());
                                MessageMap.put("Name", name);
                                MessageMap.put("Phone", phone);
                                MessageMap.put("Address", address);
                                MessageMap.put("Timing", opentime);
                                MessageMap.put("Link", link);
                                MessageMap.put("Location", loc);
                                MessageMap.put("Description", desc);
                                MessageMap.put("Rating", "5");
                                MessageMap.put("Uri", uri + "");

                                rootRef.child(category).child(model.getCountry()).child(model.getCity()).child(model.getId()).updateChildren(MessageMap).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {

                                        if (task.isSuccessful()) {
                                            Toasty.info(EditShopping.this, "Place is edited Successfully.....", Toasty.LENGTH_SHORT).show();

                                            finish();
                                        } else {
                                            loadingBar.setVisibility(View.GONE);
                                            Toasty.error(EditShopping.this, "Some Problem happen will editing the Shopping Place...!", Toasty.LENGTH_SHORT).show();

                                        }

                                    }
                                });
                            }

                        });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });
            } catch (Exception error) {
                loadingBar.setVisibility(View.GONE);
                Toasty.error(EditShopping.this, "Some Problem happen will editing item...!", Toasty.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void showToast(String s)
    {
        Toasty.info(EditShopping.this , s, Toasty.LENGTH_SHORT).show();
    }

    private void SendUserToActivity(Activity activity)
    {
        Intent intent = new Intent(EditShopping.this , activity.getClass());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                productUri = result.getUri();


                File actualImage = new File(productUri.getPath());

                Bitmap compressedImage = null;
                try {
                    compressedImage = new Compressor(this)
                            .setMaxWidth(250)
                            .setMaxHeight(250)
                            .setQuality(50)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .compressToBitmap(actualImage);
                    previewImage.setImageBitmap(Bitmap.createScaledBitmap(compressedImage, 256, 256, true));
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toasty.error(EditShopping.this, "" + error, Toasty.LENGTH_SHORT).show();

            }
        }
    }
}



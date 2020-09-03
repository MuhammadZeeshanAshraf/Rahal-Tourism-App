package com.octaitsolutions.rahal.User.City;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.octaitsolutions.rahal.R;

public class CityWeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

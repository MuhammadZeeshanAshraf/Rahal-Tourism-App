package com.octaitsolutions.rahal.User.City;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.octaitsolutions.rahal.R;

public class AboutCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_city);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

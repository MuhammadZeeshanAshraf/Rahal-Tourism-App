package com.octaitsolutions.rahal.User.City.CityInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.octaitsolutions.rahal.R;

public class InfoInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info_information);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

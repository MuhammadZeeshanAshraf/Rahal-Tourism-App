package com.octaitsolutions.rahal.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.octaitsolutions.rahal.R;

import es.dmoral.toasty.Toasty;

public class AdminDashboardActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout addShopping , mangeShopping , addRestaurant , mangeRestaurant , addCafe , mangeCafe , addTTD , mangeTTD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin_dashboard);

        addShopping = findViewById(R.id.ll_add_shopping);
        mangeShopping = findViewById(R.id.ll_manage_shopping);
        addRestaurant = findViewById(R.id.ll_add_restaurant);
        mangeRestaurant = findViewById(R.id.ll_manage_restaurant);
        addCafe = findViewById(R.id.ll_add_cafe);
        mangeCafe = findViewById(R.id.ll_mange_cafe);
        addTTD = findViewById(R.id.ll_add_things_to_do);
        mangeTTD = findViewById(R.id.ll_mange_things_to_do);

        addShopping.setOnClickListener(this::onClick);
        mangeShopping.setOnClickListener(this::onClick);
        addRestaurant.setOnClickListener(this::onClick);
        mangeRestaurant.setOnClickListener(this::onClick);
        addCafe.setOnClickListener(this::onClick);
        mangeCafe.setOnClickListener(this::onClick);
        addTTD.setOnClickListener(this::onClick);
        mangeTTD.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ll_add_shopping:
               SendUserToActivity(new AddShopping());
                break;
            case R.id.ll_manage_shopping:
                Intent b = new Intent(AdminDashboardActivity.this , SelectCountryAndCity.class);
                b.putExtra("checker", "1");
                startActivity(b);
                break;
            case R.id.ll_add_restaurant:
                SendUserToActivity(new AddRestaurant());
                break;
            case R.id.ll_manage_restaurant:
                Intent intent = new Intent(AdminDashboardActivity.this , SelectCountryAndCity.class);
                intent.putExtra("checker", "2");
                startActivity(intent);
                break;
            case R.id.ll_add_cafe:
                SendUserToActivity(new AddCafe());
                break;
            case R.id.ll_mange_cafe:
                Intent i = new Intent(AdminDashboardActivity.this , SelectCountryAndCity.class);
                i.putExtra("checker", "3");
                startActivity(i);
                break;
            case R.id.ll_add_things_to_do:
                SendUserToActivity(new AddThings());
                break;
            case R.id.ll_mange_things_to_do:
                Intent a = new Intent(AdminDashboardActivity.this , SelectCountryAndCity.class);
                a.putExtra("checker", "4");
                startActivity(a);
                break;



        }
    }

    private void showToast(String s)
    {
        Toasty.info(AdminDashboardActivity.this , s, Toasty.LENGTH_SHORT).show();
    }

    private void SendUserToActivity(Activity activity)
    {
        Intent intent = new Intent(AdminDashboardActivity.this , activity.getClass());
        startActivity(intent);
    }
}

package com.octaitsolutions.rahal.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {
    LottieAnimationView logo;
    TextView app_name, slogan;
    ImageView app_logo;
    Animation sideAnim, bottomAnim, anime;

    private static int SPLASH_TIMER = 5000;
    SharedPreferences onBoardingScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.animation_view);
        app_name = findViewById(R.id.app_name);
        slogan = findViewById(R.id.description);
        app_logo = findViewById(R.id.app_logo);

        //Animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
        sideAnim.setDuration(1500);
        bottomAnim.setDuration(1500);

        moveIcon(logo);
        app_name.setAnimation(sideAnim);
        slogan.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime" , true);

                if(isFirstTime)
                {
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext() , OnBoardingActivity.class);
                    startActivity(intent);
                    finish();
                }else
                {
                    Intent intent = new Intent(getApplicationContext() , HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },SPLASH_TIMER);

    }

    private void moveIcon(View view) {
        int[] originalPos = new int[2];
        view.getLocationOnScreen(originalPos);
        anime = new TranslateAnimation(0, 0, 0, originalPos[1] + 100);
        anime.setDuration(2000);
        anime.setFillAfter(true);
        view.startAnimation(anime);
    }

    private void  SendUserToActivity(Activity activity)
    {
        Intent intent = new Intent(SplashScreenActivity.this, activity.getClass());
        startActivity(intent);
        finish();
    }
}

package com.octaitsolutions.rahal.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.octaitsolutions.rahal.Admin.AdminDashboardActivity;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.HomeActivity;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Button signupButton , LoginButton;
    EditText Email, Password;
    ProgressBar loadingBar;

    DatabaseReference rootReference;
    FirebaseAuth firebaseAuth;
    private int count = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        rootReference = FirebaseDatabase.getInstance().getReference();

        InitializeFields();


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToActivity(new RegisterActivity());
            }
        });


        imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                if (count == 0) {
                    imageView.setImageResource(R.drawable.afternoon);
                    textView.setText("Afternoon");
                    count = 1;
                } else if (count == 1) {
                    imageView.setImageResource(R.drawable.evening);
                    textView.setText("Evening");
                    count = 2;
                } else if (count == 2) {
                    imageView.setImageResource(R.drawable.good_night_img);
                    textView.setText("Night");
                    count = 3;
                } else if (count == 3) {
                    imageView.setImageResource(R.drawable.good_morning_img);
                    textView.setText("Morning");
                    count = 0;
                }

            }

            public void onSwipeLeft() {
                if (count == 0) {
                    imageView.setImageResource(R.drawable.afternoon);
                    textView.setText("Afternoon");
                    count = 1;
                } else if (count == 1) {
                    imageView.setImageResource(R.drawable.evening);
                    textView.setText("Evening");
                    count = 2;
                } else if (count == 2) {
                    imageView.setImageResource(R.drawable.good_night_img);
                    textView.setText("Night");
                    count = 3;
                } else if (count == 3) {
                    imageView.setImageResource(R.drawable.good_morning_img);
                    textView.setText("Morning");
                    count = 0;
                }

            }

            public void onSwipeBottom() {
            }

        });


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String email = Email.getText().toString();
                    final String password = Password.getText().toString();

                    if(TextUtils.isEmpty(email))
                    {
                        Email.setError("Email Required");
                        Toasty.error(LoginActivity.this ,"Enter Your Email", Toasty.LENGTH_SHORT).show();

                    }
                    if(TextUtils.isEmpty(password))
                    {
                        Password.setError("Password Required");
                        Toasty.error(LoginActivity.this ,"Enter Your Password", Toasty.LENGTH_SHORT).show();

                    }
                    if(!(TextUtils.isEmpty(email)))
                    {
                        if(!(TextUtils.isEmpty(password)))
                        {
                            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                            ConnectivityManager cm =
                                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                            boolean isConnected = activeNetwork != null &&
                                    activeNetwork.isConnectedOrConnecting();


                            if (mWifi.isConnected() || isConnected) {

                                AllowUserToLogin(email, password);
                            }
                            else
                            {
                                Toasty.warning(LoginActivity.this, "Check Your Internet ! Make Sure Your are Connected to Internet ", Toasty.LENGTH_SHORT).show();
                            }



                        }

                    }
                }catch (Exception e)
                {
                    Toast.makeText(LoginActivity.this, ""+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    private void InitializeFields() {
        signupButton = findViewById(R.id.signup);
        LoginButton = findViewById(R.id.signin);
        Email = findViewById(R.id.login_email);
        Password = findViewById(R.id.login_password);
        loadingBar = findViewById(R.id.loadingbar);
        Wave wave = new Wave();
        loadingBar.setIndeterminateDrawable(wave);
        loadingBar.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.register_imageView);
        textView = findViewById(R.id.register_textView);
        Calendar c = Calendar.getInstance();

        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 10) {
            imageView.setImageResource(R.drawable.good_morning_img);
            textView.setText("Morning");
        } else if (timeOfDay >= 10 && timeOfDay < 16) {
            imageView.setImageResource(R.drawable.afternoon);
            textView.setText("Afternoon");
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            imageView.setImageResource(R.drawable.evening);
            textView.setText("Evening");
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            imageView.setImageResource(R.drawable.good_night_img);
            textView.setText("Night");
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void SendUserToActivity(Activity activity)
    {
        Intent intent = new Intent(LoginActivity.this , activity.getClass());
        startActivity(intent);
        finish();
    }

    private void AllowUserToLogin(final String email, final String password) {

        loadingBar.setVisibility(View.VISIBLE);

        if(email.equals("admin@admin.com") && password.equals("admin123"))
        {
            firebaseAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toasty.success(LoginActivity.this, "Admin Login Successfully" , Toast.LENGTH_SHORT).show();
                        loadingBar.setVisibility(View.INVISIBLE);
                        SendUserToActivity(new AdminDashboardActivity());
                    }else
                    {
                        loadingBar.setVisibility(View.INVISIBLE);
                        Toasty.error(LoginActivity.this ,"Some Problem occur while login....", Toasty.LENGTH_SHORT).show();

                    }
                }
            });

        }
        else
        {
            firebaseAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {

                        SendUserToActivity(new HomeActivity());
                    }else
                    {
                        loadingBar.setVisibility(View.INVISIBLE);
                        Toasty.error(LoginActivity.this ,"Some Problem occur while login....", Toasty.LENGTH_SHORT).show();

                    }
                }
            });
        }




    }
}

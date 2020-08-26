package com.octaitsolutions.rahal.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.HomeActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import id.zelory.compressor.Compressor;

public class RegisterActivity extends AppCompatActivity {

    ImageView imageView;
    TextView register_text, textView;
    Button signupButton;
    EditText Email, Username, Password, Confirm_Password;
    ProgressBar loadingBar;
    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    private int count = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        InitializeFields();


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateNewAccount();

            }
        });
        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
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
    }

    private void CreateNewAccount() {
        final String email = Email.getText().toString();
        final String username = Username.getText().toString();
        final String password = Password.getText().toString();
        String confirm_password = Confirm_Password.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toasty.error(RegisterActivity.this, "Enter Your Username", Toast.LENGTH_SHORT, true).show();
        } else if (TextUtils.isEmpty(email)) {
            Toasty.error(RegisterActivity.this, "Enter Your Email", Toast.LENGTH_SHORT, true).show();
        } else if (TextUtils.isEmpty(password)) {
            Toasty.error(RegisterActivity.this, "Enter Your Password", Toast.LENGTH_SHORT, true).show();
        } else if (TextUtils.isEmpty(confirm_password)) {
            Toasty.error(RegisterActivity.this, "Confirm Your Password", Toast.LENGTH_SHORT, true).show();
        } else if (!(password.equals(confirm_password))) {
            Toasty.error(RegisterActivity.this, "Password and Confirm Password Must Be Same", Toast.LENGTH_SHORT, true).show();
        } else {
//            loadingBar.setVisibility(View.VISIBLE);

            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            ConnectivityManager cm =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();


            if (mWifi.isConnected() || isConnected) {


                AddUserToDatabase(username, email, password);

            } else {
                Toasty.warning(RegisterActivity.this, "Check Your Internet ! Make Sure Your are Connected to Internet ", Toasty.LENGTH_SHORT).show();
            }


        }

    }


    private void InitializeFields() {
        register_text = findViewById(R.id.register_text);
        signupButton = findViewById(R.id.register_signup);
        Email = findViewById(R.id.register_email);
        Username = findViewById(R.id.register_username);
        Password = findViewById(R.id.register_password);
        Confirm_Password = findViewById(R.id.register_confirm_password);
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


    private void AddUserToDatabase(final String a, final String b, final String c) {
        loadingBar.setVisibility(View.VISIBLE);

        try {
            DatabaseReference userKeyRef = rootRef.child("Users").push();
            firebaseAuth.createUserWithEmailAndPassword(b, c).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        final String currentUserID = firebaseAuth.getCurrentUser().getUid();
                        Map MessageMap = new HashMap<>();
                        MessageMap.put("ID", currentUserID);
                        MessageMap.put("Name", a);
                        MessageMap.put("Email", b);
                        MessageMap.put("Password", c);

                        rootRef.child("Users").child(currentUserID).setValue(MessageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    Intent intent = new Intent(RegisterActivity.this , HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else
                                {
                                    loadingBar.setVisibility(View.GONE);
                                    Toasty.error(RegisterActivity.this, "Some Problem happen will adding user...!", Toasty.LENGTH_SHORT).show();

                                }
                            }
                        });

                    }else
                    {
                        loadingBar.setVisibility(View.GONE);
                        Toasty.error(RegisterActivity.this, "Some Problem happen will adding user...!", Toasty.LENGTH_SHORT).show();

                    }
                }
            });




        } catch (Exception error) {
            loadingBar.setVisibility(View.GONE);
            Toasty.error(RegisterActivity.this, "Some Problem happen will adding user...!", Toasty.LENGTH_SHORT).show();
        }
    }

}

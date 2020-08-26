package com.octaitsolutions.rahal.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.octaitsolutions.rahal.R;
import com.yugansh.tyagi.smileyrating.SmileyRatingView;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class RatingActivity extends AppCompatActivity {

    RatingBar ratingBar;
    SmileyRatingView smileyRating;
    Button submit;
    EditText review;
    float rate = 1;

    DatabaseReference rootReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rating);

        firebaseAuth = FirebaseAuth.getInstance();
        rootReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            currentUserId = firebaseAuth.getCurrentUser().getUid();

        }

        try {
            currentUserId = firebaseAuth.getCurrentUser().getUid();
        }catch (Exception e)
        {
            currentUserId = "null";
        }

        submit = findViewById(R.id.submit_review);
        review = findViewById(R.id.review_box);
        smileyRating = findViewById(R.id.smiley_view);
        ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                smileyRating.setSmiley(rating);
                rate = rating;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(review.getText().toString().equals(""))
                {
                    review.setError("Kindly enter you honest review about us");
                }else
                {
                    ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                    ConnectivityManager cm =
                            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    boolean isConnected = activeNetwork != null &&
                            activeNetwork.isConnectedOrConnecting();


                    if (mWifi.isConnected() || isConnected) {

                        DatabaseReference userKeyRef = rootReference.child("Rating").child(currentUserId).push();

                        final String PushID = userKeyRef.getKey();

                        Map MessageMap = new HashMap<>();
                        MessageMap.put("ID", PushID);
                        MessageMap.put("UserID", currentUserId);
                        MessageMap.put("Rating", rate);
                        MessageMap.put("Review", review.getText().toString().trim());

                        rootReference.child("Rating").child(currentUserId).child(PushID).updateChildren(MessageMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {

                                if(task.isSuccessful())
                                {
                                    review.setText("");
                                    Toasty.warning(RatingActivity.this , "Thanks For Your Review", Toasty.LENGTH_SHORT).show();

                                    finish();
                                }else
                                {
                                    Toasty.error(RatingActivity.this ,"Some problem occur while rating...", Toasty.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                    else
                    {
                        Toasty.warning(RatingActivity.this, "Check Your Internet ! Make Sure Your are Connected to Internet ", Toasty.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

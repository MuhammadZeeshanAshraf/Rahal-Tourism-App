package com.octaitsolutions.rahal.User.City;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codesgood.views.JustifiedTextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.octaitsolutions.rahal.Adapter.ManageAdapter;
import com.octaitsolutions.rahal.Adapter.ReviewAdapter;
import com.octaitsolutions.rahal.Admin.AddShopping;
import com.octaitsolutions.rahal.Admin.ManageRestaurant;
import com.octaitsolutions.rahal.Model.Review;
import com.octaitsolutions.rahal.Model.TouristLocation;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.HomeActivity;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class PlaceActivity extends AppCompatActivity {

    ImageView image;
    JustifiedTextView description, numRating;
    TextView name , address , phone  , link , time , SeeMore;
    TouristLocation model;
    RatingBar starRating;

    DatabaseReference rootReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String currentUserId;

    CircleImageView profileImage;
    EditText comment;
    RelativeLayout thumbUp;
    ScaleRatingBar scaleRatingBar;

    RecyclerView recyclerView;
    ArrayList<Review> list , shortList;
    ReviewAdapter adapter;

    int checker = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        firebaseAuth = FirebaseAuth.getInstance();
        rootReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            currentUserId = firebaseAuth.getCurrentUser().getUid();

        }

        Intent i = getIntent();
        model = (TouristLocation) i.getSerializableExtra("Location");
        hideKeyboard(PlaceActivity.this);


        list = new ArrayList<>();
        shortList = new ArrayList<>();
        recyclerView = findViewById(R.id.review_list);
        image = findViewById(R.id.place_image);

        SeeMore = findViewById(R.id.see_more_reviews);
        profileImage = findViewById(R.id.place_profile_image);
        comment = findViewById(R.id.place_comment_box);
        thumbUp = findViewById(R.id.place_thumb_up);
        scaleRatingBar = findViewById(R.id.place_scale_rating_bar);

        name = findViewById(R.id.place_name);
        description = findViewById(R.id.place_des);
        address = findViewById(R.id.place_address);
        time = findViewById(R.id.place_time);
        phone = findViewById(R.id.place_phone);
        link = findViewById(R.id.place_link);
        numRating = findViewById(R.id.place_no_rate);
        starRating = findViewById(R.id.place_star_rate);

        Picasso.get().load(model.getUri()).placeholder(R.drawable.pizza).error(R.drawable.pizza).into(image);

        name.setText(model.getName());
        description.setText(model.getDescription());
        address.setText(model.getAddress());
        time.setText(model.getTiming());
        phone.setText(model.getPhone());
        link.setText(model.getLink());
        numRating.setText(model.getRating());
        starRating.setRating(Float.parseFloat(model.getRating()));


        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getLink()));
                startActivity(browserIntent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + model.getPhone()));
                startActivity(intent);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String uri = "http://maps.google.com/maps?saddr=" + 30.5765 + "," + 74.6786 + "&daddr=" + 33.765 + "," + 79.211;
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+ model.getAddress()));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });


        thumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             CheckFields();
            }
        });


        SeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayoutManager layoutManager = new LinearLayoutManager(PlaceActivity.this, LinearLayoutManager.VERTICAL, false);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                adapter = new ReviewAdapter(PlaceActivity.this, list);

                recyclerView.setAdapter(adapter);

                SeeMore.setVisibility(View.GONE);

            }
        });

    }

    private void CheckFields() {
        String  com = comment.getText().toString().trim();
        float rat = scaleRatingBar.getRating();

        if(TextUtils.isEmpty(com))
        {
            Toasty.error(PlaceActivity.this , "Write some comment before posting review." , Toasty.LENGTH_SHORT).show();
        }
        else if(rat == 0.0)
        {
            Toasty.error(PlaceActivity.this , "Rate some star before posting review." , Toasty.LENGTH_SHORT).show();

        }
        else
        {
            PostingReviewOnDatabase(com , rat);
        }
    }

    private void PostingReviewOnDatabase(String com, float rat) {

        DatabaseReference userKeyRef = rootReference.child("Review").child(model.getCountry()).child(model.getCity())
                .child(model.getId()).push();

        final String PushID = userKeyRef.getKey();

        Map MessageMap = new HashMap<>();
        MessageMap.put("ID", PushID);
        MessageMap.put("Comment", com);
        MessageMap.put("Rating", rat+"");
        MessageMap.put("Name", HomeActivity.USERNAME);
        MessageMap.put("UserId", HomeActivity.ID);
        if(!(HomeActivity.PIURI.equals("")))
        {
            MessageMap.put("Uri", HomeActivity.PIURI);
        }else
        {
            MessageMap.put("Uri", "Null");
        }

        rootReference.child("Review").child(model.getCountry()).child(model.getCity())
                .child(model.getId()).child(PushID).updateChildren(MessageMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful())
                {
                    comment.setText("");
                    scaleRatingBar.setRating(0);
                    Toasty.info(PlaceActivity.this, "Place review is added Successfully.....", Toasty.LENGTH_SHORT).show();
                    RetrieveDataFromDatabase();
                }
                else
                {
                    Toasty.error(PlaceActivity.this, "Some Problem happen will posting review about the Place...!", Toasty.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(firebaseUser == null)
        {
            Toasty.info(PlaceActivity.this , "You have to login if want to review about this place." ,Toasty.LENGTH_SHORT).show();

        }else
        {
//            Toasty.info(PlaceActivity.this , "Click on Thumb Up OR Thumb Down to post review about this place." ,Toasty.LENGTH_SHORT).show();

        }

        if(!(HomeActivity.PIURI.equals("")))
        {
            Picasso.get().load(HomeActivity.PIURI).placeholder(R.drawable.face_1).error(R.drawable.face_1).into(profileImage);

        }
        RetrieveDataFromDatabase();
    }


    private void RetrieveDataFromDatabase()
    {
        list.clear();
        rootReference.child("Review").child(model.getCountry()).child(model.getCity()).child(model.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String key = snapshot.getKey();
                    list.clear();

                    if(key != null)
                    {
                        rootReference.child("Review").child(model.getCountry()).child(model.getCity()).child(model.getId()).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                String id = dataSnapshot.child("ID").getValue().toString();
                                String userId = dataSnapshot.child("UserId").getValue().toString();
                                String name = dataSnapshot.child("Name").getValue().toString();
                                String comment = dataSnapshot.child("Comment").getValue().toString();
                                String rating = dataSnapshot.child("Rating").getValue().toString();
                                String uri = dataSnapshot.child("Uri").getValue().toString();


                                if(list.size() < 4)
                                {
                                    Review model = new Review(id , userId , name , comment , rating , uri);
                                    list.add(model);

                                    LinearLayoutManager layoutManager = new LinearLayoutManager(PlaceActivity.this, LinearLayoutManager.VERTICAL, false);

                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                                    adapter = new ReviewAdapter(PlaceActivity.this, list );

                                    recyclerView.setAdapter(adapter);

                                }else
                                {
                                    SeeMore.setVisibility(View.VISIBLE);


                                    Review model = new Review(id , userId , name , comment , rating , uri);
                                    list.add(model);

                                    if(checker < 4)
                                    {
                                        shortList.add(model);
                                        checker = checker + 1;
                                    }


                                    LinearLayoutManager layoutManager = new LinearLayoutManager(PlaceActivity.this, LinearLayoutManager.VERTICAL, false);

                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                                    adapter = new ReviewAdapter(PlaceActivity.this, shortList );

                                    recyclerView.setAdapter(adapter);
                                }




                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

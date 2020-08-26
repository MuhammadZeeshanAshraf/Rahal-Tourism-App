package com.octaitsolutions.rahal.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.BubbleToggleView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.Fragments.HomeFragment;
import com.octaitsolutions.rahal.User.Fragments.OfferFragment;
import com.octaitsolutions.rahal.User.Fragments.RealFragment;
import com.octaitsolutions.rahal.User.Fragments.SettingFragment;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import id.zelory.compressor.Compressor;

public class HomeActivity extends AppCompatActivity {
    BubbleNavigationConstraintView navigation;
    FrameLayout viewPager;

    BubbleToggleView home , near , fav ,settiing;

    public static String ID = "";
    public static String USERNAME = "";
    public static String EMAIL = "";
    public static String PASSWORD = "";
    public static String PIURI = "";
    public static String LANG = "en";
    public static int LOCATION = 0;

    DatabaseReference rootReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    StorageReference ShopImageReference;
    String currentUserId;

    Uri productUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        productUri = null;

        home = findViewById(R.id.l_item_dash);
        near = findViewById(R.id.l_item_map);
        home = findViewById(R.id.l_item_wallet);
        home = findViewById(R.id.l_item_account);

        firebaseAuth = FirebaseAuth.getInstance();
        rootReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            currentUserId = firebaseAuth.getCurrentUser().getUid();

        }
        ShopImageReference = FirebaseStorage.getInstance().getReference().child("Profile");


        navigation = findViewById(R.id.home_navigation);
        viewPager = findViewById(R.id.home_view_pager);
        getSupportFragmentManager().beginTransaction().replace(viewPager.getId(), new HomeFragment()).commit();

        navigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                Fragment selectedFragment = null;
                switch (position) {
                    case 0:
                        selectedFragment = new HomeFragment();
                        break;
                    case 1:
                        selectedFragment = new OfferFragment();
                        break;
                    case 2:
                        selectedFragment = new RealFragment();
                        break;
                    case 3:
                        selectedFragment = new SettingFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(viewPager.getId(), selectedFragment).commit();

            }
        });
        RetriveDataFromDatabase();

        AskForPermissions();


    }

    @Override
    protected void onStart() {
        super.onStart();
        RetriveDataFromDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetriveDataFromDatabase();
    }

    @Override
    protected void onPause() {
        super.onPause();
        RetriveDataFromDatabase();
    }

    private void RetriveDataFromDatabase() {
        if (firebaseUser != null) {
            if (firebaseUser.getUid().equalsIgnoreCase("bz5WFcZAEsewUNucgi0lsAxOcQE2")) {
                ID = "bz5WFcZAEsewUNucgi0lsAxOcQE2";
                USERNAME = "Admin";
                EMAIL = "admin@admin.com";
                PASSWORD = "admin123";

            } else {
                rootReference.child("Users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            ID = firebaseUser.getUid();
                            USERNAME = dataSnapshot.child("Name").getValue().toString();
                            EMAIL = dataSnapshot.child("Email").getValue().toString();
                            PASSWORD = dataSnapshot.child("Password").getValue().toString();

                            if (dataSnapshot.hasChild("Uri")) {
                                PIURI = dataSnapshot.child("Uri").getValue().toString();
                            }

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
//                    profileImage.setImageBitmap(Bitmap.createScaledBitmap(compressedImage, 256, 256, true));


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
                                    MessageMap.put("ID", currentUserId);
                                    MessageMap.put("Email", HomeActivity.EMAIL);
                                    MessageMap.put("Password", HomeActivity.PASSWORD);
                                    MessageMap.put("Name", HomeActivity.USERNAME);
                                    MessageMap.put("Uri", uri + "");

                                    rootReference.child("Users").child(currentUserId).updateChildren(MessageMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {

                                            if (task.isSuccessful()) {
                                                Toasty.info(HomeActivity.this, "Profile Image is added Successfully.....", Toasty.LENGTH_SHORT).show();

                                            } else {

                                                Toasty.error(HomeActivity.this, "Some Problem happen will adding the Profile Image...!", Toasty.LENGTH_SHORT).show();

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
                } catch (IOException e) {

                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
                Toasty.error(HomeActivity.this, "" + error, Toasty.LENGTH_SHORT).show();

            }
        }
    }

    private void AskForPermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {

                }

                if (report.isAnyPermissionPermanentlyDenied()) {
                    showSettingsDialog();

                }

                if (report.getDeniedPermissionResponses().size() > 0) {
                    LOCATION = 1;
                }


            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();

    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Need Location Permissions ");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    public void onBackPressed() {

        showExitDialog();

    }

    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setMessage("You are about to exit the app. \nYou want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", (dialog, id) -> finishAffinity())
                .setNegativeButton("No", null)
                .show();
    }
}

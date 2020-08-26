package com.octaitsolutions.rahal.User.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.octaitsolutions.rahal.Admin.AddShopping;
import com.octaitsolutions.rahal.Auth.LoginActivity;
import com.octaitsolutions.rahal.Auth.RegisterActivity;
import com.octaitsolutions.rahal.BuildConfig;
import com.octaitsolutions.rahal.R;
import com.octaitsolutions.rahal.User.ContactUsActivity;
import com.octaitsolutions.rahal.User.HomeActivity;
import com.octaitsolutions.rahal.User.RatingActivity;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import id.zelory.compressor.Compressor;
import im.delight.android.location.SimpleLocation;

import static com.octaitsolutions.rahal.User.HomeActivity.PIURI;

public class SettingFragment extends Fragment implements View.OnClickListener {
    private View view;
    private LinearLayout Username, Email, Password, SignIn, Login, Notification, Location, Rate, Share, Contact, language;
    private TextView name, mail, changeProfile;
    private CircleImageView profileImage;
    Uri productUri;
    ProgressBar loadingBar;


    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    StorageReference ShopImageReference;
    String currentUserId;

    
    TextView ar_set, ar_email , ar_reg , ar_log , ar_change_lan , ar_act_not , ar_act_loc, ar_rate , ar_share ,ar_cont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        initializeVariables(view);
        productUri = null;
        firebaseAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            currentUserId = firebaseAuth.getCurrentUser().getUid();

        }
        ShopImageReference = FirebaseStorage.getInstance().getReference().child("Profile");

        Username = view.findViewById(R.id.set_username);
        Email = view.findViewById(R.id.set_email);
        Password = view.findViewById(R.id.set_password);
        SignIn = view.findViewById(R.id.set_sign_in);
        Login = view.findViewById(R.id.set_log_in);
        Notification = view.findViewById(R.id.set_notification);
        Location = view.findViewById(R.id.set_location);
        Rate = view.findViewById(R.id.set_rate);
        Share = view.findViewById(R.id.set_share);
        Contact = view.findViewById(R.id.set_contact_us);
        name = view.findViewById(R.id.username);
        mail = view.findViewById(R.id.useremail);
        profileImage = view.findViewById(R.id.profile_image);
        language = view.findViewById(R.id.change_lang);
        changeProfile = view.findViewById(R.id.setting_change_profile);

        loadingBar = view.findViewById(R.id.as_loading_bar);
        Wave wave = new Wave();
        loadingBar.setIndeterminateDrawable(wave);
        loadingBar.setVisibility(View.GONE);

        if(!(HomeActivity.PIURI.equals("")))
        {
            Picasso.get().load(HomeActivity.PIURI).placeholder(R.drawable.face_1).error(R.drawable.face_1).into(profileImage);

        }


        Username.setOnClickListener(this::onClick);
        Email.setOnClickListener(this::onClick);
        Password.setOnClickListener(this::onClick);
        SignIn.setOnClickListener(this::onClick);
        Login.setOnClickListener(this::onClick);
        Notification.setOnClickListener(this::onClick);
        Location.setOnClickListener(this::onClick);
        Rate.setOnClickListener(this::onClick);
        Share.setOnClickListener(this::onClick);
        Contact.setOnClickListener(this::onClick);
        language.setOnClickListener(this::onClick);
        changeProfile.setOnClickListener(this::onClick);

        if (!HomeActivity.USERNAME.equals("")) {
            name.setText(HomeActivity.USERNAME);
            mail.setText(HomeActivity.EMAIL);
        }

        return view;
    }

    private void initializeVariables(View view) {
        ar_set = view.findViewById(R.id.lang_setting);
        ar_email = view.findViewById(R.id.lang_email);
        ar_reg = view.findViewById(R.id.lang_register);
        ar_log = view.findViewById(R.id.lang_login);
        ar_change_lan = view.findViewById(R.id.lang_change_lang);
        ar_act_not = view.findViewById(R.id.lang_act_not);
        ar_act_loc = view.findViewById(R.id.lang_act_loc);
        ar_rate = view.findViewById(R.id.lang_rate_app);
        ar_share = view.findViewById(R.id.lang_share_app);
        ar_cont = view.findViewById(R.id.lang_contact_us);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_username:
//                SendUserToActivity(new RegisterActivity());
                break;
            case R.id.set_email:
//                SendUserToActivity(new RegisterActivity());
                break;
            case R.id.set_password:
//                SendUserToActivity(new RegisterActivity());
                break;
            case R.id.set_sign_in:
                SendUserToActivity(new RegisterActivity());
                break;
            case R.id.set_log_in:
                SendUserToActivity(new LoginActivity());
                break;
            case R.id.set_notification:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                    intent.putExtra(Settings.EXTRA_APP_PACKAGE, BuildConfig.APPLICATION_ID);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                    startActivity(intent);
                }
                break;
            case R.id.set_location:
                SimpleLocation.openSettings(getActivity());
                break;
            case R.id.set_rate:
                SendUserToActivity(new RatingActivity());
                break;
            case R.id.set_share:
                ShareApp();
                break;
            case R.id.set_contact_us:
                SendUserToActivity(new ContactUsActivity());
                break;
            case R.id.profile_image:
//                SendUserToActivity(new RegisterActivity());
                break;
            case R.id.change_lang:
                if(HomeActivity.LANG.equals("en"))
                {
                    ChangeLanguageOfTheAppToArabic();
                }
                else
                {
                    ChangeLanguageOfTheAppToEnglish();
                }

                break;
            case R.id.setting_change_profile:
                if(firebaseAuth.getCurrentUser() == null)
                {
                    Toasty.info(getContext() , "You have to login to use this feature..." , Toasty.LENGTH_SHORT).show();
                }else
                {
                    try {
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAspectRatio(1, 1)
                                .start(getActivity());
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "00", Toast.LENGTH_SHORT).show();

                    }
                }


                break;
        }
    }

    private void ChangeLanguageOfTheAppToEnglish()
    {
        HomeActivity.LANG = "en";
        ar_set.setText(R.string.settings);
        ar_email.setText(R.string.email);
        ar_reg.setText(R.string.register);
        ar_log.setText(R.string.login);
        ar_change_lan.setText(R.string.change_the_language);
        ar_act_not.setText(R.string.activate_notifications);
        ar_act_loc.setText(R.string.activate_location);
        ar_rate.setText(R.string.rate_the_app);
        ar_share.setText(R.string.share_the_app);
        ar_cont.setText(R.string.contact_us);
        changeProfile.setText(R.string.change_profile_photo);
    }

    private void ChangeLanguageOfTheAppToArabic()
    {
        HomeActivity.LANG = "ar";
        ar_set.setText(R.string.ar_settings);
        ar_email.setText(R.string.ar_email);
        ar_reg.setText(R.string.ar_register);
        ar_log.setText(R.string.ar_login);
        ar_change_lan.setText(R.string.ar_change_the_language);
        ar_act_not.setText(R.string.ar_activate_notifications);
        ar_act_loc.setText(R.string.ar_activate_location);
        ar_rate.setText(R.string.ar_rate_the_app);
        ar_share.setText(R.string.ar_share_the_app);
        ar_cont.setText(R.string.ar_contact_us);
        changeProfile.setText(R.string.ar_change_profile_photo);
    }

    public void SendUserToActivity(Activity activity) {
        Intent intent = new Intent(getContext(), activity.getClass());
        getActivity().startActivity(intent);
    }

    private void ShareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
            String shareMessage = "\nThis is an awesome traveling app\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == getActivity().RESULT_OK) {

                loadingBar.setVisibility(View.VISIBLE);
                productUri = result.getUri();


                File actualImage = new File(productUri.getPath());

                Bitmap compressedImage = null;
                try {
                    compressedImage = new Compressor(getContext())
                            .setMaxWidth(250)
                            .setMaxHeight(250)
                            .setQuality(50)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .compressToBitmap(actualImage);
                    profileImage.setImageBitmap(Bitmap.createScaledBitmap(compressedImage, 256, 256, true));


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

                                    rootRef.child("Users").child(currentUserId).updateChildren(MessageMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {

                                            if (task.isSuccessful()) {
                                                loadingBar.setVisibility(View.GONE);
                                                Toasty.info(getContext(), "Profile Image is added Successfully.....", Toasty.LENGTH_SHORT).show();

                                            } else {
                                                loadingBar.setVisibility(View.GONE);

                                                Toasty.error(getContext(), "Some Problem happen will adding the Profile Image...!", Toasty.LENGTH_SHORT).show();

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
                    loadingBar.setVisibility(View.GONE);

                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                loadingBar.setVisibility(View.GONE);

                Exception error = result.getError();
                Toasty.error(getContext(), "" + error, Toasty.LENGTH_SHORT).show();

            }
        }
    }
}



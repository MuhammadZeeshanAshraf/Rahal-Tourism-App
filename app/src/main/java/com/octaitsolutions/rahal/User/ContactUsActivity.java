package com.octaitsolutions.rahal.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.octaitsolutions.rahal.Auth.LoginActivity;
import com.octaitsolutions.rahal.R;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ContactUsActivity extends AppCompatActivity {

    EditText title , message;
    Button sendMsg;

    DatabaseReference rootReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String currentUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contact_us);

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

        title = findViewById(R.id.cu_title);
        message = findViewById(R.id.cu_message);
        sendMsg = findViewById(R.id.cu_send_message);

        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = title.getText().toString().trim();
                String b = message.getText().toString().trim();

                if(TextUtils.isEmpty(a))
                {
                    title.setError("Enter the Message Title");
                    Toasty.error(ContactUsActivity.this ,"Title of the message required...", Toasty.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(b))
                {
                    message.setError("Enter the Message ");
                    Toasty.error(ContactUsActivity.this ,"Details of the message required...", Toasty.LENGTH_SHORT).show();
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

                        ContactUs(a, b);
                    }
                    else
                    {
                        Toasty.warning(ContactUsActivity.this, "Check Your Internet ! Make Sure Your are Connected to Internet ", Toasty.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void ContactUs(String title, String message)
    {
        DatabaseReference userKeyRef = rootReference.child("ContactUs").child(currentUserId).push();

        final String PushID = userKeyRef.getKey();

        Map MessageMap = new HashMap<>();
        MessageMap.put("ID", PushID);
        MessageMap.put("UserID", currentUserId);
        MessageMap.put("Title", title);
        MessageMap.put("Message", message);

        rootReference.child("ContactUs").child(currentUserId).child(PushID).updateChildren(MessageMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful())
                {
                    Toasty.info(ContactUsActivity.this ,"Your Message has been deliver. Thank You for contacting us...", Toasty.LENGTH_SHORT).show();
                    finish();
                }else
                {
                    Toasty.error(ContactUsActivity.this ,"Some problem occur while delivering your message...", Toasty.LENGTH_SHORT).show();

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

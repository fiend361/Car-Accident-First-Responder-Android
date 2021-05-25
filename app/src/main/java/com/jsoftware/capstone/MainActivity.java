package com.jsoftware.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient client;
    private DatabaseReference databaseReference;
    private WebView mWebView;

    int id;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent prevIntent= getIntent();
        if (prevIntent != null){
            Bundle b = prevIntent.getExtras();
            int i = (int) b.get("name");
            id = i+1;
        }

        client = LocationServices.getFusedLocationProviderClient(this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("emergencies").child(String.valueOf(id));
        databaseReference.keepSynced(true);

        FirebaseMessaging.getInstance().subscribeToTopic("NEWS");

        mWebView = findViewById(R.id.mWebView2);
        mWebView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
            final double latitude = location.getLatitude();
            final double longitude = location.getLongitude();

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //Toast.makeText(MainActivity.this, dataSnapshot, Toast.LENGTH_SHORT).show();
                    String lat = dataSnapshot.child("latitude").getValue().toString();
                    String lon = dataSnapshot.child("longitude").getValue().toString();

                    //Toast.makeText(MainActivity.this, lat +""+ lon, Toast.LENGTH_LONG).show();
                    String url = "https://www.google.com/maps/dir/" + latitude +","+ longitude + "/" + lat + "," + lon;
                    mWebView.loadUrl(url);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            }
        });
    }
}

                                                                                                                                                        /* {
                                                                                                                                                            "to":
                                                                                                                                                             "/topics/ANDROID_NEWS"

                                                                                                                                                             ,
                                                                                                                                                             "data": {
                                                                                                                                                                "extra_information": "this is some extra information"
                                                                                                                                                             },
                                                                                                                                                             "notification": {
                                                                                                                                                                "title": "This is the title",
                                                                                                                                                                "text": "hello i am a notification",
                                                                                                                                                                "click_action": "LOGINACTIVITY"
                                                                                                                                                             }
                                                                                                                                                        }*/
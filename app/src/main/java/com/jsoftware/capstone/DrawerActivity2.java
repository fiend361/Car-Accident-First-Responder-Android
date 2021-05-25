package com.jsoftware.capstone;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.telephony.SmsManager;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DrawerActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imageView;
    private FusedLocationProviderClient client;
    private Geocoder geocoder;
    private List<Address> addresses;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference databaseReference, databaseReference1, databaseReference2;

    private long id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("users");
//        databaseReference.keepSynced(true);

        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("medics");
//        databaseReference.keepSynced(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("emergencies");
        databaseReference.keepSynced(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    id = dataSnapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        client = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this, Locale.getDefault());

        imageView = findViewById(R.id.embellimage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentLocation();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        final ImageView profileImageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageViewForProfile);
        final TextView profileTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textViewForProfile);

        if (user != null){
            final String id = auth.getCurrentUser().getUid();
            databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(id)){
                        String imageURL = dataSnapshot.child(id).child("image").getValue().toString();
                        Picasso.get().load(imageURL).into(profileImageView);
                        profileTextView.setText(dataSnapshot.child(id).child("medicName").getValue().toString());
                    }else {
                        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String imageURL = dataSnapshot.child(id).child("image").getValue().toString();
                                Picasso.get().load(imageURL).into(profileImageView);
                                profileTextView.setText(dataSnapshot.child(id).child("firstName").getValue().toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void getCurrentLocation() {
        client.getLastLocation().addOnSuccessListener(DrawerActivity2.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                try {
                    addresses = geocoder.getFromLocation(latitude, longitude,1);
                    String address= addresses.get(0).getAddressLine(0);
                    String fullAddress= address+".";
                    databaseReference.child(String.valueOf(id+1)).child("emergencyLocation").setValue(fullAddress);
                    databaseReference.child(String.valueOf(id+1)).child("latitude").setValue(String.valueOf(latitude));
                    databaseReference.child(String.valueOf(id+1)).child("longitude").setValue(String.valueOf(longitude));


                    SmsManager mySmsManager = SmsManager.getDefault();
                    mySmsManager.sendTextMessage("01030982347", null, "ya raaaab", null, null);

                    Toast.makeText(DrawerActivity2.this, "Emergency location Added.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_emergency) {
            Intent intent = new Intent(DrawerActivity2.this, ListMedicsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_sign_out) {
            auth.signOut();
            if (user == null)
                Toast.makeText(this, "Sign out successful", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Login) {
            Intent intent = new Intent(DrawerActivity2.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_emergencies) {
            Intent intent = new Intent(DrawerActivity2.this, EmergencyListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_firstAid) {
            Intent intent = new Intent(DrawerActivity2.this, SearchRecyclerViewActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

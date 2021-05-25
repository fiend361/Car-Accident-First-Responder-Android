package com.jsoftware.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EmergencyAddedActivity extends AppCompatActivity {

    private DatabaseReference dbReference;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;

    private long id2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_added);

        dbReference = FirebaseDatabase.getInstance().getReference().child("emergencies");

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    id2 = dataSnapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toast.makeText(this, String.valueOf(id2), Toast.LENGTH_SHORT).show();
        dbReference.child(String.valueOf(id2+1)).child("emergencyLocation").setValue("Unnamed Road, First 6th of October, Giza Governorate, Egypt.");
        dbReference.child(String.valueOf(id2+1)).child("latitude").setValue("29.9318816");
        dbReference.child(String.valueOf(id2+1)).child("longitude").setValue("31.072991");
    }
}

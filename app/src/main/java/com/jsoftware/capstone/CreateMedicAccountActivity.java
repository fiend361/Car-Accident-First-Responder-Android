package com.jsoftware.capstone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CreateMedicAccountActivity extends AppCompatActivity {

    private EditText hosName, hosAddress, hosEmail, hosExp, hosPassword, hosNumber, hosQualification;
    private Button createHospitalAccBtn;
    private ImageView hospitalProfileImage;
    private Uri resultUri;
    private DatabaseReference dbReference;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medic_account);

        hosName = findViewById(R.id.medic_name_ET);
        hosAddress = findViewById(R.id.medic_address_ET);
        hosExp = findViewById(R.id.medic_exp_ET);
        hosEmail = findViewById(R.id.medic_email_ET);
        hosPassword = findViewById(R.id.medic_password_ET);
        hosNumber = findViewById(R.id.medic_number_ET);
        hosQualification = findViewById(R.id.medic_qualification_ET);

        hospitalProfileImage = findViewById(R.id.medic_profile_image);
        hospitalProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
            }
        });

        createHospitalAccBtn = findViewById(R.id.medic_create_acc_btn);
        createHospitalAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

        progressDialog = new ProgressDialog(this);

        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Pictures");
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference().child("medics");
        auth = FirebaseAuth.getInstance();
    }

    private void createNewAccount() {
        final String hosNameS, hosAddressS, hosEmailS, hosExpS, hosPasswordS, hosNumberS, hosQualificationS;
        hosNameS = hosName.getText().toString();
        hosAddressS = hosAddress.getText().toString();
        hosEmailS = hosEmail.getText().toString();
        hosExpS = hosExp.getText().toString();
        hosPasswordS = hosPassword.getText().toString();
        hosNumberS = hosNumber.getText().toString();
        hosQualificationS = hosQualification.getText().toString();


        //check all inputs
        if (!TextUtils.isEmpty(hosNameS) && !TextUtils.isEmpty(hosAddressS)
                && !TextUtils.isEmpty(hosEmailS) && !TextUtils.isEmpty(hosExpS) && !TextUtils.isEmpty(hosPasswordS)) {

            progressDialog.setMessage("Creating Account...");
            progressDialog.show();

            auth.createUserWithEmailAndPassword(hosEmailS, hosPasswordS).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    //if (authResult != null) {
                    StorageReference imagePath = storageReference.child(resultUri.getLastPathSegment());
                    imagePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String userID = auth.getCurrentUser().getUid();
                            DatabaseReference newUser = dbReference.child(userID);
                            newUser.child("medicName").setValue(hosNameS);
                            newUser.child("medicAddress").setValue(hosAddressS);
                            newUser.child("medicExperience").setValue(hosExpS);
                            newUser.child("medicNumber").setValue(hosNumberS);
                            newUser.child("medicQualification").setValue(hosQualificationS);
                            newUser.child("image").setValue(resultUri.toString());

                            progressDialog.dismiss();
                            Toast.makeText(CreateMedicAccountActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateMedicAccountActivity.this, DrawerActivity2.class));
                            finish();
                        }
                    });
                    //}
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(CreateMedicAccountActivity.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri imageURI = data.getData();
            CropImage.activity(imageURI)
                    .setAspectRatio(1, 1)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                hospitalProfileImage.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}

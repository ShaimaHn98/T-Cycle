package com.example.t_cycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Activity extends AppCompatActivity {
    CircleImageView img_profile_upadte;
    EditText txt_username, txt_phone_num;
    RadioButton r_m, r_f;
    Button btn_location, btn_update;
    RadioGroup r_group;
    Uri image_uri = null;
    String Gender;
    static String lat, lon;
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    StorageReference firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        img_profile_upadte = findViewById(R.id.img_profile_update);
        txt_username = findViewById(R.id.txt_username);
        txt_phone_num = findViewById(R.id.txt_phone_n);
        r_m = findViewById(R.id.r_male);
        r_f = findViewById(R.id.r_female);
        r_group = findViewById(R.id.gr);
        btn_update = findViewById(R.id.btn_up_info);
        firestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        btn_location = findViewById(R.id.btn_location);
        img_profile_upadte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .start(Profile_Activity.this);
            }
        });
        final Intent intent = getIntent();
        lat = intent.getStringExtra("latitude");
        lon = intent.getStringExtra("longitude");


        r_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i) {
                    case R.id.r_male:
                        Gender = "Male";
                        break;
                    case R.id.r_female:
                        Gender = "Female";
                        break;
                }
                Toast.makeText(getApplicationContext(), "gende is :" + Gender, Toast.LENGTH_LONG).show();

            }


        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile_Activity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String us_name = txt_username.getText().toString();
                final String phone_n = txt_phone_num.getText().toString();
                final String gender = Gender;
                final String Lat_loc = lat;
                final String long_loc = lon;
                final String uid = mAuth.getCurrentUser().getUid();
                final StorageReference img_path = firebaseStorage.child("Profile_Image").child(uid + ".jpg");
                img_path.putFile(image_uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        img_path.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String imguri = task.getResult().toString();
                                HashMap<String, String> user_info = new HashMap<>();
                                user_info.put("username", us_name);
                                user_info.put("phone_num", phone_n);
                                user_info.put("gender", gender);
                                user_info.put("latitude", Lat_loc);
                                user_info.put("longitude", long_loc);
                                user_info.put("img_profile", imguri);
                                firestore.collection("User_Info").document(uid).set(user_info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            finish();
                                            Intent intent1 = new Intent(Profile_Activity.this, Home_Activity.class);
                                            startActivity(intent1);
                                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                image_uri = result.getUri();
                img_profile_upadte.setImageURI(image_uri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}

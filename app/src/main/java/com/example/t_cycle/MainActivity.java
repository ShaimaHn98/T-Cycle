package com.example.t_cycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.t_cycle.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
FirebaseAuth mAuth;
FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        if (mAuth.getCurrentUser()==null){
        Intent intent=new Intent(MainActivity.this,Login_Activity.class);
        startActivity(intent);
        finish();
        }
        else
            { Intent intent=new Intent(MainActivity.this, Home_Activity.class);
            startActivity(intent);

        }
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.d("ERRORS", "getInstanceId failed", task.getException());
                            return;
                        }


                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        HashMap<String, String> tokenmap = new HashMap<>();
                        tokenmap.put("Token", token);
                        firestore.collection("Token").document(mAuth.getCurrentUser().getUid()).set(tokenmap);
Log.d("TokenID",String.valueOf(token));
                    }
                });
    }
}

package com.tcycle.tcycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login_Activity extends AppCompatActivity {
EditText txt_username,txt_phone_num;
Button btn_login;
FirebaseAuth mAuth;
FirebaseFirestore firestore;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        firestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        txt_username=findViewById(R.id.txt_username);
        txt_phone_num=findViewById(R.id.txt_ph_num);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 number=txt_phone_num.getText().toString().trim();
                if (number.isEmpty() || number.length() != 10) {
                    txt_phone_num.setError("تعبئة الحقل بالرقم الصحيح");
                    txt_phone_num.requestFocus();
                    return;
                }

                      else{

                     final String phoneNumber = "+962"+ number.substring(1);

               String username=txt_username.getText().toString().trim();

                Intent intent = new Intent(Login_Activity.this,Verify_Code_Activity.class);
                intent.putExtra("phonenumber", phoneNumber);
                intent.putExtra("username",username);
                startActivity(intent);}
            }
        });
    }
}

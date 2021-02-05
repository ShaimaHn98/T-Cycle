package com.tcycle.tcycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Verify_Code_Activity extends AppCompatActivity {
    private String verificationId;
    private FirebaseAuth mAuth;
    private EditText txt_code;
    private Button btn_signin;
    FirebaseFirestore firestore;
    String phonenumber;
    String username;
    final Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify__code_);
        btn_signin=findViewById(R.id.btn_signin);
        txt_code=findViewById(R.id.txt_code);
        mAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        phonenumber=getIntent().getStringExtra("phonenumber");
        username=getIntent().getStringExtra("username");
        Log.d("Pho",String.valueOf(phonenumber));
     send_ver_code(phonenumber);
      btn_signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String c=txt_code.getText().toString().trim();
verify_code(c);
    }
});
    }
private void verify_code(String code)
{
PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
SignInWithCred(credential);
}

    private void SignInWithCred(PhoneAuthCredential credential) {
mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful())
        {
            HashMap<String,Object> userinfo= new HashMap<>();
            userinfo.put("phonenumber",phonenumber);
            userinfo.put("username",username);
            userinfo.put("Date",date);
            firestore.collection("Users").document(mAuth.getCurrentUser().getUid()).set(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent intent=new Intent(Verify_Code_Activity.this,Home_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });


        }else{
            Toast.makeText(Verify_Code_Activity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
        }
    }
});
    }

    public void send_ver_code(String number)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallback

        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
String code=phoneAuthCredential.getSmsCode();
if (code!=null)
    verify_code(code);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
Toast.makeText(Verify_Code_Activity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };

}
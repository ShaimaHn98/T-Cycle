package com.example.t_cycle.ui.wallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.t_cycle.Login_Activity;
import com.example.t_cycle.Notification;
import com.example.t_cycle.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SnapshotMetadata;

import javax.annotation.Nullable;

/*
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment {
FirebaseAuth mAuth;
Context context;
Button btn_amount,btn_dues;
FirebaseFirestore firebaseFirestore;
    public WalletFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        View view= inflater.inflate(R.layout.fragment_wallet2, container, false);
        context=getContext();
       btn_amount=view.findViewById(R.id.btn_amount);
       btn_dues=view.findViewById(R.id.btn_dues);
firebaseFirestore.collection("My_Recycling").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {

    }
});
        return  view;
    }
}

package com.example.t_cycle.ui.wallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.t_cycle.Login_Activity;
import com.example.t_cycle.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment {
FirebaseAuth mAuth;
Context context;
    public WalletFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth=FirebaseAuth.getInstance();
        View view= inflater.inflate(R.layout.fragment_wallet2, container, false);
        context=getContext();
mAuth.signOut();
        Intent intent = new Intent(context, Login_Activity.class);
        startActivity(intent);
        return  view;
    }
}

package com.tcycle.tcycle.ui.My_Recycling;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.tcycle.tcycle.My_Recycling;
import com.tcycle.tcycle.R;
import com.tcycle.tcycle.View_Order_Adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class My_Recycling_Fragment extends Fragment implements Serializable {

   // private ViewModel galleryViewModel;

        FirebaseAuth mAuth;
        FirebaseFirestore firestore;
        List<My_Recycling>my_recyclingList;
        View_Order_Adapter viewOrderAdapter;
        RecyclerView rec_order;
        Context context;

    public My_Recycling_Fragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_myresycling, container, false);
        try {

        firestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        context=getActivity().getApplicationContext();
       rec_order=root.findViewById(R.id.rec_order);
       my_recyclingList= new ArrayList<>();
        viewOrderAdapter=new View_Order_Adapter(my_recyclingList);
        rec_order.setAdapter(viewOrderAdapter);
        rec_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query first_query=firestore.collection("My_Recycling").orderBy("Date", Query.Direction.DESCENDING);
        first_query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshots != null)
                {
                    for(DocumentChange documentChange :documentSnapshots.getDocumentChanges())
                    {
                        if (documentChange.getType() == DocumentChange.Type.ADDED){
                            try {
                                My_Recycling my_recycling = documentChange.getDocument().toObject(My_Recycling.class);
                                if (my_recycling.getUID().equals(mAuth.getUid())) {
                                    my_recyclingList.add(my_recycling);
                                    viewOrderAdapter.notifyDataSetChanged();
                                    rec_order.scheduleLayoutAnimation();
                                }
                            }catch (Exception ex ){
                                Log.e("Error",ex.getMessage());
                            }

                        }

                    }

                }
            }
        });

        }catch (Exception ex)
        {
            Toast.makeText(getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }

        return root;
    }
}

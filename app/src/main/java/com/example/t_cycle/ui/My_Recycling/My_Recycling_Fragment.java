package com.example.t_cycle.ui.My_Recycling;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t_cycle.My_Recycling;
import com.example.t_cycle.R;
import com.example.t_cycle.View_Order_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class My_Recycling_Fragment extends Fragment {

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
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        firestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        context=getActivity().getApplicationContext();
       rec_order=root.findViewById(R.id.rec_order);
       my_recyclingList= new ArrayList<>();
        viewOrderAdapter=new View_Order_Adapter(my_recyclingList);
        rec_order.setAdapter(viewOrderAdapter);
        rec_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query first_query=firestore.collection("My_Recycling").orderBy("Date", Query.Direction.DESCENDING).whereEqualTo("UID",mAuth.getCurrentUser().getUid());
        first_query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (!documentSnapshots.isEmpty())
                {
                    for(DocumentChange documentChange :documentSnapshots.getDocumentChanges())
                    {
                        if (documentChange.getType() ==DocumentChange.Type.ADDED)
                       try {
                            My_Recycling my_recycling=documentChange.getDocument().toObject(My_Recycling.class);
                            my_recyclingList.add(my_recycling);
                            viewOrderAdapter.notifyDataSetChanged();
                            rec_order.scheduleLayoutAnimation();



                        }
                        catch (Exception e1)
                        {
                            Toast.makeText(getContext(),"Catch:"+e1.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        });

        return root;
    }
}

package com.example.t_cycle.ui.Notification;

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
import com.example.t_cycle.Notification;
import com.example.t_cycle.Notification_Adapter;
import com.example.t_cycle.R;
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

public class Notification_Fragment extends Fragment {

Context context;
FirebaseFirestore firestore;
FirebaseAuth mAuth;
RecyclerView recyclerView;
Notification_Adapter notification_adapter;
List<Notification>notificationList;

    public Notification_Fragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        firestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        context=getActivity().getApplicationContext();
        recyclerView=root.findViewById(R.id.rec_v_notification);
        notificationList=new ArrayList<>();
        notification_adapter=new Notification_Adapter(notificationList);
        recyclerView.setAdapter(notification_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query first_query=firestore.collection("Notification").orderBy("date",Query.Direction.DESCENDING);
        first_query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (!documentSnapshots.isEmpty())
                {
                    for(DocumentChange documentChange :documentSnapshots.getDocumentChanges())
                    {
                        if (documentChange.getType() ==DocumentChange.Type.ADDED) {


                            Notification my_notification = documentChange.getDocument().toObject(Notification.class);
                            if (my_notification.getUserID().equals(mAuth.getCurrentUser().getUid())) {
                                notificationList.add(my_notification);
                                notification_adapter.notifyDataSetChanged();
                                recyclerView.scheduleLayoutAnimation();
                            }
                        }


                    }

                }
            }
        });

        return root;
    }
}

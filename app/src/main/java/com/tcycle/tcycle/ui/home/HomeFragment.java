package com.tcycle.tcycle.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.tcycle.tcycle.All_Waste_Activity;
import com.tcycle.tcycle.Aw_pic;
import com.tcycle.tcycle.R;
import com.tcycle.tcycle.SliderAdapterExample;
import com.tcycle.tcycle.SliderItem;
import com.tcycle.tcycle.Waste_Sort_Activity;
import com.tcycle.tcycle.dialoge_confirmation;

public class HomeFragment extends Fragment {
dialoge_confirmation dialoge_confirmation;
    
     Button btn_part,btn_all;
     boolean profile_status = false;
     FirebaseAuth mAuth;
     FirebaseFirestore firebaseFirestore ;
    private SliderView imageslider;
    private SliderAdapterExample adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

    /*    firebaseFirestore.collection("User_Info").document(mAuth.getCurrentUser().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                    profile_status = true;
            }
        });*/
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_part=view.findViewById(R.id.btn_part);
        btn_all=view.findViewById(R.id.btn_all);
        imageslider = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(getContext());
        imageslider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        imageslider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageslider.setScrollTimeInSec(2);
        imageslider.setAutoCycle(true);
        imageslider.setSliderAdapter(adapter);
        slidergetitem();
        btn_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Intent intent = new Intent(view.getContext(), Waste_Sort_Activity.class);
                startActivity(intent);
            /*    if (profile_status) {

                }
                else
                    Toast.makeText(getContext(),"يرجى تحديث بيناتك الشخصية ..",Toast.LENGTH_LONG).show();*/
            }
        });
            btn_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { Intent intent2 = new Intent(view.getContext(), All_Waste_Activity.class);
                    startActivity(intent2);
               /*     if (profile_status) {

                    }else
                        Toast.makeText(getContext(),"يرجى تحديث بيناتك الشخصية ..",Toast.LENGTH_LONG).show();*/
                }
            });
        return view;
    }
    void slidergetitem() {

        final Query firstQuery = firebaseFirestore.collection("Awareness_Pic").limit(4); // to show newest Ads at first


        firstQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (!documentSnapshots.isEmpty()) { // this to don't replace ads again

                    for (DocumentChange documentChange : documentSnapshots.getDocumentChanges()) {

                        if (documentChange.getType() == DocumentChange.Type.ADDED) {

                            Aw_pic sliderViewadapter = documentChange.getDocument().toObject(Aw_pic.class);
                            adapter.addItem(new SliderItem(sliderViewadapter.getImg_Awareness()));

                        }
                    }
                }
            }
        });
    }
}

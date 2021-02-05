package com.tcycle.tcycle.ui.wallet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.tcycle.tcycle.My_Recycling;
import com.tcycle.tcycle.R;

import java.util.HashMap;

import javax.annotation.Nullable;

/*
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment {
FirebaseAuth mAuth;
Context context;
Button btn_amount,btn_dues;
FirebaseFirestore firebaseFirestore;
TextView txt_totalBalance;
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
        txt_totalBalance = view.findViewById(R.id.txt_money);
       btn_amount=view.findViewById(R.id.btn_amount);
       btn_dues=view.findViewById(R.id.btn_dues);
        final Query first_query=firebaseFirestore.collection("My_Recycling").orderBy("Date", Query.Direction.DESCENDING);

       btn_dues.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               first_query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                   @Override
                   public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                       if (documentSnapshots != null)
                       {
                           Double TotalBalance = 0.0;
                           for(DocumentChange documentChange :documentSnapshots.getDocumentChanges())
                           {
                               if (documentChange.getType() == DocumentChange.Type.ADDED){
                                   My_Recycling my_recycling=documentChange.getDocument().toObject(My_Recycling.class);
                                   if (my_recycling.getUID().equals( mAuth.getUid())) {
                                       TotalBalance += my_recycling.getTotal();
                                   }


                               }

                           }

                         if (TotalBalance <= 10)
                             Toast.makeText(getContext(), "عذراً يجب أن تكون مستحقاتك أكبر من 10 دنانير" , Toast.LENGTH_LONG).show();
                         else
                         {
                             HashMap<String,Object> Request = new HashMap<>();
                             Request.put("Balance", TotalBalance);
                             Request.put("User",mAuth.getCurrentUser().getUid());
                             firebaseFirestore.collection("RequestMoney").document(mAuth.getCurrentUser().getUid())
                                     .set(Request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     Toast.makeText(getContext(),"تم إرسال طلبك سيتم التواصل معك عن قريب", Toast.LENGTH_LONG).show();
                                 }
                             });
                         }
                       }
                   }
               });
           }
       });
       btn_amount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               first_query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                   @Override
                   public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                       if (documentSnapshots != null)
                       {
                           Double TotalBalance = 0.0;
                           for(DocumentChange documentChange :documentSnapshots.getDocumentChanges())
                           {
                               if (documentChange.getType() == DocumentChange.Type.ADDED){
                                   My_Recycling my_recycling=documentChange.getDocument().toObject(My_Recycling.class);
                                   if (my_recycling.getUID().equals( mAuth.getUid())) {
                                       TotalBalance += my_recycling.getTotal();
                                   }


                               }

                           }

                            ShowDialog(round(TotalBalance,3) + " دينار أردني ");
                       }
                   }
               });
           }
       });


        return  view;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


    private void ShowDialog(String MSG)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("قيمة رصيدك الحالي");
        builder1.setMessage(MSG);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "حسناً",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();

    }


}

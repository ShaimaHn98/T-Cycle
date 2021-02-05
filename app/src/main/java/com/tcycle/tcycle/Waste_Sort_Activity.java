package com.tcycle.tcycle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class Waste_Sort_Activity extends AppCompatActivity {
    Button btn_iron, btn_blastic, btn_pap, btn_alm, btn_carton, btn_nuhas, btn_confirm;
    dialog_weight dialog_weight;
    dialoge_confirmation dialoge_confirmation;
    TextView txt_w;
    int weight_iron, weigh_alm, weight_plas, weight_pap, weight_cart, weight_nuhas;
    static String order_Type_alm, order_Type_nuhas, order_Type_iron, order_Type_cart, order_Type_paper, order_Type_plas;
    int t_input;
    FirebaseFirestore firestore ;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste__sort_);
        btn_iron = findViewById(R.id.btn_iron);
        btn_blastic = findViewById(R.id.btn_plastic);
        btn_pap = findViewById(R.id.btn_paper);
        btn_nuhas = findViewById(R.id.btn_nuhas);
        btn_carton = findViewById(R.id.btn_carton);
        btn_alm = findViewById(R.id.btn_almanuim);
        btn_confirm = findViewById(R.id.btn_confirme);
        txt_w = findViewById(R.id.txt_w);
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoge_confirmation = new dialoge_confirmation();
                dialoge_confirmation.show(getSupportFragmentManager(), null);

            }
        });
        btn_iron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_weight = new dialog_weight();
                dialog_weight.show(getSupportFragmentManager(), null);
                order_Type_iron = "حديد";
                com.tcycle.tcycle.dialoge_confirmation.order_type_iron = String.valueOf(order_Type_iron);
                t_input = 0;
                com.tcycle.tcycle.dialog_weight.type_input = t_input;
            }
        });
        btn_blastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_weight = new dialog_weight();
                dialog_weight.show(getSupportFragmentManager(), null);
                order_Type_plas = "بلاستيك";
                com.tcycle.tcycle.dialoge_confirmation.order_type_plastic = String.valueOf(order_Type_plas);
                t_input = 1;
                com.tcycle.tcycle.dialog_weight.type_input = t_input;

            }
        });
        btn_alm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_weight = new dialog_weight();
                dialog_weight.show(getSupportFragmentManager(), null);
                weigh_alm = com.tcycle.tcycle.dialog_weight.weight;

                order_Type_alm = "أمنيوم";
                com.tcycle.tcycle.dialoge_confirmation.order_type_aluminium = String.valueOf(order_Type_alm);
                com.tcycle.tcycle.dialoge_confirmation.weight_tpye = weigh_alm;
                t_input = 2;
                com.tcycle.tcycle.dialog_weight.type_input = t_input;
            }
        });

        btn_pap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_weight = new dialog_weight();
                dialog_weight.show(getSupportFragmentManager(), null);
                order_Type_paper = "ورق";
                com.tcycle.tcycle.dialoge_confirmation.order_type_paper = String.valueOf(order_Type_paper);
                t_input = 3;
                com.tcycle.tcycle.dialog_weight.type_input = t_input;
            }
        });
        btn_carton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_weight = new dialog_weight();
                dialog_weight.show(getSupportFragmentManager(), null);
                order_Type_cart = "كرتون";
                com.tcycle.tcycle.dialoge_confirmation.order_type_carton = String.valueOf(order_Type_cart);
                t_input = 4;
                com.tcycle.tcycle.dialog_weight.type_input = t_input;
            }
        });
        btn_nuhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_weight = new dialog_weight();
                dialog_weight.show(getSupportFragmentManager(), null);
                order_Type_nuhas = "نحاس";
                com.tcycle.tcycle.dialoge_confirmation.order_type_copper = String.valueOf(order_Type_nuhas);
                t_input = 5;
                com.tcycle.tcycle.dialog_weight.type_input = t_input;
            }
        });


    }
    public void CheckBalance() {
        final Query first_query = firestore.collection("My_Recycling").orderBy("Date", Query.Direction.DESCENDING);

        first_query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot documentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (documentSnapshots != null) {
                    Double TotalBalance = 0.0;
                    for (DocumentChange documentChange : documentSnapshots.getDocumentChanges()) {
                        if (documentChange.getType() == DocumentChange.Type.ADDED) {
                            My_Recycling my_recycling = documentChange.getDocument().toObject(My_Recycling.class);
                            if (my_recycling.getUID().equals(mAuth.getUid())) {
                                TotalBalance += my_recycling.getTotal();
                            }


                        }

                    }

                    if (TotalBalance >= 5) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext());
                        builder1.setTitle("تنبيه رصيد");
                        builder1.setMessage("قيمة مستحقاتك الحالية أكبر من 5 دنانير هل تريد سحبها ؟");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "نعم",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        builder1.setPositiveButton(
                                "لا",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        //getActivity().finish();
                                        Intent intent_s = new Intent(getBaseContext(), Home_Activity.class);
                                        startActivity(intent_s);
                                        Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();

                                    }
                                });



                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }


                }
            }
        });
    }

}

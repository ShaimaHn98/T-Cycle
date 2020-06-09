package com.example.t_cycle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class dialoge_confirmation extends DialogFragment {
    Button btn_conf_weight;
    TextView txt_conf_weight;
    static String lat, lon;
    static String tot_waste;
    String usernam, phonenumbe;
    int total_waste;
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    static int weight_tpye;
    static String order_type_m, order_type_i, order_type_p, order_type_n, order_type_c, order_type_pap;
    static String inp_type_alm, inp_type_ir, inp_type_pl, inp_type_pa, inp_type_nuh, inp_type_cart;
    static double priceof_iron, price_of_alm, price_of_cart, price_of_nuh, price_of_plas, price_of_pap;
    static double total_d;
    TextView txt_loc;
String UID,User_Order;
Context context;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialoge_confirme, null);
        btn_conf_weight = view.findViewById(R.id.btn_confirme_location);
        txt_conf_weight = view.findViewById(R.id.txt_con_weight);
        txt_loc = view.findViewById(R.id.txt_con_loc);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        txt_conf_weight.setText(tot_waste + " Kg");
        firestore.collection("User_Info").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                usernam = task.getResult().getString("username");
                phonenumbe = task.getResult().getString("phone_num");

            }
        });


        final Intent intent = getActivity().getIntent();
        lat = intent.getStringExtra("latitude");
        lon = intent.getStringExtra("longitude");
        final Intent[] total = {getActivity().getIntent()};
        total[0].getStringExtra("total_waste");
        Log.d("order_Type_almanium", String.valueOf(order_type_m));
        total_waste = Integer.parseInt(tot_waste);
        btn_conf_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent1);

            }
        });


        builder.setView(view).setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (total_waste >= 5) {
                    final String uid = mAuth.getCurrentUser().getUid();
                    final String us_n = usernam;
                    final String ph_n = phonenumbe;
                    final String total_waste = tot_waste;
                    final String lat_loc = lat;
                    final String long_loc = lon;
                    final Date date = new Date();

                    //   set User_Order   //

                    HashMap<String, Object> user_order = new HashMap<>();
                    user_order.put("username", us_n);
                    user_order.put("phone_number", ph_n);
                    user_order.put("Date", date);
                    user_order.put("Total_waste", total_waste);
                    user_order.put("Latitude", lat_loc);
                    user_order.put("Longitude", long_loc);
                    user_order.put("weight of iron", inp_type_ir);
                    user_order.put("weight of plastic", inp_type_pl);
                    user_order.put("weight of almanium", inp_type_alm);
                    user_order.put("weight of paper", inp_type_pa);
                    user_order.put("weight of carton", inp_type_cart);
                    user_order.put("weight of copper", inp_type_nuh);
                    user_order.put("uid", uid);
                    firestore.collection("User_Order").add(user_order).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            HashMap<String, Object> my_recycling = new HashMap<>();
                            if (task.isSuccessful()) {
                                final Date date = new Date();

                                if (inp_type_ir != null) {
                                    priceof_iron = Integer.parseInt(inp_type_ir) * 0.05;
                                    my_recycling.put("price_of_iron", priceof_iron);
                                    total_d = total_d + priceof_iron;
                                }

                                if (inp_type_pl != null) {
                                    price_of_plas = Integer.parseInt(inp_type_pl) * 0.03;
                                    my_recycling.put("Price_of_plastic", price_of_plas);
                                    total_d = total_d + price_of_plas;
                                }
                                if (inp_type_alm != null) {
                                    price_of_alm = Integer.parseInt(inp_type_alm) * 0.20;
                                    my_recycling.put("price_of_alm", price_of_alm);
                                    total_d = total_d + price_of_alm;
                                }
                                if (inp_type_cart != null) {
                                    price_of_cart = Integer.parseInt(inp_type_cart) * 0.02;
                                    my_recycling.put("Price_of_cart", price_of_cart);
                                    total_d += price_of_cart;
                                }

                                if (inp_type_nuh != null) {
                                    price_of_nuh = Integer.parseInt(inp_type_nuh) * 0.80;
                                    my_recycling.put("Price_of_cu", price_of_nuh);
                                    total_d += price_of_nuh;
                                }
                                if (inp_type_pa != null) {
                                    price_of_pap = Integer.parseInt(inp_type_pa) * 0.02;
                                    my_recycling.put("Price_of_pap", price_of_pap);
                                    total_d += price_of_pap;
                                }
                                my_recycling.put("UID", mAuth.getCurrentUser().getUid());
                                my_recycling.put("Date", date);
                                my_recycling.put("Total", total_d);
                                firestore.collection("My_Recycling").add(my_recycling).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful()) {
                                            firestore.collection("Token").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    Reqnot(task.getResult().get("Token").toString(),"thank you for user tcyle");
                                                    HashMap<String,Object> Notificationmap = new HashMap<>();
                                                    Notificationmap.put("Msg" , "شكراً لك على مساهمتك للحفاظ على البيئة");
                                                    Notificationmap.put("date" , new Date());
                                                    Notificationmap.put("UserID",uid);

                                                    firestore.collection("Notification").document(UUID.randomUUID().toString())
                                                            .set(Notificationmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful())
                                                            {

                                                            }
                                                        }
                                                    });
                                                }
                                            });

                                        }
                                    }
                                });


                            }
                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "الوزن لا يكفي لطلبك", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog_weight.total_waste = 0;

        return builder.create();

    }
    public void Reqnot(String ID, String Msg) {
        Notify notify = new Notify(ID, Msg);
        notify.execute();

    }

    public class Notify extends AsyncTask<Void, Void, Void> {

        private String ID, Msg;

        Notify(String Reciver, String Msg) {
            ID = Reciver;
            this.Msg = Msg;
            Log.d("Not", ID+"\n"+String.valueOf(Msg));
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                URL url = new URL("https://fcm.googleapis.com/fcm/send");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization","key=AIzaSyARUYhU0qleq6Dlbj6ZQ9b0JXyB3bqsYIo");
                conn.setRequestProperty("Content-Type", "application/json");

                JSONObject json = new JSONObject();

                json.put("to", ID);

                JSONObject info = new JSONObject();
                info.put("body", Msg);   // Notification title
                info.put("title", "Tcycle"); // Notification body
                info.put("content_available", "true");
                info.put("priority", "high");

                json.put("notification", info);
                Log.d("Not", String.valueOf(info));
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(json.toString());
                wr.flush();
                conn.getInputStream();

                //Toast.makeText(getBaseContext(),"Done",Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Log.d("ErrorOne", "" + e);
            }
            return null;
        }
    }
}

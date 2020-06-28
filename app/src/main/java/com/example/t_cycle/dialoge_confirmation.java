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
import java.text.DecimalFormat;
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
    static String order_type_aluminium, order_type_iron, order_type_plastic, order_type_copper, order_type_carton, order_type_paper;
    static String inp_type_aluminium, inp_type_iron, inp_type_plastic, inp_type_paper, inp_type_copper, inp_type_carton;
    static String price_of_iron, price_of_aluminium, price_of_carton, price_of_copper, price_of_plastic, price_of_paper;
   static Double total_price;

    TextView txt_loc;
   String UID,User_Order;
   Context context;
    DecimalFormat  s= new DecimalFormat();


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
        Log.d("order_Type_almanium", String.valueOf(order_type_aluminium));
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
                    user_order.put("weight of iron", inp_type_iron);
                    user_order.put("weight of plastic", inp_type_plastic);
                    user_order.put("weight of aluminium", inp_type_aluminium);
                    user_order.put("weight of paper", inp_type_paper);
                    user_order.put("weight of carton", inp_type_carton);
                    user_order.put("weight of copper", inp_type_copper);
                    user_order.put("uid", uid);
                    firestore.collection("User_Order").add(user_order).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            HashMap<String, Object> my_recycling = new HashMap<>();
                            if (task.isSuccessful()) {
                                final Date date = new Date();
                                total_price=0.0;
                                if (inp_type_iron != null) {
                                    price_of_iron=s.format(Integer.parseInt(inp_type_iron.trim())*(0.05));
                                    my_recycling.put("price_of_iron",price_of_iron);
                                    //Double s=Double.parseDouble(total_price.trim());

                                    total_price = total_price +  Double.parseDouble(price_of_iron);
                                }

                                if (inp_type_plastic != null) {
                                   price_of_plastic= s.format(Integer.parseInt(inp_type_plastic) * (0.03));
                                    my_recycling.put("Price_of_plastic", price_of_plastic);

                                    total_price = total_price + Double.parseDouble(price_of_plastic);
                                }
                                if (inp_type_aluminium != null) {
                                price_of_aluminium = s.format(Integer.parseInt(inp_type_aluminium.trim())*(0.20));
                                my_recycling.put("price_of_alm", (price_of_aluminium));
                                    total_price = total_price + Double.parseDouble(price_of_aluminium);
                                }
                                if (inp_type_carton != null) {
                                   price_of_carton = s.format(Integer.parseInt(inp_type_carton) * (0.02));
                                    my_recycling.put("Price_of_cart", price_of_carton);
                                    total_price = total_price+Double.parseDouble(price_of_carton);
                                }

                                if (inp_type_copper != null) {
                                    price_of_copper=s.format( Integer.parseInt(inp_type_copper) * 0.80);
                                    my_recycling.put("Price_of_cu", price_of_copper);
                                    total_price = total_price+Double.parseDouble(price_of_copper);
                                }
                                if (inp_type_paper != null)
                                {
                                   price_of_paper = s.format(Integer.parseInt(inp_type_paper) * 0.02);
                                    my_recycling.put("Price_of_pap", price_of_paper);
                                    total_price = total_price+Double.parseDouble(price_of_paper);
                                }

                                my_recycling.put("UID", mAuth.getCurrentUser().getUid());
                                my_recycling.put("Date", date);
                                my_recycling.put("Total",total_price);

                                firestore.collection("My_Recycling").add(my_recycling).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful()) {

                                            firestore.collection("Token").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    String Token_ID=task.getResult().get("tokenid").toString();
                                                    Request_Notify(Token_ID,"thank you for using t-cycle");
                                                    HashMap<String,Object> notification_map = new HashMap<>();
                                                    notification_map.put("Msg" , "شكراً لك على مساهمتك للحفاظ على البيئة");
                                                    notification_map.put("date" , new Date());
                                                    notification_map.put("UserID",uid);
                                                    firestore.collection("Notification").document(UUID.randomUUID().toString())
                                                            .set(notification_map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful())
                                                            { //total_price=0;

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
    public void Request_Notify(String ID, String Msg) {
        Notify notify = new Notify(ID, Msg);
        notify.execute();
        Log.d("ID_NOT",String.valueOf(ID));

    }

    public class Notify extends AsyncTask<Void, Void, Void> {

        private String UID_notify, Msg;
        Notify(String Rec, String msg) {
            this.UID_notify = Rec;
            this.Msg = msg;
            Log.d("Not", UID_notify+"\n"+String.valueOf(Msg));
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
                conn.setRequestProperty("Authorization","key=AAAACcZAZDU:APA91bFKDWLgH_u1En_R5_sWvkOD4legjv81RAjqLhUcZ0O3tOluX2RnRRA3gl5lCeA4n0VoswLXXlszWu1JxxCNMA25Nb7TyiV-qj_2wUSeqwHtcpKPVRxxU269oLakLhU2afkPDF1g");
                conn.setRequestProperty("Content-Type", "application/json");
                JSONObject json = new JSONObject();
                json.put("to", UID_notify);
                Log.d("notify", String.valueOf(json));
                JSONObject info = new JSONObject();
                info.put("body", Msg);
                info.put("title", "T-cycle");
                info.put("content_available", "true");
                info.put("priority", "high");
                json.put("notification", info);
                Log.d("notify", String.valueOf(info));
                OutputStreamWriter writer_json = new OutputStreamWriter(conn.getOutputStream());
                writer_json.write(json.toString());
                writer_json.flush();
                conn.getInputStream();
            } catch (Exception e) {
                Log.d("Error", "" + e.getMessage());
            }
            return null;
        }
    }
}

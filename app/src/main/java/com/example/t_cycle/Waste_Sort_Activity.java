package com.example.t_cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Waste_Sort_Activity extends AppCompatActivity {
Button btn_iron,btn_blastic,btn_pap,btn_alm,btn_carton,btn_nuhas,btn_confirm;
    dialog_weight dialog_weight;
    dialoge_confirmation dialoge_confirmation;
  TextView txt_w;
    int weight_iron,weigh_alm,weight_plas,weight_pap,weight_cart,weight_nuhas;
   static String order_Type_alm,order_Type_nuhas,order_Type_iron,order_Type_cart,order_Type_paper,order_Type_plas;
   int t_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste__sort_);
        btn_iron=findViewById(R.id.btn_iron);
        btn_blastic=findViewById(R.id.btn_plastic);
        btn_pap=findViewById(R.id.btn_paper);
        btn_nuhas=findViewById(R.id.btn_nuhas);
        btn_carton=findViewById(R.id.btn_carton);
        btn_alm=findViewById(R.id.btn_almanuim);
        btn_confirm=findViewById(R.id.btn_confirme);
        txt_w=findViewById(R.id.txt_w);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoge_confirmation=new dialoge_confirmation();
                dialoge_confirmation.show(getSupportFragmentManager(),null);
            }
        });
        btn_iron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_weight=new dialog_weight();
                dialog_weight.show(getSupportFragmentManager(),null);
                order_Type_iron ="حديد";
                com.example.t_cycle.dialoge_confirmation.order_type_iron=String.valueOf(order_Type_iron);
                t_input=0;
                com.example.t_cycle.dialog_weight.type_input=t_input;
            }
        });
        btn_blastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_weight=new dialog_weight();
                dialog_weight.show(getSupportFragmentManager(),null);
                order_Type_plas="بلاستيك";
                com.example.t_cycle.dialoge_confirmation.order_type_plastic=String.valueOf(order_Type_plas);
                t_input=1;
                com.example.t_cycle.dialog_weight.type_input=t_input;

            }
        });
     btn_alm.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        dialog_weight=new dialog_weight();
        dialog_weight.show(getSupportFragmentManager(),null);
        weigh_alm= com.example.t_cycle.dialog_weight.weight;

        order_Type_alm="أمنيوم";
        com.example.t_cycle.dialoge_confirmation.order_type_aluminium=String.valueOf(order_Type_alm);
        com.example.t_cycle.dialoge_confirmation.weight_tpye=weigh_alm;
        t_input=2;
        com.example.t_cycle.dialog_weight.type_input=t_input;
    }
});

  btn_pap.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog_weight=new dialog_weight();
        dialog_weight.show(getSupportFragmentManager(),null);
        order_Type_paper="ورق";
        com.example.t_cycle.dialoge_confirmation.order_type_paper=String.valueOf(order_Type_paper);
        t_input=3;
        com.example.t_cycle.dialog_weight.type_input=t_input;
    }
});
        btn_carton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_weight=new dialog_weight();
                dialog_weight.show(getSupportFragmentManager(),null);
                order_Type_cart="كرتون";
                com.example.t_cycle.dialoge_confirmation.order_type_carton=String.valueOf(order_Type_cart);
                t_input=4;
                com.example.t_cycle.dialog_weight.type_input=t_input;
            }
        });
  btn_nuhas.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog_weight=new dialog_weight();
        dialog_weight.show(getSupportFragmentManager(),null);
        order_Type_nuhas="نحاس";
        com.example.t_cycle.dialoge_confirmation.order_type_copper=String.valueOf(order_Type_nuhas);
        t_input=5;
        com.example.t_cycle.dialog_weight.type_input=t_input;
    }
});


    }
}

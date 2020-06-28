package com.example.t_cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class All_Waste_Activity extends AppCompatActivity {
Button btn_w_all;
dialoge_confirmation dialoge_confirmation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__waste_);
        btn_w_all=findViewById(R.id.btn_weight_all);
        btn_w_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialoge_confirmation=new dialoge_confirmation();
                dialoge_confirmation.show(getSupportFragmentManager(),null);

            }
        });
    }
}

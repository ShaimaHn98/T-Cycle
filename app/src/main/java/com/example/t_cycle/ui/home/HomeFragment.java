package com.example.t_cycle.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.t_cycle.All_Waste_Activity;
import com.example.t_cycle.R;
import com.example.t_cycle.Waste_Sort_Activity;
import com.example.t_cycle.dialoge_confirmation;

public class HomeFragment extends Fragment {
dialoge_confirmation dialoge_confirmation;
    
     Button btn_part,btn_all;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_part=view.findViewById(R.id.btn_part);
        btn_all=view.findViewById(R.id.btn_all);
        btn_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), Waste_Sort_Activity.class);
                startActivity(intent);
            }
        });
            btn_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2=new Intent(view.getContext(), All_Waste_Activity.class);
                    startActivity(intent2);
                }
            });
        return view;
    }
}

package com.example.t_cycle.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.t_cycle.Home_Activity;
import com.example.t_cycle.Profile_Activity;
import com.example.t_cycle.R;
import com.example.t_cycle.Waste_Sort_Activity;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
     Button btn_part,btn_all;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
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



        return view;
    }
}

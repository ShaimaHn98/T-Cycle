package com.tcycle.tcycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class recycle_view_adapter extends RecyclerView.Adapter<recycle_view_adapter.ViewHolder> {

    Context context;
    LinearLayout l_w;

    @Override
    public void onBindViewHolder(@NonNull recycle_view_adapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public recycle_view_adapter() {
    }


    @NonNull
    @Override
    public recycle_view_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_adapter, parent, false);
        context =parent.getContext();
        l_w=view.findViewById(R.id.linear_adapter);


        return new recycle_view_adapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

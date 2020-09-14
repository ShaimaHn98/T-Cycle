package com.example.t_cycle;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

public class Notification_Adapter  extends RecyclerView.Adapter<Notification_Adapter.ViewHolder> {


    List<Notification>notificationList;
    FirebaseFirestore firestore;
    Context context;

    public Notification_Adapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public Notification_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View notify_view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_view,parent,false);
        context=parent.getContext();
        return new ViewHolder(notify_view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notification_Adapter.ViewHolder holder, int position) {
        String UID=notificationList.get(position).getUserID();
        String Msg=notificationList.get(position).getMsg();
        Date date=notificationList.get(position).getDate();
        String date_order=notificationList.get(position).getDate_order();
        long order_date_not = notificationList.get(position).getDate().getTime();
        String date_not = DateFormat.format("dd MMM yyyy", new java.sql.Date(order_date_not)).toString();
        holder.set_txt_dt(date_not);
        holder.setTxt_msg(Msg);
        holder.setTxt_order_date(date_order);

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView txt_dt,txt_msg,txt_order_date;




            public void setTxt_order_date(String txt_order_date) {
            this.txt_order_date = view.findViewById(R.id.txt_date_order);
            this.txt_order_date.setText(" موعدك مع T-Cycle هو يوم "+txt_order_date+" وفقاُ لإختيارك ");
        }

            public void set_txt_dt(String date)

            {
            this.txt_dt=view.findViewById(R.id.txt_dt);

        this.txt_dt.setText(date);


        }

        public void setTxt_msg(String msg) {
            this.txt_msg = view.findViewById(R.id.txt_msg);
            this.txt_msg.setText(msg);
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
        }
    }
}

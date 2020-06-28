package com.example.t_cycle;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;
import java.util.List;

public class View_Order_Adapter extends RecyclerView.Adapter<View_Order_Adapter.ViewHolder> {

public Context context;
List<My_Recycling>my_recyclings;
CardView card_order;

    public View_Order_Adapter(List<My_Recycling> my_recyclings) {
        this.my_recyclings = my_recyclings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_oder,parent,false);

        context=parent.getContext();
        card_order=root.findViewById(R.id.card_order);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       holder.setIsRecyclable(false);
        String user=my_recyclings.get(position).getUID();
        String pr_of_iron=my_recyclings.get(position).getPrice_of_iron();
        String pr_of_pla=my_recyclings.get(position).getPrice_of_plastic();
        String pr_of_alm=my_recyclings.get(position).getPrice_of_alm();
        String pr_of_cu=my_recyclings.get(position).getPrice_of_cu();
        String pr_of_cart=my_recyclings.get(position).getPrice_of_cart();
        String pr_of_pap=my_recyclings.get(position).getPrice_of_pap();
       Double total_rec=my_recyclings.get(position).getTotal();

        holder.pr_iron(String.valueOf(pr_of_iron));

        holder.pr_pla(String.valueOf(pr_of_pla));

        holder.pr_alm(String.valueOf(pr_of_alm));

        holder.setTotal(String.valueOf(total_rec));
        long order_date = my_recyclings.get(position).getDate().getTime();
        String ord_date = DateFormat.format("dd MMM yyyy", new Date(order_date)).toString();
        holder.setDate(ord_date);
        Log.d("Pr_iron",String.valueOf(pr_of_iron));

    }

    @Override
    public int getItemCount() {
        return my_recyclings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
View mview;

        TextView txt_tit1,txt_tit2,txt_tit3,txt_we_1,txt_we_2,txt_we_3,txt_tot;
Button btn_date;
        public void pr_iron( String ir){
            this.txt_tit1=mview.findViewById(R.id.txt_tit_1);
            this.txt_we_1=mview.findViewById(R.id.txt_weight_1);
            this.txt_tit1.setText("الحديد");
            this.txt_we_1.setText(ir);

        }
        public void pr_pla( String pla){
            this.txt_tit2=mview.findViewById(R.id.txt_tit_2);
            this.txt_we_2=mview.findViewById(R.id.txt_weigh_2);
            this.txt_tit2.setText("بلاستيك");
            this.txt_we_2.setText(pla);

        }
        public void pr_alm( String almanium){
            this.txt_tit3=mview.findViewById(R.id.txt_tit_3);
            this.txt_we_3=mview.findViewById(R.id.txt_weight_3);
            this.txt_tit3.setText("ألمنيوم");
            this.txt_we_3.setText(almanium);

        }
        public void setDate(String dt)
        { this.btn_date =mview.findViewById(R.id.btn_date_order);
            this.btn_date.setText(dt);

        }
        public void setTotal(String Tot)
        {
            this.txt_tot=mview.findViewById(R.id.txt_tot_rec);
            this.txt_tot.setText(Tot);
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
        }
    }
}

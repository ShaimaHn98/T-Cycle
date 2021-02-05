package com.tcycle.tcycle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dialog_weight extends DialogFragment {
    private ArrayList<String> arrayList;
    private RecyclerView RC_Main_w;
    private recycle_view_adapter adapter;

private ImageButton add,sub;
private TextView txt_w;
String verigy_w;
public static int total_waste;
static int weight;
    int cont,total;
static int type_input;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder bulider = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialoge_weight,null);

        bulider.setView(view).setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (type_input==0)
                { dialoge_confirmation.inp_type_iron=txt_w.getText().toString();

                    //weight=cont;

                }else if (type_input==1)
                {dialoge_confirmation.inp_type_plastic=txt_w.getText().toString();
                    // weight=cont;

                }else if (type_input==2){
                    dialoge_confirmation.inp_type_aluminium=txt_w.getText().toString();
                    //  weight=cont;
                }
                else if (type_input==3){
                    dialoge_confirmation.inp_type_paper=txt_w.getText().toString();
                    //  weight=cont;
                }
                else if (type_input==4){
                    dialoge_confirmation.inp_type_carton=txt_w.getText().toString();
                    //  weight=cont;
                }
                else if (type_input==5){
                    dialoge_confirmation.inp_type_copper=txt_w.getText().toString();
                    //  weight=cont;
                }
            verigy_w=txt_w.getText().toString();
                total_waste+=Integer.parseInt(verigy_w);
                 ShowDialog("الوزن المقدر هو "+total_waste+"Kg ");
                dialoge_confirmation.tot_waste=String.valueOf(total_waste);


            }
        });
        add=view.findViewById(R.id.add_w);
        sub=view.findViewById(R.id.sub_w);
        txt_w=view.findViewById(R.id.txt_w);


        txt_w.setText("0");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                   // int con,total;
                    String w=txt_w.getText().toString();
                    total=Integer.parseInt(w);
                    cont=total+1;

                Log.d("Value of Count ",String.valueOf(cont));
                txt_w.setText(String.valueOf(cont));

                if (type_input==0)
                { dialoge_confirmation.inp_type_iron=String.valueOf(cont);

                    //weight=cont;

                }else if (type_input==1)
                {dialoge_confirmation.inp_type_plastic=String.valueOf(cont);
                   // weight=cont;

                }else if (type_input==2){
                    dialoge_confirmation.inp_type_aluminium=String.valueOf(cont);
                    //  weight=cont;
                }
              else if (type_input==3){
                    dialoge_confirmation.inp_type_paper=String.valueOf(cont);
                    //  weight=cont;
                }
                else if (type_input==4){
                    dialoge_confirmation.inp_type_carton=String.valueOf(cont);
                    //  weight=cont;
                }
                else if (type_input==5){
                    dialoge_confirmation.inp_type_copper=String.valueOf(cont);
                    //  weight=cont;
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                  //  int con=1,total;
                    String w=txt_w.getText().toString();
                    total=Integer.parseInt(w);
                    if (total >=1 ){
                    cont=total-1;
                        txt_w.setText(String.valueOf(cont));

}
                    if (type_input==0)
                    { dialoge_confirmation.inp_type_iron=String.valueOf(txt_w.getText().toString())+"Kg";
                        //weight=cont;

                    }else if (type_input==1)
                    {dialoge_confirmation.inp_type_plastic=cont+"Kg";
                        //weight=cont;

                    }else if (type_input==2){
                        dialoge_confirmation.inp_type_aluminium=cont+"Kg";
                        //  weight=cont;
                    }else if (type_input==3){
                        dialoge_confirmation.inp_type_paper=cont+"Kg";
                        //  weight=cont;
                    }
                    else if (type_input==4){
                        dialoge_confirmation.inp_type_carton=cont+"Kg";
                        //  weight=cont;
                    }
                    else if (type_input==5){
                        dialoge_confirmation.inp_type_copper=cont+"Kg";
                        //  weight=cont;
                    }
                    else if(type_input==6)
                    {
                        dialoge_confirmation.tot_waste=String.valueOf(total);
                    }


                }
            }
        });


        return bulider.create();




    }
    private void ShowDialog(String MSG)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setTitle("الوزن المقدر");
        builder1.setMessage(MSG);
        builder1.setIcon(R.drawable.logo_tcycle);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "حسناً",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
}

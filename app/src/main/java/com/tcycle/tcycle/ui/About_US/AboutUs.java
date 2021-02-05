package com.tcycle.tcycle.ui.About_US;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tcycle.tcycle.R;

public class AboutUs extends Fragment {
    ImageButton fac_log;
    public static String FACEBOOK_URL = "https://www.facebook.com/exchangeForWaste";
    public static String FACEBOOK_PAGE_ID = "1603727641390833";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aboutus, container, false);
        fac_log = view.findViewById(R.id.facebook_logo);
        fac_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1603727641390833"));
                    startActivity(intent);
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/exchangeForWaste" + ""));
                }
*/
            //  getFacebookPageURL("1603727641390833");
            }
        });
        return view;
    }


}
package com.example.t_cycle;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
 if (mMap==null)
 { return;}
 try{
     mMap.setMyLocationEnabled(true);
     mMap.getUiSettings().setMyLocationButtonEnabled(true);
     final MarkerOptions markerOptions = new MarkerOptions();
     mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
         @Override
         public void onMapClick(final LatLng latLng) {
             AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
             builder.setTitle("Location Alert ");
             builder.setMessage("Are you sure Select Location ?");
             builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     String lati = String.valueOf(latLng.latitude);
                     String longa = String.valueOf(latLng.longitude);
                     //String cityname = null;
                     try {
                         Geocoder geocoder = new Geocoder(MapsActivity.this);
                         List<Address> addresses = null;
                         addresses = geocoder.getFromLocation(Double.parseDouble(lati), Double.parseDouble(longa), 1);
                         //cityname = addresses.get(0).getAddressLine();

                     } catch (IOException e) {
                         e.printStackTrace();

                     }
                     mMap.addMarker(markerOptions.position(latLng).title("Ad Location"));
                     finish();
                     Profile_Activity.lat = lati;
                     Profile_Activity.lon = longa;
                     dialoge_confirmation.lat=lati;
                     dialoge_confirmation.lon=longa;
                 }
             });
             builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             });
             builder.create().show();
         }
     });

 } catch (SecurityException e) {
            e.printStackTrace();

    }
    }
}

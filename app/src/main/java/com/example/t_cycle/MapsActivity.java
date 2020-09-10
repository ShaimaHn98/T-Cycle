package com.example.t_cycle;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
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
            final Circle circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(30.833244, 35.615557))
                    .radius(3381.71)
                    .strokeColor(Color.RED)
                    .fillColor(Color.TRANSPARENT));

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(final LatLng latLng) {
                    String lati = String.valueOf(latLng.latitude);
                    String longa = String.valueOf(latLng.longitude);



                    float[] distance = new float[2];
                    Location.distanceBetween(latLng.latitude, latLng.longitude, circle.getCenter().latitude,circle.getCenter().longitude,distance);


                    if (!( distance[0] <= circle.getRadius()))
                    {
                        Toast.makeText(getApplicationContext(), "المنطقة غير مدعومة ", Toast.LENGTH_LONG).show();
                        return;
                    }
                    mMap.addMarker(markerOptions.position(latLng).title("Ad Location"));

                    finish();
                    Profile_Activity.lat = lati;
                    Profile_Activity.lon = longa;
                    dialoge_confirmation.lat=lati;
                    dialoge_confirmation.lon=longa;

                }
            });

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
package com.project.incidenciascr;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;


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

        //double lat = (double)R.id.latTextView;
        //double lng = (double)R.id.lonTextView;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            double latitude = extras.getDouble("Latitude");
            double longitude = extras.getDouble("Longitude");

            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            LatLng incidente = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(incidente).title("Ubicación del incidente"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(incidente, 19));
        }
    }
}

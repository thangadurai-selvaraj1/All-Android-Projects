package com.example.liveproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    boolean mLocationPermissionGranted = false;
    GoogleMap mMap;
    Location location1;
    FusedLocationProviderClient mfusedLocationProviderClient;
    List<Address> addresslist = new ArrayList<>();
    List<Address> getAddress = new ArrayList<>();
    private static final int REQUEST_CODE = 123;
    private static final int MAP_DURATION = 13;
    AutoCompleteTextView inputLoc;
    Place place;
    Button direction;
    Marker marker1, marker2;
    ImageView image_drag;
    TextView idle;
    PlacesAutocompleteTextView placesAutocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        inputLoc = findViewById(R.id.edit);

        direction = findViewById(R.id.direction);

        image_drag = findViewById(R.id.image_drag);

        idle = findViewById(R.id.idle);


        placesAutocomplete = findViewById(R.id.places_autocomplete);
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (marker1 != null && marker2 != null) {
                    polyline(marker1, marker2);
                }

            }
        });

        placesAutocomplete.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
            @Override
            public void onPlaceSelected(@NonNull com.seatgeek.placesautocomplete.model.Place place) {

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapActivity.this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            getPermissions();

        }
    }

    private void polyline(Marker marker1, Marker marker2) {

        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(marker1.getPosition().latitude, marker1.getPosition().longitude),
                        new LatLng(marker2.getPosition().latitude, marker1.getPosition().longitude)
                ).color(Color.RED)
                .geodesic(true)
                .jointType(1)
                .visible(true)
                .width(8f));
    }


    private void getPermissions() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||

                ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET},
                    REQUEST_CODE);
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&

                ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationPermissionGranted = true;

        } else {
            startActivity(new Intent(MapActivity.this, MainActivity.class));

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();

                    mLocationPermissionGranted = true;

                } else {
                    Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    private void getCurrentLocation() {

        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            Task location = mfusedLocationProviderClient.getLastLocation();
            if (location != null) {
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task task) {

                        if (task.isSuccessful()) {

                            location1 = (Location) task.getResult();

                            if (location1 != null) {
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(location1.getLatitude(),
                                                location1.getLongitude()), MAP_DURATION));
                                updateLocationUI();
                            }
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {

            if (mLocationPermissionGranted) {

                mMap.setMyLocationEnabled(true);

                LatLng currentLoc = new LatLng(location1.getLatitude(), location1.getLongitude());

              /*  marker1 = mMap.addMarker(new MarkerOptions().position(kuya)
                        .title("Current Location"));*/

                mMap.addCircle(new CircleOptions()
                        .center(currentLoc)
                        .radius(500.0)
                        .strokeWidth(3f)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70, 150, 50, 50)));


            } else {
                mMap.setMyLocationEnabled(false);

                location1 = null;

                getPermissions();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        getCurrentLocation();

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                Geocoder geocoder = new Geocoder(MapActivity.this);

                LatLng latlng = mMap.getCameraPosition().target;

                try {
                    getAddress = geocoder.getFromLocation(latlng.latitude, latlng.longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                image_drag.setAnimation(AnimationUtils.loadAnimation(MapActivity.this, R.anim.animation));

                for (int i = 0; i < getAddress.size(); i++) {
                    {
                        Address address = getAddress.get(i);
                        idle.setText(address.getAddressLine(i));
                    }

                }

            }
        });

       /* LatLng kuya = new LatLng();
        googleMap.addMarker(new MarkerOptions().position(kuya)
                .title("Kuya Technologies"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(kuya));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);*/
    }

    public void Search(View view) {

        String name = inputLoc.getText().toString();

        if (!name.isEmpty()) {
            Geocoder geocoder = new Geocoder(MapActivity.this);
            try {
                addresslist = geocoder.getFromLocationName(name, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addresslist.size() > 0) {
                Address address = addresslist.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                marker2 = mMap.addMarker(new MarkerOptions().position(latLng).title(address.getCountryName()));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                inputLoc.setText("");
            }
        }
    }


    public void getBackToCurrentLocation(View view) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location1.getLatitude(),
                        location1.getLongitude()), MAP_DURATION));
    }


}

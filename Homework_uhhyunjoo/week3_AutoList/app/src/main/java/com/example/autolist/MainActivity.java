package com.example.autolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    PlacesClient placesClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        String apikey = "AIzaSyCT_Gld7Ftx9zsMQPcj8qiuXfCjGG47imQ";

        if (!Places.isInitialized()){
            Places.initialize(getApplicationContext(), apikey);

        }

        placesClient = Places.createClient(this);

        final AutocompleteSupportFragment autocompleteSupportFragment =
                (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID,Place.Field.LAT_LNG,Place.Field.NAME));
        //setPlaceFields is used for the purpose when they auto complete places!!!!
        //and it returns the list of places which you search

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                final LatLng latLng = place.getLatLng();

                Log.i("PlacesApi", "onPlaceSelected: " +latLng.latitude+"\n"+latLng.longitude);
            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });
    }
}

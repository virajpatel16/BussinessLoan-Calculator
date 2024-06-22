package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class Near_By_Me_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_near__by__me_, container, false);

        // Set click listeners for card views
        LinearLayout bank = view.findViewById(R.id.bankcard);
        ImageButton backnear=view.findViewById(R.id.backnear);
        LinearLayout atm = view.findViewById(R.id.atmcard);
        LinearLayout Hospital = view.findViewById(R.id.Hospital);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout Restaurants = view.findViewById(R.id.Restaurants);
        LinearLayout policestation = view.findViewById(R.id.policestation);
        LinearLayout firestation = view.findViewById(R.id.firestation);
        LinearLayout petrolpump = view.findViewById(R.id.petrolpump);
        LinearLayout hotels = view.findViewById(R.id.hotels);
        LinearLayout gym = view.findViewById(R.id.gym);
        LinearLayout movietheatres = view.findViewById(R.id.movietheatres);
        LinearLayout medicalcenter = view.findViewById(R.id.medicalcenter);
        LinearLayout malls = view.findViewById(R.id.malls);
        LinearLayout railwaystation = view.findViewById(R.id.railwaystation);
        LinearLayout busstops = view.findViewById(R.id.busstops);
        LinearLayout taxiservices = view.findViewById(R.id.taxiservices);
        LinearLayout tample = view.findViewById(R.id.tample);
        LinearLayout airport = view.findViewById(R.id.airport);

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("Bank");
            }
        });

        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("ATM");
            }
        });
        Hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("Hospital");
            }
        });
        Restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("Restaurants");
            }
        });
        policestation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("policestation");
            }
        });
        firestation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("firestation");
            }
        });
        petrolpump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("petrolpump");
            }
        });
        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("hotels");
            }
        });
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("gym");
            }
        });
        movietheatres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("moivetheatres");
            }
        });
        medicalcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("medicalcenter");
            }
        });
        malls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("malls");
            }
        });
        railwaystation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("railwaystation");
            }
        });
        busstops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("busstops");
            }
        });
        taxiservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("taxiservices");
            }
        });
        tample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("tample");
            }
        });
        airport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap("airport");
            }
        });

        backnear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), MainScreen.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        return view;
    }
    public void openMap(String locationName) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + locationName);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}

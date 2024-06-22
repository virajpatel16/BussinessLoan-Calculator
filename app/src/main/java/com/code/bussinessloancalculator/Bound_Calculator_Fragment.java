package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;


public class Bound_Calculator_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Button shareButton;
    ImageButton backbond;
    Button YTCbtn, YTmbtn, duration;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bound__calculator_, container, false);
        shareButton = view.findViewById(R.id.Share);
        backbond = view.findViewById(R.id.backbond);
        YTCbtn = view.findViewById(R.id.YTCbtn);
        YTmbtn = view.findViewById(R.id.YTMbtn);
        duration = view.findViewById(R.id.Durationbtn);


        backbond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.bond_frame, new Banking__Calculator_Fragment())

                        .commit();
            }
        });
        YTCbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), YTC.class);
                startActivity(intent);
            }
        });
        duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), Duration.class);
                startActivity(intent);
            }
        });
        YTmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), YTM.class);
                startActivity(intent);
            }
        });






        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent with action ACTION_SEND
                Intent shareIntent = new Intent(Intent.ACTION_SEND);

                // Set the type of data to be shared
                shareIntent.setType("text/plain");

                // Add the content to be shared, in this case, your app link
                String appLink = "https://play.google.com/store/apps/details?id=com.yourpackagename";
                String shareMessage = "Check out this awesome app:\n" + appLink;
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

                // Create a chooser to let the user choose how to share the content
                Intent chooserIntent = Intent.createChooser(shareIntent, "Share via");

                // Start the chooser activity
                startActivity(chooserIntent);
            }
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
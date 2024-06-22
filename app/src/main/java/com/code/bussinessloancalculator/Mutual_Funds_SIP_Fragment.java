package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Mutual_Funds_SIP_Fragment extends Fragment {

    CardView sip;
    CardView swp;
    CardView stp;
    CardView stpp;
    CardView Addinvestment;

    ImageButton backmutualfund;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mutual__funds__s_i_p_, container, false);


        sip = view.findViewById(R.id.Sip);
        swp=view.findViewById(R.id.swp);
        stp=view.findViewById(R.id.stp);
        stpp=view.findViewById(R.id.stpp);
        Addinvestment=view.findViewById(R.id.Addinvestment);

        backmutualfund=view.findViewById(R.id.backmutualfund);
        sip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), Sip.class);
                startActivity(intent);

            }
        });


        Addinvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Addinvestment.class);
                startActivity(intent);
            }
        });

        backmutualfund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), MainScreen.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        swp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), Swp.class);
                startActivity(intent);

            }
        });
        stp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), Stp.class);
                startActivity(intent);

            }
        });

        stpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireActivity(), Stpp.class);
                startActivity(intent);

            }
        });




    return view;
    }
}
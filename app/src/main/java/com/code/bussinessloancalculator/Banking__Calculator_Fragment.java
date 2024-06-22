package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;


public class Banking__Calculator_Fragment extends Fragment {

    ImageButton backBanking;
    LinearLayout FixedDespositecard;

    LinearLayout publicprovidentfund;
    LinearLayout RecurringDeposite;

    LinearLayout Interest;
    LinearLayout Bondcalculator;

    LinearLayout Tipcalculator;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_banking___calculator_, container, false);

        FixedDespositecard=view.findViewById(R.id.FixedDespositecard);
        Bondcalculator=view.findViewById(R.id.Bondcalculator);
        backBanking=view.findViewById(R.id.backBanking);
        publicprovidentfund=view.findViewById(R.id.publicprovidentfund);
        Interest=view.findViewById(R.id.Interest);
        Tipcalculator=view.findViewById(R.id.Tipcalculator);
        RecurringDeposite=view.findViewById(R.id.RecurringDeposite);




        publicprovidentfund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Public.class);
                startActivity(intent);
            }
        });
        RecurringDeposite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Recurring.class);
                startActivity(intent);
            }
        });

        Tipcalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Tip.class);
                startActivity(intent);
            }
        });
        Bondcalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Bond.class);
                startActivity(intent);
            }
        });

        Interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Interest.class);
                startActivity(intent);
            }
        });
        FixedDespositecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Fixed.class);
                startActivity(intent);
            }
        });

        backBanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().startActivity(new Intent(requireContext(), MainScreen.class));
                requireActivity().finish();

            }
        });
    return  view;
    }
}
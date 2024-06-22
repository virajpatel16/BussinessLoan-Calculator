package com.code.bussinessloancalculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class Loan_Calculator_Fragment extends Fragment {

    LinearLayout emiloan,Loaneligibility,Taxloan,Compareloan,returnofinvestmenst,tvmcalculator;
    ImageButton backloan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_loan__calculator_, container, false);

        backloan=view.findViewById(R.id.backloan);
        emiloan=view.findViewById(R.id.emiloan);
        Loaneligibility=view.findViewById(R.id.Loaneligibility);
        Taxloan=view.findViewById(R.id.Taxloan);
        Compareloan=view.findViewById(R.id.Compareloan);
        returnofinvestmenst=view.findViewById(R.id.returnofinvestmenst);
        tvmcalculator=view.findViewById(R.id.tvmcalculator);


        backloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), MainScreen.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
        emiloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Emi.class);
                startActivity(intent);

            }
        });
        tvmcalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(),TVm.class);
                startActivity(intent);

            }
        });
        returnofinvestmenst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Return.class);
                startActivity(intent);

            }
        });
        Compareloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Compare.class);
                startActivity(intent);

            }
        });
        Taxloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Tax.class);
                startActivity(intent);

            }
        });
        Loaneligibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Loan.class);
                startActivity(intent);

            }
        });

    return view;
    }
}
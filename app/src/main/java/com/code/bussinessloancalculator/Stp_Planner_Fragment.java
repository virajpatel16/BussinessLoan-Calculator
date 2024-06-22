package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Stp_Planner_Fragment extends Fragment {

    LinearLayout resultinvestmentamountstpp,resultsip;
    ImageButton reset;
    ImageButton stppback;

    private EditText sipAmountEditText, rateOfReturnstppEditText, tenureInEdittext;
    private TextView resultInvestmentAmountTextView, resultSipTextView;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stp__planner_, container, false);

        resultinvestmentamountstpp=view.findViewById(R.id.resultinvestmentamountstpp);
        resultsip=view.findViewById(R.id.resultsip);
        reset = view.findViewById(R.id.resetbuttonstpplanner);

        sipAmountEditText = view.findViewById(R.id.stppAmountEditText);
        tenureInEdittext = view.findViewById(R.id.tenureInstppEdittext);
        rateOfReturnstppEditText = view.findViewById(R.id.rateOfReturnstppEditText);
        stppback = view.findViewById(R.id.backstpplanner);
        resultInvestmentAmountTextView = view.findViewById(R.id.answerinvestmentamountstpp);
        resultSipTextView = view.findViewById(R.id.answergrosspricevat);
        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonstpplanner);


        resultinvestmentamountstpp.setVisibility(View.INVISIBLE);
        resultsip.setVisibility(View.INVISIBLE);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSIP();
                resultinvestmentamountstpp.setVisibility(View.VISIBLE);
                resultsip.setVisibility(View.VISIBLE);

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the text from the EditText
                sipAmountEditText.setText("");
                rateOfReturnstppEditText.setText("");
                tenureInEdittext.setText("");
                resultinvestmentamountstpp.setVisibility(View.INVISIBLE);
                resultsip.setVisibility(View.INVISIBLE);
            }
        });



        stppback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                // Optional: Add transaction to back stack
                        .replace(R.id.sipp_frame, new Mutual_Funds_SIP_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });
        return view;
    }
    private void calculateSIP() {
        String sipAmountStr = sipAmountEditText.getText().toString().trim();
        String rateOfReturnStr = rateOfReturnstppEditText.getText().toString().trim();
        String tenureInStr = tenureInEdittext.getText().toString().trim();

        if (TextUtils.isEmpty(sipAmountStr)) {
            sipAmountEditText.setError("Enter SIP Amount");
            sipAmountEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(rateOfReturnStr)) {
            rateOfReturnstppEditText.setError("Enter Rate of Return");
            rateOfReturnstppEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(tenureInStr)) {
            tenureInEdittext.setError("Enter Tenure In");
            tenureInEdittext.requestFocus();
            return;
        }

        double sipAmount = Double.parseDouble(sipAmountStr);
        double rateOfReturn = Double.parseDouble(rateOfReturnStr) / 100.0; // Convert rate of return to decimal
        int tenureIn = Integer.parseInt(tenureInStr);
        int totalMonths = tenureIn * 12; // Total number of months

        // Monthly interest rate
        double monthlyRate = rateOfReturn / 12.0;

        // Calculate future value (FV) of SIP
        double maturityValue = sipAmount * ((Math.pow(1 + monthlyRate, totalMonths) - 1) / monthlyRate) * monthlyRate;

        // Display the results
        resultInvestmentAmountTextView.setText(String.format("%.2f", sipAmount));
        resultSipTextView.setText(String.format("%.2f", maturityValue));
    }


}
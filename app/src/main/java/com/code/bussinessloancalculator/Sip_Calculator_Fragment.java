package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Sip_Calculator_Fragment extends Fragment  {

    ImageButton Sipback;
    LinearLayout resultinvestmentamount,resultmaturityvalue;

    private EditText sipAmountEditText, rateOfReturnEditText, tenureInEdittext, initialInvestmentEdittext;
    private TextView resultInvestmentAmountTextView, resultMaturityValueTextView;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sip__calculator_, container, false);
        resultinvestmentamount=view.findViewById(R.id.resultinvestmentamount);
        resultmaturityvalue=view.findViewById(R.id.resultmaturityvalue);
        sipAmountEditText = view.findViewById(R.id.sipAmountEditText);
        rateOfReturnEditText = view.findViewById(R.id.rateOfReturnsipEditText);
        tenureInEdittext = view.findViewById(R.id.tenureInsipEdittext);
        initialInvestmentEdittext = view.findViewById(R.id.initialinvestmentsipEdittext);
        Sipback=view.findViewById(R.id.backsip);
        resultInvestmentAmountTextView = view.findViewById(R.id.answerinvestmentamount);
        resultMaturityValueTextView = view.findViewById(R.id.answergrosspricevat);
        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonsip);

        resultmaturityvalue.setVisibility(View.INVISIBLE);
        resultinvestmentamount.setVisibility(View.INVISIBLE);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSIP();
                resultmaturityvalue.setVisibility(View.VISIBLE);
                resultinvestmentamount.setVisibility(View.VISIBLE);
            }
        });

        Sipback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager =getActivity() .getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Sip_frame, new Mutual_Funds_SIP_Fragment());
                // Optional: Add transaction to back stack
                fragmentTransaction.commit();


            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbutonsip);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resultmaturityvalue.setVisibility(View.INVISIBLE);
                resultinvestmentamount.setVisibility(View.INVISIBLE);
            }
        });

        resultmaturityvalue.setVisibility(View.INVISIBLE);
        resultinvestmentamount.setVisibility(View.INVISIBLE);





        return view;
    }


    @SuppressLint("DefaultLocale")
    private void calculateSIP() {
        String sipAmountStr = sipAmountEditText.getText().toString().trim();
        String rateOfReturnStr = rateOfReturnEditText.getText().toString().trim();
        String tenureInStr = tenureInEdittext.getText().toString().trim();
        String initialInvestmentStr = initialInvestmentEdittext.getText().toString().trim();

        if (TextUtils.isEmpty(sipAmountStr)) {
            sipAmountEditText.setError("Enter SIP Amount");
            sipAmountEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(rateOfReturnStr)) {
            rateOfReturnEditText.setError("Enter Rate of Return");
            rateOfReturnEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(tenureInStr)) {
            tenureInEdittext.setError("Enter Tenure In");
            tenureInEdittext.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(initialInvestmentStr)) {
            initialInvestmentEdittext.setError("Enter Initial Investment");
            initialInvestmentEdittext.requestFocus();
            return;
        }

        double sipAmount = Double.parseDouble(sipAmountStr);
        double rateOfReturn = Double.parseDouble(rateOfReturnStr) / 100.0 / 12.0; // Monthly rate
        int tenureInYears = Integer.parseInt(tenureInStr);
        double initialInvestment = Double.parseDouble(initialInvestmentStr);

        // Calculate investment amount and maturity value
        double investmentAmount = sipAmount * tenureInYears * 12;
        double maturityValue = 0;

        // Calculate maturity value for SIP using the corrected formula
        maturityValue = sipAmount * ((Math.pow(1 + rateOfReturn, tenureInYears * 12) - 1) / rateOfReturn) * (1 + rateOfReturn) + initialInvestment;

        // Display the results
        resultInvestmentAmountTextView.setText(String.format("%.2f", investmentAmount));
        resultMaturityValueTextView.setText(String.format("%.2f", maturityValue));
    }


    private void resetFields() {
        sipAmountEditText.setText("");
        rateOfReturnEditText.setText("");
        tenureInEdittext.setText("");
        initialInvestmentEdittext.setText("");
        resultInvestmentAmountTextView.setText("");
        resultMaturityValueTextView.setText("");
    }


}
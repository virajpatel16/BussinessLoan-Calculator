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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class Duration_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ImageButton backduration;
    CardView resultduration;


    private EditText faceValueEditText, couponRateEditText, annualInterestRateEditText, yearsToMaturityEditText;
    private Spinner spinner;
    private ImageButton calculateButton, resetButton;
    private TextView answerDurationTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_duration_, container, false);
        spinner = view.findViewById(R.id.Compoundingdurationspinner);
        backduration = view.findViewById(R.id.backduration);

        resultduration = view.findViewById(R.id.resultduration);

        resultduration.setVisibility(View.INVISIBLE);



        faceValueEditText = view.findViewById(R.id.facevaluedurationedittext);
        couponRateEditText = view.findViewById(R.id.couponerateEditText);
        annualInterestRateEditText = view.findViewById(R.id.AnnualinterestrateEdittext);
        yearsToMaturityEditText = view.findViewById(R.id.AnnualYieldEdittext);

        calculateButton = view.findViewById(R.id.calculatebuttonduration);
        resetButton = view.findViewById(R.id.resetbuttonduration);
        answerDurationTextView = view.findViewById(R.id.answerduration);

        // Set click listeners
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDuration();
                resultduration.setVisibility(View.VISIBLE);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resultduration.setVisibility(View.INVISIBLE);
            }
        });








        backduration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction
                        .replace(R.id.duration_frame, new Bound_Calculator_Fragment());
                // Optional: Add transaction to back stack
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });


        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Annually");
        categories.add("Semiannually");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, categories);

// Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


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


    @SuppressLint("DefaultLocale")
    private void calculateDuration() {
        // Retrieve input values
        double faceValue = Double.parseDouble(faceValueEditText.getText().toString());
        double couponRate = Double.parseDouble(couponRateEditText.getText().toString()) / 100;
        double annualInterestRate = Double.parseDouble(annualInterestRateEditText.getText().toString()) / 100;
        int yearsToMaturity = Integer.parseInt(yearsToMaturityEditText.getText().toString());
        // Compounding duration

        // Convert annual interest rate to periodic interest rate
        double periodicInterestRate = annualInterestRate;

        // Convert years to maturity to periods
        double periodsToMaturity = yearsToMaturity;

        // Adjust for different compounding frequencies
        String compoundingDuration = spinner.getSelectedItem().toString();



        // Adjust for different compounding frequencies
        if (compoundingDuration.equals("Semiannually")) {
            periodicInterestRate /= 2.0;

            periodsToMaturity *= 2.0;
        }

        // Calculate the present value of each cash flow
        double presentValue = 0.0;
        double weightedPresentValue = 0.0;
        for (int t = 1; t <= periodsToMaturity; t++) {
            double cashFlow = faceValue * couponRate / periodsToMaturity;
            presentValue += cashFlow / Math.pow(1 + periodicInterestRate, t);
            weightedPresentValue += (cashFlow * t) / Math.pow(1 + periodicInterestRate, t);
        }
        presentValue += faceValue / Math.pow(1 + periodicInterestRate, periodsToMaturity);
        weightedPresentValue += (faceValue * periodsToMaturity) / Math.pow(1 + periodicInterestRate, periodsToMaturity);

        // Calculate the duration
        double duration = weightedPresentValue / presentValue;

        // Display the calculated duration
        answerDurationTextView.setText(String.format("%.3f", duration));


    }


    private void resetFields() {
        // Clear all input fields and result text view
        faceValueEditText.setText("");
        couponRateEditText.setText("");
        annualInterestRateEditText.setText("");
        yearsToMaturityEditText.setText("");

    }
}
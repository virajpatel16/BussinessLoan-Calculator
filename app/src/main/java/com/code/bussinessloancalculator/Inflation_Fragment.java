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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Inflation_Fragment extends Fragment {


    ImageButton backInflation;
    private EditText inflationEditText, startYearEditText, finalYearEditText, initialValueEditText;
    private TextView resultFinalValueTextView;
    LinearLayout resultfinalvalueinflation;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_inflation_, container, false);

        backInflation=view.findViewById(R.id.backInflation);
        inflationEditText = view.findViewById(R.id.InflationEditText);
        startYearEditText = view.findViewById(R.id.StartyearEditText);
        finalYearEditText = view.findViewById(R.id.finalyeareditext);
        initialValueEditText = view.findViewById(R.id.InitialvalueInflationEdittext);
        resultFinalValueTextView = view.findViewById(R.id.answerfinalvalueinflation);
        resultfinalvalueinflation=view.findViewById(R.id.resultfinalvalueinflation);

        resultfinalvalueinflation.setVisibility(View.INVISIBLE);
        ImageButton calculateButton = view.findViewById(R.id.calculatebuttoninflation);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFinalValue();
                resultfinalvalueinflation.setVisibility(View.VISIBLE);
            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbuttoninflation);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resultfinalvalueinflation.setVisibility(View.INVISIBLE);
            }
        });
        backInflation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });
        return view;
    }
    private void calculateFinalValue() {
        String inflationStr = inflationEditText.getText().toString().trim();
        String startYearStr = startYearEditText.getText().toString().trim();
        String finalYearStr = finalYearEditText.getText().toString().trim();
        String initialValueStr = initialValueEditText.getText().toString().trim();

        if (TextUtils.isEmpty(inflationStr)) {
            inflationEditText.setError("Enter Inflation Rate");
            inflationEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(startYearStr)) {
            startYearEditText.setError("Enter Start Year");
            startYearEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(finalYearStr)) {
            finalYearEditText.setError("Enter Final Year");
            finalYearEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(initialValueStr)) {
            initialValueEditText.setError("Enter Initial Value");
            initialValueEditText.requestFocus();
            return;
        }

        double inflationRate = Double.parseDouble(inflationStr) / 100; // Convert percentage to decimal
        int startYear = Integer.parseInt(startYearStr);
        int finalYear = Integer.parseInt(finalYearStr);
        double initialValue = Double.parseDouble(initialValueStr);

        // Calculate future value
        double futureValue;
        int numberOfYears = finalYear - startYear;
        futureValue = initialValue * Math.pow((1 + inflationRate), numberOfYears);

        // Display the result
        resultFinalValueTextView.setText(String.format("%.2f", futureValue));
    }



    private void resetFields() {
        inflationEditText.setText("");
        startYearEditText.setText("");
        finalYearEditText.setText("");
        initialValueEditText.setText("");
        resultFinalValueTextView.setText("");
    }
}
package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class Add_Investment_Fragment extends Fragment {


LinearLayout resultinvestmentamountadd,resultmaturityvalueadd;
    ImageButton backAddinvestment;


    private EditText nameEditText, monthlyInvestmentEditText, rateOfReturnEditText, tenureInYearsEditText, editTextDate,answermaturityvalueadd;


    private EditText answerInvestmentAmount, answerMaturityValue;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add__investment_, container, false);
        nameEditText = view.findViewById(R.id.nameedittext);
        monthlyInvestmentEditText = view.findViewById(R.id.intialinvestmentaddEditext);
        rateOfReturnEditText = view.findViewById(R.id.rateeditext);
        tenureInYearsEditText = view.findViewById(R.id.tenureInEdittextadd);
        editTextDate = view.findViewById(R.id.editTextDate);
backAddinvestment=view.findViewById(R.id.backAddinvestment);
        resultinvestmentamountadd = view.findViewById(R.id.resultinvestmentamountadd);
        resultmaturityvalueadd = view.findViewById(R.id.resultmaturityvalueadd);
        answerInvestmentAmount = view.findViewById(R.id.answerinvestmentamountadd);
        answerMaturityValue = view.findViewById(R.id.answermaturityvalueadd);

        resultmaturityvalueadd.setVisibility(View.INVISIBLE);

        resultinvestmentamountadd.setVisibility(View.INVISIBLE);


        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonadd);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInvestment();
                resultmaturityvalueadd.setVisibility(View.VISIBLE);

                resultinvestmentamountadd.setVisibility(View.VISIBLE);
            }
        });
        backAddinvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Add_frame, new Mutual_Funds_SIP_Fragment());
                // Optional: Add transaction to back stack
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbuttonadd);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resultmaturityvalueadd.setVisibility(View.INVISIBLE);

                resultinvestmentamountadd.setVisibility(View.INVISIBLE);
            }
        });



        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

    return view;
    }
    public void showDatePickerDialog(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
           @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Do something with the selected date
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                editTextDate.setText(selectedDate);

            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
    @SuppressLint("DefaultLocale")
    private void calculateInvestment() {
        // Get input values
        String investmentStr = monthlyInvestmentEditText.getText().toString().trim();
        String rateOfReturnStr = rateOfReturnEditText.getText().toString().trim();
        String tenureInYearsStr = tenureInYearsEditText.getText().toString().trim();

        // Validate input fields
        if (investmentStr.isEmpty() || rateOfReturnStr.isEmpty() || tenureInYearsStr.isEmpty()) {
            // Show error message or handle invalid input
            return;
        }

        // Parse input values
        double investment = Double.parseDouble(investmentStr);
        double rateOfReturn = Double.parseDouble(rateOfReturnStr);
        double tenureInYears = Double.parseDouble(tenureInYearsStr);

        // Calculate investment amount
        double investmentAmount = calculateInvestmentAmount(investment, rateOfReturn, tenureInYears);
        // Calculate maturity value
        double maturityValue = calculateMaturityValue(investment, rateOfReturn, tenureInYears);

        // Display results
        answerInvestmentAmount.setText(String.format("%.2f", investmentAmount));
        answerMaturityValue.setText(String.format("%.2f", maturityValue));

        // Show result cards

    }

    private double calculateInvestmentAmount(double A, double r, double t) {
        // Formula: P = A / (1 + r/n)^(nt)
        return A / Math.pow((1 + r / 100), t);
    }

    private double calculateMaturityValue(double P, double r, double t) {
        // Formula: A = P(1 + r/n)^(nt)
        return P * Math.pow((1 + r / 100), t);
    }

    private void resetFields() {
        nameEditText.setText("");
        monthlyInvestmentEditText.setText("");
        rateOfReturnEditText.setText("");
        tenureInYearsEditText.setText("");
        answerInvestmentAmount.setText("");
        answerMaturityValue.setText("");

    }

}
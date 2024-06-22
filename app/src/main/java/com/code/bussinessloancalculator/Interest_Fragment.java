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


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Interest_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    LinearLayout resultmaturityinterest, resultinvestmentamountinterest;


    ImageButton backInterest;


    private EditText investmentAmountEditText, rateOfInterestEditText, tenureEditText, typeOfInterestEditText;
    private TextView answerInvestmentAmountTextView, answerTotalInterestValueTextView, answerMaturityValueTextView;
    private ImageButton calculateButton, resetButton;
    private Button Sharebtninterest;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interest_, container, false);

        resultmaturityinterest = view.findViewById(R.id.resultinvestmentamountinterest);
        resultinvestmentamountinterest = view.findViewById(R.id.resultmaturityinterest);
        backInterest = view.findViewById(R.id.backInterest);




        resultmaturityinterest.setVisibility(View.INVISIBLE);
        resultinvestmentamountinterest.setVisibility(View.INVISIBLE);
        typeOfInterestEditText = view.findViewById(R.id.TypeInterestEditext);

        typeOfInterestEditText.setText("GST is Added");
        investmentAmountEditText = view.findViewById(R.id.InvestmentAmountedInterestittext);
        rateOfInterestEditText = view.findViewById(R.id.ExpectrateofinterestInterestEditText);
        tenureEditText = view.findViewById(R.id.TenureInterestEdittext);


        answerInvestmentAmountTextView = view.findViewById(R.id.answerinvestmentamountinterest);
        answerTotalInterestValueTextView = view.findViewById(R.id.answertotalinterestvalueinterest);
        answerMaturityValueTextView = view.findViewById(R.id.answermatururityvalueinterest);

        calculateButton = view.findViewById(R.id.calculatebuttoninterest);
        resetButton = view.findViewById(R.id.resebuttoninterest);
        Sharebtninterest=view.findViewById(R.id.Sharebtninterest);





        Sharebtninterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupShareButton();
            }
        });
        // Set click listeners
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInterestAndMaturity();
                resultmaturityinterest.setVisibility(View.VISIBLE);
                resultinvestmentamountinterest.setVisibility(View.VISIBLE);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();


                resultmaturityinterest.setVisibility(View.INVISIBLE);
                resultinvestmentamountinterest.setVisibility(View.INVISIBLE);
            }
        });
        backInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.interest_frame, new Banking__Calculator_Fragment());
                // Optional: Add transaction to back stack
                fragmentTransaction.commit();
                requireActivity().finish();
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


    private void calculateInterestAndMaturity() {
        // Retrieve input values
        String investmentAmountStr = investmentAmountEditText.getText().toString().trim();
        String rateOfInterestStr = rateOfInterestEditText.getText().toString().trim();
        String tenureStr = tenureEditText.getText().toString().trim();

        // Validate input
        if (investmentAmountStr.isEmpty() || rateOfInterestStr.isEmpty() || tenureStr.isEmpty()) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert input strings to appropriate data types
        double investmentAmount = Double.parseDouble(investmentAmountStr);
        double rateOfInterest = Double.parseDouble(rateOfInterestStr) / 100; // Convert percentage to decimal
        int tenure = Integer.parseInt(tenureStr);

        // Perform interest and maturity calculation
        double totalInterest = investmentAmount * rateOfInterest * tenure;
        double maturityValue = investmentAmount + totalInterest;

        // Display the calculated values
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        answerInvestmentAmountTextView.setText(decimalFormat.format(investmentAmount));
        answerTotalInterestValueTextView.setText(decimalFormat.format(totalInterest));
        answerMaturityValueTextView.setText(decimalFormat.format(maturityValue));
    }


    private void resetFields() {
        // Clear all input fields
        investmentAmountEditText.setText("");
        rateOfInterestEditText.setText("");
        tenureEditText.setText("");
        typeOfInterestEditText.setText("");

        // Clear all result TextViews
        answerInvestmentAmountTextView.setText("");
        answerTotalInterestValueTextView.setText("");
        answerMaturityValueTextView.setText("");
    }
    private void setupShareButton() {
        // Retrieve the text from the result views
        String investmentAmount = answerInvestmentAmountTextView.getText().toString();
        String totalInterestValue = answerTotalInterestValueTextView.getText().toString();

        String maturityValue = answerMaturityValueTextView.getText().toString();

        // Create the share message
        String shareMessage = "Fixed Deposit Details:\n\n" +
                "Investment Amount: " + investmentAmount + "\n" +
                "Total Interest Value: " + totalInterestValue + "\n" +
                "Maturity Value: " + maturityValue;

        // Create an intent to share the message
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

        // Start the chooser activity to select an app to share the message
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

}
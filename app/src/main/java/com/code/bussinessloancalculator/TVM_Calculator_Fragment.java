package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
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

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TVM_Calculator_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Button sharebtn;
    private EditText presentValueEditText;
    private EditText paymentEditText;
    private EditText futureValueEditText;
    private EditText annualRateEditText;
    private EditText periodsEditText;
     Button authorizationButton,savebtn;

    private TextView answerPeriodTextView;
    private TextView answerPVTextView;
    private TextView answerPMTTextView;
    private TextView answerFVTextView;
    LinearLayout resultvm;

    ImageButton backtvm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_t_v_m__calculator_, container, false);


        resultvm = view.findViewById(R.id.resultvm);

        presentValueEditText = view.findViewById(R.id.peresntvalueedittext);
        paymentEditText = view.findViewById(R.id.paymentEditText);
        futureValueEditText = view.findViewById(R.id.futurevalueEdittext);
        annualRateEditText = view.findViewById(R.id.AnnualrateEdittext);
        periodsEditText = view.findViewById(R.id.periodstvmeditext);
        authorizationButton = view.findViewById(R.id.authorization);

        backtvm=view.findViewById(R.id.backTvm);



        backtvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        // Optional: Add transaction to back stack
                        .replace(R.id.tvm_frame, new Loan_Calculator_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });

        answerPeriodTextView = view.findViewById(R.id.answerperiod);
        answerPVTextView = view.findViewById(R.id.answerpv);
        answerPMTTextView = view.findViewById(R.id.answerpmt);
        answerFVTextView = view.findViewById(R.id.answerfv);

        resultvm.setVisibility(View.INVISIBLE);

        authorizationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultvm.setVisibility(View.VISIBLE);
                // Calculate the result based on input values
                double presentValue = Double.parseDouble(presentValueEditText.getText().toString());
                double payment = Double.parseDouble(paymentEditText.getText().toString());
                double futureValue = Double.parseDouble(futureValueEditText.getText().toString());
                double annualRate = Double.parseDouble(annualRateEditText.getText().toString());
                int periods = Integer.parseInt(periodsEditText.getText().toString());

                // Perform your calculation here
                // For example:
                double resultPeriod = calculatePeriod(presentValue, payment, futureValue, annualRate, periods);
                double resultPV = calculatePV(presentValue, payment, futureValue, annualRate, periods);
                double resultPMT = calculatePMT(presentValue, payment, futureValue, annualRate, periods);
                double resultFV = calculateFV(presentValue, payment, futureValue, annualRate, periods);

                // Set the calculated results to respective TextViews or EditTexts
                answerPeriodTextView.setText(String.valueOf(resultPeriod));
                answerPVTextView.setText(String.valueOf(resultPV));
                answerPMTTextView.setText(String.valueOf(resultPMT));
                answerFVTextView.setText(String.valueOf(resultFV));
            }
        });

        sharebtn = view.findViewById(R.id.sharebtn);

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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



        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button resetButton = view.findViewById(R.id.resetbuton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset all EditText fields
                presentValueEditText.setText("");
                paymentEditText.setText("");
                futureValueEditText.setText("");
                annualRateEditText.setText("");
                periodsEditText.setText("");
                resultvm.setVisibility(View.INVISIBLE);

            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private double calculatePeriod(double presentValue, double payment, double futureValue, double annualRate, int periods) {
        double i = annualRate / 100.0;
        double n = periods;
        double result = Math.log((futureValue + payment * n) / (presentValue + payment * n)) / Math.log(1 + i);
        return Double.isFinite(result) ? result : 0.0; // Handle infinite or NaN results
    }

    private double calculatePV(double presentValue, double payment, double futureValue, double annualRate, int periods) {
        double i = annualRate / 100.0;
        double n = periods;
        double denominator = Math.pow(1 + i, n);
        double result = futureValue / denominator + payment * ((1 - 1 / denominator) / i);
        return Double.isFinite(result) ? result : 0.0; // Handle infinite or NaN results
    }

    private double calculatePMT(double presentValue, double payment, double futureValue, double annualRate, int periods) {
        double i = annualRate / 100.0;
        double n = periods;
        double denominator = 1 - Math.pow(1 + i, -n);
        double result = (futureValue - presentValue * Math.pow(1 + i, n)) / (denominator / i);
        return Double.isFinite(result) ? result : 0.0; // Handle infinite or NaN results
    }

    private double calculateFV(double presentValue, double payment, double futureValue, double annualRate, int periods) {
        double i = annualRate / 100.0;
        double n = periods;
        double denominator = 1 - Math.pow(1 + i, -n);
        double result = presentValue * Math.pow(1 + i, n) + payment * (denominator / i);
        return Double.isFinite(result) ? result : 0.0; // Handle infinite or NaN results
    }




}
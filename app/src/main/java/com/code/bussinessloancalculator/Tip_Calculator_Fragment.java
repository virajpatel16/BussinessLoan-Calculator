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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Tip_Calculator_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {
    ImageButton backtip;



    private final int digitValue = 0;
    private EditText billEditText, tipPercentageEditText, splitEditText, annualYieldEditText;
    private ImageButton calculateButton, resetButton;
    LinearLayout cardviewtip;
    private EditText totalTipAmountEditText, totalCheckEditText, eachTipAmountEditText, eachPayEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tip__calculator_, container, false);

        cardviewtip=view.findViewById(R.id.cardviewtip);


        backtip=view.findViewById(R.id.backtip);
        billEditText = view.findViewById(R.id.billedittext);
        tipPercentageEditText = view.findViewById(R.id.tipEditText);
        splitEditText = view.findViewById(R.id.spilitEditText);
        annualYieldEditText = view.findViewById(R.id.taxEdittext);
        calculateButton = view.findViewById(R.id.calculateButtontip);
        resetButton = view.findViewById(R.id.resetButtontip);

        totalTipAmountEditText = view.findViewById(R.id.editexttotalTipAmount);
        totalCheckEditText = view.findViewById(R.id.edittexttotalcheck);
        eachTipAmountEditText = view.findViewById(R.id.Editexteachtipamount);
        eachPayEditText = view.findViewById(R.id.Editexteachpay);

        // Set OnClickListener for calculate button

        cardviewtip.setVisibility(View.INVISIBLE);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTip();

                cardviewtip.setVisibility(View.VISIBLE);
            }
        });

        backtip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                // Optional: Add transaction to back stack
                        .replace(R.id.tip_frame, new Banking__Calculator_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });

        // Set OnClickListener for reset button
        resetButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               // Reset all input fields
                                               billEditText.setText("");
                                               tipPercentageEditText.setText("");
                                               splitEditText.setText("");
                                               annualYieldEditText.setText("");

                                               // Clear result cards
                                               totalTipAmountEditText.setText("");
                                               totalCheckEditText.setText("");
                                               eachTipAmountEditText.setText("");
                                               eachPayEditText.setText("");

                                               // Hide result cards

                                               cardviewtip.setVisibility(View.INVISIBLE);
                                           }
                                       });


    return  view;
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

    private void calculateTip() {
        // Get input values
        String billStr = billEditText.getText().toString().trim();
        String tipPercentageStr = tipPercentageEditText.getText().toString().trim();
        String splitStr = splitEditText.getText().toString().trim();
        String annualYieldStr = annualYieldEditText.getText().toString().trim();

        // Validate input fields
        if (billStr.isEmpty() || tipPercentageStr.isEmpty() || splitStr.isEmpty() || annualYieldStr.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse input values
        double bill = Double.parseDouble(billStr);
        double tipPercentage = Double.parseDouble(tipPercentageStr);
        int split = Integer.parseInt(splitStr);
        double annualYield = Double.parseDouble(annualYieldStr);

        // Perform calculations
        double totalTipAmount = bill * (tipPercentage / 100);
        double totalCheck = bill + totalTipAmount;
        double eachTipAmount = totalTipAmount / split;
        double eachPay = totalCheck / split;

        // Display results
        totalTipAmountEditText.setText(String.format("%.2f", totalTipAmount));
        totalCheckEditText.setText(String.format("%.2f", totalCheck));
        eachTipAmountEditText.setText(String.format("%.2f", eachTipAmount));
        eachPayEditText.setText(String.format("%.2f", eachPay));

        // Show result cards
        cardviewtip.setVisibility(View.VISIBLE);

    }
}
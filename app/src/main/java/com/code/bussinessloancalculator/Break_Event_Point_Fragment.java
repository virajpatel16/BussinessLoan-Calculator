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

public class Break_Event_Point_Fragment extends Fragment {


ImageButton backBreakEvent;
    private EditText fixedCostEditText, variableCostEditText, sellingPriceEditText;
    private TextView resultTextView;
    LinearLayout resultbreakcard;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_break__event__point_, container, false);

        backBreakEvent=view.findViewById(R.id.backBreakEvent);
        fixedCostEditText = view.findViewById(R.id.FixedcostEditText);
        variableCostEditText = view.findViewById(R.id.VeriableCostEdittext);
        sellingPriceEditText = view.findViewById(R.id.SellingpriceEdittext);
        resultTextView = view.findViewById(R.id.answerbreak);
        resultbreakcard=view.findViewById(R.id.resultbreakcard);

        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonbreak);
        resultbreakcard.setVisibility(View.INVISIBLE);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBreakEvenPoint();
                resultbreakcard.setVisibility(View.VISIBLE);
            }
        });
        ImageButton resetButton = view.findViewById(R.id.resetbuttonbreak);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resultbreakcard.setVisibility(View.INVISIBLE);
            }
        });
        backBreakEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });


   return view;
    }
    private void calculateBreakEvenPoint() {
        String fixedCostStr = fixedCostEditText.getText().toString().trim();
        String variableCostStr = variableCostEditText.getText().toString().trim();
        String sellingPriceStr = sellingPriceEditText.getText().toString().trim();

        if (TextUtils.isEmpty(fixedCostStr)) {
            fixedCostEditText.setError("Enter Fixed Cost");
            fixedCostEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(variableCostStr)) {
            variableCostEditText.setError("Enter Variable Cost");
            variableCostEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(sellingPriceStr)) {
            sellingPriceEditText.setError("Enter Selling Price");
            sellingPriceEditText.requestFocus();
            return;
        }

        double fixedCost = Double.parseDouble(fixedCostStr);
        double variableCost = Double.parseDouble(variableCostStr);
        double sellingPrice = Double.parseDouble(sellingPriceStr);

        double epsilon = 0.0001; // small margin of error
        if (Math.abs(sellingPrice - variableCost) < epsilon) {
            resultTextView.setText("Infinity");
        } else {
            // Calculate break-even point
            double breakEvenPoint = fixedCost / (sellingPrice - variableCost);
            resultTextView.setText(String.format("%.2f", breakEvenPoint));
        }
    }

    private void resetFields() {
        fixedCostEditText.setText("");
        variableCostEditText.setText("");
        sellingPriceEditText.setText("");
        resultTextView.setText("");
    }
}
package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Gross_Profit_Fragment extends Fragment {


    ImageButton backGrossprofit;
    EditText answergross, answergrossmarkup;
    private EditText sellingPriceEditText, costPriceEditText;
    LinearLayout resultpricecard, resultmarkupcard;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gross__profit_, container, false);

        backGrossprofit = view.findViewById(R.id.backGrossprofit);
        sellingPriceEditText = view.findViewById(R.id.SellingpriceEditText);
        costPriceEditText = view.findViewById(R.id.CostpriceEdittext);
        ImageButton calculateButton = view.findViewById(R.id.calculateButtonGross_profit);
        answergross = view.findViewById(R.id.answergross);
        answergrossmarkup = view.findViewById(R.id.answergrossmarkup);
        resultpricecard = view.findViewById(R.id.resultpricecard);
        resultmarkupcard = view.findViewById(R.id.resultmarkupgrosscard);

        resultpricecard.setVisibility(View.INVISIBLE);
        resultmarkupcard.setVisibility(View.INVISIBLE);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGrossProfit();
                resultpricecard.setVisibility(View.VISIBLE);
                resultmarkupcard.setVisibility(View.VISIBLE);
            }

        });
        ImageButton resetButton = view.findViewById(R.id.redsetButtonGross_profit);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resultpricecard.setVisibility(View.INVISIBLE);
                resultmarkupcard.setVisibility(View.INVISIBLE);
            }
        });

        backGrossprofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });
        return view;
    }

    private void calculateGrossProfit() {
        String sellingPriceStr = sellingPriceEditText.getText().toString().trim();
        String costPriceStr = costPriceEditText.getText().toString().trim();


        if (TextUtils.isEmpty(sellingPriceStr)) {
            sellingPriceEditText.setError("Enter Selling Price");
            sellingPriceEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(costPriceStr)) {
            costPriceEditText.setError("Enter Cost Price");
            costPriceEditText.requestFocus();
            return;
        }

        try {
            double sellingPrice = Double.parseDouble(sellingPriceStr);
            double costPrice = Double.parseDouble(costPriceStr);

            // Calculate gross profit
            double grossProfit = sellingPrice - costPrice;
            answergross.setText(String.valueOf(grossProfit));

            // Calculate gross profit markup
            if (costPrice != 0) {
                double markup = (grossProfit / costPrice) * 100;
                answergrossmarkup.setText(String.format("%.2f%%", markup));
            } else {
                // Handle division by zero (costPrice is zero)
                answergrossmarkup.setText("N/A");
            }

            // Display a toast message with the calculated gross profit
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric values)
            Toast.makeText(requireContext(), "Invalid input. Please enter numeric values.", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetFields() {
        sellingPriceEditText.setText("");
        costPriceEditText.setText("");
        answergrossmarkup.setText("");
        answergross.setText("");
    }

}
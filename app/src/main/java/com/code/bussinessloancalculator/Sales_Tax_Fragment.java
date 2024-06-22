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
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Sales_Tax_Fragment extends Fragment {

    ImageButton backsalestax;


    private EditText netAmountEditText, taxEditText;
    private TextView resultGrossPriceTextView, resultTaxAmountTextView;
    LinearLayout resultgrossprice, resulttaxamount;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales__tax_, container, false);
        backsalestax = view.findViewById(R.id.backsales);
        resultgrossprice = view.findViewById(R.id.resultgrossprice);
        resulttaxamount = view.findViewById(R.id.resulttaxamount);

        resultgrossprice.setVisibility(View.INVISIBLE);
        resulttaxamount.setVisibility(View.INVISIBLE);


        netAmountEditText = view.findViewById(R.id.NetAmountEditText);
        taxEditText = view.findViewById(R.id.TaxEdittext);
        resultGrossPriceTextView = view.findViewById(R.id.answergrossprice);
        resultTaxAmountTextView = view.findViewById(R.id.answertaxamount);

        backsalestax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });

        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonsalestax);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSalesTax();
                resultgrossprice.setVisibility(View.VISIBLE);
                resulttaxamount.setVisibility(View.VISIBLE);
            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbuttonsalestax);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resultgrossprice.setVisibility(View.INVISIBLE);
                resulttaxamount.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }

    private void calculateSalesTax() {
        String netAmountStr = netAmountEditText.getText().toString().trim();
        String taxRateStr = taxEditText.getText().toString().trim();

        if (TextUtils.isEmpty(netAmountStr)) {
            netAmountEditText.setError("Enter Net Amount");
            netAmountEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(taxRateStr)) {
            taxEditText.setError("Enter Tax Rate");
            taxEditText.requestFocus();
            return;
        }

        double netAmount = Double.parseDouble(netAmountStr);
        double taxRate = Double.parseDouble(taxRateStr);

        // Calculate gross price and tax amount
        double grossPrice = netAmount * (1 + (taxRate / 100));
        double taxAmount = grossPrice - netAmount;

        // Display the results
        resultGrossPriceTextView.setText(String.format("%.2f", grossPrice));
        resultTaxAmountTextView.setText(String.format("%.2f", taxAmount));
    }

    private void resetFields() {
        netAmountEditText.setText("");
        taxEditText.setText("");
        resultGrossPriceTextView.setText("");
        resultTaxAmountTextView.setText("");
    }
}
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


public class Vat_Fragment extends Fragment {


    ImageButton backvat;

    private EditText netAmountEditText, vatEditText;
    private TextView resultTaxAmountTextView, resultGrossPriceTextView;
    LinearLayout resulttaxamountvat,resultgrosspricevat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_vat_, container, false);

        backvat=view.findViewById(R.id.backvat);
        resultgrosspricevat=view.findViewById(R.id.resultgrosspricevat);
        resulttaxamountvat=view.findViewById(R.id.resulttaxamountvat);

        resulttaxamountvat.setVisibility(View.INVISIBLE);
        resultgrosspricevat.setVisibility(View.INVISIBLE);

        netAmountEditText = view.findViewById(R.id.NetamountvatEditText);
        vatEditText = view.findViewById(R.id.VatEdittext);
        resultTaxAmountTextView = view.findViewById(R.id.answertaxamountvat);
        resultGrossPriceTextView = view.findViewById(R.id.answergrosspricevat);

        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonvat);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateVAT();
                resulttaxamountvat.setVisibility(View.VISIBLE);
                resultgrosspricevat.setVisibility(View.VISIBLE);
            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbuttonvat);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();

                resulttaxamountvat.setVisibility(View.INVISIBLE);
                resultgrosspricevat.setVisibility(View.INVISIBLE);
            }
        });
        backvat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });
        return view;
    }
    @SuppressLint("DefaultLocale")
    private void calculateVAT() {
        String netAmountStr = netAmountEditText.getText().toString().trim();
        String vatRateStr = vatEditText.getText().toString().trim();

        if (TextUtils.isEmpty(netAmountStr)) {
            netAmountEditText.setError("Enter Net Amount");
            netAmountEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(vatRateStr)) {
            vatEditText.setError("Enter VAT Rate");
            vatEditText.requestFocus();
            return;
        }
        double netAmount = Double.parseDouble(netAmountStr);
        double vatRate = Double.parseDouble(vatRateStr);

        // Calculate tax amount
        double taxAmount = netAmount * (vatRate / 100);

        // Calculate gross price
        double grossPrice = netAmount + taxAmount;

        // Display the results
        resultTaxAmountTextView.setText(String.format("%.2f", taxAmount));
        resultGrossPriceTextView.setText(String.format("%.2f", grossPrice));
    }

    private void resetFields() {
        netAmountEditText.setText("");
        vatEditText.setText("");
        resultTaxAmountTextView.setText("");
        resultGrossPriceTextView.setText("");
    }
}
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


public class Price_Calculation_Fragment extends Fragment {


    ImageButton backprice;
    private EditText costEditText, grossMarginEditText;
    private TextView resultPriceTextView, resultMarkupTextView;
    LinearLayout priceresultpricecard,priceresultmarkupcard;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_price__calculation_, container, false);

        backprice=view.findViewById(R.id.backprice);
        costEditText = view.findViewById(R.id.CostEditText);
        grossMarginEditText = view.findViewById(R.id.GrossmarginEdittext);
        resultPriceTextView = view.findViewById(R.id.answerprice);
        resultMarkupTextView = view.findViewById(R.id.answerpricemarkup);
        ImageButton calculateButton = view.findViewById(R.id.calculatorbuttonprice);
        priceresultmarkupcard=view.findViewById(R.id.priceresultmarkupcard);
        priceresultpricecard=view.findViewById(R.id.priceresultpricecard);
        priceresultpricecard.setVisibility(View.INVISIBLE);
        priceresultmarkupcard.setVisibility(View.INVISIBLE);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePrice();
                priceresultpricecard.setVisibility(View.VISIBLE);
                priceresultmarkupcard.setVisibility(View.VISIBLE);
            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbuttonprice);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                priceresultpricecard.setVisibility(View.INVISIBLE);
                priceresultmarkupcard.setVisibility(View.INVISIBLE);
            }
        });

        backprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                requireActivity().finish();

            }
        });

        return  view;
    }
    private void calculatePrice() {
        String costStr = costEditText.getText().toString().trim();
        String grossMarginStr = grossMarginEditText.getText().toString().trim();

        if (TextUtils.isEmpty(costStr)) {
            costEditText.setError("Enter Monthly Investment");
            costEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(grossMarginStr)) {
            grossMarginEditText.setError("Enter Rate of Return");
            grossMarginEditText.requestFocus();
            return;
        }

        double cost = Double.parseDouble(costStr);
        double grossMargin = Double.parseDouble(grossMarginStr);

        // Calculate price
        double price = cost / (1 - grossMargin / 100);
        resultPriceTextView.setText(String.format("%.2f", price));

        // Calculate markup
        double markup = (price - cost) / cost * 100;
        resultMarkupTextView.setText(String.format("%.2f%%", markup));
    }

    private void resetFields() {
        costEditText.setText("");
        grossMarginEditText.setText("");
        resultPriceTextView.setText("");
        resultMarkupTextView.setText("");
    }

}
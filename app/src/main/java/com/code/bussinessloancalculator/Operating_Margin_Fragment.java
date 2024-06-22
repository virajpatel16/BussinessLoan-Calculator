package com.code.bussinessloancalculator;

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

public class Operating_Margin_Fragment extends Fragment {


    ImageButton backoperating;
    private EditText operatingIncomeEditText, revenueEditText;
    private TextView resultTextView;
    LinearLayout Operatingincomecard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_operating__margin_, container, false);
        operatingIncomeEditText = view.findViewById(R.id.OperatingincomeEditText);
        revenueEditText = view.findViewById(R.id.RevenueEdittext);
        resultTextView = view.findViewById(R.id.answeroperating);

        backoperating = view.findViewById(R.id.backoperating);
        Operatingincomecard=view.findViewById(R.id.resultoperatingcard);
        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonoperating);


Operatingincomecard.setVisibility(View.INVISIBLE);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateOperatingMargin();

                Operatingincomecard.setVisibility(View.VISIBLE);
            }
        });
        ImageButton resetButton = view.findViewById(R.id.resetbuttonoperating);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();

                Operatingincomecard.setVisibility(View.INVISIBLE);
            }
        });


        backoperating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });
    return  view;
    }
    private void calculateOperatingMargin() {
        String operatingIncomeStr = operatingIncomeEditText.getText().toString().trim();
        String revenueStr = revenueEditText.getText().toString().trim();

        if (TextUtils.isEmpty(operatingIncomeStr)) {
            operatingIncomeEditText.setError("Enter Operating Income");
            operatingIncomeEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(revenueStr)) {
            revenueEditText.setError("Enter Revenue");
            revenueEditText.requestFocus();
            return;
        }

        double operatingIncome = Double.parseDouble(operatingIncomeStr);
        double revenue = Double.parseDouble(revenueStr);

        // Calculate operating margin
        double operatingMargin = (operatingIncome / revenue) * 100;
        resultTextView.setText(String.format("%.2f%%", operatingMargin));
    }
    private void resetFields() {
        operatingIncomeEditText.setText("");
        revenueEditText.setText("");
        resultTextView.setText("");
    }
}
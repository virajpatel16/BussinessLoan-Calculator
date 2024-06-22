package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
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

public class Margin_Fragment extends Fragment {

ImageButton backmargin;
    private EditText costMarginEditText, revenueMarginEditText;
    private TextView resultMarginTextView, resultProfitTextView;
    LinearLayout resultmargincard,resultmarginprofitcard;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_margin_, container, false);

        backmargin=view.findViewById(R.id.backmargin);
        costMarginEditText = view.findViewById(R.id.CostmarginEditText);
        revenueMarginEditText = view.findViewById(R.id.RevenuemarginEdittext);
        resultMarginTextView = view.findViewById(R.id.answermargin);
        resultProfitTextView = view.findViewById(R.id.answerprofit);
        resultmargincard=view.findViewById(R.id.resultmargincard);
        resultmarginprofitcard=view.findViewById(R.id.resultmarginprofitcard);

        resultmargincard.setVisibility(View.INVISIBLE);
        resultmarginprofitcard.setVisibility(View.INVISIBLE);
        ImageButton calculateMarginButton = view.findViewById(R.id.calculatebuttonmargin);
        calculateMarginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMarginAndProfit();
                resultmarginprofitcard.setVisibility(View.VISIBLE);
                resultmargincard.setVisibility(View.VISIBLE);
            }
        });

        backmargin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requireActivity().finish();

            }
        });

        ImageButton resetMarginButton = view.findViewById(R.id.resetbuttonmargin);
        resetMarginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMarginFields();

                resultmargincard.setVisibility(View.INVISIBLE);
                resultmarginprofitcard.setVisibility(View.INVISIBLE);
            }
        });
   return view;
    }
    private void calculateMarginAndProfit() {
        String costMarginStr = costMarginEditText.getText().toString().trim();
        String revenueMarginStr = revenueMarginEditText.getText().toString().trim();

        if (TextUtils.isEmpty(costMarginStr)) {
            costMarginEditText.setError("Enter Cost");
            costMarginEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(revenueMarginStr)) {
            revenueMarginEditText.setError("Enter Revenue");
            revenueMarginEditText.requestFocus();
            return;
        }

        double costMargin = Double.parseDouble(costMarginStr);
        double revenueMargin = Double.parseDouble(revenueMarginStr);

        // Calculate margin percentage
        double margin = ((revenueMargin - costMargin) / revenueMargin) * 100;

        // Calculate profit
        double profit = revenueMargin - costMargin;

        // Display the result
        resultMarginTextView.setText(String.format("%.2f", margin));
        resultProfitTextView.setText(String.format("%.2f", profit));
    }

    private void resetMarginFields() {
        costMarginEditText.setText("");
        revenueMarginEditText.setText("");
        resultMarginTextView.setText("");
        resultProfitTextView.setText("");
    }
}
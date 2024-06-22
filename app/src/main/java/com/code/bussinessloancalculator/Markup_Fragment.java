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

public class Markup_Fragment extends Fragment {

ImageButton backmarkup;
    private EditText costMarkupEditText, revenueMarkupEditText;
    private TextView resultMarkupTextView;
    LinearLayout resultmarkupcard;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_markup_, container, false);

        backmarkup=view.findViewById(R.id.backmarkup);
        costMarkupEditText =view. findViewById(R.id.CostmarkupEditText);
        revenueMarkupEditText = view.findViewById(R.id.RevenuemarkupEdittext);
        resultMarkupTextView = view.findViewById(R.id.answermarkup);
        resultmarkupcard=view.findViewById(R.id.resultmarkupcard);
        resultmarkupcard.setVisibility(View.INVISIBLE);

        ImageButton calculateMarkupButton = view.findViewById(R.id.calculatemarkup);
        calculateMarkupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMarkup();
                resultmarkupcard.setVisibility(View.VISIBLE);
            }
        });
        ImageButton resetMarkupButton = view.findViewById(R.id.resetbuttonmarkup);
        resetMarkupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMarkupFields();
                resultmarkupcard.setVisibility(View.INVISIBLE);
            }
        });
        backmarkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });

    return view;
    }
    private void calculateMarkup() {
        String costMarkupStr = costMarkupEditText.getText().toString().trim();
        String revenueMarkupStr = revenueMarkupEditText.getText().toString().trim();

        if (TextUtils.isEmpty(costMarkupStr)) {
            costMarkupEditText.setError("Enter Cost");
            costMarkupEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(revenueMarkupStr)) {
            revenueMarkupEditText.setError("Enter Revenue");
            revenueMarkupEditText.requestFocus();
            return;
        }

        double costMarkup = Double.parseDouble(costMarkupStr);
        double revenueMarkup = Double.parseDouble(revenueMarkupStr);

// Calculate markup percentage
        double markup = Math.abs((revenueMarkup - costMarkup) / costMarkup) * 100;

// Display the result
        resultMarkupTextView.setText(String.format("%.2f", markup));

    }

    private void resetMarkupFields() {
        costMarkupEditText.setText("");
        revenueMarkupEditText.setText("");
        resultMarkupTextView.setText("");
    }
}
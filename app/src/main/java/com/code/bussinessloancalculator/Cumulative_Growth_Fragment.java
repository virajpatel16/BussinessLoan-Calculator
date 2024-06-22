package com.code.bussinessloancalculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Cumulative_Growth_Fragment extends Fragment {

    ImageButton back;

    private EditText growthRateEditText, periodEditText, initialValueEditText;
    private TextView resultFinalValueTextView;

    LinearLayout resultfinalvaluecumulativegrowth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cumulative__growth_, container, false);
        growthRateEditText = view.findViewById(R.id.GrowthRateEditText);
        periodEditText = view.findViewById(R.id.periodEdittext);
        initialValueEditText = view.findViewById(R.id.InitialvalueEdittext);
        resultFinalValueTextView = view.findViewById(R.id.answerfinalvaluecumulative);
        resultfinalvaluecumulativegrowth = view.findViewById(R.id.resultfinalvaluecumulativegrowth);

        back = view.findViewById(R.id.backcummulative);
        resultfinalvaluecumulativegrowth.setVisibility(View.INVISIBLE);
        ImageButton calculateButton = view.findViewById(R.id.calculatebuttoncumulativegrowth);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCumulativeGrowth();
                resultfinalvaluecumulativegrowth.setVisibility(View.VISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbuttoncumulativegrowth);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resultfinalvaluecumulativegrowth.setVisibility(View.INVISIBLE);
            }

        });
        return view;
    }

    private void calculateCumulativeGrowth() {
        String growthRateStr = growthRateEditText.getText().toString().trim();
        String periodStr = periodEditText.getText().toString().trim();
        String initialValueStr = initialValueEditText.getText().toString().trim();

        if (TextUtils.isEmpty(growthRateStr)) {
            growthRateEditText.setError("Enter Growth Rate");
            growthRateEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(periodStr)) {
            periodEditText.setError("Enter Period");
            periodEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(initialValueStr)) {
            initialValueEditText.setError("Enter Initial Value");
            initialValueEditText.requestFocus();
            return;
        }

        double growthRate = Double.parseDouble(growthRateStr);
        int period = Integer.parseInt(periodStr);
        double initialValue = Double.parseDouble(initialValueStr);

        // Calculate final value
        double finalValue = initialValue * Math.pow((1 + (growthRate / 100)), period);

        // Display the result
        resultFinalValueTextView.setText(String.format("%.2f", finalValue));
    }

    private void resetFields() {
        growthRateEditText.setText("");
        periodEditText.setText("");
        initialValueEditText.setText("");
        resultFinalValueTextView.setText("");
    }
}
package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;


public class YTM_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {


    ImageButton backytm;
    EditText bondPriceEditText, faceValueEditText, couponRateEditText, yearsToMaturityEditText;
    Spinner compoundingSpinnerytm;
    ImageButton calculateButton, resetButton;
    TextView resultTextView;
    LinearLayout cardytm;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_y_t_m_, container, false);


        backytm = view.findViewById(R.id.backytm);

        bondPriceEditText = view.findViewById(R.id.Bondpriceytcedittext);
        faceValueEditText = view.findViewById(R.id.facevalueytmEditText);
        couponRateEditText = view.findViewById(R.id.couponerateytmEdittext);
        yearsToMaturityEditText = view.findViewById(R.id.yearstomaturityytmEdittext);
        cardytm = view.findViewById(R.id.cardytm);
        calculateButton = view.findViewById(R.id.calculatebuttonytm);
        resetButton = view.findViewById(R.id.resetbuttonytm);
        resultTextView = view.findViewById(R.id.resultTextViewytm);
        cardytm.setVisibility(View.INVISIBLE);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateYtm();
                cardytm.setVisibility(View.VISIBLE);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardytm.setVisibility(View.INVISIBLE);
            }
        });

        backytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Optional: Add transaction to back stack
                fragmentTransaction
                        .replace(R.id.ytm_frame, new Bound_Calculator_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });



        return view;
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

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void calculateYtm() {
        try {
            // Retrieve input values
            double bondPrice = Double.parseDouble(bondPriceEditText.getText().toString());
            double faceValue = Double.parseDouble(faceValueEditText.getText().toString());
            double couponRate = Double.parseDouble(couponRateEditText.getText().toString()) / 100; // Convert percentage to decimal
            int yearsToMaturity = Integer.parseInt(yearsToMaturityEditText.getText().toString());

            // Implement YTM calculation logic
            double ytm = 0;
            double annualCoupon = couponRate * faceValue;
            double pv = bondPrice; // Present value is the bond price
            double fv = faceValue;
            int numberOfCompoundingPeriods = yearsToMaturity;

            double numerator = annualCoupon + ((fv - pv) / numberOfCompoundingPeriods);
            double denominator = (fv + pv) / 2;

            // Calculate YTM
            ytm = (numerator / denominator) * 100;

            // Display the calculated YTM
            resultTextView.setText(String.format("%.4f", ytm) + "%");
        } catch (NumberFormatException e) {
            // Handle parsing errors
            resultTextView.setText("Invalid input");
        }
    }


    private void resetFields() {

        bondPriceEditText.setText("");
        faceValueEditText.setText("");
        couponRateEditText.setText("");
        yearsToMaturityEditText.setText("");

        resultTextView.setText("");
    }
}
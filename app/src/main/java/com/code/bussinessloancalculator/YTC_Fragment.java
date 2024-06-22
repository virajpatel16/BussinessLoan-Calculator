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


public class YTC_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ImageButton backytc;
    EditText bondPriceEditText, faceValueEditText, couponRateEditText, yearsToMaturityEditText, callPriceEditText, yearsUntilCallDateEditText;

    LinearLayout cardytc;
    ImageButton calculateButton, resetButton;

    TextView compoundingTextView, resulttextview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_y_t_c_, container, false);
        backytc = view.findViewById(R.id.backytc);
        cardytc = view.findViewById(R.id.cardytc);
        resulttextview = view.findViewById(R.id.resultTextView);
        bondPriceEditText = view.findViewById(R.id.Bondpriceytcedittext);
        faceValueEditText = view.findViewById(R.id.facevalueytcEditText);
        couponRateEditText = view.findViewById(R.id.couponerateytcEdittext);
        yearsToMaturityEditText = view.findViewById(R.id.yearstomaturityytcEdittext);
        callPriceEditText = view.findViewById(R.id.callpriceeditext);
        yearsUntilCallDateEditText = view.findViewById(R.id.yearsuntilcallDateeditext);




        cardytc.setVisibility(View.INVISIBLE);
        calculateButton = view.findViewById(R.id.calculatebuttonytc);
        resetButton = view.findViewById(R.id.resetbuttonytc);

        // Set up the spinner

        // Set up onClickListeners for buttons
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateYtc();

                cardytc.setVisibility(View.VISIBLE);
            }
        });
        backytc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Optional: Add transaction to back stack
                fragmentTransaction
                        .replace(R.id.ytc_frame, new Bound_Calculator_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                cardytc.setVisibility(View.INVISIBLE);
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
    private void calculateYtc() {
        // Retrieve input values
        double bondPrice = Double.parseDouble(bondPriceEditText.getText().toString());
        double faceValue = Double.parseDouble(faceValueEditText.getText().toString());
        double couponRate = Double.parseDouble(couponRateEditText.getText().toString());
        int yearsToMaturity = Integer.parseInt(yearsToMaturityEditText.getText().toString());
        double callPrice = Double.parseDouble(callPriceEditText.getText().toString());

        int yearsUntilCallDate = Integer.parseInt(yearsUntilCallDateEditText.getText().toString());

        // Implement YTC calculation logic
        // Example calculation:
        double ytc = ((faceValue - callPrice) / ((bondPrice + callPrice) / 2)) / (yearsUntilCallDate + ((faceValue - callPrice) / ((bondPrice + callPrice) / 2)));

        // Display the calculated YTC

        resulttextview.setText(String.format("%.2f", ytc) + "%");
    }

    private void resetFields() {
        bondPriceEditText.setText("");
        faceValueEditText.setText("");
        couponRateEditText.setText("");
        yearsToMaturityEditText.setText("");
        callPriceEditText.setText("");
        yearsUntilCallDateEditText.setText("");
        // You can also reset the spinner selection if needed

    }
}
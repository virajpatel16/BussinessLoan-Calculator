package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;


public class Stp_Calculator_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {


    ImageButton backstp;
    Spinner spinnerstp;
    LinearLayout resulttotalamounttranferred, resulttotalprofitstp, resultbalanceintranferor, resultintranfree;
    private EditText stpAmountEditText, rateOfReturnstpEditText, tenureInstpEdittext, initialinvestmentstpEdittext, investmentperiodstp;
    private EditText answertotalamounttranferred, answertotalprofitstp, answerbalanceintranferor, answerintranfree;
    private Button calculateButton;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stp__calculator_, container, false);
        resulttotalamounttranferred = view.findViewById(R.id.resulttotalamounttranferred);
        resulttotalprofitstp = view.findViewById(R.id.resulttotalprofitstp);
        resultbalanceintranferor = view.findViewById(R.id.resultbalanceintranferor);
        resultintranfree = view.findViewById(R.id.resultintranfree);
        resulttotalamounttranferred.setVisibility(View.INVISIBLE);
        resulttotalprofitstp.setVisibility(View.INVISIBLE);
        resultbalanceintranferor.setVisibility(View.INVISIBLE);
        resultintranfree.setVisibility(View.INVISIBLE);
        backstp = view.findViewById(R.id.backstp);
        stpAmountEditText = view.findViewById(R.id.stpAmountEditText);
        stpAmountEditText = view.findViewById(R.id.stpAmountEditText);
        rateOfReturnstpEditText = view.findViewById(R.id.rateOfReturnstpEditText);
        tenureInstpEdittext = view.findViewById(R.id.tenureInstpEdittext);
        initialinvestmentstpEdittext = view.findViewById(R.id.initialinvestmentstpEdittext);
        investmentperiodstp = view.findViewById(R.id.investmentperiodstp);

         spinnerstp = view.findViewById(R.id.spinnerstp);
        answertotalamounttranferred = view.findViewById(R.id.answertotalamounttranferred);
        answertotalprofitstp = view.findViewById(R.id.answertotalprofitstp);
        answerbalanceintranferor = view.findViewById(R.id.answerbalanceintranferor);
        answerintranfree = view.findViewById(R.id.answerintranfree);



        ImageButton calculateButton = view.findViewById(R.id.caculatebuttonstp);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSTP();
                resulttotalamounttranferred.setVisibility(View.VISIBLE);
                resulttotalprofitstp.setVisibility(View.VISIBLE);
                resultbalanceintranferor.setVisibility(View.VISIBLE);
                resultintranfree.setVisibility(View.VISIBLE);


            }
        });

        backstp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        // Optional: Add transaction to back stack
                        .replace(R.id.Stp_frame, new Mutual_Funds_SIP_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbuttonstp);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resulttotalamounttranferred.setVisibility(View.INVISIBLE);
                resulttotalprofitstp.setVisibility(View.INVISIBLE);
                resultbalanceintranferor.setVisibility(View.INVISIBLE);
                resultintranfree.setVisibility(View.INVISIBLE);
            }
        });


        spinnerstp.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Years");
        categories.add("Month");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, categories);

// Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Attaching data adapter to spinner
        spinnerstp.setAdapter(dataAdapter);


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        String tenureInStr = tenureInstpEdittext.getText().toString().trim();

        // Check if the string is not empty before parsing
        if (!TextUtils.isEmpty(tenureInStr)) {
            int tenureIn = Integer.parseInt(tenureInStr); // Assuming tenure is in months

            // Convert tenure to months if spinner selected "Years"
            if (position == 0) { // Years selected
                tenureIn *= 12; // Convert years to months
                tenureInstpEdittext.setText(String.valueOf(tenureIn)); // Update the EditText with the converted value
            } else if (position == 1) { // Months selected
                // Convert tenure back to years if it was previously converted from years to months
                if (tenureIn % 12 == 0) {
                    tenureIn /= 12; // Convert months to years
                    tenureInstpEdittext.setText(String.valueOf(tenureIn)); // Update the EditText with the converted value
                }
            } else {
                // Perform other calculations here if needed
            }
        }
    }


        @Override
        public void onNothingSelected (AdapterView < ? > parent){

        }

        @SuppressLint("DefaultLocale")
        private void calculateSTP() {
            String stpAmountStr = stpAmountEditText.getText().toString().trim();
            String rateOfReturnStr = rateOfReturnstpEditText.getText().toString().trim();
            String tenureInStr = tenureInstpEdittext.getText().toString().trim();
            String initialInvestmentStr = initialinvestmentstpEdittext.getText().toString().trim();
            String investmentPeriodStr = investmentperiodstp.getText().toString().trim();

            if (TextUtils.isEmpty(stpAmountStr) || TextUtils.isEmpty(rateOfReturnStr) ||
                    TextUtils.isEmpty(tenureInStr) || TextUtils.isEmpty(initialInvestmentStr) || TextUtils.isEmpty(investmentPeriodStr)) {
                Toast.makeText(requireContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double stpAmount = Double.parseDouble(stpAmountStr);
            double rateOfReturn = Double.parseDouble(rateOfReturnStr) / 100.0; // Convert rate of return to decimal
            int tenureIn = Integer.parseInt(tenureInStr); // Assuming tenure is in months
            double initialInvestment = Double.parseDouble(initialInvestmentStr);
            int investmentPeriod = Integer.parseInt(investmentPeriodStr); // Assuming investment period is in months

            // Calculate total amount transferred using the formula V = (n × R × T) / P
            double totalAmountTransferred = (investmentPeriod * stpAmount * tenureIn) / initialInvestment;

            // Calculate total profit (assuming no profit for STP)
            double totalProfitSTP = totalAmountTransferred - initialInvestment;
            if (totalProfitSTP < 0) {
                totalProfitSTP = 0; // If total amount transferred is less than initial investment, profit is zero
            }


            // Calculate balance in transferor
            double balanceInTransferor = initialInvestment - totalAmountTransferred;

            // Calculate balance in transferee
            double balanceInTransferee = totalAmountTransferred;

            // Display the results
            answertotalamounttranferred.setText(String.format("%.2f", totalAmountTransferred));
            answertotalprofitstp.setText(String.format("%.2f", totalProfitSTP));
            answerbalanceintranferor.setText(String.format("%.2f", balanceInTransferor));
            answerintranfree.setText(String.format("%.2f", balanceInTransferee));
        }

        private void resetFields () {
            stpAmountEditText.setText("");
            rateOfReturnstpEditText.setText("");
            tenureInstpEdittext.setText("");
            initialinvestmentstpEdittext.setText("");
            investmentperiodstp.setText("");


            // Clear all result TextViews
            answertotalamounttranferred.setText("");
            answertotalprofitstp.setText("");
            answerbalanceintranferor.setText("");
            answerintranfree.setText("");
        }

    }